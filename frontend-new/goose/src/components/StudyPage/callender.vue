<template>
  <h1>Callender.vue</h1>

  <!-- 전체보기 누를경우 보이는 내용 -->
  <div
    class="modal fade"
    id="callenderModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-fullscreen modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">캘린더</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <FullCalendar :options="calendarOptionsM" />
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
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
  <FullCalendar :options="calendarOptionsW" />

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

<style></style>
