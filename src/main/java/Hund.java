
public class Hund extends Djur{

	public String sfavLeksak = "pinge Pingvin";
	
	/**
	 * constructor 1
	 */
	public Hund() {}
	
	/**
	 * constructor 2
	 * @param a
	 */
	public Hund(String namn, String favFood, String favLeksak){
		super(namn, favFood);
		this.sfavLeksak =  favLeksak;
		
	}
	
	public void lekMed() {
		System.out.println("yep... " +sfavLeksak);
	}
	
	public void gaRunt(){
		System.out.println(this.getNamn() + " skutt och hopp");
	}
	
	public String getLeksak(){
		return this.getLeksak();
	}
}
