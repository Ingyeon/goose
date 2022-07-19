import Vue from 'vue'
import Vuex from 'vuex'

import accounts from './accounts.js'

Vue.useAttrs(Vuex)

export default new Vuex.Store({
    modules: {accounts},
})