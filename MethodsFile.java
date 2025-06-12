/*
Author: Tommy Yan
Program name: Blackjack
Last modified: 2025/06/12
Version number: v3.5
*/

public class MethodsFile {	
	public static int [][] deck() {
		// creating the array for the deck
		int intCards [] [];
		intCards = new int [52][3];
		
		int intCard = 1;
		int intSuit = 1;
		int intRow;
		
		//cycles through 1 to 13 for the card, and 1 to 4 for the suit
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
		//returns the deck
		return intCards;
	}
	
	public static int [][] sort(int intDeck [][]) {

		int intTemp [] = new int [3];
		int intCount;
		int intCount2;
		int intCount3;
		// massive for loops, bubble sorts based on the random int on the third column
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
		// returns the sorted deck
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
				continue;// skips empty arrays
			}
			if (intCardValue == 1) {
				intTotal += 11;
				intAce++; // tallies the number of aces
			} else if (intCardValue >= 11) {
				intTotal += 10; // all face cards are 10
			} else {
				intTotal += intCardValue;
			}
		}
		while (intTotal > 21 && intAce > 0) {
			intTotal -= 10; // if the total is bigger than 21 but there is an ace, subtract 10 to make the ace worth 1
			intAce--;
		}
		// return the total
		return intTotal;
	}
	
	public static String[][] leaderboard(String strLB[][], int intLBCount) {
		String strTemp[] = new String[2];
		int intCount;
		int intCount2;
		
		// same logic as sorting the deck sorting, but now based on money and descending instead of ascending
		for (intCount = 0; intCount < intLBCount - 1; intCount++) {
			for (intCount2 = 0; intCount2 < intLBCount - 1; intCount2++) {
				if (Integer.parseInt(strLB[intCount2][1]) < Integer.parseInt(strLB[intCount2 + 1][1])) {
					// swap name
					strTemp[0] = strLB[intCount2][0];
					strTemp[1] = strLB[intCount2][1];

					strLB[intCount2][0] = strLB[intCount2 + 1][0];
					strLB[intCount2][1] = strLB[intCount2 + 1][1];

					strLB[intCount2 + 1][0] = strTemp[0];
					strLB[intCount2 + 1][1] = strTemp[1];
				}
			}
		}
		// returns sorted array
		return strLB;
	}
	public static String[] getCardNames(int intHands [][]) {
		int intCount;
		int intRank;
		int intSuit;
		String[] strRanks = {"", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10","Jack", "Queen", "King"};
		String[] strSuits = {"", "Diamonds", "Clubs", "Hearts", "Spades"};
		String[] cardNames = new String[intHands.length];
		
		// based on the values in the int player arrays, make a string array of what cards is in it
		for (intCount = 0; intCount < intHands.length; intCount++) {
			intRank = intHands[intCount][0];
			intSuit = intHands[intCount][1];
			if (intRank == 0 || intSuit == 0) {
				cardNames[intCount] = ""; // skip empty cards
			} else {
				cardNames[intCount] = strRanks[intRank] + " of " + strSuits[intSuit];
			}
		}
		// returns the card names
		return cardNames;
	}
}
