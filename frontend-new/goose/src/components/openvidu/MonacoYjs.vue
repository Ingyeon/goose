<template>
  <div>
    <!-- <div id="type">{{ selectedStudy.category }}</div> -->
    <div id="language">{{ compiler }}</div>
    <div>compiler</div>
    <button type="button" id="y-connect-btn">Disconnect</button>
    <div id="monaco-editor" @input="titleUpdate" ref="monaco" />
    <button @click="ride">컴파일 실행</button>
    <div>실행 결과 : {{ result }}</div>
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
      result: store.getters.result,
    };
  },
  methods: {
    titleUpdate(e) {
      this.code.script = e.target.value;
      console.log(this.code.script);
      console.log(this.code.language);
    },
    ride() {
      console.log(this.compiler);
      this.code.language = this.compiler.toLowerCase();
      if (this.code.language == "python") this.code.language = "python3";
      else if (this.code.language == "c++") this.code.language = "cpp";
      this.$store.dispatch("compile", this.code);
    },
  },
};
</script>

<style>
#monaco-editor {
  width: 100%;
  height: 600px;
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
