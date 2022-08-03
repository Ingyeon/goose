<template>
<div>
    <!-- <div class="container">
        <div class="title">
            <h1 class="fw-bold">Profile</h1>
        </div> -->
         <div class="change-btn">
            <input type="button" value="프로필 변경" @click="changeProfile">
        </div> 
        <!-- <div class="container">
            <div class="row">
                <div class="user-img col-4">
                    <img :src="state.photo" alt="대표사진">
                    https://i.imgur.com/cMy8V5j.png
                </div>
                <div class="user-detail col-8">
                    <div class="user-information">자기소개글</div>
                    <div class="user-attendance">출석일</div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="card">
                <div class="card-title">참여 스터디</div>
            </div>
        </div>
    </div>     -->
    <!-- {{state.photo}} -->
    <div class="container mt-5">
        <div class="wrapper">
            <div class="left">
                <img :src="state.profilephoto" alt="user" width="200">
                <!-- <img :src="state.photo" alt=""> -->
                <h4>{{state.name}}</h4>
                <p>{{state.info}}</p>
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
                        <h4>Phone</h4>
                            <p>0001-213-998761</p>
                    </div>
                    </div>
                </div>
            
            <div class="projects">
                    <h3>Projects</h3>
                    <div class="projects_data">
                        <div class="data">
                            <h4>Recent</h4>
                            <p>Lorem ipsum dolor sit amet.</p>
                        </div>
                        <div class="data">
                        <h4>Most Viewed</h4>
                            <p>dolor sit amet.</p>
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
import { reactive,computed } from 'vue'
import { useStore } from 'vuex'
// import router from '@/router'
// import assets from '@/assets'
import profile1 from '../../assets/profile1.png'
import profile2 from '../../assets/profile2.jpg'
import profile4 from '../../assets/profile4.png'
import { useRouter } from 'vue-router'
// import 
export default {
    setup() {
        const store = useStore()
        const router = useRouter()
        const state = reactive({
            id: store.getters.loginUser.userId,
            name : store.getters.loginUser.name,
            info : store.getters.loginUser.info,
            email : store.getters.loginUser.email,
            photo : store.getters.loginUser.photo,
            interest : store.getters.loginUser.interest,
            profilephoto : ''
        })
        console.log(state.photo)
        const changeProfile = function() {
            router.push({name:'ProfileUpdate'})
            console.log(state.photo)
        }
        const savePhoto = function() {
            if (state.photo === '../../assets/profile1.png') {
              state.profilephoto = profile1
            } 
            else if (state.photo === '../../assets/profile2.jpg') {
              state.profilephoto = profile2
            } else {
              state.profilephoto = profile4
            }
         }
        savePhoto()
        return {
            store,
            router,
            state,
            changeProfile,
            // savePhoto
            // getImgUrl
            // savePath,
        }
    },
}
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
  color: #fff;
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
  background: linear-gradient(to right,#01a9ac,#01dbdf);
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
</style>