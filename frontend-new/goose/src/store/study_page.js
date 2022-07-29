import axios from "axios"
import rest from '@/api/rest'
import router from "@/router"

export default {
  state:{
    myStudyList : [],
    authStudyList: [],
    studyJoinList : [],
    selectedStudy : {},
    studyBoard : [],
  },

  getters:{
    selectedStudy : state => state.selectedStudy,
    myStudyList: state => state.myStudyList,
    camURL: state => state.selectedStudy.urlConf,
    studyURL: state => state.selectStudy.urlPage,
    studyBoard : state => state.studyBoard,
    // 스터디 참가 신청 관련
    studyJoinList: state => state.studyJoinList,
    isJoinList: state => !!state.saveJoinList
  },

  mutations:{
    // 스터디 조회시 주로 작동
    SET_STUDY: (state, study) => state.selectedStudy = study,

    SET_STUDY_BOARD: (state, studyBoard) => state.studyBoard = studyBoard,
    SET_NOTICE: (state, notice) => state.selectedStudy.notice = notice,
    // 스터디 참가 신청 관련
    SET_JOIN_LIST: (state,joinArray) => state.studyJoinList = joinArray,
    // 참여하고 있는 스터디 조회
    SET_MY_STUDY_LIST: (state,studyList) => state.myStudyList = studyList,
    SET_AUTH_STUDY_LIST: (state, authStudyList) => state.authStudyList = authStudyList,
  },

  actions:{
    saveStudyBoard({commit}, studyBoard) {
      commit('SET_STUDY_BOARD',studyBoard)
    },

    saveJoinList({commit}, joinArray) {
      commit('SET_JOIN_LIST',joinArray)
    },

    // 한번에 처리
    selectStudy({commit,getters},id){
      axios({
        url: rest.study.study_search(id),
        method: 'get',
        headers: getters.authHeader
      })
      .then((res)=>{
        commit('SET_STUDY', res.data)
        router.push({ path: '/studyHome',})
      })
      .catch((err)=>{
        console.log("스터디 선택 실패")
        console.log(err)
      })
    },
    
    createStudy({getters},credential){
      axios({
        url: rest.study.study_create(),
        method: 'post',
        headers: getters.authHeader,
        data: credential
      })  
      .then((res) =>{
        console.log(res)
        router.push({name: 'Home'})
      })
      .catch(err => {
        console.log('catch')
        console.log(err)
    })
    },

    deleteStudy({dispatch,getters},studyId){
      axios({
        url: rest.study.study_remove(studyId),
        method: 'delete',
        params: {"id":studyId}
      })
      .then(()=>{
        dispatch('selectStudy',{})
      })
      .catch(err => {
        console.log(err)
        console.log("망함")
        alert("스터디 삭제에 실패했습니다.")
      })
      router.push({ name:'Home' })
    },
    myStudyList({getters,commit}){
      axios({
        url: rest.study.my_study_list(),
        method: 'get',
        headers: getters.authHeader
      })
      .then(res =>{
        console.log("스터디 리스트")
        console.log(res)
        commit('SET_MY_STUDY_LIST',res.data)
      })
    },
    authStudyList({getters,commit}){
      axios({
        url: rest.study.auth_study_list(),
        method: 'get',
        headers: getters.authHeader,
      })
      .then(res=> {
        commit('SET_AUTH_STUDY_LIST',res.data)
      })
    },


    joinList({getters,dispatch},){
      axios({
        url: rest.study.study_join_list(getters.studyId),
        method: 'get',
      })
      .then(res => {
        dispatch('saveJoinList',res)
      })
      .catch(err => {
        console.log("catch")
        console.log(err)
      })
    },
    
    //스터디 가입 신청
    joinStudy({getters},studyId){
      axios({
        url: rest.study.joinStudy(studyId),
        method: 'post',
        headers: getters.authHeader
      })
      .then(res=>{
        console.log("스터디 가입신청 완료")
        console.log(res)
      })
      .catch(res=>{
        console.log("스터디 가입 신청 실패")
        console.log(res)
      })
    },

    // 스터디 가입신청 승락
    joinAgree({getters},credential){
      console.log("승락")
      // axios({
      //   url: rest.study.study_join_agree(),
      //   method: 'post',
      //   headers: getters.authHeader,
      //   data: credential
      // })
      // .then(res =>{
      //   console.log(res)
      // })
    },

    // 스터디 가입신청 거절
    joinRefuse({getters},joinStudyId){
      console.log("거절")
      // axios({
      //   url: rest.study.study_join_delete(joinStudyId),
      //   method: 'delete',
      //   headers: getters.authHeader,
      // })
    }


  },
}