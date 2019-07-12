# Gomoku
五目並べをJavaで書いてみる。

## how to play
```Shell
$ javac ./bin/gomoku/Game.class
```

## 主要クラスの説明
- Game.java: アプリケーションクラス
- BoardData: 盤面データクラス
- Player: 仮想プレイヤークラス。CPUプレイヤーとして利用
- Judge: 五目並べのルールに関する記述