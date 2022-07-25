import axios from "axios"
import rest from '@/api/rest'
import router from "@/router"

export default {
    state: {
        articles: [],   // 전체 게시물 조회 시 사용
        article: {},   // 개별 게시물의 CRUD에 사용
        replies: [], // 해당 게시물의 전체 댓글 조회 시 사용
        reply: {}, //개별 댓글의 CRUD
    },
    getters: {
        articles: state => state.articles,
        article: state => state.article,
        isAuthor: (state,getters) => {return state.article.user_pk == getters.loginUser}     // 게시물 작성자 권한 확인 (fetchCurrentUser 활용)

    },
    mutations: {
        SET_ARTICLES: (state,articles) => state.articles = articles,
        SET_ARTICLE: (state, article) => state.article = article,
    },
    actions: {
        fetchArticles({commit}){
            axios({
                url: rest.article.article_list(),
                method: 'get',
            })
            .then(res => 
                commit('SET_ARTICLES',res.data)
            .catch(err => 
                {console.log(err)})
            )

        },
        createArticle({commit,getters},form_data){
            axios({
                url: rest.article.article_create(),
                method: 'post',
                data: form_data,
                headers: getters.authHeader,
            })
            .then(res => {
                console.log('성공')
                commit('SET_ARTICLE',res.data)
                router.push({
                    name: 'ArticleDetail',
                    params: {
                        id: getters.article.id
                    }
            .catch(err => {
                console.log(err)
            })        
                })
                
            })
        },
        // updateArticle({commit},{})
    }
}