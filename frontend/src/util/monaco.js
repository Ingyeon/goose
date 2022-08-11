import * as Y from "yjs";
import { WebsocketProvider } from "y-websocket";
import * as awarenessProtocol from "y-protocols/awareness.js";
import { MonacoBinding, yCursorPlugin } from "y-monaco";
import * as monaco from "monaco-editor";

// @ts-ignore
window.MonacoEnvironment = {
  getWorkerUrl: function (moduleId, label) {
    if (label === "json") {
      return "/monaco/dist/json.worker.bundle.js";
    }
    if (label === "css") {
      return "/monaco/dist/css.worker.bundle.js";
    }
    if (label === "html") {
      return "/monaco/dist/html.worker.bundle.js";
    }
    if (label === "typescript" || label === "javascript") {
      return "/monaco/dist/ts.worker.bundle.js";
    }
    return "/monaco/dist/editor.worker.bundle.js";
  },
};

window.addEventListener("load", () => {
  const ydoc = new Y.Doc();
  const link = document.getElementById("link").innerText;
  let provider = new WebsocketProvider(
    "wss://demos.yjs.dev",
    "goose-session" + link,
    ydoc
  );
  const ytext = ydoc.getText("monaco");
  const compiler = document.getElementById("language").innerHTML.toLowerCase();
  let awareness = provider.awareness;
  monaco.editor.defineTheme("my-dark", {
    base: "vs",
    inherit: true,
    rules: [{ background: "EDF9FA" }],
    colors: {
      "editor.background": "#FAFAFA",
    },
  });

  awareness.setLocalStateField("user", {
    name: "123",
    color: "#0033ff",
  });

  editor = monaco.editor.create(
    /** @type {HTMLElement} */ (document.getElementById("monaco-editor")),
    {
      value: "",
      language: compiler,
      theme: "my-dark",
      // plugins: [yCursorPlugin(awareness)],
    }
  );
  const monacoBinding = new MonacoBinding(
    ytext,
    /** @type {monaco.editor.ITextModel} */ (editor.getModel()),
    new Set([editor]),
    awareness
  );

  // @ts-ignore
  window.example = { provider, ydoc, ytext, monacoBinding };
});

var editor;

function save() {
  // get the value of the data
  var value = editor.getValue();
  return value;
}

export default save;
