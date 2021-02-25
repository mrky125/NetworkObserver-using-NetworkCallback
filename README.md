# NetworkObserver-using-NetworkCallback
ConnectivityManager.NetworkCallback でネットワーク接続状態を監視するサンプル

# やりたいこと
- アプリがフォアグラウンドにいるときは、常にネットワーク状態を監視する
  - WiFiに接続した場合は、Activityを起動する

（このActivityでユーザに強制的にネットワーク状態の切り替えを知らせたりする想定）

- このActivityがスタックの一番上にある場合、新しいインスタンスは作らず表示したままにする
  - launchMode `"singleTop"` を使用すればOK

# 参考
- [タスクとバックスタックについて](https://developer.android.com/guide/components/activities/tasks-and-back-stack?hl=ja)
- [【Kotlin】ActivityのLaunchModeについて](https://qiita.com/s_emoto/items/1eeac92342f224bdd372)
- [Android ログイン後にログイン画面に戻れないようにしたい](https://qiita.com/takehilo/items/e677c343d689d239bf81)
- [Androidの勉強：Contextについて](https://qiita.com/iduchikun/items/34b3ae26cfc438e7e5dc)
