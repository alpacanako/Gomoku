/**
 * 仮想プレイヤークラス
 * @author alpacanako
 * @version 1.0
 */

package gomoku;

public class Player {
	// フィールド宣言
	private int playerNo; // プレーヤー番号

	// 引数なしコンストラクタ
	public Player() {
		super();
	}

	// 引数ありコンストラクタ
	public Player(int playerNo) {
		super();
		this.playerNo = playerNo;
	}

	/**
	 * 盤面上にコマを置くメソッド
	 * @param boardData
	 */
	public void play(BoardData boardData) throws NoSquareException, NoPlayerException, FilledSquareException {
		int maxValue = eval(boardData); // 評価値の最大値記録用
		int maxX = 0; // 最善手記録用
		int maxY = 0; // 最善手記録用

		int boardSize = boardData.getBoardSize();
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				BoardData testBoardData = new BoardData(boardData); // 評価値計算用テスト盤面データ
				if (boardData.square(x, y) == 0) {
					testBoardData.inputBoard(x, y, playerNo);
					int value = eval(testBoardData);
					if (maxValue < value) {
						maxX = x;
						maxY = y;
						maxValue = value;
					}
				}
			}
		}
		boardData.inputBoard(maxX, maxY, playerNo);
	}

	private int eval(BoardData boardData) throws NoSquareException {
		int boardSize = boardData.getBoardSize();
		int value = 0;
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				value += verticalEval(boardData, x, y) + horizontalEval(boardData, x, y) + slashEval(boardData, x, y)
						+ backslashEval(boardData, x, y);
			}
		}
		return value;
	}

	/**
	 * 指定されたマスを含む縦の連続する五マスを評価する関数
	 * @param boardData
	 * @param x
	 * @param y
	 * @return
	 * @throws NoSquareException
	 */
	private int verticalEval(BoardData boardData, int x, int y) throws NoSquareException {
		int boardSize = boardData.getBoardSize();

		// 指定されたマスが存在するかどうか確認
		if (x < 0 || boardSize <= x || y < 0 || boardSize <= y) {
			throw new NoSquareException();
		}

		/*
		 *  指定されたマスから始まる含む
		 *  縦の連続する五マスが存在しなければ0を返す
		 */
		if (boardSize - 4 <= y) {
			return 0;
		}

		/*
		 *  連続する五マスに置かれたコマを確認
		 *  自分のコマがn個置かれていて、
		 *  相手のコマが置かれていなければ(n^2)を足す
		 */
		int countPlayer = 0;
		int countOpponent = 0;
		for (int i = 0; i < 5; i++) {
			int square = boardData.square(x, y + i);
			if (square == playerNo) {
				countPlayer++;
			} else if (square != 0) {
				countOpponent++;
			}
		}
		if (countPlayer > 0 && countOpponent == 0) {
			return countPlayer * countPlayer;
		} else if (countPlayer == 0 && countOpponent > 0) {
			return -(countOpponent * countOpponent);
		}
		return 0;
	}

	/**
	 * 指定されたマスを含む横の連続する五マスを評価する関数
	 * @param boardData
	 * @param x
	 * @param y
	 * @return
	 * @throws NoSquareException
	 */
	private int horizontalEval(BoardData boardData, int x, int y) throws NoSquareException {
		int boardSize = boardData.getBoardSize();

		// 指定されたマスが存在するかどうか確認
		if (x < 0 || boardSize <= x || y < 0 || boardSize <= y) {
			throw new NoSquareException();
		}

		/*
		 *  指定されたマスから始まる横の連続する五マスが存在しなければ0を返す
		 */
		if (boardSize - 4 <= x) {
			return 0;
		}

		/*
		 *  連続する五マスに置かれたコマを確認
		 *  自分のコマがn個置かれていて、
		 *  相手のコマが置かれていなければ(n^2)を足す
		 */
		int countPlayer = 0;
		int countOpponent = 0;
		for (int i = 0; i < 5; i++) {
			int square = boardData.square(x + i, y);
			if (square == playerNo) {
				countPlayer++;
			} else if (square != 0) {
				countOpponent++;
			}
		}
		if (countPlayer > 0 && countOpponent == 0) {
			return countPlayer * countPlayer;
		} else if (countPlayer == 0 && countOpponent > 0) {
			return -(countOpponent * countOpponent);
		}
		return 0;
	}

	/**
	 * 指定されたマスを含む右上がり斜めの連続する五マスを評価する関数
	 * @param boardData
	 * @param x
	 * @param y
	 * @return
	 * @throws NoSquareException
	 */
	private int slashEval(BoardData boardData, int x, int y) throws NoSquareException {
		int boardSize = boardData.getBoardSize();

		// 指定されたマスが存在するかどうか確認
		if (x < 0 || boardSize <= x || y < 0 || boardSize <= y) {
			throw new NoSquareException();
		}

		/*
		 *  指定されたマスから始まる右上がり斜めの連続する五マスが存在しなければ0を返す
		 */
		if (boardSize - 4 <= x || boardSize - 4 <= y) {
			return 0;
		}

		/*
		 *  連続する五マスに置かれたコマを確認
		 *  自分のコマがn個置かれていて、
		 *  相手のコマが置かれていなければ(n^2)を足す
		 */
		int countPlayer = 0;
		int countOpponent = 0;
		for (int i = 0; i < 5; i++) {
			int square = boardData.square(x + i, y + i);
			if (square == playerNo) {
				countPlayer++;
			} else if (square != 0) {
				countOpponent++;
			}
		}
		if (countPlayer > 0 && countOpponent == 0) {
			return countPlayer * countPlayer;
		} else if (countPlayer == 0 && countOpponent > 0) {
			return -(countOpponent * countOpponent);
		}
		return 0;
	}

	/**
	 * 指定されたマスを含む右下がり斜めの連続する五マスを評価する関数
	 * @param boardData
	 * @param x
	 * @param y
	 * @return
	 * @throws NoSquareException
	 */
	private int backslashEval(BoardData boardData, int x, int y) throws NoSquareException {
		int boardSize = boardData.getBoardSize();

		// 指定されたマスが存在するかどうか確認
		if (x < 0 || boardSize <= x || y < 0 || boardSize <= y) {
			throw new NoSquareException();
		}

		/*
		 *  指定されたマスから始まる右下がり斜めの連続する五マスが存在しなければ0を返す
		 */
		if (boardSize - 4 <= x || y < 4) {
			return 0;
		}

		/*
		 *  連続する五マスに置かれたコマを確認
		 *  自分のコマがn個置かれていて、
		 *  相手のコマが置かれていなければ(n^2)を足す
		 */
		int countPlayer = 0;
		int countOpponent = 0;
		for (int i = 0; i < 5; i++) {
			int square = boardData.square(x + i, y - i);
			if (square == playerNo) {
				countPlayer++;
			} else if (square != 0) {
				countOpponent++;
			}
		}
		if (countPlayer > 0 && countOpponent == 0) {
			return countPlayer * countPlayer;
		} else if (countPlayer == 0 && countOpponent > 0) {
			return -(countOpponent * countOpponent);
		}
		return 0;
	}
}
