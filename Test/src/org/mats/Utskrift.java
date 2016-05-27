package org.mats;

public class Utskrift {

	private String namn;
	
	/**
	 * constructor
	 * @param a
	 * @param b
	 */
	public Utskrift(String a, String b){
		namn = a+ " " +b;
	}
	/**
	 * constructor
	 */
	public Utskrift(){};
	
	public void setString(String sValue){
		this.namn = sValue;
	}
	
	public String skrivUt(){
		//System.out.println("Hello!");
		return namn;
	}

}
