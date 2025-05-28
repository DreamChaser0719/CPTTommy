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
		int intCount3;
		
		for (intCount2 = 0; intCount2 < 52-1; intCount2++) {
			for (intCount = 0; intCount < 52-1; intCount++) {
				if (intDeck [intCount][2] > intDeck [intCount+1][2]) {
					for (intCount3 = 0; intCount3 < 3; intCount3++) {
						intTemp [intCount3] = intDeck [intCount][intCount3];
						intDeck [intCount][intCount3] = intDeck [intCount+1][intCount3];
						intDeck [intCount+1][intCount3] = intTemp [intCount3];
					}
				}
			}
		}
		return intDeck;
	}
}
