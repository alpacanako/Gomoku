# Gomoku
五目並べをJavaで書いてみる。
ver1.0: ランダムにコマを置くCPUと対戦

## how to play
```Shell
$ java ./bin/gomoku/Game.class
```

## 主要クラスの説明
- Game.java: アプリケーションクラス
- BoardData: 盤面データクラス
- Player: 仮想プレイヤークラス。CPUプレイヤーとして利用
- Judge: 五目並べのルールに関する記述