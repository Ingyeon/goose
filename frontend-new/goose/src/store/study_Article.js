import router from '@/router'
import axios from 'axios'
import rest from '@/api/rest'

export default {
    state: {
        studyArticles:[]
    },
    getters: {
        studyArticles: state => state.studyArticles
    },
    mutations: {
       SET_STUDY_ARTICLES: (state,articles) => state.studyArticles = articles
    },
    actions: {
       createStudyArticle({commit},credential){
        console.log(credential)

       }
    }
}
