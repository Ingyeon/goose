import axios from "axios"
import rest from '@/api/rest'
import router from "@/router"

export default {
  state:{
    studyTotal : [],
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
      "notice":"저희 스터디로 말씀드릴것 같으면 SSAFY 2학기 공통프로젝트를 맞아 Spring과 Vue를 활용하여 화상 웹서비스를 제공하는 프로젝트를 만드는 스터디입니다.",
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
  },

  mutations:{
    SET_ID: (state, id) => state.selectStudy.id = id,
    SET_STUDY: (state, studyName) => state.selectedStudy.title = studyName,
    SET_MAX_MEMBER: (state, num) => state.selectedStudy.maxmember = num,
    SET_MEMBER: (state, num) => state.selectedStudy.member = num,
    SET_CAM_URL: (state, URL) => state.selectedStudy.urlConf = URL,
    SET_STUDY_URL: (state, URL) => state.selectStudy.studyURL = URL,

    SET_STUDY_BOARD: (state, studyBoard) => state.selectedStudy.studyBoard = studyBoard,
    SET_NOTICE: (state, notice) => state.selectedStudy.notice = notice,
  },

  actions:{
    saveId({commit}, id) {
      commit('SET_ID',id)
    },

    saveStudy({ commit }, studyName) {
      commit('SET_STUDY',studyName)
    },
    saveMaxMember({ commit }, num) {
      commit('SET_MAX_MEMBER',num)
    },
    saveMember({ commit }, num) {
      commit('SET_MEMBER',num)
    },
    saveCamURL({ commit }, URL) {
      commit('SET_CAM_URL', URL)
    },
    saveStudyURL({commit},URL){
      commit('SET_STUDY_URL',URL)
    },

    saveNotice({ commit }, notice) {
      commit('SET_NOTICE', notice)
    },
    saveStudyBoard({commit}, studyBoard) {
      commit('SET_STUDY_BOARD',studyBoard)
    },

    // 한번에 처리
    selectStudy({dispatch}, obj){
      dispatch('saveStudy',obj.title)
      dispatch('saveMaxMember',obj.maxmember)
      dispatch('saveMember',obj.member)
      dispatch('saveCamURL',obj.url_conf)
      dispatch('safeStudyURL',obj.url_page)
    },
<<<<<<< HEAD
    
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
=======
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)

    deleteStudy({dispatch,getters},studyId){
      axios({
        uri: rest.study.study_remove(studyId),
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
    }
  },
}