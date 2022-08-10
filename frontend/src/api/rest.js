const HOST = "http://localhost:8083/api/v1/";
const COMP = "https://api.jdoodle.com/v1/";

const Article = "articles/";
const ArticleReply = "articlesReply";
const Auth = "auth/login";
const Study = "study/";
const User = "users";
const Member = "member/";
const Calendar = "calendar/";
const StudyArticles = "studyArticles/";

export default {
  accounts: {
    login: () => HOST + Auth,
  },
  article: {
    article_cud: () => HOST + Article,
    article_read: (id) => HOST + Article + `?id=${id}`,
    article_list: (page) => HOST + Article + "list" + `?page=${page}`,
  },
  articles_reply: {
    reply_crud: () => HOST + ArticleReply,
  },
  study: {
    study_create: () => HOST + Study + "create",
    study_remove: (id) => HOST + Study + "remove/" + `${id}`,
    study_search: (id) => HOST + Study + "search/" + `${id}`,
    study_update: () => HOST + Study + "update",
    my_study_list: () => HOST + Study + Member + "mystudylist",
    auth_study_list: () => HOST + Study + Member + "studylistA3",
    study_join: (study_pk) =>
      HOST + Study + Member + "joinstudy/" + `${study_pk}`,
    study_join_list: (study_pk) =>
      HOST + Study + Member + "joinlist/" + `${study_pk}`,
    study_join_agree: () => HOST + Study + Member + "joinagree",
    study_join_delete: (id) => HOST + Study + Member + "joindelete/" + `${id}`,
    study_member_list: (study_pk) =>
      HOST + Study + Member + "studymemberlsit/" + `${study_pk}`, // 오타 불편
    study_member_out: () => HOST + Study + Member + "studymemberout",
    compile: () => COMP + "/execute",
    open_study: () => HOST + Study + Member + "publicstudylist",
  },
  studyArticle: {
    studyArticles: () => HOST + "studyArticles",
    studyArticleList: () => HOST + StudyArticles + "list",
    studyArticleReply: () => HOST + "studyArticlesReply",
  },
  user: {
    user: () => HOST + User,
    user_check: (userId) => HOST + User + `/{userId}?id=${userId}`, // pk값이 아닌 로그인 할 때 그 id
    user_myprofile: () => HOST + User + "/me",
    user_passwordupdate: () => HOST + User + "/pw",
    user_findid: (useremail, name) =>
      HOST + User + `/findID?email=${useremail}&name=${name}`,
    user_findpw: () => HOST + User + "/findpw",
    user_email: (userEmail) => HOST + User + `/email?email=${userEmail}`,
    get_user:(user_id)=> HOST + User + `/${user_id}`,
  },

  calendar: {
    calendar_list: (studyPk) =>
      HOST + Calendar + "list" + `?studyPk=${studyPk}`,

    create_calendar: () => HOST + Calendar,
    select_calendar: (id) => HOST + Calendar + `?id=${id}`,
    update_calendar: () => HOST + Calendar,
    delete_calendar: () => HOST + Calendar,
  },
};
