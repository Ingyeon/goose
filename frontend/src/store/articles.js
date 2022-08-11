import axios from "axios"
import rest from '@/api/rest'
import router from "@/router"
import { _ } from "core-js"
import store from "."


export default {
    state: {
        articles: [],   // 전체 게시물 조회 시 사용
        article: [],   // 개별 게시물의 CRUD에 사용
        replies: [], // 해당 게시물의 전체 댓글 조회 시 사용
        reply: {}, //개별 댓글의 CRUD
        total: [],
        total_replies: [],
        // test: [],
        // myReplyList: [
        //     // id : 댓글단 글의 id가 들어감
        //     // title: 댓글단 글의 제목이 들어감
        // ],
        // pageIndex: 1,
        // articleCheck: {test:true},
        // tempList : [
        //     // myReplyList가 n명 이상이라 못들어간 목록 임시 저장소 
        // ]
    },
    getters: {
        articles: state => state.articles,
        article: state => state.article,
        isAuthor: (state,getters) => {return state.article.user_pk == getters.loginUser},     // 게시물 작성자 권한 확인 (fetchCurrentUser 활용)
        replies: state => state.replies,
        reply: state => state.reply,
        total: state => state.total,
        total_replies: state => state.total_replies
        // test: state => state.test,
        // myReplyList: state => state.myReplyList,
        // pageIndex: state => state.pageIndex,
        // articleCheck: state => state.articleCheck,
        // tempList: state => state.tempList
    },
    mutations: {
        SET_ARTICLES: (state, articles) => state.articles = articles,
        SET_ARTICLE: (state, article) => state.article = article,
        SET_REPLIES: (state, replies) => state.replies = replies,
        SET_REPLY: (state, reply) => state.reply = reply,
        SET_TOTALPAGE: (state, total) => state.total = total,
        SET_TOTALREPLIES: (state, total_replies) => state.total_replies = total_replies
        // SET_TEST: (state, test) => state.test = test,
        // RESET_MY_REPLY_LIST: (state) => state.myReplyList = [],
        // ADD_MY_REPLY_LIST: (state,replyList) => state.myReplyList.push(replyList),
        // SET_PAGE_INDEX: (state,pageIndex) => state.pageIndex = pageIndex,
        // ADD_ARTICLE_CHECK: (state,value) => Object.defineProperty(state.articleCheck,value,{value:true}),
        // CLEAR_ARTICLE_CHECK:(state) => state.articleCheck = {},
        // ADD_TEMP_LIST: (state,item) => state.tempList.push(item),
        // CLEAR_TEMP_LIST:(state) => state.tempList = []
    },
    actions: {
        // 전체 페이지 조회
        // 백엔드 -> 페이지 별이 아닌 전체 리스트 뽑는 api 요청하기
        async fetchArticles({commit,getters},page){
            await axios({
                url: rest.article.article_list(page),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_TOTALPAGE',res.data.totalPages)
                commit('SET_ARTICLES',res.data.content
                )
            
            })
                
            .catch(err => 
                {console.log(err)})

        },

        sortedArticles({commit,getters}, item){
            axios({
                // 필터 된 상황에서의 페이지 네이션 생각해보기
                url: rest.article.article_list(1),
                method: 'get',
                headers: getters.authHeader,
                params: {
                    category:item,
                }
            })
            .then(res => {
                console.log(res.data)
                commit('SET_TOTALPAGE',res.data.totalPages)
                commit('SET_ARTICLES',res.data.content
                )
            
            })
            .catch(err => 
                {console.log(err)})},

        filterArticles({commit,getters}, form){
            axios({
                // 필터 된 상황에서의 페이지 네이션 생각해보기
                url: rest.article.article_list(form.page),
                method: 'get',
                headers: getters.authHeader,
                params: {
                    category:form.category,
                    state: form.state,
                    title: form.title,
                }
            })
            .then(res => {
                commit('SET_TOTALPAGE',res.data.totalPages)
                commit('SET_ARTICLES',res.data.content
                )
            
            })
            .catch(err => 
                {console.log(err)})

        },
        // 단일 페이지 조회
        fetchArticle({ commit, getters,dispatch  }, id){
            axios({
                url: rest.article.article_read(id),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                console.log(res.data.title)
                commit('SET_ARTICLE', res.data)
                dispatch('selectStudy',res.data.study_pk)}
                )
                

            .then(()=>{
                dispatch('fetchReplies', {article_pk:getters.article.id,reply_page:1} )
            })
            .catch(err => {
                console.error(err.response)
                router.push({name:'ArticleList'})})
            },
        // 프로필에서 댓글 단 글 조회시 사용할 용도
        // fetchArticle2({ commit, getters }, id){
        //     axios({
        //         url: rest.article.article_read(id),
        //         method: 'get',
        //         headers: getters.authHeader,
        //     })
        //     .then((res)=>{
        //         commit('ADD_MY_REPLY_LIST',{id:res.data.id ,title:res.data.title})
        //     })
        // },
        
        
        
        createArticle({getters},form_data){
            axios({
                url: rest.article.article_cud(),
                method: 'post',
                data: form_data,
                headers: getters.authHeader,
            })
            .then(res => {
                console.log(res)
                // 생성 후 바로 상세 페이지 가려면 res 데이터에 article_pk 값 받아올 수 있어야 함 / 백앤드?
                router.push({path:`/articles/`})
            })
            .catch(err=> {
                alert('값을 모두 입력하세요!')
            })
        },
            updateArticle({  getters,dispatch  },form_data ){
                axios({
                    url: rest.article.article_read(getters.article.id),
                    method: 'patch',
                    data: form_data,
                    headers: getters.authHeader,
                })
                .then(res => {
                    dispatch('fetchArticle',getters.article.id)
                    router.push({
                        name: 'ArticleDetail',
                        params: {  id: getters.article.id  }
                    })
                })
            },
            deleteArticle({  commit, getters  },id){
                if (confirm('게시글을 삭제합니다')){
                    axios({
                        url: rest.article.article_cud(),
                        method: 'delete',
                        headers: {'Authorization' : getters.authHeader.Authorization, 'id':id},
                    })
                    .then(() => {
                        console.log('성공')
                        commit('SET_ARTICLE', {})
                        router.push({
                            name: 'Articles'})                    
                        })
                    .catch(err=> console.error(err.response))
                }
            },
        
        createReply({getters}, reply_data){

            axios({
                url: rest.articles_reply.reply_crud(),
                method: 'post',
                data: reply_data,
                headers: getters.authHeader,
            })
            .then(res => {
            })
            .catch(err => 
                console.log('실패'))
            
        },

        deleteReply({dispatch, getters},id){
            if (confirm('댓글을 삭제하시겠습니까?')){
                axios({
                    url: rest.articles_reply.reply_crud(),
                    method: 'delete',
                    headers: {'Authorization' : getters.authHeader.Authorization, 'id':id},
                })
                .then(res => {
                    dispatch('fetchReplies',{article_pk:getters.article.id,reply_page:1})
                })
                .catch(err => console.error(err.response))
            }
        },
        
        // updateReply({  dispatch, getters  }, data){
        //     axios({
        //         url: rest.articles_reply.reply_crud(), 
        //         method: 'patch',
        //         data: data.re_content,
        //         params: {id:data.id},
        //         headers: getters.authHeader,
                // "Content-Type": "application/json"
            // })
            // .then(res => {
            //     dispatch('fetchReplies',{article_pk:getters.article.id,reply_page:1})
            // })
            // .catch(err => console.error(err.response))
        // },

        fetchReplies({  commit }, reply_data){
            axios.get(rest.articles_reply.reply_crud(),{
                params:{
                    articlePk:reply_data.article_pk,
                    page: 1}})
            .then(res => {
                commit('SET_TOTALREPLIES',res.data.totalPages)
                commit('SET_REPLIES',res.data.content)
            })
            .catch(err => console.error(err.response))
        },

        Repliesnation({  commit }, reply_data){
            console.log(reply_data)
            console.log('위')
            axios.get(rest.articles_reply.reply_crud(),{
                params:{
                    articlePk:reply_data.articlePk,
                    page: reply_data.page}})
            .then(res => {
                console.log(res.data.content)
                commit('SET_TOTALREPLIES',res.data.totalPages)
                commit('SET_REPLIES',res.data.content)
            })
            .catch(err => console.error(err.response))
        },

        // resetMyReplyList({commit}){
        //     commit('RESET_MY_REPLY_LIST')
        // },

        clearArticleCheck({commit}){
            commit('CLEAR_ARTICLE_CHECK')
        },
        
        // 포기
        // findMyReplyArticles({  state,commit,getters }){
        //     commit('RESET_MY_REPLY_LIST')
        //     if (getters.tempList){
        //         while(!_.isEmpty(getters.tempList && getters.myReplyList.length <5)){
        //             commit('ADD_MY_REPLY_LIST',state.tempList.pop())
        //         }
        //     }
            
        //     // while (getters.myReplyList.length != 5){
        //         axios.get(rest.articles_reply.reply_crud(),{params:{page: 
        //             // getters.pageIndex}})
        //             1}})
        //         .then(res => {
        //             for (let reply of res.data.content){
        //                 if(reply.user_pk == getters.loginUser.id){
        //                     // 해당 아티클 조회하여 title 가져오기 위해
        //                     axios({
        //                         url: rest.article.article_read(reply.article_pk),
        //                         method: 'get',
        //                         headers: getters.authHeader,
        //                     })
        //                     .then((res)=>{
        //                         if(!getters.articleCheck[res.data.id]){
        //                             if (getters.myReplyList.length <5){
        //                                 commit('ADD_MY_REPLY_LIST',{id:res.data.id, title:res.data.title})
        //                                 commit('ADD_ARTICLE_CHECK',res.data.id)
        //                             } else {
        //                                 commit('ADD_TEMP_LIST',{id:res.data.id, title:res.data.title})
        //                             }
        //                         }
                                
        //                     })
        //                 }
        //             }
        //             commit('SET_PAGE_INDEX',getters.pageIndex+1)
        //         })
        //         .catch(err => console.error(err.response))
                
        //     // }
        // },
    }}