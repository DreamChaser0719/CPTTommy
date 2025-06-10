import arc.*;

public class CPTTommy {
	public static void main(String[] args){
		Console con = new Console();

		int intMoney;
		int intBet = 0;
		int intCount = 2;
		int intHits = 0;
		int intSum = 0;
		int intSumDealer = 0;
		int intLBMoney;
		int intLBCount = 0;
		String strName;
		String strInput;
		String strLBName;
		String strLB [][];
		boolean doubledDown;
		boolean skipDealerTurn;

		while (true) {
			con.println("Blackjack");
			con.println("Play (P)");
			con.println("Leaderboards (L)");
			con.println("Quit (Q)");
			char chrInputHome = con.getChar();

			if (chrInputHome == 'l' || chrInputHome == 'L') {
				TextInputFile lb = new TextInputFile("Leaderboard.txt");
				while (!lb.eof()) {
					strLBName = lb.readLine();
					intLBMoney = lb.readInt();
					con.println(strLBName + " - " + intLBMoney);
				}
				lb.close();
			} else if (chrInputHome == 'q' || chrInputHome == 'Q') {
				con.closeConsole();
			} else if (chrInputHome == 'p' || chrInputHome == 'P') {
				
				intMoney = 1000;
				con.println("Enter your name:");
				strName = con.readLine();

				boolean keepPlaying = true;
				while (keepPlaying && intMoney > 0) {
					skipDealerTurn = false;
					doubledDown = false;
				
					int intDeck [][] = MethodsFile.deck();
					intDeck = MethodsFile.sort(intDeck);
					int intPlayers [][] = new int [5][2];
					int intDealer [][] = new int [5][2];

					intPlayers [0][0] = intDeck [0][0];
					intPlayers [0][1] = intDeck [0][1];
					intDealer [0][0] = intDeck [1][0];
					intDealer [0][1] = intDeck [1][1];
					intPlayers [1][0] = intDeck [2][0];
					intPlayers [1][1] = intDeck [2][1];

					con.println("Enter your bet:");
					intBet = con.readInt();

					con.println();
					con.println("Your cards are:");
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							con.print(intPlayers[i][j] + " ");
						}
						con.println();
					}

					intSum = MethodsFile.CalculateTotal(intPlayers);
					con.println("The cards currently adds up to " + intSum);

					if ((intPlayers[0][0] == 1 && intPlayers[1][0] >= 10) || (intPlayers[0][0] >= 10 && intPlayers[1][0] == 1)) {
						con.println("BLACKJACK! You win 3x your bet!");
						intMoney += intBet * 2;
						skipDealerTurn = true;
						intSum = 0;
						intSumDealer = 0;
						intCount = 2;
						intHits = 0;
						continue; // Skip rest of the loop, go to next round
					}


					if (intSum == 9 || intSum == 10 || intSum == 11) {
						con.println("Do you wish to double down? (Y/N)");
						char chrInputDD = con.getChar();
						if (chrInputDD == 'y' || chrInputDD == 'Y') {
							if (intBet * 2 <= intMoney) {
								intBet *= 2;
								intPlayers[2][0] = intDeck[3][0];
								intPlayers[2][1] = intDeck[3][1];
								
								con.println(intPlayers[2][0] + " " + intPlayers[2][1]);
								intSum = MethodsFile.CalculateTotal(intPlayers);
								con.println("The cards add up to " + intSum);

								intCount = 3;
								doubledDown = true;
							} else {
								con.println("You don't have enough money!");
								doubledDown = false;
							}
						}
					}
					
					if (!doubledDown) {
						while (intSum < 22 && intCount < 5) {
							con.println("Hit or Stand? (H/S)");
							char chrInputMain = con.getChar();
							if (chrInputMain == 'h' || chrInputMain == 'H') {
								
								intPlayers[intCount][0] = intDeck[intCount+1][0];
								intPlayers[intCount][1] = intDeck[intCount+1][1];
								con.println("Your new card is:");
								con.print(intPlayers[intCount][0] + " " + intPlayers[intCount][1]);
								con.println();
								intCount++;
								intHits++;

								intSum = MethodsFile.CalculateTotal(intPlayers);
								con.println("Your new sum is: " + intSum);

								if (intCount == 5 && intSum <= 21) {
									con.println("You have 5 cards and didn’t bust!");
									skipDealerTurn = true;
									break;
								}

							} else if (chrInputMain == 's' || chrInputMain == 'S') {
								break;
							}
						}
					}
					
					if (!skipDealerTurn) {
						con.println("The dealer's cards are: ");
						con.println(intDealer [0][0] + " " + intDealer [0][1]);
						intSumDealer = MethodsFile.CalculateTotal(intDealer);
						intHits = 1;

						while (intSumDealer < 17 && intHits < 5) {
							intDealer [intHits][0] = intDeck [intCount+1][0];
							intDealer [intHits][1] = intDeck [intCount+1][1];
							con.print(intDealer[intHits][0] + " " + intDealer[intHits][1]);
							con.println();
							intSumDealer = MethodsFile.CalculateTotal(intDealer);
							intHits++;
							intCount++;
						}
						con.println("Dealer's sum is: " + intSumDealer);
					}
				
					if (intSum > 21) {
						con.println("You busted!");
						intMoney -= intBet;
					} else if (intSumDealer > 21) {
						con.println("Dealer busted!");
						intMoney += intBet;
					} else if (intDealer[4][0] != 0 && intSumDealer <= 21) {
						con.println("Dealer got 5 cards and did not bust! You lost your bet.");
						intMoney -= intBet;
					} else if (intSum > intSumDealer) {
						con.println("You won!");
						intMoney += intBet;
					} else if (intSum < intSumDealer) {
						con.println("You lost!");
						intMoney -= intBet;
					} else {
						con.println("Tied! Bet returned");
					}


					if (intMoney < 0) {
						intMoney = 0;
					}
					
					con.println("You currently have: " + intMoney);
					intSum = 0;
					intSumDealer = 0;
					intCount = 2;
					intHits = 0;

					if (intMoney <= 0) {
						keepPlaying = false;
					} else {
						con.println("Play another round? (Y to continue, any other key to quit)");
						char chrContinue = con.getChar();
						if (chrContinue != 'y' && chrContinue != 'Y') {
							keepPlaying = false;
						}
					}
				}

				TextOutputFile lbOutput = new TextOutputFile("Leaderboard.txt", true);
				lbOutput.println(strName);
				lbOutput.println(intMoney);
				lbOutput.close();

				TextInputFile lbInput = new TextInputFile("Leaderboard.txt");
				while (!lbInput.eof()) {
					strLBName = lbInput.readLine();
					intLBMoney = lbInput.readInt();
					intLBCount++;
				}
				lbInput.close();

				lbInput = new TextInputFile("Leaderboard.txt");
				strLB = new String [intLBCount][3];
				intLBCount = 0;
				
				while (!lbInput.eof()) {
					strLBName = lbInput.readLine();
					intLBMoney = lbInput.readInt();
					strLB[intLBCount][0] = strLBName;
					strLB[intLBCount][1] = intLBMoney + "";
					strLB[intLBCount][2] = (int)(100*Math.random()) + "";
					intLBCount++;
				}
				strLB = MethodsFile.leaderboard(strLB, intLBCount);
				lbInput.close();

				TextOutputFile lbSorted = new TextOutputFile("Leaderboard.txt");
				for (int i = 0; i < intLBCount; i++) {
					lbSorted.println(strLB[i][0]);
					lbSorted.println(strLB[i][1]);
				}
				lbSorted.close();
			}
		}
	}
}
