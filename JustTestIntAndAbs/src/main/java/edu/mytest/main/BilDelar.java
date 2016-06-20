package edu.mytest.main;
/**
 * interface till Fordon, alla metoder implenteras, BilDelar -> Fordon.
 * @author Goran Lindqvist
 *
 */
public interface BilDelar {

	String farg1 = "Svart";
	String farg2 = "Mörk Blå";

	public abstract int getWheels();
	public abstract void setWheels(int numWheels);	
	public abstract double getSpeed();
	public abstract void setSpeed(double speed);
	
}
