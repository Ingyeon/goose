// import Vue from 'vue'
// import Vuex from 'vuex'

// import accounts from './accounts.js'

// Vue.useAttrs(Vuex)

// export default new Vuex.Store({
//     modules: {accounts},
// })


import { createStore } from "vuex";
import accounts from './accounts.js'
import study_page from './study_page.js'
<<<<<<< HEAD
import articles from './articles.js'

export default createStore({
  modules: { accounts,study_page,articles }
=======

export default createStore({
  modules: { accounts,study_page }
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
});
