<template>
  <div id="main">
    <div id="main-container" class="d-flex">
      <div id="session-center">
        <div id="session" v-if="session">
          <div id="session-header" class="d-flex">
            <h1 id="session-title">{{ this.roomName }}</h1>
            <!-- 방 제목 -->
          </div>

          <div>
            <div
              v-if="isLeader"
              id="video-container"
              class="d-flex flex-wrap row"
            >
              <!-- 참가자 화면 -->
              <UserVideo
                :session="session"
                class="col-md-4"
                :stream-manager="publisher"
                @click="updateMainVideoStreamManager(publisher)"
              />
              <!--자기 -->
              <UserVideoPublisher
                :session="session"
                class="col-md-4"
                v-for="sub in subscribers"
                :key="sub.stream.connection.connectionId"
                :stream-manager="sub"
                @click="updateMainVideoStreamManager(sub), outUser(sub)"
              />
              <!-- 다른 참가자 -->
            </div>
            <div v-else id="video-container" class="d-flex flex-wrap row">
              <!-- 참가자 화면 -->
              <UserVideo
                :session="session"
                class="col-md-4"
                :stream-manager="publisher"
                @click="updateMainVideoStreamManager(publisher)"
              />
              <!--자기 -->
              <UserVideo
                :session="session"
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
                  @click="muteAudio()"
                >
                  <i class="fa-solid fa-microphone"></i>
                  <span class="footerBtnText">&nbsp{{ audioMsg }}</span>
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

              <!-- 채팅 버튼 -->
              <div v-if="!asideRight" class="buttomMenu">
                <b-button
                  class="btn btn-large btn-default footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="asideRight = true"
                >
                  <i class="fa-solid fa-comment-slash"></i>
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
                  <i class="fa-solid fa-comment"></i>
                  <span class="footerBtnText">&nbsp채팅닫기</span>
                </b-button>
              </div>

              <!-- 나가기 버튼 설정 -->
              <div class="buttomMenu">
                <button
                  class="btn btn-large btn-default footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="leaveSession"
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
    <!-- #main-container -->
  </div>
</template>
<style scoped>
@import "@/assets/style/StudyRoom/video.css";
@import "@/assets/style/style.css";
@import "@/assets/style/StudyRoom/room.css";
</style>
<script>
import axios from "axios";
import http from "@/util/http-common.js";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/openvidu/PublicUserVideo";
import UserVideoPublisher from "@/components/openvidu/PublicUserVideoPublisher";
import UserList from "@/components/openvidu/UserList";
import jwt_decode from "jwt-decode";
import { computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://localhost:4443";
const OPENVIDU_SERVER_SECRET = "MY_GOOSEGOOSE";

export default {
  name: "App",

  components: {
    UserVideo,
    UserList,
    UserVideoPublisher,
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    const selectedStudy = computed(() => store.getters.selectedStudy);
    const loginUser = computed(() => store.getters.loginUser);
    const isManager = computed(() => store.getters.isStudyManager);

    const open_set = async function () {
      await store.dispatch("saveOpenList");
    };

    return { selectedStudy, loginUser, isManager, router, open_set };
  },
  data() {
    return {
      //방정보
      roomName: "none",
      roomUrl: "none",
      participant: "none",
      roomStudyNo: "none",
      studyMembers: "none",
      isaudio: false,
      isvideo: false,
      // 화면 공유
      // OVForScreenShare: undefined,
      // sessionForScreenShare: undefined,
      sharingPublisher: undefined,
      sharing: true,
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      mySessionId: null,
      myUserName: null,
      audioEnabled: true,
      videoEnabled: true,
      audio: true,
      video: true,
      audioMsg: "마이크 ON",
      videoMsg: "비디오 ON",
      messages: "",
      userId: "",
      isChatVisible: false,

      // 타이머
      timer: null,
      time: 0,

      // 공개스터디 참가자 목록
      publicStudyMembers: [],

      // 강퇴관련
      outMemberModal: false,
      isLeader: null,
      power: "",

      // 스터디 규칙 관련
      studyRuleModal: false,
      roomRule: null,

      // 오른쪽 사이드 메뉴
      asideRight: false,
    };
  },
  // computed:{
  // 	...mapState(meetingStore,["isaudio","isvideo"]),
  // 	...mapState(publicStudyStore,['roomName', 'roomUrl', 'participant',"roomStudyNo", "studyRule"])
  // },
  created() {
    this.roomName = this.selectedStudy.title;
    this.roomUrl = this.selectedStudy.url_conf;
    this.roomStudyNo = this.selectedStudy.id;
    this.power = this.participant = this.loginUser.userId;
    this.isLeader = this.isManager;

    // 초기 장치 셋팅
    (this.audioEnabled = this.isaudio), (this.videoEnabled = this.isvideo);
    this.audio = this.isaudio;
    this.video = this.isvideo;
    console.log(this.isaudio);
    console.log(this.isvideo);
    if (this.video == true) this.videoMsg = "비디오 OFF";
    else this.videoMsg = "비디오 ON";

    if (this.audio == true) this.audioMsg = "마이크 OFF";
    else this.audioMsg = "마이크 ON";

    this.mySessionId = this.roomUrl;
    this.myUserName = this.participant;
    this.joinSession();

    // 텍스트 채팅에서 사용하기위한 유저 아이디(임시)
    this.userId = jwt_decode(localStorage.getItem("token")).sub;
    // console.log(">>>>>>>>>>>>>>>>>>>> userId : ", this.userId);

    // 강퇴기능위해 공개스터디멤버 불러오기
    this.getPublicStudyMembers(this.roomUrl);
    // console.log(this.roomUrl)
  },
  mounted() {
    window.addEventListener("beforeunload", this.unLoadEvent);
  },
  beforeUnmount() {
    window.removeEventListener("beforeunload", this.unLoadEvent);
  },
  methods: {
    unLoadEvent: function (event) {
      if (this.canLeaveSite) return;

      event.preventDefault();
      event.returnValue = "";
    },
    getUserToken() {
      const token = localStorage.getItem("token");
      const header = {
        Authorization: `${token}`,
      };
      return header;
    },

    // 공개스터디 멤버 불러오기 (예비)
    async getPublicStudyMembers(publicstudyroomid) {
      console.log(publicstudyroomid);
    },

    outUser(memberId) {
      const { connection } = memberId.stream;
      const { clientData } = JSON.parse(connection.data);

      this.publisher.session.signal({
        data: clientData,
        to: [],
        type: "out",
      });
    },

    // 권한부여 기능(예비)
    async giveAuthority(publicmember_no, leader, memberId) {
      console.log(publicmember_no);
      console.log(leader);
      console.log(memberId);
    },
    async joinSession() {
      // await this.getPublicStudyMembers(this.roomUrl);
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", async ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
        await this.getPublicStudyMembers(this.roomUrl);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
        setTimeout(() => {
          this.getPublicStudyMembers(this.roomUrl);
        }, 300);
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // receive 강퇴
      this.session.on("signal:out", async (event) => {
        var id = event.data;

        if (id == this.myUserName) {
          await this.leaveSession();
          alert("방에서 추방당하셨습니다.");
        }
        this.getPublicStudyMembers(this.roomUrl);
      });

      // 권한 넘기는
      this.session.on("signal:leader", async () => {
        // console.log(event.data)
        // console.log(this.userId)
        // if(event.data==this.userId){
        // 	alert("스터디장 권한을 받았습니다.")
        // }
        await this.getPublicStudyMembers(this.roomUrl);
      });

      // 같은 session 내에서 텍스트 채팅을 위한 signal
      this.session.on("signal:my-chat", (event) => {
        var message = event.data.split("&$");
        console.log(">>>>>>>>>>>>>> message : ", message);

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
              '<div style="width: 60%; background-color: #f2f2f2; color: #000; border-radius: 10px; word-wrap: break-word;">' +
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
            // console.log("Dasdasdasdasdasdasdasdasdasdasdasdasdqwrqwrqw");
            // console.log(this.session)
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
            console.log(this.session);
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
      // this.session.on('signal:study-timer', (event) => {
      // 	this.time = Number(event.data);
      // })

      window.addEventListener("beforeunload", this.leaveSession);
    },

    async getPublicStudyAuth() {
      await http({
        method: "get",
        url: `/study/member/publicstudyAuth/${this.roomStudyNo}`,
        headers: this.getUserToken(),
      })
        .then((res) => {
          this.power = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    async removePublicRoom() {
      if (this.power >= 3) {
        await http({
          method: "delete",
          url: `/study/remove/${this.roomStudyNo}`,
        })
          .then((res) => {
            console.log(res);
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },

    async leaveSession() {
      await this.getPublicStudyAuth();
      await this.removePublicRoom();
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

      this.sharing = !this.sharing;
      if (this.sessionForScreenShare) this.sessionForScreenShare.disconnect();
      this.sessionForScreenShare = undefined;
      this.mainStreamManager = undefined;
      this.sharingPublisher = undefined;
      this.OVForScreenShare = undefined;
      window.removeEventListener(
        "beforeunload",
        this.leaveSessionForScreenSharing
      );
      this.open_set();
      this.$router.push("Home");
    },

    // 텍스트 채팅을 위한 메세지 전송하기
    sendMessage() {
      var message = document.getElementById("msg").value;
      if (message != "") {
        console.log("message ", message);

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

    muteVideo() {
      this.videoEnabled = !this.videoEnabled;
      this.video = !this.video;
      if (this.video == true) this.videoMsg = "비디오 OFF";
      else this.videoMsg = "비디오 ON";
      this.publisher.publishVideo(this.videoEnabled);
    },

    muteAudio() {
      this.audioEnabled = !this.audioEnabled;
      this.audio = !this.audio;
      if (this.audio == true) this.audioMsg = "마이크 OFF";
      else this.audioMsg = "마이크 ON";
      this.publisher.publishAudio(this.audioEnabled);
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
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
  },

  watch: {
    messages() {
      this.$nextTick(() => {
        let msg = this.$refs.messages;

        msg.scrollTo({ top: msg.scrollHeight, behavior: "smooth" });
      });
    },
  },
};
</script>
