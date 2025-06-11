import arc.*;

public class CPTTommy {
	public static void main(String[] args){
		Console con = new Console("Blackjack", 1280, 720);

		int intMoney;
		int intLBMoney;
		int intBet = 0;
		int intCount = 2;
		int intHits = 1;
		int intSum = 0;
		int intSumDealer = 0;
		int intLBCount = 0;
		String strName;
		String strInput;
		String strLBName;
		String strLB [][];
		boolean blndoubledDown;
		boolean blnskipDealerTurn;
		boolean blnkeepPlaying;
		boolean blnskipOutcomes;

		while (true) {
			con.println("Blackjack");
            con.println("Play (P)");
            con.println("Leaderboards (L)");
            con.println("Help (H)");
            con.println("Quit (Q)");
			char chrInputHome = con.getChar();
			System.out.println("User selected menu option '" + chrInputHome + "'");

			if (chrInputHome == 'l' || chrInputHome == 'L') {
				System.out.println("Entering leaderboard view");
				TextInputFile lb = new TextInputFile("Leaderboard.txt");
				while (!lb.eof()) {
					strLBName = lb.readLine();
					intLBMoney = lb.readInt();
					con.println(strLBName + " - " + intLBMoney);
				}
				lb.close();
			} else if (chrInputHome == 'h' || chrInputHome == 'H') {
                con.clear();
                con.println("=== HELP MENU ===");
                con.println("P - Play Blackjack");
                con.println("L - View Leaderboards");
                con.println("Q - Quit the game");
                con.println("H - Show this help menu");
                con.println("Try a secret option from the main menu...");
                con.println("Press any key to return to the menu.");
                con.getChar();
                con.clear();
            } else if (chrInputHome == 's' || chrInputHome == 'S') {
                con.clear();
                con.println("You found the secret menu!");
                con.println("Why don't scientists trust atoms?");
                con.println("Because they make up everything!");
                con.println();
                con.println("Press any key to go back...");
                con.getChar();
                con.clear();
            } else if (chrInputHome == 'q' || chrInputHome == 'Q') {
				System.out.println("Quitting game");
				con.closeConsole();
			} else if (chrInputHome == 'p' || chrInputHome == 'P') {
				System.out.println("User selected Play");
				intMoney = 1000;
				con.println("Enter your name:");
				strName = con.readLine();
				System.out.println("Player name is " + strName);

				if (strName.equalsIgnoreCase("statitan")) {
					intMoney = 5000;
					con.println("Cheat activated! Starting with $5000.");
				} else {
					intMoney = 1000;
				}

				blnkeepPlaying = true;
				while (blnkeepPlaying && intMoney > 0) {
					intBet = 0;
					intCount = 2;
					intHits = 1;
					intSum = 0;
					intSumDealer = 0;
					intLBCount = 0;
					blndoubledDown = false;
					blnskipDealerTurn = false;
					blnskipOutcomes = false;

					int intDeck [][] = MethodsFile.deck();
					intDeck = MethodsFile.sort(intDeck);
					int intPlayers [][] = new int [5][2];
					int intDealer [][] = new int [5][2];

					intPlayers[0] = intDeck[0];
					intDealer[0] = intDeck[1];
					intPlayers[1] = intDeck[2];

					con.println("You currently have: $" + intMoney);
					con.println("Enter your bet:");
					intBet = con.readInt();
					if (intBet > intMoney) {
						con.println("You tried to bet more than you have. Betting all in with $" + intMoney + ".");
						intBet = intMoney;
					}

					con.clear();
					con.println("Your cards:");
					String[] playerCardNames = MethodsFile.getCardNames(intPlayers);
					for (int i = 0; i < playerCardNames.length; i++) {
						if (!playerCardNames[i].equals("")) {
							con.println(playerCardNames[i]);
						}
					}

					intSum = MethodsFile.CalculateTotal(intPlayers);
					con.println("Your total: " + intSum);

					con.println("\nDealer's card:");
					String[] dealerCardNames = MethodsFile.getCardNames(intDealer);
					con.println(dealerCardNames[0]);
					intSumDealer = MethodsFile.CalculateTotal(intDealer);

					// Check blackjack
					if ((intPlayers[0][0] == 1 && intPlayers[1][0] >= 10) || (intPlayers[0][0] >= 10 && intPlayers[1][0] == 1)) {
						con.println("\nBLACKJACK! You win 3x your bet!");
						intMoney += intBet * 2;
						con.println("Current money: $" + intMoney);
						con.println("Play another round? (Y to continue)");
						char chrContinue = con.getChar();
						if (chrContinue != 'y' && chrContinue != 'Y') {
							blnkeepPlaying = false;
						}
						con.clear();
						continue;
					}
					
					//Check double down
					if (intSum == 9 || intSum == 10 || intSum == 11) {
						con.println("Would you like to double down? (Y/N)");
						char charDD = con.getChar();
						if (charDD == 'y' || charDD == 'Y') {
							if (intMoney >= 2 * intBet) {
								blndoubledDown = true;
								intBet *= 2;
								con.println("You doubled down! Your bet is now: $" + intBet);
								intPlayers[2] = intDeck[3];
								
								intSum = MethodsFile.CalculateTotal(intPlayers);
								playerCardNames = MethodsFile.getCardNames(intPlayers);
								
								con.sleep(3000);
								con.clear();
								con.println("Your cards:");
								for (int i = 0; i < playerCardNames.length; i++) {
									if (!playerCardNames[i].equals("")) {
										con.println(playerCardNames[i]);
									}
								}
								con.println("Your total is: " + intSum);
							} else {
								con.println("You don't have enough money, double down failed.");
								blndoubledDown = false;
							}
						} else {
							con.println("You declined!");
						}
					}

					// Player's Turn
					if (!blndoubledDown) {
						while (intSum < 22 && intCount < 5) {
							con.println("\nHit or Stand? (H/S)");
							char charAction = con.getChar();
							if (charAction == 'h' || charAction == 'H') {
								intPlayers[intCount] = intDeck[intCount + 1];
								intCount++;
								con.clear();
								con.println("Your cards:");
								playerCardNames = MethodsFile.getCardNames(intPlayers);
								for (int i = 0; i < playerCardNames.length; i++) {
									if (!playerCardNames[i].equals("")) {
										con.println(playerCardNames[i]);
									}
								}
								intSum = MethodsFile.CalculateTotal(intPlayers);
								con.println("Your total: " + intSum);

								con.println("\nDealer's card:");
								con.println(dealerCardNames[0]);
							} else if (charAction == 's' || charAction == 'S') {
								break;
							}
						}
					}
					
					if (intCount == 5 && intSum <= 21) {
						con.println("\nYou got 5 cards! 3x your bet!");
						intMoney += intBet * 2;
						blnskipDealerTurn = true;
						blnskipOutcomes = true;
						con.println("Current money: $" + intMoney);
						con.println("Play another round? (Y to continue)");
						char chrContinue = con.getChar();
						if (chrContinue != 'y' && chrContinue != 'Y') {
							blnkeepPlaying = false;
						}
					con.clear();
					}
					
					// Dealer's Turn
					if (intSum <= 21 && intCount <= 5) {
						con.println("\nDealer's turn...");
						con.sleep(1000);
						intSumDealer = MethodsFile.CalculateTotal(intDealer);
						while (intSumDealer < 17 && intHits < 5) {
							intDealer[intHits] = intDeck[intCount + 2];
							intHits++;
							intCount++;
							intSumDealer = MethodsFile.CalculateTotal(intDealer);

							con.clear();
							con.println("Dealer's cards:");
							dealerCardNames = MethodsFile.getCardNames(intDealer);
							for (int i = 0; i < dealerCardNames.length; i++) {
								if (!dealerCardNames[i].equals("")) {
									con.println(dealerCardNames[i]);
								}
							}
							con.println("Dealer's total: " + intSumDealer);
							con.sleep(1000);
						}
					}
					
					
					if(!blnskipOutcomes) {
						// Final Totals
						con.println("\n--- ROUND RESULT ---");
						con.println("Your total: " + intSum);
						con.println("Dealer's total: " + intSumDealer);

						// Outcomes
						if (intHits == 5 && intSumDealer <= 21) {
							con.println("Dealer got 5 cards! You lost your bet!");
							intMoney -= intBet;
						} else if (intSum > 21) {
							con.println("You busted!");
							intMoney -= intBet;
						} else if (intSumDealer > 21) {
							con.println("Dealer busted! You win!");
							intMoney += intBet;
						}  else if (intSum > intSumDealer) {
							con.println("You won!");
							intMoney += intBet;
						} else if (intSum < intSumDealer) {
							con.println("You lost!");
							intMoney -= intBet;
						} else {
							con.println("Tie! Bet returned.");
						}

						if (intMoney <= 0) {
							con.println("You're bankrupt! Restarting game...");
							con.sleep(5000);
							con.clear();
							blnkeepPlaying = false;
							break;
						}

						con.println("Current money: $" + intMoney);
						con.println("Play another round? (Y to continue)");
						char chrContinue = con.getChar();
						if (chrContinue != 'y' && chrContinue != 'Y') {
							blnkeepPlaying = false;
						}

						con.clear();
					}
				}

				System.out.println("Saving leaderboard...");
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
				System.out.println("Leaderboard saved.");
			}
		}
	}
}
