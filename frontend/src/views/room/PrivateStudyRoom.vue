<template>
  <div id="main" @input="handleScroll">
    <div id="main-container" class="d-flex">
      <div id="session-center">
        <div id="session" v-if="session">
          <div id="session-header" class="d-flex">
            <h1 id="session-title">{{ this.roomName }}</h1>
            <!-- 방 제목 -->
            <div
              id="session-timer"
              class="text-center d-flex"
              style="margin-left: 5%; margin-top: 10px"
            >
              <div>
                <h3 id="session-time">
                  {{ hours }} : {{ minutes }} : {{ seconds }}
                </h3>
              </div>

              <div id="timerBtn" v-if="true" class="d-flex">
                <b-button
                  v-if="!timer"
                  @click="startTimer()"
                  style="
                    margin-left: 20px;
                    height: 38px;
                    background-color: #2e9afe;
                  "
                  >시작</b-button
                >
                <b-button
                  v-else
                  variant="danger"
                  @click="stopTimer"
                  style="height: 38px; margin-left: 20px"
                >
                  정지
                </b-button>
                <b-button
                  v-if="resetButton"
                  @click="resetTimer"
                  style="height: 38px; background-color: #6c757d"
                >
                  종료
                </b-button>
                <b-button
                  v-if="!timer"
                  @click="editTimer"
                  style="height: 38px; background-color: #2e9afe"
                >
                  시간 설정
                </b-button>
                <div
                  v-if="edit"
                  class="d-flex justify-content-center mt-1 d-flex"
                >
                  <b-form-input
                    class="p-3 text-center"
                    type="text"
                    v-model="inputHour"
                    placeholder="시"
                    style="
                      width: 80px;
                      height: 40px;
                      margin-left: 10px;
                      margin-top: -5px;
                    "
                  />
                  <b-form-input
                    class="p-3 text-center"
                    type="text"
                    v-model="inputMin"
                    placeholder="분"
                    style="
                      width: 80px;
                      height: 40px;
                      margin-top: -5px;
                      margin-left: 1px;
                    "
                  />
                  <b-form-input
                    class="p-3 text-center"
                    type="text"
                    v-model="inputSec"
                    placeholder="초"
                    style="
                      width: 80px;
                      height: 40px;
                      margin-top: -5px;
                      margin-left: 1px;
                    "
                  />
                </div>
              </div>
            </div>
          </div>
          <div id="main-video-session">
            <div id="main-video" style="width: 60%; margin: 0 auto">
              <UserVideo
                v-if="mainOnOff"
                :stream-manager="mainStreamManager"
                :mainStream="true"
                @click="deleteMainVideoStreamManager()"
              />
            </div>
            <div id="video-container" class="d-flex flex-wrap row">
              <!-- 참가자 화면 -->
              <UserVideo
                class="col-md-4"
                v-if="!isScreenShared"
                :stream-manager="publisher"
                @click="updateMainVideoStreamManager(publisher)"
              />
              <!--자기 -->
              <UserVideo
                class="col-md-4"
                v-for="sub in subscribers"
                :key="sub.stream.connection.connectionId"
                :stream-manager="sub"
                @click="updateMainVideoStreamManager(sub)"
              />
              <!-- 다른 참가자 -->
            </div>
          </div>
        </div>

        <div id="session-footer-wrap" class="d-flex justify-content-center">
          <div id="session-footer" v-if="session">
            <div class="session-footer_btn d-flex justify-content-center">
              <!-- microphone 버튼 설정 -->
              <div v-if="audio === true" class="buttomMenu">
                <button
                  class="btn btn-large btn-danger footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="muteAudio(), muteAudio2()"
                >
                  <i class="fa-solid fa-microphone"></i>
                  <span class="footerBtnText">{{ audioMsg }}</span>
                </button>
                <!-- 마이크 on/off 버튼 -->
              </div>
              <div v-else class="roomFun buttomMenu">
                <button
                  class="btn btn-large btn-default footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="muteAudio()"
                >
                  <i class="fa-solid fa-microphone-slash"></i>
                  <span class="footerBtnText">&nbsp{{ audioMsg }}</span></button
                ><!-- 마이크 on/off 버튼 -->
              </div>

              <!-- video 버튼 설정 -->
              <div v-if="video === true" class="buttomMenu">
                <button
                  class="btn btn-large btn-danger footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="muteVideo()"
                >
                  <i class="fa-solid fa-video"></i>
                  <span class="footerBtnText">&nbsp{{ videoMsg }}</span>
                </button>
              </div>
              <div v-else class="roomFun buttomMenu">
                <button
                  class="btn btn-large btn-default footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="muteVideo()"
                >
                  <i class="fa-solid fa-video-slash"></i>
                  <span class="footerBtnText">&nbsp{{ videoMsg }}</span>
                </button>
              </div>

              <!-- 화면공유 버튼 설정 -->
              <div v-if="sharing === true" class="buttomMenu">
                <button
                  class="btn btn-large btn-default footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="startScreenSharing()"
                >
                  <i class="fa-solid fa-tv"></i>
                  <span class="footerBtnText">&nbsp화면공유</span>
                </button>
                <!-- 나가기 버튼 -->
              </div>

              <!-- 화면공유 중지 버튼 설정 -->
              <div v-else class="buttomMenu">
                <button
                  class="btn btn-large btn-danger footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="leaveSessionForScreenSharing()"
                >
                  <i class="fa-solid fa-tv"></i>
                  <span class="footerBtnText">&nbsp공유중지</span>
                </button>
                <!-- 나가기 버튼 -->
              </div>

              <!-- 채팅 버튼 -->
              <div v-if="!asideRight" class="buttomMenu">
                <b-button
                  class="btn btn-large btn-default footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="asideRight = true"
                >
                  <i class="fa-solid fa-comment"></i>
                  <span class="footerBtnText">&nbsp채팅보기</span>
                </b-button>
              </div>
              <div v-else class="buttomMenu">
                <b-button
                  class="btn btn-large btn-default footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="asideRight = false"
                >
                  <i class="fa-solid fa-comment-slash"></i>
                  <span class="footerBtnText">&nbsp채팅닫기</span>
                </b-button>
              </div>

              <!-- 나가기 버튼 설정 -->
              <div class="buttomMenu">
                <button
                  class="btn btn-large btn-default footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="leaveSession()"
                >
                  <i class="fa-solid fa-door-closed"></i>
                  <span class="footerBtnText">&nbsp나가기</span>
                </button>
                <!-- 나가기 버튼 -->
              </div>
            </div>
          </div>
        </div>
        <!-- #session-footer-wrap -->
      </div>

      <!-- #session-center -->
      <transition-group name="fade">
        <div id="session-aside-right" v-if="session && asideRight">
          <div class="participant">
            <div class="right_label_participant">
              <span>참가자</span>
            </div>
            <div class="participant_list">
              <!-- 참가자 리스트 -->
              <UserList :stream-manager="publisher" />
              <UserList
                v-for="sub in subscribers"
                :key="sub.stream.connection.connectionId"
                :stream-manager="sub"
              />
            </div>
          </div>

          <!-- 채팅 시작 -->
          <div class="user_chat">
            <div class="right_label">
              <span>채팅</span>
            </div>
            <div class="chat">
              <div class="messages" v-html="messages" ref="messages"></div>

              <form class="chatFooter" onsubmit="return false">
                <input
                  class="chat_input"
                  id="msg"
                  type="text"
                  autocomplete="off"
                  placeholder="메세지를 입력하세요."
                  style="background-color: #fff"
                />
                <button id="submitBtn" type="submit" @click="sendMessage()">
                  전송
                </button>
              </form>
            </div>
          </div>
          <!-- 채팅 끝 -->
        </div>
      </transition-group>
      <!-- session-right -->
    </div>
    <MonacoYjs
      :language="language"
      @sendResult="setResult"
      v-bind:propcompile="propcompile"
      v-bind:propstdin="propstdin"
      @sendCodestdin="setCodestdin"
    />
    <div v-if="this.scrollPosition > 800" id="monaco-timer" class="d-flex">
      <div>
        <h3 id="session-time">{{ hours }} : {{ minutes }} : {{ seconds }}</h3>
      </div>
      <div id="timerBtn" v-if="true" class="d-flex">
        <b-button
          v-if="!timer"
          @click="startTimer()"
          style="margin-left: 20px; height: 38px; background-color: #2e9afe"
          >시작</b-button
        >
        <b-button
          v-else
          variant="danger"
          @click="stopTimer"
          style="height: 38px; margin-left: 20px"
        >
          정지
        </b-button>
        <b-button
          v-if="resetButton"
          @click="resetTimer"
          style="height: 38px; background-color: #6c757d"
        >
          종료
        </b-button>
        <b-button
          v-if="!timer"
          @click="editTimer"
          style="height: 38px; background-color: #2e9afe"
        >
          시간 설정
        </b-button>
        <div v-if="edit" class="d-flex justify-content-center mt-1 d-flex">
          <b-form-input
            class="p-3 text-center"
            type="text"
            v-model="inputHour"
            placeholder="시"
            style="
              width: 80px;
              height: 40px;
              margin-left: 10px;
              margin-top: -5px;
            "
          />
          <b-form-input
            class="p-3 text-center"
            type="text"
            v-model="inputMin"
            placeholder="분"
            style="
              width: 80px;
              height: 40px;
              margin-top: -5px;
              margin-left: 1px;
            "
          />
          <b-form-input
            class="p-3 text-center"
            type="text"
            v-model="inputSec"
            placeholder="초"
            style="
              width: 80px;
              height: 40px;
              margin-top: -5px;
              margin-left: 1px;
            "
          />
        </div>
      </div>
    </div>
    <!-- #main-container -->
    <div class="MonacoScroll">
      <button
        id="MonacoScroll"
        class="btn btn-large footerBtn"
        style="background-color: #e6e6e6; color: #424242"
        type="button"
        @click="scrollToDown()"
        v-if="this.scrollPosition < 500"
      >
        다 같이<br />코딩하기
      </button>
      <button
        id="MonacoScroll"
        class="btn btn-large footerBtn"
        style="background-color: #e6e6e6; color: #424242"
        type="button"
        @click="scrollToUp()"
        v-if="this.scrollPosition > 500"
      >
        화상화면으로
      </button>
    </div>
  </div>
</template>
<style scoped>
@import "@/assets/style/StudyRoom/video.css";
@import "@/assets/style/style.css";
@import "@/assets/style/StudyRoom/room.css";
</style>
<script>
import axios from "axios";
// import http from "@/util/http-common.js";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/openvidu/PrivateUserVideo";
import UserList from "@/components/openvidu/UserList";
import jwt_decode from "jwt-decode";
import { computed, reactive } from "vue";
import { useStore } from "vuex";
import MonacoYjs from "@/components/openvidu/MonacoYjs";
import { useRouter } from "vue-router";
import { mapMutations } from "vuex";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://i7c209.p.ssafy.io:4443";
const OPENVIDU_SERVER_SECRET = "MY_GOOSEGOOSE";

export default {
  name: "App",

  components: {
    UserVideo,
    UserList,
    MonacoYjs,
  },
  setup() {
    const store = useStore();
    const state = reactive({
      reloadCheck: store.getters.reloadCheck,
    });
    const router = useRouter();

    const selectedStudy = computed(() => store.getters.selectedStudy);
    const loginUser = computed(() => store.getters.loginUser);
    // const reloadCheck = computed(() => store.getters.reloadCheck);
    const flip = function () {
      if (state.reloadCheck == false) {
        state.reloadCheck = true;
        store.commit("SET_RELOADCHECK", state.reloadCheck);
        router.go();
      }
    };
    flip();
    return { selectedStudy, loginUser };
  },
  computed: {
    totalTime() {
      return (
        Number(this.inputHour * 3600) +
        Number(this.inputMin * 60) +
        Number(this.inputSec)
      );
    },
    hours() {
      const hours = Math.floor(this.time / 3600);
      return this.padTime(hours);
    },
    minutes() {
      const minutes = Math.floor((this.time - this.hours * 3600) / 60);
      return this.padTime(minutes);
    },
    seconds() {
      const seconds = this.time - (this.hours * 3600 + this.minutes * 60);
      return this.padTime(seconds);
    },
  },

  data() {
    return {
      propstdin: "",
      propcompile: "",
      stdin: "",
      resultCom: "",
      language: "python",
      scrollPosition: "",
      reload: false,
      monacococo: true,
      //방정보
      roomName: "none",
      roomUrl: "none",
      participant: "none",
      roomStudyNo: "none",
      studyMembers: "none",
      power: "0",

      // 화면 공유
      OVForScreenShare: undefined,
      sessionForScreenShare: undefined,
      sharingPublisher: undefined,
      sharing: true,
      spublisher: undefined,
      sminStreamManager: undefined,
      isScreenShared: false,
      mainOnOff: false,

      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
      audioEnabled: false,
      videoEnabled: false,
      audio: false,
      video: false,
      audioMsg: "마이크 OFF",
      videoMsg: "비디오 OFF",
      messages: "",
      userId: "",
      isChatVisible: false,

      // 타이머
      timer: null,
      inputHour: null,
      inputMin: null,
      inputSec: null,
      time: 0,
      resetButton: false,
      edit: false,

      // 시간
      userhistoryNo: 0,

      // 오른쪽 사이드 메뉴
      asideRight: false,
    };
  },

  created() {
    this.roomName = this.selectedStudy.title;
    this.roomUrl = this.selectedStudy.url_conf;
    this.roomStudyNo = this.selectedStudy.id;
    this.power = this.participant = this.loginUser.userId;

    // 초기 장치 셋팅
    // this.audioEnabled = this.$store.state.audio;
    // this.videoEnabled = this.$store.state.video;
    // this.audio = this.$store.state.audio;
    // this.video = this.$store.state.video;

    if (this.video == true) this.videoMsg = "비디오 OFF";
    else this.videoMsg = "비디오 ON";

    if (this.audio == true) this.audioMsg = "마이크 OFF";
    else this.audioMsg = "마이크 ON";

    this.mySessionId = this.roomUrl;
    this.myUserName = this.participant;
    this.joinSession();

    // 텍스트 채팅에서 사용하기위한 유저 아이디(임시)
    this.userId = jwt_decode(localStorage.getItem("token")).sub;
  },
  mounted() {
    this.preventBack();
    window.addEventListener("beforeunload", this.unLoadEvent);
    window.addEventListener("scroll", this.updateScroll);
  },
  beforeUnmount() {
    window.removeEventListener("beforeunload", this.unLoadEvent);
  },
  methods: {
    preventBack: function () {
      const thisObject = this;
      history.pushState(null, null, location.href);
      window.onbeforeunload = null;

      window.onpopstate = function () {
        alert("나가기 버튼을 이용해 주세요");
        history.go(1);
      };
    },
    setCodestdin(stdin) {
      this.stdin = stdin;
      // console.log(">>>>emit check");
      this.sendStdin();
    },
    setResult(result) {
      this.resultCom = result;
      // console.log(">>>>emit check");
      this.sendCompile();
    },
    ...mapMutations(["SET_RELOADCHECK"]),
    scrollToUp() {
      window.scrollTo(0, 0);
    },
    scrollToDown() {
      window.scrollTo(0, 10000);
    },
    updateScroll() {
      this.scrollPosition =
        window.scrollY || document.documentElement.scrollTop;
      // console.log(this.scrollPosition);
    },
    unLoadEvent: function (event) {
      if (this.canLeaveSite) return;

      event.preventDefault();
      event.returnValue = "";
    },
    getUserToken() {
      const token = localStorage.getItem("token");
      const header = {
        Authorization: `Bearer ${token}`,
      };
      return header;
    },
    startTimer() {
      if (!this.inputHour && !this.inputMin && !this.inputSec) {
        alert("시간을 설정해주세요.");
      } else {
        this.timer = setInterval(() => this.countdown(), 1000);
        this.resetButton = true;
        this.edit = false;
      }
    },
    stopTimer: function () {
      clearInterval(this.timer);
      this.timer = null;
      this.resetButton = true;
    },
    resetTimer: function () {
      this.time = 0;
      clearInterval(this.timer);
      this.timer = null;
      this.resetButton = false;
      this.inputHour = null;
      this.inputMin = null;
      this.inputSec = null;
    },
    editTimer: function () {
      this.edit = !this.edit;
    },
    padTime: function (time) {
      return (time < 10 ? "0" : "") + time;
    },
    countdown: function () {
      if (this.time > 0) {
        this.time--;
      } else {
        this.resetTimer();
      }
    },
    sendTimer() {
      // 타이머 send
      this.session
        .signal({
          data: this.time,
          to: [],
          type: "study-timer",
        })
        .then(() => {
          console.log("timer success");
          if (this.time == 0) {
            alert("시간이 종료되었습니다.");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },

    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      //컴파일 결과 전송
      this.session.on("signal:result-com", (event) => {
        console.log("session test");
        var compile = event.data;
        this.propcompile = compile;
      });
      this.session.on("signal:stdin", (event) => {
        var stdin = event.data;
        this.propstdin = stdin;
      });

      // 같은 session 내에서 텍스트 채팅을 위한 signal
      this.session.on("signal:my-chat", (event) => {
        var message = event.data.split("&$");

        if (message == "") {
          this.messages += "";
        } else {
          if (this.myUserName == message[0]) {
            // console.log("나나나>");
            this.messages +=
              '<div align="right">' +
              '<div style="width: 60%; background-color: #fae100; border-radius: 10px; word-wrap: break-word;">' +
              '<div style="font-weight: 900; margin-right:10px;">' +
              message[0] +
              " 님의 메시지: </div>" +
              '<div class="mb-3" style="margin-right:10px;">' +
              message[1] +
              " </div>" +
              "</div>" +
              "</div>";
          } else {
            // console.log('너너너>');
            this.messages +=
              '<div align="left">' +
              '<div style="width: 60%; background-color: #F2F2F2; color: #000; border-radius: 10px; word-wrap: break-word;">' +
              '<div style="font-weight: 900; margin-left:10px;">' +
              message[0] +
              " 님의 메시지: </div>" +
              '<div class="mb-3" style="margin-left:10px;">' +
              message[1] +
              " </div>" +
              "</div>";
          }
        }
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: this.audio, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: this.video, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      // 타이머 receive
      this.session.on("signal:study-timer", (event) => {
        this.time = Number(event.data);
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },

    leaveSession() {
      this.SET_RELOADCHECK(false);
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;
      this.OVForScreenShare = undefined;
      this.sharingPublisher = undefined;
      window.removeEventListener("beforeunload", this.leaveSession);

      this.$router.push({
        name: "StudyHome",
        params: { studyPk: this.roomStudyNo },
      });
    },

    // 텍스트 채팅을 위한 메세지 전송하기
    sendMessage() {
      var message = document.getElementById("msg").value;
      if (message != "") {
        // console.log("message " , message)
        this.session
          .signal({
            data: this.myUserName + "&$" + message,
            to: [],
            type: "my-chat",
          })
          .then(() => {
            console.log("message sent successfully!!");
            document.getElementById("msg").value = "";
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },

    // 컴파일 결과 전송
    sendCompile() {
      if (this.resultCom != "") {
        this.session
          .signal({
            data: JSON.stringify(this.resultCom),
            to: [],
            type: "result-com",
          })
          .then(() => {})
          .catch((error) => {
            console.error(error);
          });
      }
    },
    // 컴파일 결과 전송
    sendStdin() {
      if (this.stdin != "") {
        this.session
          .signal({
            data: this.stdin,
            to: [],
            type: "stdin",
          })
          .then(() => {})
          .catch((error) => {
            console.error(error);
          });
      }
    },

    muteVideo() {
      this.videoEnabled = !this.videoEnabled;
      this.video = !this.video;
      if (this.video == true) this.videoMsg = "비디오 OFF";
      else this.videoMsg = "비디오 ON";
      this.publisher.publishVideo(this.videoEnabled);
    },

    muteAudio() {
      if (this.sharing == true) {
        this.audioEnabled = !this.audioEnabled;
        this.audio = !this.audio;
        if (this.audio == true) this.audioMsg = "마이크 OFF";
        else this.audioMsg = "마이크 ON";

        this.publisher.publishAudio(this.audioEnabled);
      }
    },

    muteAudio2() {
      if (this.sharing == true) {
        this.spublisher.publishAudio(this.audioEnabled);
      }
    },

    updateMainVideoStreamManager(stream) {
      this.mainOnOff = true;
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },
    deleteMainVideoStreamManager() {
      //화면 크게 한거 지우기.
      this.mainOnOff = false;
    },

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },
    // 화면 공유 부분
    startScreenSharing() {
      this.OVForScreenShare = new OpenVidu();
      this.sessionForScreenShare = this.OVForScreenShare.initSession();

      var mySessionId = this.mySessionId;

      this.getToken(mySessionId).then((token) => {
        this.sessionForScreenShare
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            this.spublisher = this.OVForScreenShare.initPublisher(undefined, {
              audioSource: true,
              videoSource: "screen",
              publishAudio: this.audio, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true,
              resolution: "1280x720",
              frameRate: 30,
              insertMode: "APPEND",
              mirror: false,
            });
            // console.log("publisher",this.spublisher);
            this.spublisher.once("accessAllowed", () => {
              try {
                console.log("subscriber >>>>> ", this.subscribers);
                this.isScreenShared = true;
                this.session.unpublish(this.publisher); // 송출하고 있는거 중단 (안하면 에러) -- 세션을 없앤다는 뜻.

                this.mainStreamManager = undefined;
                this.OV = undefined;
                this.sharing = !this.sharing; // 화면 공유 버튼에서 중지 버튼으로 change toggle

                const constraints = {
                  width: { min: 640, ideal: 1280 },
                  height: { min: 480, ideal: 720 },
                  advanced: [
                    { width: 1920, height: 1280 },
                    { aspectRatio: 1.333 },
                  ],
                };
                this.spublisher.stream
                  .getMediaStream()
                  .getVideoTracks()[0]
                  .applyConstraints(constraints, () => {}),
                  this.spublisher.stream
                    .getMediaStream()
                    .getVideoTracks()[0]
                    .addEventListener("ended", () => {
                      console.log('User pressed the "Stop sharing" button');
                      this.leaveSessionForScreenSharing();
                      this.isScreenShared = false;
                    });
              } catch (error) {
                console.error("Error applying constraints: ", error);
              }
            });
            this.spublisher.once("accessDenied", () => {
              console.warn("ScreenShare: Access Denied");
            });
            this.mainStreamManager = this.spublisher;
            this.sharingPublisher = this.spublisher;
            this.sessionForScreenShare.publish(this.sharingPublisher);
          })
          .catch((error) => {
            console.warn(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });
      //window.addEventListener('beforeunload', this.leaveSessionForScreenSharing)
    },
    leaveSessionForScreenSharing() {
      // 화면 공유 중지
      this.sharing = !this.sharing; // 화면 공유 버튼에서 중지 버튼으로 change toggle
      this.isScreenShared = false;

      var mySessionId = this.mySessionId;
      console.log(mySessionId); // 제대로있고.
      this.sessionForScreenShare.unpublish(this.spublisher); // 송출하고 있는거 중단 (안하면 에러)
      //  if (this.sessionForScreenShare) this.sessionForScreenShare.disconnect();
      this.sessionForScreenShare = undefined;
      this.smainStreamManager = undefined;
      this.sharingPublisher = undefined;
      this.spublisher = undefined;
      this.OVForScreenShare = undefined;

      this.session.publish(this.publisher).then(() => {
        // 송출하기
        this.publisher(this.publisher);
      });

      window.removeEventListener(
        "beforeunload",
        this.leaveSessionForScreenSharing
      );
    },
  },
  stopScreenShare() {
    this.sharing = !this.sharing;
    var mySessionId = this.mySessionId;
    console.log(mySessionId);

    window.removeEventListener(
      "beforeunload",
      this.leaveSessionForScreenSharing
    );
  },
  watch: {
    messages() {
      this.$nextTick(() => {
        let msg = this.$refs.messages;

        msg.scrollTo({ top: msg.scrollHeight, behavior: "smooth" });
      });
    },

    totalTime() {
      this.time = this.totalTime;
    },
    time() {
      this.sendTimer();
    },
    resultCom() {
      this.resultCom;
    },
  },
};
</script>
