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

## Day8：React × API（GET /memos）連携、表示反映、tags型の調整

### 実施内容
- React（Vite）から Spring Boot の GET /memos を呼び出し、ダミーデータ表示を廃止
- useReducer の status（loading / success / error）に合わせて画面を分岐表示
- Spring Boot 側に CORS 設定（@CrossOrigin）を追加し、フロント（localhost:5173）からのアクセスを許可
- 当初 tags が文字列（"a,b"）で返り、フロント側で join がエラーになる問題を確認
- API契約を明確にするため、MemoResponse の tags を List<String> に変更し、Service で split して返却
- Controller の GET /memos を Entity 返却から DTO（MemoResponse）返却に変更し、tags を配列で返すように統一
- 動作確認：React画面にメモ一覧が安定して表示され、tags も期待通り表示できることを確認

## Day9：React × API（POST /memos）連携、新規作成と一覧即時反映

### 実施内容
- React のフォーム（MemoForm）から POST /memos API を呼び出し、新規メモ登録を実装
- 入力項目（title / content / tags）を state で管理し、送信時に API へリクエスト
- POST 成功時に返却される MemoResponse を useReducer の state に追加し、再取得せず一覧へ即時反映
- 送信中はボタンや入力欄を disabled にし、二重送信を防止
- Storybook 用に MemoForm.stories.jsx を作成し、通常時／送信中状態を確認可能にした
- Spring Boot 側の POST /memos が作成済みエンティティを返却する設計であることを確認し、フロント側の実装と整合していることを検証

## Day10：React × API（PUT / DELETE）連携、更新・削除と一覧即時反映

### 実施内容

- Spring Boot 側で PUT /memos/{id}・DELETE /memos/{id} API を実装し、メモの更新・削除処理を追加
- 更新・削除時に存在しない ID が指定された場合、例外を送出し 404 Not Found を返却する設計を確認
- Entity に setter を持たせず、update メソッドによる更新処理を実装し、状態変更を意味のある操作に限定
- @PreUpdate を利用して updatedAt を自動更新し、更新時刻を Entity の責務として管理する構成を理解
- React 側で PUT / DELETE 用の API 関数を追加し、API 層を CRUD 対応に拡張
- useReducer に更新・削除用の処理を追加し、再取得せず state を差し替える方式で一覧を即時反映
- メモ一覧に編集モードを追加し、その場で内容を更新できる UI を実装
- 削除ボタン押下時に DELETE API を呼び出し、成功時に該当メモを state から除外する UI を実装
- 更新・削除中は submitting 状態を共有し、二重操作を防止
- フロントエンドとバックエンドの役割分担（API 契約・状態管理・UI）が一貫していることを確認