<template>
  <div>
    <head>
      <meta charset="utf-8" />
      <title>Yjs Monaco Example</title>
    </head>
    <body>
      <button type="button" id="y-connect-btn">Disconnect</button>
      <p></p>
      <p>
        This is a demo of the <a href="https://github.com/yjs/yjs">Yjs</a> ⇔
        <a href="https://microsoft.github.io/monaco-editor/">Monaco</a> binding:
        <a href="https://github.com/yjs/y-monaco">y-monaco</a>.
      </p>
      <p>
        The content of this editor is shared with every client that visits this
        domain.
      </p>
      <!-- <div id="monaco-editor" :value="title" @input="titleUpdate" /> -->
      <div id="monaco-editor" />
      <!-- <input type="text" :v-model="code"> -->
      <!-- <input type="text" :value = "title" @input = "titleUpdate" 
style = "padding: 20px; border: 1px pink solid"> -->
      <!-- <input v-model="testv" /> -->
      <button @click="ride()">fff</button>
    </body>
  </div>
</template>

<script>
import monaco from "@/util/monaco.js";
export default {
  data() {
    return {
      code: "12345",
      testv: "",
    };
  },
  methods: {
    titleUpdate(e) {
      console.log(e.target.value);
      console.log()
    },
    ride() {
      console.log("dddddd", this.testv);
      console.log(this.testv);
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
