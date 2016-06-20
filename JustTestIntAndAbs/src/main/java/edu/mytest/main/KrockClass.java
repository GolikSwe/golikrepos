package edu.mytest.main;
/**
 * Abs-Klass Krockklass
 * Varje metod behövs ej implenteras då abstract klass används KrockKlass -> Fordon
 * @author Goran Lindqvist
 *
 */
public abstract class KrockClass {

	public String farg3 = "Klar Röd";
	boolean fordonKorklar = true;
	
	public void duKrockat() {
		this.fordonKorklar = false;
	}
	//impl i Fordon
	public abstract void setKrockStyrka(int styrka);
	
	public abstract int getKrockStyrka();
}
