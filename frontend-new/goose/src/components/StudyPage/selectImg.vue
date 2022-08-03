<template>
    <div class="input-Box">
        <label for="imagename" class="form-label">대표사진</label>
        
        <ul class="d-flex justify-content-evenly">
            <li>
                <input name="profile" type="radio" value="../../assets/study1.png" id="../../assets/study1.png">
                <label for="../../assets/study1.png"><img @click="selectImg('../../assets/study1.png')" src="../../assets/study1.png"></label>
            </li>
            <li>
                <input name="profile" type="radio" value="../../assets/study2.png" id="../../assets/study2.png">
                <label for="../../assets/study2.png"><img @click="selectImg('../../assets/study2.png')" src="../../assets/study2.png"></label>
            </li>
            <li>
                <input name="profile" type="radio" value="../../assets/study3.jpg" id="../../assets/study3.jpg">
                <label for="../../assets/study3.jpg"><img @click="selectImg('../../assets/study3.jpg')" src="../../assets/study3.jpg"></label>
            </li>
            
        </ul>
    </div>
<!-- <img @click.prevent="selectImg('https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Anser_cygnoides.jpg/1280px-Anser_cygnoides.jpg')"  src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Anser_cygnoides.jpg/1280px-Anser_cygnoides.jpg" alt="">
<img @click.prevent="selectImg('https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Domestic_Greylag.jpg/1024px-Domestic_Greylag.jpg')" src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Domestic_Greylag.jpg/1024px-Domestic_Greylag.jpg" alt=""> -->
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex"
export default {
    setup(){
        const store = useStore()
        const selectImg = (url)=>{
            store.dispatch('selectImg',url)
        }
        return {selectImg,}
    }
}
</script>

<style scoped>
ul {
  list-style-type: none;
}

li {
  display: inline-block;
}

input[type="radio"][id^="../../"] {
  display: none;
}

label {
  border: 1px solid #fff;
  padding: 10px;
  display: block;
  position: relative;
  margin: 10px;
  cursor: pointer;
}

label:before {
  background-color: white;
  color: white;
  content: " ";
  display: block;
  border-radius: 50%;
  border: 1px solid grey;
  position: absolute;
  top: -5px;
  left: -5px;
  width: 25px;
  height: 25px;
  text-align: center;
  line-height: 28px;
  transition-duration: 0.4s;
  transform: scale(0);
}

label img {
  height: 250px;
  width: 250px;
  transition-duration: 0.2s;
  transform-origin: 50% 50%;
}

:checked + label {
  border-color: #ddd;
}

:checked + label:before {
  content: "✓";
  background-color: grey;
  transform: scale(1);
}

:checked + label img {
  transform: scale(0.9);
  /* box-shadow: 0 0 5px #333; */
  z-index: -1;
}
</style>