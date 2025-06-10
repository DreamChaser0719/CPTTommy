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
		
		for (intCount = 0; intCount < 51; intCount++) {
			for (intCount2 = 0; intCount2 < 51; intCount2++) {
				if (intDeck [intCount2][2] > intDeck [intCount2+1][2]) {
					for (intCount3 = 0; intCount3 < 3; intCount3++) {
						intTemp [intCount3] = intDeck [intCount2][intCount3];
						intDeck [intCount2][intCount3] = intDeck [intCount2+1][intCount3];
						intDeck [intCount2+1][intCount3] = intTemp [intCount3];
					}
				}
			}
		}
		return intDeck;
	}
	
	public static int [][] basevalue(int intDeck [][]) {
		
		int intCount;
		
		for (intCount = 0; intCount < 52; intCount++) {
			if (intDeck [intCount][0] == 11 || intDeck [intCount][0] == 12 || intDeck [intCount][0] == 13) {
				intDeck [intCount][2] = 10;
			} else intDeck [intCount][2] = intDeck [intCount][0];
		}
		return intDeck;
	}
	
	public static int CalculateTotal(int[][] intPlayingDeck) {
		int intTotal = 0;
		int intAce = 0;
		int intCardValue;
		int intCount;

		for (intCount = 0; intCount < intPlayingDeck.length; intCount++) {
			intCardValue = intPlayingDeck[intCount][0];
			if (intCardValue == 0) {
				continue;
			}
			if (intCardValue == 1) {
				intTotal += 11;
				intAce++;
			} else if (intCardValue >= 11) {
				intTotal += 10;
			} else {
				intTotal += intCardValue;
			}
		}
		while (intTotal > 21 && intAce > 0) {
			intTotal -= 10;
			intAce--;
		}
		return intTotal;
	}
	
	public static String[][] leaderboard(String strLB[][], int intLBCount) {
		String strTemp[] = new String[2];
		int intCount;
		int intCount2;

		for (intCount = 0; intCount < intLBCount - 1; intCount++) {
			for (intCount2 = 0; intCount2 < intLBCount - 1; intCount2++) {
				if (Integer.parseInt(strLB[intCount2][1]) < Integer.parseInt(strLB[intCount2 + 1][1])) {
					// Swap name
					strTemp[0] = strLB[intCount2][0];
					strTemp[1] = strLB[intCount2][1];

					strLB[intCount2][0] = strLB[intCount2 + 1][0];
					strLB[intCount2][1] = strLB[intCount2 + 1][1];

					strLB[intCount2 + 1][0] = strTemp[0];
					strLB[intCount2 + 1][1] = strTemp[1];
				}
			}
		}
		return strLB;
	}
	public static String[] getCardNames(int intHands [][]) {
		int intCount;
		int intRank;
		int intSuit;
		String[] strRanks = {"", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10","Jack", "Queen", "King"};
		String[] strSuits = {"", "Diamonds", "Clubs", "Hearts", "Spades"};
		String[] cardNames = new String[intHands.length];
		for (intCount = 0; intCount < intHands.length; intCount++) {
			intRank = intHands[intCount][0];
			intSuit = intHands[intCount][1];
			if (intRank == 0 || intSuit == 0) {
				cardNames[intCount] = ""; // skip empty cards
			} else {
				cardNames[intCount] = strRanks[intRank] + " of " + strSuits[intSuit];
			}
		}
		return cardNames;
	}
}
