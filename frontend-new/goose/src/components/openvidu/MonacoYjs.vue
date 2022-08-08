<template>
  <div class="session-monaco">
    <!-- <div id="type">{{ selectedStudy.category }}</div> -->
    <!-- <div>compiler</div> -->
    <!-- <button type="button" id="y-connect-btn">Disconnect</button> -->
    <div id="monoco-top" style="height: 7%"></div>
    <div class="d-flex" style="height: 93%">
      <div id="monaco-editor" @input="titleUpdate" ref="monaco"></div>
      <div id="session-compile">
        <p>입력 값</p>
        <textarea id="stdin" rows="5" v-model="code.stdin"></textarea>
        <b-button variant="primary" @click="ride">컴파일 실행</b-button>
        <p id="language">{{ compiler }}</p>
        <!-- <p>실행 결과 집합 : {{ result }}</p> -->
        <p>실행 결과</p>
        <textarea v-model="output" rows="5" readonly></textarea>
        <p>사용 메모리 : {{ memory }}</p>
        <p>실행 시간 : {{ cpuTime }}</p>
        <p id="link" style="opacity: 1">{{ link }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import monaco from "@/util/monaco.js";

export default {
  props: ["propcompile", "propstdin"],
  data() {
    const store = useStore();
    return {
      compiler: store.getters.language,
      link: store.getters.selectedStudy.url_conf,
      code: {
        script: "",
        language: "",
        stdin: "",
      },
      // result: "",
      output: "",
      memory: "",
      cpuTime: "",
      resultNew: "",
    };
  },
  // moounted() {
  //   this.resultNew = this.propcompile;
  // },
  watch:{
    propcompile(){
      this.result = JSON.parse(this.propcompile);
      this.output = this.result.output;
      this.memory = this.result.memory + "KB";
      this.cpuTime = this.result.cpuTime + "s";
      console.log("<dfjdkfjdkfjdkjfkdjkdjkf");
      console.log(this.propstdin);
      this.code.stdin = this.propstdin;
    },
  },
  methods: {
    titleUpdate(e) {
      this.code.script = e.target.value;
      console.log(this.code.script);
    },
    async ride() {
      this.code.language = await this.compiler.toLowerCase();

      let temp = document.getElementById("monaco-editor").innerText;
      let cnt = 1;
      while (true) {
        if (
          (temp.charAt(cnt) >= 0 && temp.charAt(cnt) <= 9) ||
          temp.charAt(cnt) == "\n"
        ) {
          cnt++;
        } else break;
      }

      this.code.script = temp.substring(cnt).replaceAll(" ", " ");


      if (this.code.language == "python") this.code.language = "python3";
      else if (this.code.language == "c++") this.code.language = "cpp";

      
      console.log("ride : ", this.code.script, this.code.language, this.code.stdin);

      await this.$store.dispatch("compile", this.code);


      // console.log("ddddddddddd>", this.$store.getters.language);
      // console.log("ddddddd result>", this.$store.getters.result);
      this.result = this.$store.getters.result;
      this.output = this.result.output;
      this.memory = this.result.memory + "KB";
      this.cpuTime = this.result.cpuTime + "s";

      this.$emit("sendCodestdin", this.code.stdin);
      this.$emit("sendResult", this.result);
      // console.log(this.result);
    },
  },
};
</script>

<style>
.session-monaco {
  background-color: #fffac7;
}
#session-compile {
  padding: 5px;
  border-radius: 5px;
  width: 23%;
  height: 93%;
  margin-left: 20px;
  background-color: #1e1e1e;
  color: #fff;
  margin-right: 20px;
}
#monaco-editor {
  margin-left: 20px;
  width: 100%;
  height: 93%;
  border: 1px solid #ccc;
  overflow: visible;
}
.yRemoteSelection {
  background-color: rgb(250, 129, 0, 0.5);
}
.yRemoteSelectionHead {
  position: absolute;
  border-left: orange solid 2px;
  border-top: orange solid 2px;
  border-bottom: orange solid 2px;
  height: 100%;
  box-sizing: border-box;
}
.yRemoteSelectionHead::after {
  position: absolute;
  content: " ";
  border: 3px solid orange;
  border-radius: 4px;
  left: -4px;
  top: -5px;
}
</style>
