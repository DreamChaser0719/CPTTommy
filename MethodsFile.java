import arc.*;
	
public class MethodsFile {	
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
	
	public static int [][] sort(int intDeck [][]) {

		int intTemp [] = new int [3];
		int intCount;
		int intCount2;
		
		for (intCount2 = 0; intCount2 < 52-1; intCount2++) {
			for (intCount = 0; intCount < 52-1; intCount++) {
				if (intDeck [intCount][2] > intDeck [intCount+1][2]) {
					intTemp [0] = intDeck [intCount][0];
					intTemp [1] = intDeck [intCount][1];
					intTemp [2] = intDeck [intCount][2];
					intDeck [intCount][0] = intDeck [intCount+1][0];
					intDeck [intCount][1] = intDeck [intCount+1][1];
					intDeck [intCount][2] = intDeck [intCount+1][2];
					intDeck [intCount+1][0] = intTemp [0];
					intDeck [intCount+1][1] = intTemp [1];
					intDeck [intCount+1][2] = intTemp [2];
				}
			}
		}
		return intDeck;
	}
}
