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

# Day3

## 実施内容
- src 配下のディレクトリ構成を整理（components/hooks/reducers/api/pages/styles）
- global.css の読み込みを確認（Vite: main 側で import）
- useReducer の下準備（initialState / actions / memoReducer）を作成
- HomePage で reducer を接続し、ダミーデータ2件の表示を確認

# Day4 作業ログ

## 実施内容
- Storybook を導入し、起動を確認（npm run storybook）
- MemoItem を Storybook に登録し表示確認（Default / NoTags など）
- MemoList を Storybook に登録し表示確認（Empty / Single / Multiple）

## Day5：Spring Boot 基盤（Controller / Service 分離）

### 実施内容
- Spring Boot アプリ起動確認
- Controller / Service / Repository のレイヤ構造を作成
- HealthController を Service 経由で実装
- `/health` エンドポイントで疎通確認

## Day6：Memoエンティティ作成とGET /memos実装

### 実施内容
- Memo エンティティを作成（id / title / content / tags / updatedAt）
- JPA（Hibernate）を利用した Repository を作成
- MemoService を作成し、全件取得処理を実装
- GET /memos エンドポイントを実装
- H2（インメモリDB）を利用して動作確認

## Day7：POST /memos 実装とバリデーション追加

### 実施内容
- 新規メモ登録用の POST /memos API を実装
- リクエスト／レスポンス用 DTO を作成（CreateMemoRequest / MemoResponse）
- DTO にバリデーション（title 必須）を追加
- Controller に @Valid を付与してバリデーションを有効化
- Service に新規作成処理を追加
- MockMvc を用いた Controller テストを作成
