package gomoku;

import java.util.Random;

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

	public void play(BoardData boardData) {
		while (true) {
			Random random = new Random();
			int x = random.nextInt(boardData.getBoardSize());
			int y = random.nextInt(boardData.getBoardSize());

			if (boardData.square(x, y) == 0) {
				try {
				boardData.inputBoard(x, y, playerNo);
				return;
				}catch(FilledSquareException e) {

				}catch(NoSquareException e) {
					System.out.println("Error");
				}catch(NoPlayerException e) {
					System.out.println("Error");
				}
			}
		}
	}
}
