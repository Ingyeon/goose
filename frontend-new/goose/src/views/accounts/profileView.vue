<template>
<div>
    <div class="container mt-5">
        <div class="wrapper">
            <div   class="left">
                <img :src="state.profilephoto" alt="user" width="200">
                <!-- <img :src="state.photo" alt=""> -->
                <h4>{{state.name}}</h4>
                <p>관심언어: {{state.interest}}</p>
                <div class="change-btn">
                  <input type="button" value="프로필 변경" @click="changeProfile">
                </div>
            </div>
            <div class="right">
                <div class="info">
                    <h3>Information</h3>
                    <div class="info_data">
                        <div class="data">
                            <h4>Email</h4>
                            <p>{{state.email}}</p>
                        </div>
                        <div class="data">
                        <h4>자기소개</h4>
                            <p>{{state.info}}</p>
                    </div>
                    </div>
                </div>
            
            <div class="projects">
                    <h3>Article</h3>
                    <div class="projects_data">
                        <div class="data">
                            <h4>작성한 글</h4>
                            <MyArticles/>
                        </div>
                        <div class="data">
                        <h4>댓글 단 글</h4>
                        <tr v-for="row in replyArticle" :key="row">
                          <td>{{row}}</td>
                        </tr>
                    </div>
                    </div>
                </div>
            
                <div class="social_media">
                    <ul>
                    <li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
                    <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                    <li><a href="#"><i class="fab fa-instagram"></i></a></li>
                </ul>
            </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import myArticles from './components/myArticlesList.vue'
import { reactive,computed } from 'vue'
import { useStore } from 'vuex'
// import router from '@/router'
// import assets from '@/assets'
import profile1 from '../../assets/profile1.png'
import profile2 from '../../assets/profile2.jpg'
import profile4 from '../../assets/profile4.png'
import { useRouter } from 'vue-router'
import MyArticles from './components/myArticlesList.vue'
// import 

export default {
  components: {myArticles},
    setup() {
        const store = useStore();
        const router = useRouter();
        const state = reactive({
            id: store.getters.loginUser.userId,
            name: store.getters.loginUser.name,
            info: store.getters.loginUser.info,
            email: store.getters.loginUser.email,
            photo: store.getters.loginUser.photo,
            interest: store.getters.loginUser.interest,
            profilephoto: "",
            form: []
        });
        // 내가 댓글 단 글 확인
        // 1. 게시글 및 댓글 리스트를 불러온다.
        // 2. 댓글의 user_pk와 profile 유저의 id가 일치하면 단일 게시물 정보 api 불러옴
        // 3. form에 저장된 댓글 데이터의 article_pk와 게시글 리스트의 개별 게시글 article_pk 비교
        // 4. 같을 경우 저장한다. 
        // 디버깅해야 하는 버그
        // 1. id 값은 제대로 전달되는데 commit 되는 값이 전부 동일하게 들어감 => 원인 파악도 못함
        const myReplies = async function () {
            await store.dispatch("fetchArticles", 1); // 임의값으로 1 줌
            await store.dispatch("fetchReplies", { article_pk: null, reply_page: 1 });
            var item = null;
            for (item of store.getters.replies) {
                if (item.user_pk == store.getters.loginUser.id) {
                    console.log(item.article_pk);
                    store.dispatch("fetchArticle", item.article_pk);
                    console.log(store.getters.article.title);
                    state.form.push(store.getters.article.title);
                }
            }
          }

          const set = new Set(state.form)
          store.commit('SET_TEST',set)

        const replyArticle = computed(() => store.getters.test)
        myReplies()

        console.log(state.photo)
        const changeProfile = function() {
            router.push({name:'ProfileUpdate'})
            console.log(state.photo)
        }
        const savePhoto = function() {
            if (store.getters.loginUser.photo === '../../assets/profile1.png') {
              console.log("야구")
              state.profilephoto = profile1
            } 
            else if (store.getters.loginUser.photo === '../../assets/profile2.jpg') {
              console.log("커피")
              state.profilephoto = profile2
            } else {
              console.log("노말")
              state.profilephoto = profile4
            }
         }
        savePhoto()
        const photo = computed(()=> store.getters.loginUser.photo)
        return {
            store,
            router,
            state,
            changeProfile,
            savePhoto,
            replyArticle,
            // getImgUrl
            // savePath,
            photo,
        }
    },

    watch:{
      photo:{
        handler(){
          this.savePhoto()
        }
    },
    components: { MyArticles }
}}


</script>

<style scoped>
    /* .change-btn {
    display: flex;
    justify-content: end;
    }
    .change-btn input[type="button"] {
    background: #fff;
    color: #666;
    cursor: pointer;
    width: 100px;
    margin-bottom: 20px;
    font-weight: 600;
    text-align: center;
    } */
@import url('https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap');

*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  list-style: none;
  font-family: 'Josefin Sans', sans-serif;
}

body{
   background-color: #f3f3f3;
}

.wrapper{
  /* position: absolute; */
  /* top: 50%;
  left: 50%; */
  /* transform: translate(-50%,-50%); */
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  align-items: center;
  box-shadow: 0 1px 20px 0 rgba(69,90,100,.08);
}

.wrapper .left{
  width: 45%;
  background: linear-gradient(to right,#ffd700,#ffd700);
  padding: 30px 25px;
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
  text-align: center;
  color: #000;
}

.wrapper .left img{
  border-radius: 5px;
  margin-bottom: 10px;
}

.wrapper .left h4{
  margin-bottom: 10px;
}

.wrapper .left p{
  font-size: 12px;
}

.wrapper .right{
  width: 65%;
  background: #fff;
  padding: 30px 25px;
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}

.wrapper .right .info,
.wrapper .right .projects{
  margin-bottom: 25px;
}

.wrapper .right .info h3,
.wrapper .right .projects h3{
    margin-bottom: 15px;
    padding-bottom: 5px;
    border-bottom: 1px solid #e0e0e0;
    color: #353c4e;
  text-transform: uppercase;
  letter-spacing: 5px;
}

.wrapper .right .info_data,
.wrapper .right .projects_data{
  display: flex;
  justify-content: space-between;
}

.wrapper .right .info_data .data,
.wrapper .right .projects_data .data{
  width: 45%;
}

.wrapper .right .info_data .data h4,
.wrapper .right .projects_data .data h4{
    color: #353c4e;
    margin-bottom: 5px;
}

.wrapper .right .info_data .data p,
.wrapper .right .projects_data .data p{
  font-size: 13px;
  margin-bottom: 10px;
  color: #919aa3;
}

.wrapper .social_media ul{
  display: flex;
}

.wrapper .social_media ul li{
  width: 45px;
  height: 45px;
  background: linear-gradient(to right,#ffd700,#ffd700);
  margin-right: 10px;
  border-radius: 5px;
  text-align: center;
  line-height: 45px;
}

.wrapper .social_media ul li a{
  color :#fff;
  display: block;
  font-size: 18px;
}
.change-btn {
  position: relative;
  bottom: 300px;
  left:100%;
}
.change-btn input[type="button"] {
background: #ffd700;
cursor: pointer;
width: 100px;
margin-bottom: 20px;
font-weight: 600;
text-align: center;
border-radius: 80px 40px;
}
</style>