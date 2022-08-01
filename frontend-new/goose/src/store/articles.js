import axios from "axios"
import rest from '@/api/rest'
import router from "@/router"


export default {
    state: {
        articles: [],   // 전체 게시물 조회 시 사용
        article: [],   // 개별 게시물의 CRUD에 사용
        replies: [], // 해당 게시물의 전체 댓글 조회 시 사용
        reply: {}, //개별 댓글의 CRUD
    },
    getters: {
        articles: state => state.articles,
        article: state => state.article,
        isAuthor: (state,getters) => {return state.article.user_pk == getters.loginUser},     // 게시물 작성자 권한 확인 (fetchCurrentUser 활용)
        replies: state => state.replies,
        reply: state => state.reply,
    },
    mutations: {
        SET_ARTICLES: (state, articles) => state.articles = articles,
        SET_ARTICLE: (state, article) => state.article = article,
        SET_REPLIES: (state, replies) => state.replies = replies,
        SET_REPLY: (state, reply) => state.reply = reply,
    },
    actions: {
        // 전체 페이지 조회
        fetchArticles({commit,getters},page){
            axios({
                url: rest.article.article_list(page),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_ARTICLES',res.data.content
                )
            
            })
                
            .catch(err => 
                {console.log(err)})

        },
        // 단일 페이지 조회
        fetchArticle({ commit, getters  }, id){
            axios({
                url: rest.article.article_read(id),
                method: 'get',
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_ARTICLE', res.data)})

            .catch(err => {
                console.error(err.response)
                if (err.response.status === 404){
                    console.log('404 페이지 제작 필요')
                }
            })
        },
        createArticle({getters},form_data){
            axios({
                url: rest.article.article_create(),
                method: 'post',
                data: form_data,
                headers: getters.authHeader,
            })
        },
            updateArticle({  commit, getters  }, {id,form_data} ){
                axios({
                    url: rest.article.article_rud(id),
                    method: 'put',
                    data: form_data,
                    headers: getters.authHeader,
                })
                .then(res => {
                    commit('SET_ARTICLE', res.data)
                    router.push({
                        name: 'ArticleDetail',
                        params: {  id: getters.article.article_pk}
                    })
                })
            },
            deleteArticle({  commit, getters  },id){
                if (confirm('게시글을 삭제합니다')){
                    console.log('시도'),
                    axios({
                        url: rest.article.article_delete(),
                        method: 'post',
                        data: id,
                        headers: getters.authHeader,
                    })
                    .then(() => {
                        console.log('완료')
                        commit('SET_ARTICLE', {})
                        router.push({
                            name: 'Articles'})                    
                        })
                    .catch(err=> console.error(err.response))
                }
            },
        
        createReply({commit, getters}, reply_data){
            console.log(reply_data)

            axios({
                url: rest.articles_reply.reply_cr(),
                method: 'post',
                data: reply_data,
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_REPLIES', res.data)
            })
            .catch(err => console.error(err.response))
        },

        deleteReply({commit, getters},{id}){
            if (confirm('댓글을 삭제하시겠습니까?')){
                axios({
                    url: rest.articles_reply.reply_ud(id),
                    method: 'delete',
                    data: {},
                    headers: getters.authHeader,
                })
                .then(res => {
                    commit('SET_REPLIES',res.data)
                })
                .catch(err => console.error(err.response))
            }
        },
        
        updateReply({  commit, getters  }, { id, re_content  }){
            axios({
                url: rest.articles_reply.reply_ud(id), 
                method: 'put',
                data: re_content,
                headers: getters.authHeader,
            })
            .then(res => {
                commit('SET_REPLIES', res.data)
            })
            .catch(err => console.error(err.response))
        },

        fetchReplies({  commit }, {article_pk,reply_page}){
            axios.get(rest.articles_reply.reply_cr(),{
                params:{
                    articlePk:article_pk,
                    page: reply_page}})
            .then(res => {
                console.log(1)
                commit('SET_REPLIES',res.data)
            })
            .catch(err => console.error(err.response))
        }
    }
}