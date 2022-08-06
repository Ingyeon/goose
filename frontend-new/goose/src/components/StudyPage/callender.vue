<template>
<div class="container">
  <div class="d-flex justify-content-between">
    <h4>스터디 일정관리</h4>
    <button type="button" class="btn btn-3 hover-border-3" data-bs-toggle="modal" data-bs-target="#callenderModal">
        전체보기
    </button>
  </div>
  <!-- 전체보기 누를경우 보이는 내용 -->
  <div class="modal fade" id="callenderModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-fullscreen modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">캘린더</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <FullCalendar :options="calendarOptionsM" />
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            Close
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- <div
    data-pym-src="https://www.jdoodle.com/plugin"
    data-language="java"
    data-version-index="4"
    data-libs="mavenlib1, mavenlib2"
  ></div> -->
  <br>
  <div class="container box">
    <FullCalendar :options="calendarOptionsW" />
  </div>
</div>
</template>

<script>
import "@fullcalendar/core/vdom"; // solves problem with Vite
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import koLocale from "@fullcalendar/core/locales/ko";
import { useStore } from "vuex";

export default {
  setup() {
    const store = useStore();
    const events_set = function () {
      store.dispatch("fetchCalendars", 6);
      const events = JSON.parse(JSON.stringify(store.getters.events));
      console.log(events);
    };
    events_set();
  },
  // mounted() {
  //   let recaptchaScript = document.createElement("script");
  //   recaptchaScript.setAttribute(
  //     "src",
  //     "https://www.jdoodle.com/assets/jdoodle-pym.min.js"
  //   );
  //   document.head.appendChild(recaptchaScript);
  // },
  components: {
    FullCalendar, // make the <FullCalendar> tag available
  },
  data() {
    const store = useStore();
    console.log(123, JSON.parse(JSON.stringify(store.getters.events)));
    return {
      calendars: [],
      calendarOptionsW: {
        plugins: [dayGridPlugin, interactionPlugin],
        // initialView: "dayGridWeek",
        initialView: "dayGrid", // the name of a generic view
        duration: { weeks: 2 },
        selectable: true,
        dateClick: this.handleDateClick,
        eventClick: this.handleEventClick,
        height: 500,
        locale: koLocale,
        timeZone: "local",
        events: JSON.parse(JSON.stringify(store.getters.events)),
      },
      calendarOptionsM: {
        plugins: [dayGridPlugin, interactionPlugin],
        initialView: "dayGridMonth",
        selectable: true,
        height: 700,
        dateClick: this.handleDateClick,
        eventClick: this.handleEventClick,

        locale: koLocale,
        timeZone: "local",
        events: JSON.parse(JSON.stringify(store.getters.events)),
      },
    };
  },
  methods: {
    handleDateClick: function (arg) {
      alert("date click! " + arg.dateStr);
    },
    handleEventClick: function (arg) {
      console.log(arg.event);
      alert("event click! " + arg.event.startStr + arg.event.id);
    },
  },
};
</script>

<style scoped>
h4 {
    font-weight:bold;
}
* {
    font-family: "NanumSquare", sans-serif;
}
:active, :hover, :focus {
outline: 0!important;
outline-offset: 0;
}
::before,
::after {
  position: absolute;
  content: "";
}
.btn {
  position: relative;
  display: inline-block;
  width: auto; height: auto;
  background-color: transparent;
  border: none;
  cursor: pointer;
  margin: 0px 25px 15px;
  min-width: 150px;
}
  .btn span {         
    position: relative;
    display: inline-block;
    font-size: 14px;
    font-weight: bold;
    letter-spacing: 2px;
    text-transform: uppercase;
    top: 0; left: 0;
    width: 100%;
    padding: 15px 20px;
    transition: 0.3s;
  }
.btn.hover-border-3::before,
.btn.hover-border-3::after {
  width: 0%; height: 0%;
  opacity: 0;
  transition: width 0.2s 0.15s linear, height 0.15s linear, opacity 0s 0.35s;
}
.btn.hover-border-3::before {
  top: 0; right: 0;
  border-top: 1px solid rgb(28, 31, 30);
  border-left: 1px solid rgb(28, 31, 30);
}
.btn.hover-border-3::after {
  bottom: 0; left: 0;
  border-bottom: 1px solid rgb(28, 31, 30);
  border-right: 1px solid rgb(28, 31, 30);
}
.btn.hover-border-3:hover::before,
.btn.hover-border-3:hover::after {
  width: 100%; height: 99%;
  opacity: 1;
  transition: width 0.2s linear, height 0.15s 0.2s linear, opacity 0s;   
}

.container.box{
    background-color: #f0f0f1;
    margin-top: 1rem;
    padding-top: 1rem;
    height:85%;
    border-radius: 10px;
    }
</style>
