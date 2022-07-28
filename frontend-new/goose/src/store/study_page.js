import axios from "axios"
import rest from '@/api/rest'
import router from "@/router"

export default {
  state:{
    studyTotal : [],
    studyJoinList : [{"apply_date": "26072022", "id":0, "name": "유저이름", "study_pk":0, "user_id":0} ],
    selectedStudy : {
      "category": "",
      "date": "",
      "id":'',
      "image": "",
      "maxmember": 6,
      "member": 6,
      "open": 0,
      "password": "",
      "title": "9조 스터디 구스",
      "urlConf": "https://edu.ssafy.com/", //캠 주소
      "urlPage":"", // 스터디 홈 주소
      "notice":"스터디 자기소개 부분인데 칼럼이 없음",
      "studyBoard":[{studyboard1:"기본값1"},{studyboard2:"기본값2"}],
    },
    defaultStudy : {
      "category": "",
      "date": "",
      "id":'',
      "image": "",
      "maxmember": 0,
      "member": 0,
      "open": 0,
      "password": "",
      "title": "",
      "urlConf": "", //캠 주소
      "urlPage":"", // 스터디 홈 주소
      "notice":"",
      "studyBoard":[],
    }

  },

  getters:{
    // 주로 스터디 조회 관련
    defaultStudy : state => state.defaultStudy,
    studyId : state => state.selectedStudy.id,
    studyTotal : state => state.studyTotal,
    studyName: state => state.selectedStudy.title,
    maxMember: state => state.selectedStudy.maxmember,
    member: state => state.selectedStudy.member,
    // notice: state => state.selectedStudy.notice,
    camURL: state => state.selectedStudy.urlConf,
    studyURL: state => state.selectStudy.urlPage,
    studyBoard : state => state.selectedStudy.studyBoard,
    // 스터디 참가 신청 관련
    studyJoinList: state => state.studyJoinList,
    isJoinList: state => !!state.saveJoinList
  },

  mutations:{
    // 스터디 조회시 주로 작동
    SET_ID: (state, id) => state.selectStudy.id = id,
    SET_STUDY: (state, studyName) => state.selectedStudy.title = studyName,
    SET_MAX_MEMBER: (state, num) => state.selectedStudy.maxmember = num,
    SET_MEMBER: (state, num) => state.selectedStudy.member = num,
    SET_CAM_URL: (state, URL) => state.selectedStudy.urlConf = URL,
    SET_STUDY_URL: (state, URL) => state.selectStudy.studyURL = URL,

    SET_STUDY_BOARD: (state, studyBoard) => state.selectedStudy.studyBoard = studyBoard,
    SET_NOTICE: (state, notice) => state.selectedStudy.notice = notice,
    // 스터디 참가 신청 관련
    SET_JOIN_LIST: (state,joinArray) => state.studyJoinList = joinArray
  },

  actions:{
    saveStudyBoard({commit}, studyBoard) {
      commit('SET_STUDY_BOARD',studyBoard)
    },

    saveJoinList({commit}, joinArray) {
      commit('SET_JOIN_LIST',joinArray)
    },

    // 한번에 처리
    selectStudy({commit}, obj){
      commit('SET_ID',obj.id),
      commit('SET_STUDY',obj.title)
      commit('SET_MAX_MEMBER',obj.maxMember)
      commit('SET_MEMBER',obj.member)
      commit('SET_CAM_URL', obj.url_conf)
      commit('SET_STUDY_URL',obj.url_page)
      // commit('SET_NOTICE', obj.notice)
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
        router.push({ name: 'Home '})
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
        dispatch('selectStudy',getters.defaultStudy)
      })
      .catch(err => {
        console.log(err)
        console.log("망함")
        alert("스터디 삭제에 실패했습니다.")
      })
      router.push({ name:'Home' })
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