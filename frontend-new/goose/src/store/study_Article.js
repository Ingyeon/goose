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
        },
        studyArticleCommentList:[],
    },
    getters: {
        studyArticleList: state => state.studyArticleList,
        selectedArticle: state => state.selectedArticle,
        studyArticleCommentList: state => state.studyArticleCommentList,
        isArticleWriter: (state,getters) => state.selectedArticle.user_pk == getters.loginUser.id
    },
    mutations: {
       SET_STUDY_ARTICLES: (state,articleList) => state.studyArticleList = articleList,
       SET_SELECTED_ARTICLE: (state,article) => state.selectedArticle = article,
       SET_STUDY_ARTICLE_COMMENT_LIST: (state,commentList) => state.studyArticleCommentList = commentList
    },
    actions: {
        getStudyArticleList({getters,commit},credential){
            axios({
                url: rest.studyArticle.studyArticleList(),
                method:'get',
                headers: getters.authHeader,
                params: { "category":credential.category, "page":credential.page, "studyPk":credential.studyPk, "title":credential.title}
            })
            .then((res)=>{
                // console.log(res.data)
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
                commit("SET_SELECTED_ARTICLE",res.data)
            })
            // .catch((err)=>{
            //     console.log(err)
            // })
        },

       createStudyArticle({getters,dispatch},credential){
        axios({
            url: rest.studyArticle.studyArticles(),
            method: 'post',
            headers: getters.authHeader,
            data: credential
        })
        .then((res)=>{
            dispatch('getStudyArticleList',{category:null, page:1, studyPk:getters.selectedStudy.id, title:null})
        })
        // .catch((err)=>{
        //     console.log(err)
        // })

       },

       updateStudyArticle({getters,dispatch},credential){
        axios({
            url: rest.studyArticle.studyArticles(),
            method: 'PATCH',
            headers: getters.authHeader,
            data: credential,
            params: {id:getters.selectedArticle.id}
        })
        .then((res)=>{
            // console.log(res)
            dispatch('getStudyArticleList',{category:null,page:1,title:null})
        })
        // .catch((err)=>{
        //     console.log(err)
        // })
       },

       deleteStudyArticle({getters}){
        axios({
            url: rest.studyArticle.studyArticles(),
            method: 'delete',
            headers: ({ Authorization: getters.token, id:getters.selectedArticle.id}),
        })
       },

    //    댓글
       createComment({getters,dispatch},credential){
        axios({
            url: rest.studyArticle.studyArticleReply(),
            method: 'post',
            headers: getters.authHeader,
            data: credential
        })
        .then((res)=>{
            dispatch('getComment',{articlePk:getters.selectedArticle.id,page:1})
        })
        // .catch((err)=>{
        //     console.log(err)
        // })
       },

       getComment({getters,commit},credential){
        axios({
            url: rest.studyArticle.studyArticleReply(),
            method: 'get',
            headers: getters.authHeader,
            params: credential //{articlePk:int ,id:int, page:int}
        })
        .then((res)=>{
            commit('SET_STUDY_ARTICLE_COMMENT_LIST',res.data.content)
        })
        // .catch((err)=>{
        //     console.log(err)
        // })
       },

       deleteComment({getters,dispatch},id){
        axios({
            url: rest.studyArticle.studyArticleReply(),
            method: 'delete',
            headers: { id:id, Authorization:getters.token }
        })
        .then((res)=>{
            dispatch('getComment',{articlePk:getters.selectedArticle.id,page:1})
        })
        // .catch((err)=>{
        //     console.log(err)
        // })
       },

    //    patchComment({getters},credential){
    //     axios({
    //         url: rest.studyArticle.studyArticleReply(),
    //         method: 'patch',
    //         data: {"re_content":"하드코딩"},
    //         // headers: {id:credential.id, Authorization:getters.token }
    //         headers: {id:4, Authorization:getters.token }
    //     })
    //     .then((res)=>{
    //         console.log("댓글 수정 완료")
    //     })
    //     .catch((err)=>{
    //         console.log("댓글 수정 실패")
    //         console.log(err)
    //     })
    //    }
    }
}
