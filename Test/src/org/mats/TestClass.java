package org.mats;

public class TestClass {
	
	
	public static void main (String args[]){
		int tuna = 5;
		int bass = 18;
		System.out.println(tuna++);
		System.out.println(tuna);
		System.out.println(bass);
		Utskrift prv = new Utskrift();
		prv.setString("mats-ola 20160527 09:02");
		System.out.println(prv.skrivUt());		
	}
}



