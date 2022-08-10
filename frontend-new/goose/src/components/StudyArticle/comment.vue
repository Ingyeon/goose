<template>
<commentItem v-for="item in commentList" :key="item.id" :item="item"/>
<!-- 댓글 작성 -->
<hr>
<createComment />
<!-- pagination -->
<hr>
  <nav aria-label="Page navigation example">
  <ul class="pagination">
    <li @click="pageDown" class="page-item"><a class="page-link" href="#">Previous</a></li>
    <li @click="pageUp" class="page-item"><a class="page-link" href="#">Next</a></li>
  </ul>
</nav>

</template>

<script>
import createComment from  "@/components/StudyArticle/createComment.vue"
import commentItem from "@/components/StudyArticle/commentItem.vue"
import { useStore } from "vuex"
import { reactive } from '@vue/reactivity'
import { computed } from '@vue/runtime-core'
import _ from 'lodash'
export default {
    components:{
        createComment,commentItem
    },
    setup(){
        const store = useStore()
        const data = reactive({
            page:1,
        })

        const pageUp = ()=>{
            if (!_.isEmpty(store.getters.studyArticleCommentList)){
                data.page++;
                fetchCommentList();  
                }
            }

        const pageDown = ()=>{
        if(data.page>1){
            data.page--;
            fetchCommentList();
            }
        }

        const fetchCommentList = ()=>{
            store.dispatch("getComment",{articlePk:store.getters.selectedArticle.id, id:null, page:data.page})
        }
        fetchCommentList()

        const commentList = computed(()=>store.getters.studyArticleCommentList)
        return { commentList,pageDown,pageUp }
    }
}
</script>

<style scoped>

</style>