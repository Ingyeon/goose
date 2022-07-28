const HOST = 'http://localhost:8080/api/v1/'

const Article = 'articles/'
const ArticleReply = 'articlesReply/'
const Auth = 'auth/login'
const Study = 'study/'
const User = 'users'
const Member = 'member/'


export default{
    accounts:{
        login: () => HOST + Auth,
        // logout: () =>
    },
    article: {
    article_create: () => HOST + Article,
    article_rud: id => HOST + Article + `${id}`,
    article_list: page => HOST + Article + 'list' + `?page=${page}`
    },
    articles_reply:{
        reply_cr: () => HOST + ArticleReply,
        reply_ud: id => HOST + ArticleReply + `${id}`,

    },
    study: {
        study_create: () => HOST + Study + 'create',
        study_remove: id => HOST + Study + 'remove/' + `${id}`,
        study_search: id => HOST + Study + 'search/' + `${id}`,
        study_update: () => HOST + Study + 'update',
        my_study_list: () => HOST + Study + Member + 'mystudylist',
        study_join: (study_pk) => HOST + Study + Member + 'joinstudy/'+ `${study_pk}`,
        study_join_list: (study_pk) => HOST + Study + Member + 'joinlist/'+ `${study_pk}`,
        study_join_agree: () => HOST + Study + Member + 'joinagree',
        study_join_delete: (id) => HOST + Study + Member + 'joindelete/'+ `${id}`,
    },
    user: {
        user_signup: () => HOST + User,
        user_check: userId => HOST + User + `${userId}`,
        user_myprofile: () => HOST + User + 'me'
    }
    

}

