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
    article_read: id => HOST + Article + `?id=${id}`,
    article_delete: () => HOST + Article,
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
        auth_study_list: () => HOST + Study + Member + 'studylistA3',
        study_join: (study_pk) => HOST + Study + Member + 'joinstudy/'+ `${study_pk}`,
        study_join_list: (study_pk) => HOST + Study + Member + 'joinlist/'+ `${study_pk}`,
        study_join_agree: () => HOST + Study + Member + 'joinagree',
        study_join_delete: (id) => HOST + Study + Member + 'joindelete/'+ `${id}`,
        study_member_list: (study_pk) => HOST + Study + Member + 'studymemberlsit/'+`${study_pk}` // 오타 불편
    },
    user: {
        user: () => HOST + User,
        user_check: userId => HOST + User + `/{userId}?id=${userId}`,  // pk값이 아닌 로그인 할 때 그 id
        user_myprofile: () => HOST + User + '/me'
    }
    

}

