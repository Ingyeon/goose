import router from '@/router'
import axios from 'axios'
import rest from '@/api/rest'

export default {
    state: {
        // token 인증 방식
        token: localStorage.getItem('token') || '.',  
        authError: null, // 오류 발생 시
        loginUser: {},
        
    },
    getters: {
        isLoggedIn: state => !!state.token,    // 로그인 했는지 확인
        authError: state => state.authError,   // 인증 에러
        authHeader: state => ({ Authorization: state.token}),  // 인증 정보
        loginUser: state => state.loginUser,  // 현재 로그인한 유저 
        
    },
    mutations: {
        SET_TOKEN: (state, token) => state.token = token,
        SET_LOGIN_USER: (state, user) => state.loginUser = user,
        SET_AUTH_ERROR: (state, error) => state.authError = error,
        LOGOUT: () => { 
            localStorage.removeItem('user')
            location.reload();
        }
    },
    actions: {
        login({commit, dispatch},credential){
            axios({
                url: rest.accounts.login(),
                method: 'post',
                data: credential
            })
            .then(res => {
                const token = res.data.accessToken
                dispatch('saveToken', token)
                // dispatch('fetchLoginUser')
                
                router.push({name: 'Home'})
            })
            .catch(err => {
                console.log("catch")
                console.error(err.response.data)
                commit('SET_AUTH_ERROR', err.response.data)
            })
        },
        saveToken({  commit  }, token) {
            commit('SET_TOKEN', token)
            localStorage.setItem('token', token)
        },
        removeToken({  commit  }){
            commit('SET_TOKEN', '')
            localStorage.setItem('token','')
        },

        signup({ commit, dispatch}, credentials){
            console.log("엑시오스 하기 전")
            console.log(credentials)
            axios({
                url: rest.user.user_signup(),
                method: 'post',
                data: credentials
            })
            .then(res => {
                console.log("then")
                console.log('signup')
                // const token = res.data.accessToken
                // dispatch('saveToken', token)
                // router.push({ name: 'Home '})
            })
            .catch(err => {
                // console.log(url)
                console.log('catch')
                console.log(err)
                // console.error(err.response.data)
                commit('SET_AUTH_ERROR', err.response.data)
            })
        },
        // 현재 접속중인 이용자
        fetchLoginUser({ commit, getters, dispatch }) {
      
            if (getters.isLoggedIn) {
              axios({
                url: rest.user.user_myprofile(), 
                method: 'get',
                headers: getters.authHeader,
              })
                .then(res => commit('SET_LOGIN_USER', res.data))
                .catch(err => {
                  if (err.response.status === 401) {
                    dispatch('removeToken')
                    router.push({ name: 'login' })
                  }
                })
            }
        },

        // logout({  getters , dispatch}){
        //     axios({
        //         url: rest.user.,
        //         method: 'post',
        //         headers: getters.authHeader,
        //     })
        //     .then( () => {
        //         dispatch('removeToken')
        //         alert('로그아웃 완료')
        //         router.push({ name: 'Home'})
        //     })
        //     .catch(err => {
        //         console.error(err.response)
        //     })
        // }
        logout({commit, dispatch}) {
            dispatch('removeToken');
        }
    }
}
