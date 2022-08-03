// Vue3 관련 설정 파일
module.exports = {
  devServer: {
    https: false,
    port: 8083,
    open: true,
    proxy: {
      "/api/v1": {
        target: "http://localhost:8080/",
      },
      "/v1/execute": {
        target: "https://api.jdoodle.com/",
        changeOrigin: true,
        secure: false,
      },
    },

    historyApiFallback: true,
    hot: true,
  },
  // css: {
  //   requireModuleExtension: false // import 시에 경로에 .module 포함 안해도 됨.
  // },
  lintOnSave: false,
  outputDir: "../backend/src/main/resources/dist",
};
