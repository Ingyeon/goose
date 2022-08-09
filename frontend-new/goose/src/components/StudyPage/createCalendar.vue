<template>
  <transition name="modal-fade">
    <div class="modal-backdrop">
      <div class="modal">
        <header class="modal-header">
          <slot name="header">
            캘린더 작성!
            <button type="button" class="btn-close" @click="close()">x</button>
          </slot>
        </header>
        <div class="modal-body">
          <div class="input-Box">
            <label class="form-label">제목</label>
            <input v-model="calendar.title" type="text" />
          </div>
          <div class="input-Box">
            <label class="form-label">내용</label>
            <textarea v-model="calendar.content"></textarea>
          </div>
          <div class="input-Box">
            <label class="form-label">시작 시간</label>
            <input id="start-time" v-model="time" type="datetime-local" />
          </div>
          <div class="input-Box">
            <label class="form-label">종료 시간</label>
            <input v-model="calendar.end" type="datetime-local" />
          </div>
        </div>
        <footer class="modal-footer">
          <slot name="footer">
            <button
              type="button"
              class="btn-green"
              @click="createCalendar(), close()"
            >
              작성
            </button>
            <button type="button" class="btn-green" @click="close()">
              취소
            </button>
          </slot>
        </footer>
      </div>
    </div>
  </transition>
</template>

<script>
import { reactive } from "@vue/reactivity";
import { useStore } from "vuex";

export default {
  props: {
    time: String,
  },
  components: {},
  setup() {
    console.log(123);
    const store = useStore();

    // const selectedStudy = computed(() => store.getters.selectedStudy);
    const calendar = reactive({
      title: "",
      content: "",
      start: "",
      end: "",
      study_pk: store.getters.selectedStudy.id,
    });

    const createCalendar = () => {
      calendar.start = document.getElementById("start-time").value;
      // calendar.start += "+09:00";
      // if (calendar.end != "") calendar.end += "+09:00";
      console.log(calendar.start);
      store.dispatch("createCalendar", calendar);
      calendar.title = "";
      calendar.content = "";
      calendar.start = "";
      calendar.end = "";
    };

    return { createCalendar, calendar };
  },

  methods: {
    close: function () {
      console.log(this.time);
      this.$emit("close");
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.modal-fade-enter,
.modal-fade-leave-active {
  opacity: 0;
}

.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity 0.5s ease;
}
.modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.3);

  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  background: #ffffff;
  box-shadow: 2px 2px 20px 1px;
  overflow-x: auto;
  display: flex;
  flex-direction: column;
  width: 50%;
  height: 50%;
}

.modal-header,
.modal-footer {
  padding: 15px;
  display: flex;
}

.modal-header {
  border-bottom: 1px solid #eeeeee;
  color: #4aae9b;
  justify-content: space-between;
}

.modal-footer {
  border-top: 1px solid #eeeeee;
  justify-content: flex-end;
}

.modal-body {
  position: relative;
  padding: 20px 10px;
}

.btn-close {
  border: none;
  font-size: 20px;
  padding: 20px;
  cursor: pointer;
  font-weight: bold;
  color: #4aae9b;
  background: transparent;
}

.btn-green {
  color: white;
  background: #4aae9b;
  border: 1px solid #4aae9b;
  border-radius: 2px;
}
</style>
