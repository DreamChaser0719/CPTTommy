/*
Author: Tommy Yan
Program name: Blackjack
Last modified: 2025/06/12
Version number: v3.5
*/

import arc.*;
import java.awt.image.BufferedImage;

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
		boolean blnBetCheck;
		
		BufferedImage imgHelp = con.loadImage("Help.png");
		BufferedImage imgSecret = con.loadImage("Secret.png");
		BufferedImage imgMainMenu = con.loadImage("MainMenu.png");
		BufferedImage imgBG = con.loadImage("Background.png");
		
		while (true) { // infinite loop
			con.clear();
			con.drawImage(imgMainMenu, 0, 0); // draw the main menu
			con.repaint();
			char chrInputHome = con.getChar();
			System.out.println("User selected menu option '" + chrInputHome + "'"); // debug purposes
			
			//depending on the input, launch different tabs
			if (chrInputHome == 'l' || chrInputHome == 'L') {
				System.out.println("Entering leaderboard view");
				con.drawImage(imgBG, -300, -200); // load background image
				intLBCount = 1;
				TextInputFile lb = new TextInputFile("Leaderboard.txt");
				
				con.println("The top 10 players are:");
				while (!lb.eof() && intLBCount < 11) {
					strLBName = lb.readLine();
					intLBMoney = lb.readInt();
					con.println(strLBName+ " - $" + intLBMoney);
					intLBCount++;
				}
				con.println();
				con.println("Press any key to go back...");
				intLBCount = 0; // resetting everything
				lb.close();
				con.getChar();
				con.repaint();
				con.clear();
			} else if (chrInputHome == 'h' || chrInputHome == 'H') {
                con.drawImage(imgHelp, 0, 0); // load help image
                con.repaint();
                con.getChar();
                con.clear();
            } else if (chrInputHome == 's' || chrInputHome == 'S') {
                con.drawImage(imgSecret, 0, 0); // load secret image
                con.repaint();
                con.getChar();
                con.clear();
            } else if (chrInputHome == 'q' || chrInputHome == 'Q') {
				System.out.println("Quitting game"); // debug purposes
				con.closeConsole(); // closes the console to quit
			} else if (chrInputHome == 'p' || chrInputHome == 'P') {
				con.clear();
				con.drawImage(imgBG, -300, -200); // draw the bg image
				System.out.println("User selected Play");
				intMoney = 1000;
				con.println("Enter your name:");
				strName = con.readLine();
				System.out.println("Player name is " + strName);

				if (strName.equalsIgnoreCase("statitan")) { // if the name is statitan, starts with $5000 instead
					intMoney = 5000;
					con.println("Cheat activated, starting with $5000 instead :)");
				} else {
					intMoney = 1000;
				}

				blnkeepPlaying = true;
				while (blnkeepPlaying && intMoney > 0) {
					intBet = 0;// resetting ALL the variables
					intCount = 2;
					intHits = 1;
					intSum = 0;
					intSumDealer = 0;
					intLBCount = 0;
					blndoubledDown = false;
					blnskipDealerTurn = false;
					blnBetCheck = false;

					int intDeck [][] = MethodsFile.deck();// creating the deck
					intDeck = MethodsFile.sort(intDeck);// shuffling the deck
					int intPlayers [][] = new int [5][2];
					int intDealer [][] = new int [5][2];// creating the player decks

					intPlayers[0] = intDeck[0];
					intDealer[0] = intDeck[1];
					intPlayers[1] = intDeck[2];

					con.println("You currently have: $" + intMoney);
					con.println("Enter your bet:");
					
					while (!blnBetCheck) {
						intBet = con.readInt();
						if (intBet > intMoney) { // failsafe checks
							con.println("You tried to bet more than you have. Betting all in with $" + intMoney + ".");
							intBet = intMoney;
							blnBetCheck = true;
							con.sleep(3000);
						} else if (intBet <= 0) {
							con.println("Invalid bet, try again.");
						} else {
							blnBetCheck = true;
						}
					}

					con.clear();
					con.println("Your cards:");
					String[] playerCardNames = MethodsFile.getCardNames(intPlayers);// get the card names and print them
					for (int i = 0; i < playerCardNames.length; i++) {
						if (!playerCardNames[i].equals("")) {
							con.println(playerCardNames[i]);
						}
					}

					intSum = MethodsFile.CalculateTotal(intPlayers);// calculate and print the player total
					con.println("Your total: " + intSum);

					con.println("\nDealer's card:");
					String[] dealerCardNames = MethodsFile.getCardNames(intDealer);// same applies to the dealer
					con.println(dealerCardNames[0]);
					intSumDealer = MethodsFile.CalculateTotal(intDealer);

					// check for blackjack
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
					
					// check for double down
					if (intSum == 9 || intSum == 10 || intSum == 11) {
						con.println("Would you like to double down? (Y/N)");
						char charDD = con.getChar();
						if (charDD == 'y' || charDD == 'Y') {
							if (intMoney >= 2 * intBet) {
								blndoubledDown = true;
								intBet += intBet;
								con.println("You doubled down! Your bet is now: $" + intBet);
								intPlayers[2] = intDeck[3];
								
								intSum = MethodsFile.CalculateTotal(intPlayers);
								playerCardNames = MethodsFile.getCardNames(intPlayers);
								
								con.sleep(1000);
								con.clear();
								con.println("Your cards:");
								for (int i = 0; i < playerCardNames.length; i++) {
									if (!playerCardNames[i].equals("")) {
										con.println(playerCardNames[i]);
									}
								}
								con.println("Your total is: " + intSum);
								con.sleep(3000);
							} else {
								con.println("You don't have enough money, double down failed."); // failsafe for not enough money
								blndoubledDown = false;
							}
						} else {
							con.println("You declined!");
						}
					}

					// player's turn, only runs if double down did not happen
					if (!blndoubledDown) {
						while (intSum < 22 && intCount < 5) {
							con.println("\nHit or Stand? (H/S)");
							char chrAction = con.getChar();
							if (chrAction == 'h' || chrAction == 'H') {// deals a card if the player hits
								intPlayers[intCount] = intDeck[intCount + 1];// dealing the cards
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
							} else if (chrAction == 's' || chrAction == 'S') {// exits loop if the player decides to stand
								break;
							}
						}
					}
					
					// check for 5 cards for the player
					if (intCount == 5 && intSum <= 21) {
						con.println("\nYou got 5 cards! 3x your bet!"); 
						intMoney += intBet * 2;
						blnskipDealerTurn = true;
						con.println("Current money: $" + intMoney);
						con.println("Play another round? (Y to continue)");
						char chrContinue = con.getChar();
						if (chrContinue != 'y' && chrContinue != 'Y') {
							blnkeepPlaying = false;
						}
						con.clear();
						continue;
					}
					
					// dealer's turn
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
					
					// final totals
					con.println("\n--- ROUND RESULT ---");
					con.println("Your total: " + intSum);
					con.println("Dealer's total: " + intSumDealer);

					// outcomes and payout
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
					
					// stops playing if the player is bankrupt
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
				
				// storing the player name and money and closing the file
				System.out.println("Saving leaderboard...");
				TextOutputFile lbOutput = new TextOutputFile("Leaderboard.txt", true);
				lbOutput.println(strName);
				lbOutput.println(intMoney);
				lbOutput.close();
				
				// reopens the file for input and counts to make an array of equal size
				TextInputFile lbInput = new TextInputFile("Leaderboard.txt");
				while (!lbInput.eof()) {
					strLBName = lbInput.readLine();
					intLBMoney = lbInput.readInt();
					intLBCount++;
				}
				lbInput.close();
				
				// reopens to store the values into a string array to sort 
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
				
				// outputs the sorted leaderboard back into the file
				TextOutputFile lbSorted = new TextOutputFile("Leaderboard.txt");
				for (int i = 0; i < intLBCount; i++) {
					lbSorted.println(strLB[i][0]);
					lbSorted.println(strLB[i][1]);
				}
				lbSorted.close();
				System.out.println("Leaderboard saved.");
				con.repaint();
			}
		}
	}
}
