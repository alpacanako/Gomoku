package gomoku;

public class BoardSizeException extends Exception {
	/**
	 *  引数なしコンストラクタ
	 */
	public BoardSizeException() {
		super();
	}

	/**
	 *  メッセージ引数ありコンストラクタ
	 * @param msg
	 */
	public BoardSizeException(String msg) {
		super(msg);
	}
}
