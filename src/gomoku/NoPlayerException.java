package gomoku;

public class NoPlayerException extends Exception{
	// 引数なしコンストラクタ
	public NoPlayerException() {
		super();
	}

	// メッセージ引数ありコンストラクタ
	public NoPlayerException(String msg) {
		super(msg);
	}
}
