/**
 * アプリケーションクラス
 * @author alpacanako
 * @version 1.0
 */

package gomoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
	public static void main(String[] args) {
		// 変数宣言
		int winner = 0; // 勝者

		// 文字列入力準備
		int inputInt = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 開始メッセージ
		System.out.println("Welcome Gomoku!");

		// 本処理
		while (true) {
			try {
				// 続行問い合わせ
				while (true) {
					System.out.println("1: New Game, 9: Exit");
					System.out.print(">");

					inputInt = Integer.parseInt(br.readLine());

					if (inputInt == 9) {
						System.out.println("Bye!");
						return;
					} else if (inputInt == 1) {
						break;
					}
				}

				// 盤面クラスのインスタンス生成
				BoardData boardData = new BoardData(20);

				// CPUプレーヤークラスのインスタンス生成
				Player cpuPlayer = new Player(2);

				while (true) {
					while (true) {
						// 盤面の出力
						boardData.outputBoard();

						// プレーヤーの入力
						System.out.println("コマを置くマスを指定してください");
						System.out.print("x>");
						int inputx = Integer.parseInt(br.readLine());
						System.out.print("y>");
						int inputy = Integer.parseInt(br.readLine());

						// 盤面へ入力
						try {
							boardData.inputBoard(inputx, inputy, 1);
							break;
						} catch (NoSquareException e) {
							System.out.println("マス目が存在しません");
						} catch (FilledSquareException e) {
							System.out.println("すでにコマが置かれています");
						} catch (NoPlayerException e) {
							System.out.println("Error");
							return;
						}
					}

					// 盤面の出力
					boardData.outputBoard();

					// 判定
					winner = Judge.winner(boardData);
					if (winner == 1) {
						System.out.println("You win!");
						break;
					} else if (winner == 2) {
						System.out.println("You lose...");
						break;
					}

					try {
						// CPUのターン
						cpuPlayer.play(boardData);
					} catch (NoSquareException e) {
						System.out.println("Error:s");
					} catch (NoPlayerException e) {
						System.out.println("Error: p");
					} catch (FilledSquareException e) {
						System.err.println("Error: f");
					}

					// 判定
					winner = Judge.winner(boardData);
					if (winner == 1) {
						System.out.println("You win!");
						break;
					} else if (winner == 2) {
						System.out.println("You lose...");
						break;
					}
				}
			} catch (IOException e) {
				System.out.println("Error");
			} catch (BoardSizeException e) {
				System.out.println("Error");
			}
		}
	}
}
