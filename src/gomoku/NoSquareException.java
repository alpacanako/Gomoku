package gomoku;

public class NoSquareException extends Exception {
	/**
	 *  引数なしコンストラクタ
	 */
	public NoSquareException() {
		super();
	}

	/**
	 *  メッセージ引数ありコンストラクタ
	 * @param msg
	 */
	public NoSquareException(String msg) {
		super(msg);
	}
}
