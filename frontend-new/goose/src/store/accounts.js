import router from '@/router'
import axios from 'axios'
import rest from '@/api/rest'

export default {
    state: {
        // token 인증 방식
        token: localStorage.getItem('token') || '',  
        authError: null, // 오류 발생 시
    },
    getters: {
        isLoggedIn: state => !!state.token,
        authError: state => state.authError,
        authHeader: state => ({ Authorization: `Token ${state.token}`})
    },
    mutations: {
        SET_TOKEN: (state, token) => state.token = token,
        SET_AUTH_ERROR: (state, error) => state.authError = error
    },
    actions: {
        saveToken({  commit  }, token) {
            commit('SET_TOKEN', token)
            localStorage.setItem('token', token)
        },
        removeToken({  commit  }){
            commit('SET_TOKEN', '')
            localStorage.setItem('token','')
        },

        login({commit, dispatch}, credentials){
            axios({
                url: rest.auth_login,
                method: 'post',
                data: credentials
            })
            .then(res => {
                const token = res.data.key
                dispatch('saveToken', token)
                dispatch('fetchCurrentUser')
            
            router.push({name: 'Home'})
            })
            .catch(err => {
                console.error(err.response.data)
                commit('SET_AUTH_ERROR', err.response.data)
            })
        }
        // savetoken 액션 제작 하기
        
        // signup({  commit, dispatch}, credentials){
        //     axios({
        //         url: ??,
        //         method: 'post',
        //         data: credentials
        //     })
        //     .then(res => {
        //         const token = res.data.key
        //         dispatch('saveToken', token)
        //         dispatch('fetchCurrentUser')
        //         router.push({ name: 'Home '})
        //     })
        //     .catch(err => {
        //         console.error(err.response.data)
        //         commit('SET_AUTH_ERROR', err.response.data)
        //     })
        // }


        // logout({  getters , dispatch}){
        //     axios({
        //         url: ??,
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
    }
}