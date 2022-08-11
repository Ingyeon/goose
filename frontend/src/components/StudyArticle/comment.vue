<template>
<commentItem v-for="item in commentList" :key="item.id" :item="item"/>
<!-- 댓글 작성 -->
<hr>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <li v-if="data.page != 1" @click="pageDown" class="page-item mx-2" style="cursor:pointer"><b-icon-arrow-left></b-icon-arrow-left></li>
        <li v-if="commentList.length == 5" @click="pageUp" class="page-item mx-2" style="cursor:pointer"><b-icon-arrow-right></b-icon-arrow-right></li>
    </ul>
</nav>
<createComment />
<!-- pagination -->
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
            page:store.getters.commentPage
        })
        const commentPage = computed(()=>store.getters.commentPage)
        const changePage = ()=>{data.page=store.getters.commentPage}
        const pageUp = ()=>{
            if (store.getters.studyArticleCommentList.length == 5){
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
        const selectedArticle = computed(()=>store.getters.selectedArticle)
        return { data,commentList,pageDown,pageUp,selectedArticle,commentPage,changePage }
    },

     watch: {
        selectedArticle: {
            handler() {
                this.data.page = 1;
            },
        },
        commentPage: {
            handler(){
                this.changePage()
            }
        }
  },
}
</script>

<style scoped>

</style>