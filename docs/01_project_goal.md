# プロジェクト概要・目的

## プロジェクト名（仮）
AWS学習用 タグ付きメモ管理アプリ

## 学習目的

1. AWS を中心としたフルスタック構成を理解すること  
   - React → Spring Boot → RDB → AWS（EB / RDS / S3+CloudFront）の流れを自分で構築する。

2. 既に触ったことのある技術を「点」ではなく「線」で整理すること  
   - React / HookForm / Zod / Reducer / Storybook  
   - Spring Boot / JUnit  
   - Playwright  
   - GitHub Actions（CI/CD）

3. 1ヶ月（1日2時間 × 週5）で完走できるミニシステムを作ること  
   - 完璧さより「一通りつながった体験」を優先する。

## 作るもの（機能イメージ）

### タグ付きメモ管理アプリ

- メモ一覧画面  
  - メモのタイトル / タグ / 更新日時を一覧表示  
  - メモの編集・削除ボタン  
  - 「新規作成」ボタン

- メモ編集画面（新規/編集共通）  
  - タイトル入力  
  - 本文入力  
  - タグ入力（とりあえず文字列）  
  - 保存ボタン

### APIエンドポイント案

- GET /memos        : メモ一覧取得
- POST /memos       : メモ新規登録
- PUT /memos/{id}   : メモ更新
- DELETE /memos/{id}: メモ削除

## 使用技術

### フロントエンド
- React（JavaScript）
- React Hook Form
- Zod
- useReducer（状態管理）
- Storybook
- Figma
- ESLint / Prettier

### バックエンド
- Spring Boot
- Spring Web / JPA / Validation
- JUnit

### テスト
- Playwright（E2E）
- JUnit（単体テスト）

### CI/CD・インフラ
- GitHub / GitHub Actions
- AWS（IAM, VPC, SG, Elastic Beanstalk, RDS, S3, CloudFront, CloudWatch）
