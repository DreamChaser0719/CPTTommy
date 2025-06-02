import arc.*;

public class CPTTommy {
	public static void main(String[] args){
		Console con = new Console();
		
		int intMoney = 1000;
		int intBet = 0;
		int intCount = 2;
		int intHits = 0;
		int intSum = 0;
		int intSumDealer = 0;
		String strName;
		String strInput;
		
		con.println("Blackjack");
		con.println("Play (P)");
		con.println("Leaderboards (L)");
		con.println("Quit (Q)");
			
		while (intMoney > 0) {
			
			int intDeck [][] = MethodsFile.deck();
			intDeck = MethodsFile.sort(intDeck);
			//intDeck = MethodsFile.basevalue(intDeck);
			int intPlayers [][] = new int [5][2];
			int intDealer [][] = new int [5][2];
			
			intPlayers [0][0] = intDeck [0][0];
			intPlayers [0][1] = intDeck [0][1];
			//intPlayers [0][2] = intDeck [0][2];
			intDealer [0][0] = intDeck [1][0];
			intDealer [0][1] = intDeck [1][1];
			intPlayers [1][0] = intDeck [2][0];
			intPlayers [1][1] = intDeck [2][1];
			//intPlayers [1][2] = intDeck [1][2];
			
			con.println("Enter your bet:");
			intBet = con.readInt();
			
			con.println();
			con.println("Your cards are:");
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					con.print(intPlayers[i][j] + " ");
				}
				if (intPlayers [i][0] == 11 || intPlayers [i][0] == 12 || intPlayers [i][0] == 13) {
					intSum += 10;
				} else intSum += intPlayers [i][0];
				con.println();
			}
			
			
			con.println("The cards currently adds up to " + intSum);
			

				while (intSum < 22) {
					char chrInputMain = con.getChar();
					if (chrInputMain == 'h' || chrInputMain == 'H') {
						intPlayers [intCount][0] = intDeck [intCount+1][0];
						intPlayers [intCount][1] = intDeck [intCount+1][1];
						con.println("Your new card is:");
						con.print(intPlayers[intCount][0] + " " + intPlayers[intCount][1]);
						con.println();
						if (intPlayers [intCount][0] == 11 || intPlayers [intCount][0] == 12 || intPlayers [intCount][0] == 13) {
							intSum += 10;
						} else { 
							 intSum += intPlayers [intCount][0];
						}
						con.println("Your new sum is: " + intSum);
						intCount++;
						intHits++;
					} else if (chrInputMain == 's' || chrInputMain == 'S') {
						break;
					} else if (intHits == 4) {
						break;
					}
				}
				
				con.println("The dealer's cards are: ");
				con.println(intDealer [0][0] + " " + intDealer [0][1]);
				if (intDealer [0][0] == 11 || intDealer [0][0] == 12 || intDealer [0][0] == 13) {
					intSumDealer += 10;
				} else { 
					 intSumDealer += intDealer [0][0];
				}
				
				intHits = 1;		
				
				while (intSumDealer < 17) {
					intDealer [intHits][0] = intDeck [intCount+1][0];
					intDealer [intHits][1] = intDeck [intCount+1][1];
					con.print(intDealer[intHits][0] + " " + intDealer[intHits][1]);
					con.println();
					if (intDealer [intHits][0] == 11 || intDealer [intHits][0] == 12 || intDealer [intHits][0] == 13) {
					intSumDealer += 10;
					} else { 
						intSumDealer += intDealer [intHits][0];
					}
					intHits++;
					intCount++;
					
					if (intHits == 4) {
						break;
					}
				}
				con.println("Dealer's sum is: " + intSumDealer);
				
				if (intSum > 21) {
					con.println("You busted!");
					intMoney = intMoney - intBet;
				} else if (intSumDealer > 21) {
					con.println("Dealer busted!");
					intMoney = intMoney + intBet;
				} else if (intPlayers[4][0] != 0) {
					con.println("You got 5 cards and did not bust! You got 3x bet!");
					intMoney = intMoney + intBet * 2;
				} else if (intDealer[4][0] != 0) {
					con.println("Dealer got 5 cards and did not bust! You lost 3x bet!");
					intMoney = intMoney - intBet * 2;
				} else if ((intPlayers[0][0] == 1 && intPlayers[1][0] == 10) || (intPlayers[0][0] == 1 && intPlayers[1][0] == 11) || (intPlayers[0][0] == 1 && intPlayers[1][0] == 12) || (intPlayers[0][0] == 1 && intPlayers[1][0] == 13) || (intPlayers[0][0] == 10 && intPlayers[1][0] == 1) || (intPlayers[0][0] == 11 && intPlayers[1][0] == 1) || (intPlayers[0][0] == 12 && intPlayers[1][0] == 1) || (intPlayers[0][0] == 13 && intPlayers[1][0] == 1)) {
					con.println("BLACKJACK! 3X BET!");
					intMoney = intMoney + intBet * 2;
				} else if (intSum > intSumDealer) {
					con.println("You won!");
					intMoney = intMoney + intBet;
				} else if (intSum < intSumDealer){
					con.println("You lost!");
					intMoney = intMoney - intBet;
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

		}
	}
}
