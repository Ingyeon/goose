export default {
  state:{
    studyName: "9조 스터디 구스",
    numOfPeople : 6,
    notice: "저희 스터디로 말씀드릴것 같으면 SSAFY 2학기 공통프로젝트를 맞아 Spring과 Vue를 활용하여 화상 웹서비스를 제공하는 프로젝트를 만드는 스터디입니다.",
    webURI: "https://edu.ssafy.com/",
  },

  getters:{
    studyName: state => state.studyName,
    numOfPeople: state => state.numOfPeople,
    notice: state => state.notice,
    webURI: state => state.webURI,
  },

  mutations:{
    SET_STUDY: (state, studyName) => state.studyName = studyName,
    SET_NUM_OF_PEOPLE: (state, numOfPeople) => state.numOfPeople = numOfPeople,
    SET_NOTICE: (state, notice) => state.notice = notice,
    SET_WEB_URI: (state, URI) => state.webURI = URI,
  },

  actions:{
    saveStudy({ commit }, studyName) {
      commit('SET_STUDY',studyName)
    },
    saveNOP({ commit }, numOfPeople) {
      commit('SET_NUM_OF_PEOPLE',numOfPeople)
    },
    saveNotice({ commit }, notice) {
      commit('SET_NOTICE', notice)
    },
    saveWebURI({ commit }, URI) {
      commit('saveWebURI', URI)
    },
  },
}