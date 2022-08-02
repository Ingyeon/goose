import router from '@/router'
import axios from 'axios'
import rest from '@/api/rest'

export default {
    state: {
        studyArticleList:[
            // {
            //     "id": 16,
            //     "article_pk": 0,
            //     "user_pk": 7,
            //     "study_pk": 6,
            //     "category": "free",
            //     "title": "6번 스터디의 첫 게시글",
            //     "content": "테스트",
            //     "date": "2022-08-02 15:23:59",
            //     "image": "",
            //     "hit": 0,
            //     "name": "테스트"
            //   },{},{}
        ],
        selectedArticle:{
            // "category": "string",
            // "content": "string",
            // "date": "string",
            // "hit": 0,
            // "id": 0,
            // "image": "string",
            // "name": "string",
            // "title": "string",
            // "user_pk": 0
        }
    },
    getters: {
        studyArticleList: state => state.studyArticleList,
        selectedArticle: state => state.selectedArticle,
    },
    mutations: {
       SET_STUDY_ARTICLES: (state,articleList) => state.studyArticleList = articleList,
       SET_SELECTED_ARTICLE: (state,article) => state.selectedArticle = article,
    },
    actions: {
        getStudyArticleList({getters,commit},credential){
            axios({
                url: rest.studyArticle.studyArticleList(),
                method:'get',
                headers: getters.authHeader,
                params: { category:credential.category, page:credential.page, title:credential.title }
            })
            .then((res)=>{
                console.log(res.data)
                commit("SET_STUDY_ARTICLES",res.data.content)
            })
        },
        
        getStudyArticle({getters,commit},articleId){
            axios({
                url: rest.studyArticle.studyArticles(),
                method: 'get',
                headers: getters.authHeader,
                params: {id:articleId}
            })
            .then((res)=>{
                console.log("정보조회 성공")
                commit("SET_SELECTED_ARTICLE",res.data)
            })
            .catch((err)=>{
                console.log(err)
            })
        },

       createStudyArticle({commit,getters},credential){
        axios({
            url: rest.studyArticle.studyArticles(),
            method: 'post',
            headers: getters.authHeader,
            data: credential
        })
        .then((res)=>{
            console.log(res)
        })
        .catch((err)=>{
            console.log(err)
        })

       },

       updateStudyArticle({getters,dispatch},credential){
        console.log(credential)
        console.log(getters.selectedArticle.id)
        console.log(rest.studyArticle.studyArticles())
        axios({
            url: rest.studyArticle.studyArticles(),
            method: 'PATCH',
            headers: getters.authHeader,
            data: credential,
            params: {id:getters.selectedArticle.id}
        })
        .then((res)=>{
            console.log("업데이트 성공")
            console.log(res)
            dispatch('getStudyArticleList',{category:null,page:1,title:null})
        })
        .catch((err)=>{
            console.log(err)
        })
       },

       deleteStudyArticle({getters}){
        console.log(getters.selectedArticle.id)
        axios({
            url: rest.studyArticle.studyArticles(),
            method: 'delete',
            headers: ({ Authorization: getters.token, id:getters.selectedArticle.id}),
        })
       }
    }
}
