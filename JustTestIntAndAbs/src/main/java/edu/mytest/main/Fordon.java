package edu.mytest.main;
/**
 * Klass: Fordon
 * @author Goran Lindqvist
 *
 */
public class Fordon extends KrockClass implements BilDelar, Cloneable {

	int numWheels = 2;
	int styrka = 0;
	double theSpeed = 0;
	
	public Fordon(){};
	
	public Fordon(int wheels, double speed){
		this.numWheels = wheels;
		this.theSpeed = speed;
	}
	//***från interface BilDelar	
	public int getWheels(){
		return this.numWheels;
	}
	
	public void setWheels(int numWheels){
		this.numWheels = numWheels;
	}
	
	public double getSpeed(){
		return this.theSpeed;
	}
	
	public void setSpeed(double theSpeed){
		
	}
//////////////////////////////////////end
	
	//***Abstract method från abs klass
	public void setKrockStyrka(int styrka){
		this.styrka = styrka;
	}
	
	public int getKrockStyrka(){
		return this.styrka;
	}
//////////////////////////////////////end
	
	//Clone object
	public Object clone(){
		Fordon fordonPaHjul;
		try{
			fordonPaHjul = (Fordon) super.clone();
		}catch(CloneNotSupportedException cex){
			System.err.println("ERROR");
			return null;
		}
		return fordonPaHjul;
	}	
}
