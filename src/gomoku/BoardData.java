/**
 * 盤面データクラス
 * @author alpacanako
 * @version 1.0
 */

package gomoku;

public class BoardData {
	// フィールド宣言
	private int boardSize; // 盤面の一片の長さ
	private int[][] board = null; // 盤面

	/**
	 *  引数なしコンストラクタ
	 */
	public BoardData() {
		super();
	}

	/**
	 *  空の盤面を生成するコンストラクタ
	 * @param boardSize
	 * @throws BoardSizeException
	 */
	public BoardData(int boardSize) throws BoardSizeException {
		super();
		if (boardSize < 5) {
			throw new BoardSizeException();
		}
		this.boardSize = boardSize;
		this.board = new int[boardSize][boardSize];
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				board[x][y] = 0;
			}
		}
	}

	/**
	 * 与えられた盤面データを別インスタンスにコピーする
	 * コンストラクタ
	 * @param boardData
	 */
	public BoardData(BoardData boardData) {
		super();
		boardSize = boardData.getBoardSize();
		this.board = new int[boardSize][boardSize];
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				board[x][y] = boardData.square(x, y);
			}
		}
	}

	/**
	 *  盤面の一辺の長さを返すメソッド
	 * @return
	 */
	public int getBoardSize() {
		return boardSize;
	}

	/**
	 *  指定された座標の値を返すメソッド
	 * @param x
	 * @param y
	 * @return
	 */
	public int square(int x, int y) {
		return board[x][y];
	}

	/**
	 * 盤面を画面出力するメソッド
	 */
	public void outputBoard() {
		System.out.println("  y");
		for (int y = boardSize - 1; y >= 0; y--) {
			System.out.print(String.format("%3d|", y));
			for (int x = 0; x < boardSize; x++) {
				if (board[x][y] == 1) {
					System.out.print(" " + "O" + " |");
				} else if (board[x][y] == 2) {
					System.out.print(" " + "X" + " |");
				} else {
					System.out.print("   |");
				}
			}
			System.out.println();
		}
		System.out.print("    ");
		for (int x = 0; x < boardSize; x++) {
			System.out.print(String.format("%3d ", x));
		}
		System.out.println("  x");
	}

	/**
	 * 盤面に入力するメソッド
	 * @param x
	 * @param y
	 * @param player
	 * @throws NoSquareException
	 * @throws NoPlayerException
	 * @throws FilledSquareException
	 */
	public void inputBoard(int x, int y, int player)
			throws NoSquareException, NoPlayerException, FilledSquareException {
		// 指定されたマスが存在するかどうか確認
		if (x < 0 || boardSize <= x || y < 0 || boardSize <= y) {
			throw new NoSquareException();
		}

		// 指定されたプレイヤーが存在するかどうか確認
		if (player < 1 || 2 < player) {
			throw new NoPlayerException();
		}

		// 指定された盤面が空いているかどうか確認
		if (board[x][y] != 0) {
			throw new FilledSquareException();
		}

		// 盤面へ入力
		board[x][y] = player;
	}
}
