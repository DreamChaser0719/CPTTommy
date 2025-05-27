import arc.*;

public class CPTTommy {
	public static void main(String[] args){
		Console con = new Console();
		
		int intMoney = 1000;
		String strName;
		
		int intDeck [][] = deck();
	
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
