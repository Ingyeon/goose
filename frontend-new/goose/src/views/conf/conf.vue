<template>
  <div id="main-container" class="container">
    <div id="join" v-if="!sessionCamera">
      <div id="img-div">
        <img src="resources/images/openvidu_grey_bg_transp_cropped.png" />
      </div>
      <div id="join-dialog" class="jumbotron vertical-center">
        <h1>Join a video session</h1>
        <div class="form-group">
          <p>
            <label>Participant</label>
            <input
              v-model="myUserName"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p>
            <label>Session</label>
            <input
              v-model="mySessionId"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p class="text-center">
            <button class="btn btn-lg btn-success" @click="joinSession()">
              Join!
            </button>
          </p>
        </div>
      </div>
    </div>

    <div id="session" v-if="sessionCamera">
      <div id="session-header">
        <h1 id="session-title">{{ mySessionId }}</h1>
        <input
          class="btn btn-large btn-danger"
          type="button"
          id="buttonLeaveSession"
          @click="leaveSession"
          value="Leave session"
        />
        <input
          v-if="!screensharing"
          class="btn btn-large btn-info"
          type="button"
          id="buttonLeaveSession"
          @click="publishScreenShare"
          value="Screen Share"
        />
      </div>
      <div id="main-video" class="col-md-6">
        <user-video :stream-manager="mainStreamManager" />
      </div>
      <div id="video-container" class="col-md-6">
        <user-video
          :stream-manager="publisherCamera"
          @click.native="updateMainVideoStreamManager(publisherCamera)"
        />
        <user-video
          v-for="sub in subscribersCamera"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
          @click.native="updateMainVideoStreamManager(sub)"
        />
      </div>
      <div>
        <user-video
          v-for="sub in subscribersScreen"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
          @click.native="updateMainVideoStreamManager(sub)"
        />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "../../components/conf/UserVideo.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
  name: "App",

  components: {
    UserVideo,
  },

  data() {
    return {
      OVCamera: undefined,
      sessionCamera: undefined,
      OVScreen: undefined,
      sessionScreen: undefined,
      publisherCamera: undefined,
      subscribersCamera: [],
      publisherScreen: undefined,
      subscribersScreen: [],

      mainStreamManager: undefined,
      screensharing: false,

      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
    };
  },

  methods: {
    joinSession() {
      // --- Get an OpenVidu object ---
      this.OVCamera = new OpenVidu();
      this.OVScreen = new OpenVidu();

      // --- Init a sessionCamera ---
      this.sessionCamera = this.OVCamera.initSession();
      this.sessionScreen = this.OVScreen.initSession();

      // --- Specify the actions when events take place in the sessionCamera ---

      // On every new Stream received...
      this.sessionCamera.on("streamCreated", ({ stream }) => {
        if (stream.typeOfVideo == "CAMERA") {
          const subscriber = this.sessionCamera.subscribe(stream);
          this.subscribersCamera.push(subscriber);
        }
      });
      this.sessionScreen.on("streamCreated", ({ stream }) => {
        if (stream.typeOfVideo == "SCREEN") {
          const subscriber = this.sessionScreen.subscribe(stream);
          this.subscribersScreen.push(subscriber);
        }
      });

      // On every Stream destroyed...
      this.sessionCamera.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribersCamera.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribersCamera.splice(index, 1);
        }
      });
      this.sessionScreen.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribersScreen.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribersScreen.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.sessionCamera.on("exception", ({ exception }) => {
        console.warn(exception);
      });
      this.sessionScreen.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the sessionCamera with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.sessionCamera
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisherCamera = this.OVCamera.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisherCamera;
            this.publisherCamera = publisherCamera;

            // --- Publish your stream ---

            this.sessionCamera.publish(this.publisherCamera);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the sessionCamera:",
              error.code,
              error.message
            );
          });
      });
      this.getToken(this.mySessionId).then((tokenScreen) => {
        this.sessionScreen
          .connect(tokenScreen, { clientData: this.myUserName })
          .then(() => {
            this.screensharing = false;
            console.log("Session screen connected");
          })
          .catch((error) => {
            console.warn(
              "There was an error connecting to the session for screen share:",
              error.code,
              error.message
            );
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },

    publishScreenShare() {
      let publisherScreen = this.OVScreen.initPublisher(undefined, {
        videoSource: "screen", // The source of video. If undefined default webcam
      });

      // --- 9.2) If the user grants access to the screen share function, publish the screen stream
      publisherScreen.once("accessAllowed", () => {
        this.screensharing = true;
        // It is very important to define what to do when the stream ends.
        publisherScreen.stream
          .getMediaStream()
          .getVideoTracks()[0]
          .addEventListener("ended", () => {
            console.log('User pressed the "Stop sharing" button');
            this.sessionScreen.unpublish(publisherScreen);
            this.screensharing = false;
          });

        this.mainStreamManager = publisherScreen;
        this.publisherScreen = publisherScreen;
        this.sessionScreen.publish(this.publisherScreen);
      });

      // publisherScreen.on("videoElementCreated", function (event) {
      //   appendUserData(event.element, sessionScreen.connection);
      //   event.element["muted"] = true;
      // });

      publisherScreen.once("accessDenied", () => {
        console.error("Screen Share: Access Denied");
      });
    },

    leaveSession() {
      // --- Leave the sessionCamera by calling 'disconnect' method over the Session object ---
      if (this.sessionCamera) this.sessionCamera.disconnect();
      if (this.sessionScreen) this.sessionScreen.disconnect();

      this.OVCamera = undefined;
      this.sessionCamera = undefined;
      this.publisherCamera = undefined;
      this.subscribersCamera = [];

      this.OVScreen = undefined;
      this.sessionScreen = undefined;
      this.publisherScreen = undefined;
      this.subscribersScreen = [];

      this.mainStreamManager = undefined;
      window.removeEventListener("beforeunload", this.leaveSession);
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

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-sessionCamera
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
};
</script>
