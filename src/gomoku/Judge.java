package gomoku;

public class Judge {
	/**
	 *  連続する五目が存在すればその色（プレーヤー）を返すメソッド
	 *  連続する五目は複数存在しないことを仮定
	 *
	 * @param boardData
	 * @return
	 */
	static public int winner(BoardData boardData) {
		int boardSize = boardData.getBoardSize();
		try {
			for (int x = 0; x < boardSize; x++) {
				for (int y = 0; y < boardSize; y++) {

					if (verticalJudge(boardData, x, y) != 0) {
						return verticalJudge(boardData, x, y);
					}
					if (horizontalJudge(boardData, x, y) != 0) {
						return horizontalJudge(boardData, x, y);
					}
					if (slashJudge(boardData, x, y) != 0) {
						return verticalJudge(boardData, x, y);
					}
					if (backslashJudge(boardData, x, y) != 0) {
						return verticalJudge(boardData, x, y);
					}

				}
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
		return 0;
	}

	/**
	 * 指定されたマスから始まる縦の連続する五マス全てに同じ色のコマが置かれていれば、
	 * そのコマの色（プレーヤー）を返す。
	 * そうでなければ0を返す。
	 *
	 * @param boardData
	 * @param x
	 * @param y
	 * @return
	 * @throws NoSquareException
	 */
	static private int verticalJudge(BoardData boardData, int x, int y) throws NoSquareException {
		int boardSize = boardData.getBoardSize();

		// 指定されたマスが存在するかどうか確認
		if (x < 0 || boardSize <= x || y < 0 || boardSize <= y) {
			throw new NoSquareException();
		}

		/*
		 *  指定されたマスが空、または指定されたマスから始まる含む
		 *  縦の連続する五マスが存在しなければ0を返す
		 */
		if (boardData.square(x, y) == 0 || boardSize - 4 <= y) {
			return 0;
		}

		/*
		 *  連続する五マスに置かれたコマを確認し、すべて同じコマならその色を返す
		 *  そうでなければ0を返す
		 */
		boolean flag = true;
		for (int i = 0; i < 5; i++) {
			flag = flag && boardData.square(x, y) == boardData.square(x, y + i);
		}
		if (flag) {
			return boardData.square(x, y);
		}
		return 0;
	}

	/**
	 * 指定されたマスから始まる横の連続する五マス全てに同じ色のコマが置かれていれば、
	 * そのコマの色（プレーヤー）を返す。
	 * そうでなければ0を返す。
	 *
	 * @param boardData
	 * @param x
	 * @param y
	 * @return
	 * @throws NoSquareException
	 */
	static private int horizontalJudge(BoardData boardData, int x, int y) throws NoSquareException {
		int boardSize = boardData.getBoardSize();

		// 指定されたマスが存在するかどうか確認
		if (x < 0 || boardSize <= x || y < 0 || boardSize <= y) {
			throw new NoSquareException();
		}

		/*
		 *  指定されたマスが空、または指定されたマスから始まる含む横の連続する五マスが
		 *  存在しなければ0を返す
		 */
		if (boardData.square(x, y) == 0 || boardSize - 4 <= x) {
			return 0;
		}

		/*
		 *  連続する五マスに置かれたコマを確認し、すべて同じコマならその色を返す
		 *  そうでなければ0を返す
		 */
		boolean flag = true;
		for (int i = 0; i < 5; i++) {
			flag = flag && boardData.square(x, y) == boardData.square(x + i, y);
		}
		if (flag) {
			return boardData.square(x, y);
		}
		return 0;
	}

	/**
	 * 指定されたマスから始まる右上がり斜めの連続する五マス全てに同じ色のコマが置かれていれば、
	 * そのコマの色（プレーヤー）を返す。
	 * そうでなければ0を返す。
	 *
	 * @param boardData
	 * @param x
	 * @param y
	 * @return
	 * @throws NoSquareException
	 */
	static private int slashJudge(BoardData boardData, int x, int y) throws NoSquareException {
		int boardSize = boardData.getBoardSize();

		// 指定されたマスが存在するかどうか確認
		if (x < 0 || boardSize <= x || y < 0 || boardSize <= y) {
			throw new NoSquareException();
		}

		/*
		 *  指定されたマスが空、または指定されたマスから始まる含む右上がり斜めの連続する五マスが
		 *  存在しなければ0を返す
		 */
		if (boardData.square(x, y) == 0 || boardSize - 4 <= x || boardSize - 4 <= y) {
			return 0;
		}

		/*
		 *  連続する五マスに置かれたコマを確認し、すべて同じコマならその色を返す
		 *  そうでなければ0を返す
		 */
		boolean flag = true;
		for (int i = 0; i < 5; i++) {
			flag = flag && boardData.square(x, y) == boardData.square(x + i, y + i);
		}
		if (flag) {
			return boardData.square(x, y);
		}
		return 0;
	}

	/**
	 * 指定されたマスから始まる右下がり斜めの連続する五マス全てに同じ色のコマが置かれていれば、
	 * そのコマの色（プレーヤー）を返す。
	 * そうでなければ0を返す。
	 *
	 * @param boardData
	 * @param x
	 * @param y
	 * @return
	 * @throws NoSquareException
	 */
	static private int backslashJudge(BoardData boardData, int x, int y) throws NoSquareException {
		int boardSize = boardData.getBoardSize();

		// 指定されたマスが存在するかどうか確認
		if (x < 0 || boardSize <= x || y < 0 || boardSize <= y) {
			throw new NoSquareException();
		}

		/*
		 *  指定されたマスが空、または指定されたマスから始まる含む縦の連続する五マスが
		 *  存在しなければ0を返す
		 */
		if (boardData.square(x, y) == 0 || boardSize - 4 <= x || y < 4) {
			return 0;
		}

		/*
		 *  連続する五マスに置かれたコマを確認し、すべて同じコマならその色を返す
		 *  そうでなければ0を返す
		 */
		boolean flag = true;
		for (int i = 0; i < 5; i++) {
			flag = flag && boardData.square(x, y) == boardData.square(x + i, y - i);
		}
		if (flag) {
			return boardData.square(x, y);
		}
		return 0;
	}
}
