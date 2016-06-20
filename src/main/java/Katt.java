
public class Katt extends Djur {

	private String leksak = "tygråttan";
	public Katt(String namn, String favFood){
		super(namn, favFood);
	}
	
	public void lekMed(){
		System.out.println("mjau... " + this.leksak);
	}
}
