import router from '@/router'
import axios from 'axios'
import rest from '@/api/rest'

export default {
    state: {
        // token 인증 방식
        token: localStorage.getItem('token') || '',  
        authError: null, // 오류 발생 시
        loginUser: {},
        targetUser: {},
        
    },
    getters: {
        isLoggedIn: state => !!state.token,    // 로그인 했는지 확인
        authError: state => state.authError,   // 인증 에러
        authHeader: state => ({ Authorization: state.token}),  // 인증 정보
        loginUser: state => state.loginUser,  // 현재 로그인한 유저
        targetUser: state => state.targetUser, // 다른 유저 정보 
        
    },
    mutations: {
        SET_TOKEN: (state, token) => state.token = token,
        SET_LOGIN_USER: (state, user) => state.loginUser = user,
        SET_AUTH_ERROR: (state, error) => state.authError = error,
        SET_TARGET_USER: (state, user) => state.targetUser = user,
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
                dispatch('fetchLoginUser')
                dispatch('myStudyList')
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

        signup({ commit}, credentials){
            console.log("엑시오스 하기 전")
            console.log(credentials)
            axios({
                url: rest.user.user(),
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
                .then(res =>
                    commit('SET_LOGIN_USER', res.data)
                )
                .catch(err => {
                  if (err.response.status === 401) {
                    dispatch('removeToken')
                    router.push({ name: 'login' })
                  }
                })
            }
        },
        // id값을 통해 해당 유저 정보 획득 (다른 유저 아이디 및 외래키를 통한 정보 접근용)
        fetchUserInfo({  commit, getters  }, user_pk){
            axios({
                url: rest.user.user_check(user_pk),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_TARGET_USER', res.data)
            })
            .catch(err => {
                console.log( err,'오류')
            })
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
        logout({ dispatch}) {
            dispatch('removeToken');
            // commit('SET_MY_STUDY_LIST',{})
            router.push({name:'Home'})
        },
        
        user_delete({getters, dispatch}) {
            const Swal = require('sweetalert2')
            Swal.fire(
                '정말 탈퇴하실건가요??'
            )
            .then((result) => {
                console.log('then1')
                if (result.isConfirmed) {
                    axios({
                        url : rest.user.user(),
                        method: 'delete',
                        headers: getters.authHeader,
                        // data: userId
                    })
                    .then(dispatch('removeToken'))
                        console.log('then2')
                        Swal.fire(
                            '그동안 Goose를 이용해주셔서 감사합니다'
                        )  
                    router.push({name:'Home'})
                }
            })
    }
    }
}
