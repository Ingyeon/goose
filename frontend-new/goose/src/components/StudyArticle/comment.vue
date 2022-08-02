<template>
<hr>
<commentItem v-for="item in commentList" :key="item.id" :item="item"/>
<!-- 댓글 작성 -->
<createComment />
</template>

<script>
import createComment from  "@/components/StudyArticle/createComment.vue"
import commentItem from "@/components/StudyArticle/commentItem.vue"
import { useStore } from "vuex"
import { reactive } from '@vue/reactivity'
import { computed } from '@vue/runtime-core'
export default {
    components:{
        createComment,commentItem
    },
    setup(){
        const store = useStore()
        const page = 1
        const state = reactive({
            credential :{
                articlePk:store.getters.selectedArticle.id,
                id:null,
                page:page
            }
        })

        const fetchCommentList = ()=>{
            console.log("코멘트 패치중")
            store.dispatch("getComment",state.credential)
        }
        fetchCommentList()

        const commentList = computed(()=>store.getters.studyArticleCommentList)
        return { commentList }
    }
}
</script>

<style>

</style>