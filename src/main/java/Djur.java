
/**
 * Superklass
 * @author Goran Lindqvist
 */
public class Djur{
	
	private String sNamn = "Djur";
	protected String sFavFood = "Hund kulor";
	
	/**
	 * constructor 1
	 */
	public Djur(){}
	
	public Djur(String namn, String favFood){
		this.andraNamn(namn);
		this.sFavFood = favFood;
	}	
	
	protected final void andraNamn(String nyttNamn) {
		this.sNamn = nyttNamn;
	}
	
	protected final String getNamn() {
		return this.sNamn;
	}
	
	public void eat(){
		System.out.println("MUMS... " +sFavFood);
	}
	
	public void gaRunt() {
		System.out.println(this.sNamn + " gå omkring, hoppa");
		
	}
} //end class
