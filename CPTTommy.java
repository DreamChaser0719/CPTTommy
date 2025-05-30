import arc.*;

public class CPTTommy {
	public static void main(String[] args){
		Console con = new Console();
		
		int intMoney = 1000;
		int intCount = 2;
		int intHits = 0;
		int intSum = 0;
		int intSumDealer = 0;
		String strName;
		String strInput;
		
		int intDeck [][] = MethodsFile.deck();
		intDeck = MethodsFile.sort(intDeck);
		//intDeck = MethodsFile.basevalue(intDeck);
		int intPlayers [][] = new int [5][2];
		int intDealer [][] = new int [5][2];
	
		con.println("Blackjack");
		con.println("Play (P)");
		con.println("Leaderboards (L)");
		con.println("Quit (Q)");
		
		intPlayers [0][0] = intDeck [0][0];
		intPlayers [0][1] = intDeck [0][1];
		//intPlayers [0][2] = intDeck [0][2];
		intDealer [0][0] = intDeck [1][0];
		intDealer [0][1] = intDeck [1][1];
		intPlayers [1][0] = intDeck [2][0];
		intPlayers [1][1] = intDeck [2][1];
		//intPlayers [1][2] = intDeck [1][2];
		
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
		
		for (intHits = 0; intHits < 3; intHits++) {
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
			} else if (chrInputMain == 's' || chrInputMain == 'S') {
				break;
			}
		}
		
		con.println("The dealer's card is: ");
		con.println(intDealer [0][0] + " " + intDealer [0][1]);
		if (intDealer [0][0] == 11 || intDealer [0][0] == 12 || intDealer [0][0] == 13) {
			intSumDealer += 10;
		} else { 
			 intSumDealer += intDealer [0][0];
		}
		
		intHits = 1;
		con.println("The dealer's new cards are:");
		
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
		}
		
		con.println("Dealer's sum is: " + intSumDealer);
	}
}
