package edu.mytest.main;
/**
 * @see En litet testprojekt med interface samt en abstrakt klass.
 * Mest gjord för jag fick en stund över.
 * @author Goran Lindqvist
 *
 */
public class MyMainClass {

	public static void main(String[] args) {
		runMe1();
		runMe2();
//		testClone();
		info();
	}
	
	
	/**
	 * runMe1
	 */
	private static void runMe1() {
		System.out.println("****runMe1 start****");
		Fordon fd = new Fordon(4, 230);
		//interface -> Fordon
		System.out.println("MX-5 färg: " + BilDelar.farg2);
		System.out.println("antal hjul: " +fd.numWheels);
		System.out.println("toppfart: " +fd.getSpeed());
		//abstract class -> Fordon
		fd.setKrockStyrka(70);
		System.out.println("krockstyrka: " +fd.getKrockStyrka());
		//interface -> Fordon
		fd = new Fordon(2, 260);
		System.out.println("\nFZ-8 färg: " + fd.farg1);
		System.out.println("antal hjul: " +fd.numWheels);
		System.out.println("toppfart: " +fd.getSpeed());
		//abstract class -> Fordon
		fd.setKrockStyrka(40);		
		System.out.println("krockstyrka: " +fd.getKrockStyrka());
		//interface -> Fordon
		fd = new Fordon(4, 185);
		System.out.println("\nI30 färg: " + fd.farg3);
		System.out.println("antal hjul: " +fd.numWheels);
		System.out.println("toppfart: " +fd.getSpeed());
		//abstract class -> Fordon
		fd.setKrockStyrka(90);		
		System.out.println("krockstyrka: " +fd.getKrockStyrka());
		
//		System.out.println(fd.fordonKorklar);		
	}
	
	/**
	 * runMe2
	 */
	private static void runMe2(){
		System.out.println("\n****runMe2 start****");
		Object objF1 = new  Fordon();
		Fordon for1 = new Fordon();
		
		//är obj lika? kör equals eller hashsum.
		System.out.println(objF1.equals(for1));
		System.out.println("objF1 hashsum: " + objF1.hashCode() + " : " + objF1.getClass());
		System.out.println("for1 hashsum: " + for1.hashCode() + " : " + for1.getClass().getName());
		System.out.println();
		System.out.println(for1.getClass());
		
		//send obj till
		sendMeObj(objF1, for1);
		//för att nå metoder måste object typecast
	//	System.out.println(((Fordon) objF1).theSpeed);
	}

	/**
	 * 
	 * @param giveMeObj1, object.
	 * @param giveMeObj2, object.
	 */
	private static void sendMeObj(Object giveMeObj1, Object giveMeObj2) {
		System.out.println("\n****sendMeObj start****");
		//typecast för att få data ur metod
		System.out.println(((Fordon)giveMeObj1).getWheels());
		
		//jmf två obj (klasser)
		if(giveMeObj1.getClass() == giveMeObj2.getClass()){
			System.out.println("klasserna är samma");
		}
		//tar ut superklass för object
		System.out.println(giveMeObj1.getClass().getSuperclass() +" : " + giveMeObj2.getClass().getSuperclass());
		
	}
	
	private static void testClone(){
		System.out.println("\n****testClone start****");
		Fordon fd = new Fordon();
		fd.setWheels(6);
		Fordon clone_fd = (Fordon) fd.clone();
		System.out.println("fd hjul: " + fd.getWheels() +" HASH: " +fd.hashCode()+ 
		                  "\nclone_fd hjul: " + clone_fd.getWheels() +" HASH: " +clone_fd.hashCode());
	}
	
	private static void info(){
		System.out.println("\n****info start****");
		Fordon fd1 = new Fordon();
		Object objFordon = new Fordon();
		
		//klass 1
		System.out.println("****klass1****");
		System.out.println(fd1.getClass().getSimpleName());
		System.out.println("HashSum: " +fd1.getClass().hashCode());
		System.out.println(fd1.getClass().getSuperclass());
		System.out.println("HashSum: "+fd1.getClass().getSuperclass().hashCode());
		System.out.println(fd1.getClass().getPackage());
		
		
		//klass2
		System.out.println("\n****klass2****");
		System.out.println(objFordon.getClass().getSimpleName());
		System.out.println("HashSum: " +objFordon.getClass().hashCode());
		System.out.println(objFordon.getClass().getSuperclass());
		System.out.println("HashSum: "+objFordon.getClass().getSuperclass().hashCode());
		System.out.println(objFordon.getClass().getPackage());		
	}
	
}

