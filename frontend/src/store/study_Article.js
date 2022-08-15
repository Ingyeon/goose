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
        refresh : false,
        commentPage : 1
    },
    getters: {
        studyArticleList: state => state.studyArticleList,
        selectedArticle: state => state.selectedArticle,
        studyArticleCommentList: state => state.studyArticleCommentList,
        isArticleWriter: (state,getters) => state.selectedArticle.user_pk == getters.loginUser.id,
        refresh : (state) => state.refresh,
        commentPage : state => state.commentPage
    },
    mutations: {
       SET_STUDY_ARTICLES: (state,articleList) => state.studyArticleList = articleList,
       SET_SELECTED_ARTICLE: (state,article) => state.selectedArticle = article,
       SET_STUDY_ARTICLE_COMMENT_LIST: (state,commentList) => state.studyArticleCommentList = commentList,
       SET_REFRESH:(state) => {
        if(state.refresh == false){state.refresh = true}
        else{state.refresh = false}
       },
       SET_COMMENT_PAGE:(state,page) => state.commentPage = page
    },
    actions: {
        getStudyArticleList({getters,commit},credential){
            axios({
                url: rest.studyArticle.studyArticleList(),
                method:'get',
                headers: getters.authHeader,
                params: { "category":credential.category, "page":credential.page, "studyPk":getters.selectedStudy.id, "title":credential.title}
            })
            .then((res)=>{
                commit("SET_STUDY_ARTICLES",res.data.content)
            })
        },
        
        getStudyArticle({getters,commit,dispatch},articleId){
            axios({
                url: rest.studyArticle.studyArticles(),
                method: 'get',
                headers: getters.authHeader,
                params: {id:articleId}
            })
            .then((res)=>{
                commit("SET_SELECTED_ARTICLE",res.data)
                commit("SET_COMMENT_PAGE",1)
                dispatch("getComment",{articlePk:getters.selectedArticle.id ,id:null, page:1})
            })
            .catch((err)=>{
                console.log(err)
            })
        },

       async createStudyArticle({getters,dispatch},credential){
        await axios({
            url: rest.studyArticle.studyArticles(),
            method: 'post',
            headers: getters.authHeader,
            data: credential
        })
        .then((res)=>{
            dispatch('getStudyArticleList',{category:credential.category, page:1,  studyPk:getters.selectedStudy.id, title:null})
        })
        .catch((err)=>{
            console.log(err)
        })

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
            dispatch('getStudyArticleList',{category:null,page:1,title:null})
        })
        .catch((err)=>{
            console.log(err)
        })
       },

       deleteStudyArticle({getters,dispatch}){
            const check = confirm("글을 삭제하시겠습니까?")
            if(check){
                axios({
                    url: rest.studyArticle.studyArticles(),
                    method: 'delete',
                    headers: ({ Authorization: getters.token, id:getters.selectedArticle.id}),
                })
                .then(()=>{
                    dispatch('getStudyArticleList',{ "category":null, "page":1, "studyPk":getters.selectedStudy.id, "title":null})
                })
            }
       },
       refresh({commit}){
        commit('SET_REFRESH')
       },

    //    댓글
       createComment({getters,dispatch},re_content){
        axios({
            url: rest.studyArticle.studyArticleReply(),
            method: 'post',
            headers: getters.authHeader,
            data: { article_pk:getters.selectedArticle.id, re_content:re_content, study_pk:getters.selectedStudy.id }
        })
        .then((res)=>{
            dispatch('getComment',{articlePk:getters.selectedArticle.id,page:1})
        })
        .catch((err)=>{
            console.log(err)
        })
       },

       getComment({getters,commit},credential){
        commit('SET_COMMENT_PAGE',credential.page)
        axios({
            url: rest.studyArticle.studyArticleReply(),
            method: 'get',
            headers: getters.authHeader,
            params: credential //{articlePk:int ,id:int, page:int}
        })
        .then((res)=>{
            commit('SET_STUDY_ARTICLE_COMMENT_LIST',res.data.content)

        })
        .catch((err)=>{
            console.log(err)
        })
       },

       deleteComment({getters,dispatch},id){
            const check = confirm("댓글을 삭제하시겠습니까?")
            if(check){
                axios({
                    url: rest.studyArticle.studyArticleReply(),
                    method: 'delete',
                    headers: { id:id, Authorization:getters.token }
                })
                .then((res)=>{
                    dispatch('getComment',{articlePk:getters.selectedArticle.id,page:1})
                })
                .catch((err)=>{
                    console.log(err)
                })
            }
        },

       patchComment({getters,dispatch},credential){
        axios({
            url: rest.studyArticle.studyArticleReply(),
            method: 'patch',
            data: {"re_content":credential.re_content},
            headers: getters.authHeader,
            params: {id:credential.id}
        })
        .then((res)=>{
            dispatch('getComment',{articlePk:getters.selectedArticle.id ,id:null, page:1})
        })
        .catch((err)=>{
            console.log(err)
        })
       }
    }
}
