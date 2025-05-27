import arc.*;

public class CPTTommy {
	public static void main(String[] args){
		Console con = new Console();
		
		int intMoney = 1000;
		String strName;
		int intCards [] [];
		
		intCards = new int [3][52];
		
		int intCard = 1;
		int intSuit = 1;
		int intRow;
		
		for (intRow = 0; intRow < 52; intRow++) {
			intCards[0][intRow] = intCard;
			intCards[1][intRow] = intSuit;
			intCards[2][intRow] = (int)(100*Math.random());
			intCard++;
			
			if (intCard == 14) {
				intCard = 1;
				intSuit++;
			}
		}
			
			
	}
}
