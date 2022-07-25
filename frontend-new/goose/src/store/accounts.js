import router from '@/router'
import axios from 'axios'
import rest from '@/api/rest'

export default {
    state: {
        // token 인증 방식
        token: localStorage.getItem('token') || '',  
        authError: null, // 오류 발생 시
        loginUser: {},
        
<<<<<<< HEAD
    },
    getters: {
        isLoggedIn: state => !!state.token,    // 로그인 했는지 확인
        authError: state => state.authError,   // 인증 에러
        authHeader: state => ({ Authorization: state.token}),  // 인증 정보
        loginUser: state => state.loginUser,  // 현재 로그인한 유저 
        
=======
        
    },
    getters: {
        isLoggedIn: state => !!state.token,
        authError: state => state.authError,
        authHeader: state => ({ Authorization: `Token ${state.token}`}),
        loginUser: state => state.loginUser,

>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
    },
    mutations: {
        SET_TOKEN: (state, token) => state.token = token,
        SET_LOGIN_USER: (state, user) => state.loginUser = user,
<<<<<<< HEAD
        SET_AUTH_ERROR: (state, error) => state.authError = error,
        LOGOUT: (state,user) => { 
            localStorage.removeItem('user')
            location.reload();
        }
=======
        SET_AUTH_ERROR: (state, error) => state.authError = error
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
    },
    actions: {
        login({commit, dispatch},credential){
            axios({
<<<<<<< HEAD
                url: rest.accounts.login(),
=======
                url: rest.auth_login.login(),
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
                method: 'post',
                data: credential
            })
            .then(res => {
                const token = res.data.accessToken
                dispatch('saveToken', token)
<<<<<<< HEAD
                // dispatch('fetchLoginUser')
=======
                dispatch('fetchLoginUser')
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
                
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
<<<<<<< HEAD
                console.log('signup')
=======
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
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
<<<<<<< HEAD
        },
=======
      
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)

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
<<<<<<< HEAD
        logout({commit, dispatch}) {
            dispatch('removeToken');
        }
    }
}
=======
    }}
}
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
