const HOST = 'http://localhost:8080/api/v1/'

const Article = 'articles/'
<<<<<<< HEAD
const ArticleReply = 'articlesReply/'
=======
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
const Auth = 'auth/login'
const Study = 'study/'
const User = 'users'

<<<<<<< HEAD


export default{
    accounts:{
        login: () => HOST + Auth,
        // logout: () =>
    },
    article: {
    article_create: () => HOST + Article,
    article_rud: id => HOST + Article + `${id}`,
    article_list: () => HOST + Article + 'list'
    },
    articles_reply:{
        reply_cr: () => HOST + ArticleReply,
        reply_ud: id => HOST + ArticleReply + `${id}`,

=======
export default{
    auth_login:{
    login: () => HOST + Auth
    },
    article: {
    article_create: () => HOST + Article
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
    },
    study: {
        study_create: () => HOST + Study + 'create',
        study_remove: id => HOST + Study + 'remove/' + `${id}`,
        study_search: id => HOST + Study + 'search/' + `${id}`,
        study_update: () => HOST + Study + 'update'
    },
    user: {
        user_signup: () => HOST + User,
        user_rud: userId => HOST + User + `${userId}`,
        user_myprofile: () => HOST + User + 'me'
    }
    
    

}

