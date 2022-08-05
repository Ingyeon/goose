<template>
  <div class="session-monaco">
    <!-- <div id="type">{{ selectedStudy.category }}</div> -->
    <!-- <div>compiler</div> -->
    <!-- <button type="button" id="y-connect-btn">Disconnect</button> -->
    <div id="monoco-top" style="height:7%"></div>
    <div class="d-flex" style="height:93%">
      <div id="monaco-editor" @input="titleUpdate" ref="monaco"></div>
      <div id="session-compile">
        <b-button variant="primary" @click="ride">컴파일 실행</b-button>
        <p id="language">{{ compiler }}</p>
        <p>실행 결과 집합 : {{ result }}</p>
        <p>실행 결과 : {{ output }}</p>
        <p>사용 메모리 : {{ memory }}</p>
        <p>실행 시간 : {{ cpuTime }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import monaco from "@/util/monaco.js";

export default {
  data() {
    const store = useStore();
    return {
      compiler: store.getters.language,
      code: {
        script: "print(50)",
        language: "",
      },
      result: "",
      output: "",
      memory: "",
      cpuTime: "",
    };
  },
  methods: {
    titleUpdate(e) {
      this.code.script = e.target.value;
      console.log(this.code.script);
      console.log(this.code.language);
    },
    async ride() {
      console.log(this.compiler);
      this.code.language = await this.compiler.toLowerCase();
      if (this.code.language == "python") this.code.language = "python3";
      else if (this.code.language == "c++") this.code.language = "cpp";
      await this.$store.dispatch("compile", this.code);
      // console.log("ddddddddddd>", this.$store.getters.language);
      // console.log("ddddddd result>", this.$store.getters.result);
      this.result = this.$store.getters.result;

      this.output = this.result.output;
      this.memory = this.result.memory;
      this.cpuTime = this.result.cpuTime;
    },
  },
};
</script>

<style>
#session-compile{
  margin-left:20px;
}
#monaco-editor {
  width: 75%;
  height: 93%;
  border: 1px solid #ccc;
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
