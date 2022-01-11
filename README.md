## ComposeTutorial
[公式のチュートリアル](https://developer.android.google.cn/jetpack/compose/tutorial?hl=ja) に従ってJetpack Composeのチュートリアルを作ってみた

### 概要
- Compose によるUIの構成
  - Row/Col によるレイアウトの作成
  - MaterialTheme による外観の設定

- **LazyColumn** によるリストレンダリング
  → メッセージリストの作成
- **remember** + **mutableStateOf** による ステート管理
  → メッセージカード開閉状態の管理
- **animateColorAsState** による外観色の動的な変更
  → カード開いた時に色が滑らかに変わるようにした
