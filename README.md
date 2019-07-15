# 簡易版 Mercari アプリ

## 成果
- [課題の仕様](https://github.com/m-rec/a42a09b1d39a68e43610a2cf54d9a5bbfaa0ec3a/blob/master/SKILL_TEST.ja.md#%E3%82%A2%E3%83%97%E3%83%AA%E3%81%AE%E6%9C%80%E4%BD%8E%E9%99%90%E3%81%AE%E4%BB%95%E6%A7%98)
- [進捗状況](https://github.com/m-rec/a42a09b1d39a68e43610a2cf54d9a5bbfaa0ec3a/issues/2)

<img src="https://user-images.githubusercontent.com/1131119/61189092-43edc400-a6c3-11e9-95ca-681e59f4eb24.png" width="200px">

## 設計方針
MVP(UIアーキテクチャ) + Clean Architecure(システムアーキテクチャ)を設計の基本方針とする。

### 背景
チーム開発を円滑に進めるためには、UIとロジックの分離及び、コンポーネント化が必要だから。
（Conflictを避けるため、ロジック再利用、UnitTest etc..）

#### なぜMVP?
- [こちらのパターンを採用](https://qiita.com/k-kagurazaka@github/items/062e21ab769773aa319a#%E3%83%91%E3%82%BF%E3%83%BC%E3%83%B32-model-view-presenter-mvp)
- 比較的容易にViewとPresentationロジックを分離できるから
- PresenterのUnitTestを書くことで、表示側のロジックをある程度担保可能

#### なぜClean Architecure?
- [こちらのパターンを採用](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- 表示、ビジネスロジック、インフラコード（通信、データ保存 etc）をコンポーネント化する統一ルールを提供
- 依存関係が厳密に定義されていることで、以下の利点があり、大規模開発や長期メンテナンスに適している。
  - 最も重要なビジネスロジックをプラットフォームや外部環境非依存に保てるため、容易にUnitTestを書ける(Domain層)
  - 各層が疎結合なため、Viewやインフラの交換が容易（UnitTest時のMock化、キャッシュロジックの実装、Viewの切り替え etc..）
  - 各コンポーネントの役割が明確なため、チーム開発がやりやすくなり、再利用可能なプログラムが書きやすい

### 設計概要
<img src="https://user-images.githubusercontent.com/1131119/61197305-20506b00-a70f-11e9-95d6-9af4f6bf68ca.png" width="800px">

- 3層の依存性の方向は、App -> Domain, Infra -> Domainとなっており、Domainはどこにも依存せず、循環依存や層を飛び越した依存（App,Infra間）もない
- 各層ごとの基本データ構造が存在し（ViewData:App, Model:Domain, Data:Infra）、層をまたぐ際にmapperで適切なデータ構造に変換される。
  - Data->Infraのmapperは、腐敗防止層（ACL）の役割を兼ねる

### 画面構成
- MainActivity : エントリポイント
  - API経由でマスターデータを取得
  - 取得したデータを元に、ViewPagerを生成し、子のフラグメントに必要なパラメータを渡す
- ItemListFragment : 商品一覧リスト表示
  - パラメータを元にAPI通信を行い、取得したデータをRecyclerViewへ表示する

## ポイント

## 使用技術
