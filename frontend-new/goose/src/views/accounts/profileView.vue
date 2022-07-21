<template>
    <div class="container">
        <div class="title">
            <h1 class="fw-bold">Profile</h1>
        </div>
        <div class="change-btn">
            <input type="button" value="사진변경" @click="changePicture">
            <input type="button" value="프로필 변경" @click="changeProfile">
        </div>
        <div class="container">
            <div class="row">
                <div class="user-img col-4">
                    <img src="" alt="대표사진">
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
    </div>    

</template>

<script>
import {reactive} from 'vue'
export default {
    setup() {
        
    },
}
</script>

<style>
    .change-btn {
    display: flex;
    justify-content: space-between;
    }
    .change-btn input[type="button"] {
    background: #fff;
    color: #666;
    cursor: pointer;
    width: 100px;
    margin-bottom: 20px;
    font-weight: 600;
    text-align: center;
    }

</style>