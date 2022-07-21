import router from '@/router'
import axios from 'axios'
import rest from '@/api/rest'

export default {
    state: {
        // token 인증 방식
        token: localStorage.getItem('token') || '',  
        authError: null, // 오류 발생 시
        loginUser: {},
        
        
    },
    getters: {
        isLoggedIn: state => !!state.token,
        authError: state => state.authError,
        authHeader: state => ({ Authorization: `Token ${state.token}`}),
        loginUser: state => state.loginUser,

    },
    mutations: {
        SET_TOKEN: (state, token) => state.token = token,
        SET_LOGIN_USER: (state, user) => state.loginUser = user,
        SET_AUTH_ERROR: (state, error) => state.authError = error
    },
    actions: {
        login({commit, dispatch},credential){
            axios({
                url: rest.auth_login.login(),
                method: 'post',
                data: credential
            })
            .then(res => {
                console.log("then")
                const token = res.data.key
                dispatch('saveToken', token)
                dispatch('fetchLoginUser')
                
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
            axios({
                url: rest.user.user_signup(),
                method: 'post',
                data: credentials
            })
            .then(res => {
                const token = res.data.key
                dispatch('saveToken', token)
                router.push({ name: 'Home '})
            })
            .catch(err => {
                console.error(err.response.data)
                commit('SET_AUTH_ERROR', err.response.data)
            })
        },
        // 현재 접속중인 이용자
        fetchLoginUser({ commit, getters, dispatch }) {
      
            if (getters.isLoggedIn) {
              axios({
                url: rest.user.user_myprofile(),   // 확인 어디서?
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
    }}
}