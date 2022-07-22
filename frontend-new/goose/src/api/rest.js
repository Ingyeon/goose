const HOST = 'http://localhost:8083/api/v1/'

const Article = 'articles/'
const Auth = 'auth/login'
const Study = 'study/'
const User = 'users'

export default{
    auth_login:{
    login: () => HOST + Auth
    },
    article: {
    article_create: () => HOST + Article
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

