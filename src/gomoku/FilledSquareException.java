package gomoku;

public class FilledSquareException extends Exception {
	// 引数なしコンストラクタ
	public FilledSquareException() {
		super();
	}

	// メッセージ引数ありコンストラクタ
	public FilledSquareException(String msg) {
		super(msg);
	}
}
