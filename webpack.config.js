const path = require("path");
const CopyPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require("clean-webpack-plugin");

module.exports = {
  mode: "production",
  entry: "./src/main/javascript/index.js",
  output: {
    filename: "main.js",
    path: path.resolve(__dirname, "./src/main/generated-resources/html"),
  },
  plugins: [
    new CopyPlugin({
      patterns: [{ from: "./src/main/javascript/dist" }],
    }),
    new CleanWebpackPlugin({
      protectWebpackAssets: false,
      cleanAfterEveryBuildPatterns: ["*.LICENSE.txt"],
    }),
  ],
};
