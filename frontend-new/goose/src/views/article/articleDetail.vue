<!-- <template>

    <h3>{{form.title}}</h3>

</template>

<script>

import {reactive} from 'vue'
import {useStore} from 'vuex'

export default{

    setup(){
        const store = useStore()
        const state = reactive({

            form: {
            recruitment: '',
            category: '',
            title: '',
            content: '',
            state: '모집중',
            }
        })
        const articleDetail = function(){
            store.dispatch(' ')
        }
    }
}
</script> -->
