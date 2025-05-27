import arc.*;

public class CPTTommy {
	public static void main(String[] args){
		Console con = new Console();
		
		int intMoney = 1000;
		int intCount = 2;
		int intHits = 0;
		String strName;
		String strInput;
		
		int intDeck [][] = deck();
		int intPlayers [][] = new int [5][2];
		int intDealer [][] = new int [5][2];
	
		con.println("Blackjack");
		con.println("Play (P)");
		con.println("Leaderboards (L)");
		con.println("Quit (Q)");
		
		intPlayers [0][0] = intDeck [0][0];
		intPlayers [0][1] = intDeck [0][1];
		intPlayers [1][0] = intDeck [1][0];
		intPlayers [1][1] = intDeck [1][1];
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				con.print(intPlayers[i][j] + " ");
			}
			con.println();
		}
		
		for (intHits = 0; intHits < 3; intHits++) {
		if (con.readLine().equalsIgnoreCase("hit")) {
			intPlayers [intCount][0] = intDeck [intCount][0];
			intPlayers [intCount][1] = intDeck [intCount][1];
			
			for (int i = 0; i < intCount+1; i++) {
				for (int j = 0; j < 2; j++) {
					con.print(intPlayers[i][j] + " ");
				}
			con.println();
			}
		
			intCount++;
		}
		con.println(intCount);
		}
	}
	public static int [][] deck() {
		
		int intCards [] [];
		intCards = new int [52][3];
		
		int intCard = 1;
		int intSuit = 1;
		int intRow;
		
		for (intRow = 0; intRow < 52; intRow++) {
			intCards[intRow][0] = intCard;
			intCards[intRow][1] = intSuit;
			intCards[intRow][2] = (int)(100*Math.random());
			intCard++;
			
			if (intCard == 14) {
				intCard = 1;
				intSuit++;
			}
		}
		return intCards;
	}
}
