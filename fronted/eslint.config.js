import js from "@eslint/js";
import globals from "globals";
import pluginReact from "eslint-plugin-react";
import prettier from "eslint-config-prettier";
import { defineConfig } from "eslint/config";

export default defineConfig([
  // 共通設定：ブラウザ環境で動くJS
  {
    files: ["**/*.{js,mjs,cjs,jsx}"],
    languageOptions: {
      globals: globals.browser,
    },
  },

  // JavaScript の推奨ルール
  js.configs.recommended,

  // React の推奨ルール
  pluginReact.configs.flat.recommended,

  // Prettier と競合するルールをオフにする設定
  prettier,
]);
