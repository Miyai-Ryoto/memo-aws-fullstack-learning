# アーキテクチャ概要

## 全体像

フロント〜バックエンド〜インフラの流れ：

React（SPA）  
→ HTTP（fetch / axios）  
→ Spring Boot（REST API）  
→ RDB（RDS: MySQL/Aurora）  

これらを AWS 上にデプロイする。

## コンポーネント一覧

- フロントエンド
  - React SPA
  - React Hook Form + Zod でフォームバリデーション
  - useReducer でメモ一覧の状態管理
  - Storybook で UI コンポーネントをカタログ化

- バックエンド
  - Spring Boot (REST API)
  - Spring MVC + Spring Data JPA
  - H2（ローカル開発）→ RDS（本番相当）

- テスト
  - Playwright: ブラウザE2E テスト
  - JUnit: API の単体 / 結合テスト

- CI/CD
  - GitHub Actions
    - Lint, Test, Build
    - 余裕があれば Deploy も自動化

- インフラ（AWS）
  - Elastic Beanstalk: Spring Boot アプリの実行環境
  - RDS: メモ情報の永続化
  - S3: React ビルド成果物のホスティング
  - CloudFront: S3 を配信する CDN
  - IAM: 権限管理
  - VPC + Security Group: ネットワーク / Firewall

## 簡易構成図（テキスト版）

[Browser] 
   ↓ (HTTPS)  
[CloudFront] 
   ↓ (静的ファイル配信)
[S3: React Build]

[Browser] 
   ↓ (HTTPS API call)
[ALB or EB Endpoint]
   ↓
[Elastic Beanstalk: Spring Boot]
   ↓ (JDBC)
[RDS: MySQL/Aurora]
