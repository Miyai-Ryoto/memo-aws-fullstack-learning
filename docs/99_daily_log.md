# Day1 作業ログ

- プロジェクトの目的・ゴールを整理
- アーキテクチャ概要をdocsに追加
- GitHubリポジトリ作成
- ローカルプロジェクトをGitに紐付け
- README初期コミットとマージ処理を実施
- Figmaにて画面のデザイン案を生成

## Day2

### フロントエンド（React）
- frontend フォルダを作成・整理
- Vite を使って React プロジェクトを初期化
- npm install によるパッケージ導入
- 開発サーバー起動確認（http://localhost:5173）
- ESLint を `npx eslint --init` に従って設定
- Prettier 導入（スクリプト追加・eslint-config-prettier 設定）
- eslint.config.js の調整（React推奨 + Prettier適用）

### バックエンド（Spring Boot）
- Spring Initializr から backend プロジェクト生成
- /health API を作成して動作確認
- フォルダ構成（frontend / backend / docs）の整備
