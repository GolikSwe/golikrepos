
public class RunMeForTest {

	public static void main(String[] args) {
		//instans super Djur
		System.out.println("..DJUR..");
		Djur genericDjur = new Djur();
		System.out.println(genericDjur.getNamn());
		System.out.println(genericDjur.sFavFood);
		
		//instans hund
		System.out.println("\n..HUND..");
		Hund kebbe = new Hund("kebbe bebbe", "leverpastej", "bollen");
		kebbe.getNamn();
		System.out.println(kebbe.sFavFood);
		System.out.println(kebbe.sfavLeksak);
		System.out.println("\n..KATT..");
		Katt mirre = new Katt("mirre", "torsk");
		System.out.println(mirre.getNamn());
		mirre.eat();
		mirre.lekMed();
		
		//instans
		Djur sappo = new Hund("Sappo", "Blodpudding", "tygbenet");
		accDjur(sappo);
	}
	
	/**
	 * accDjur
	 * @param randDjur
	 */
	public static void accDjur(Djur randDjur){
		System.out.println("\n..DJUR -> HUND..");
		System.out.println(randDjur.getNamn());
		System.out.println(randDjur.sFavFood);
		randDjur.eat();
		System.out.println("polyform");
		randDjur.gaRunt();
		System.out.println("\n..TYPE CAST HUND..");
		//type cast för få ut värde
		Hund tmpHund = (Hund) randDjur;
		System.out.println(tmpHund.sfavLeksak);				
	}

}
