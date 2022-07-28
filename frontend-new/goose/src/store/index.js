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
import articles from './articles.js'
import createPersistedState from 'vuex-persistedstate';

export default createStore({
  modules: { accounts,study_page,articles },
  plugins: [createPersistedState()]
});
