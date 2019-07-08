# 簡易版 Mercari アプリ
[アプリの仕様](https://github.com/m-rec/a42a09b1d39a68e43610a2cf54d9a5bbfaa0ec3a/blob/master/SKILL_TEST.ja.md#%E3%82%A2%E3%83%97%E3%83%AA%E3%81%AE%E6%9C%80%E4%BD%8E%E9%99%90%E3%81%AE%E4%BB%95%E6%A7%98)

## 設計方針
MVP(UIアーキテクチャ) + Clean Architecure(システムアーキテクチャ)を設計の基本方針とする。

### 背景
チーム開発を円滑に進めるためには、UIとロジックの分離及び、コンポーネント化が必要だから。
（Conflictを避けるため、ロジック再利用、UnitTest etc..）

#### なぜMVP?
- [こちらのパターン](https://qiita.com/k-kagurazaka@github/items/062e21ab769773aa319a#%E3%83%91%E3%82%BF%E3%83%BC%E3%83%B32-model-view-presenter-mvp)
- 比較的容易にViewとPresentationロジックを分離できるから
- PresenterのUnitTestを書くことで、表示側のロジックをある程度担保可能

#### なぜClean Architecure?
- [こちらのパターン](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- View、ビジネスロジック、インフラコード（通信、データ保存 etc）をコンポーネント化する
- 依存関係が厳密に定義されていることで、以下の利点があり、大規模開発や長期メンテナンスに適している。
  - 最も重要なビジネスロジックをプラットフォームや外部環境非依存に保てるため、容易にUnitTestを書ける(Domain層)
  - 各層が疎結合なため、Viewやインフラの交換が容易（UnitTest時のMock化、キャッシュロジックの実装、Viewの切り替え etc..）
  - 各コンポーネントの役割が明確なため、チーム開発がやりやすくなり、再利用可能なプログラムが書きやすい

### クラス図

### ポイント

### 使用技術