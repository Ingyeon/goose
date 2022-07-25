<template>
<h3>스터디 만들기</h3>
<form>
<select v-model="state.credential.open" class="form-select" aria-label="Default select example">
  <option selected>공개 여부</option>
  <option value="0">공개</option>
  <option value="1">비공개</option>
</select>
<select v-model="state.credential.maxMember" class="form-select" aria-label="Default select example">
  <option selected>총원</option>
  <option value="1">1명</option>
  <option value="2">2명</option>
  <option value="3">3명</option>
  <option value="4">4명</option>
  <option value="5">5명</option>
  <option value="6">6명</option>
</select>
<select v-model="state.credential.category" class="form-select" aria-label="Default select example">
  <option selected>분류</option>
  <option value="1">알고리즘</option>
  <option value="2">토익</option>
  <option value="3">칵테일</option>
  <option value="3">면접</option>
</select>
<div class="form-floating">
  <textarea v-model="state.credential.title" class="form-control" placeholder="Leave a comment here" id="floatingTextarea"></textarea>
  <label for="floatingTextarea">스터디 이름</label>
</div>
<!-- <div class="form-floating">
  <textarea v-model="" class="form-control" placeholder="Leave a comment here" id="floatingTextarea"></textarea>
  <label for="floatingTextarea">스터디 소개</label>
</div> -->
<div class="form-floating">
  <input v-model="state.credential.password" type="password" class="form-control" id="floatingPassword" placeholder="Password">
  <label for="floatingPassword">Password</label>
</div>
<div class="form-floating">
  <textarea v-model="state.credential.image" class="form-control" placeholder="Leave a comment here" id="floatingTextarea"></textarea>
  <label for="floatingTextarea">대표 사진 url</label>
</div>
  <button @click.prevent="createStudy(state.credential)" type="submit" class="btn btn-primary">Submit</button>
</form>

{{ state.credential }}
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex"

export default {
    setup(){
        const store = useStore()
        const state = reactive({
            credential:{
                category: "",

                image : "",
                maxmember:1,
                member: 1,
                open: 0,
                password: "",
                title : "",
                url_conf:"",
                url_page:"",
            }
        })
        const createStudy = ()=>{
            console.log(state.credential.category)
            store.dispatch('createStudy',state.credential)
            // store.dispatch('createStudy',{
            //     "category": state.credential.category,
            //     // "id": "",
            //     "image": state.credential.image,
            //     "maxmember": state.credential.maxmember,
            //     "member": 1,
            //     "open": state.credential.open,
            //     "password": state.credential.password,
            //     "title": state.credential.title,
            //     "url_conf": state.credential.url_conf,
            //     "url_page": state.credential.url_page,
            //     // "userId": "",
            // })
        }
        return { createStudy, state}
    }
}
</script>

<style>

</style>