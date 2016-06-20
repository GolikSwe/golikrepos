package edu.mytest.main;
/**
 * 
 * No big deal, testar bara lite arv.
 * @author Goran Lindqvist
 *
 */
public class MyMainClass {

	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		testSc1();
		testSc2();
		info();
	}
	
	/**
	 * testSc1
	 */
	private static void testSc1() {
		System.out.println("****testSc1 start****");
		SimpleClass sc = new SimpleClass();
		String s1 = sc.myName("Arne", "Anka");
		Integer i1 = sc.addMeOne(22);
		System.out.println("namn: "+s1+", ålder: "+ i1);
	}
	
	/**
	 * testSc2
	 */
	private static void testSc2() {
		System.out.println("\n****testSc2 start****");
		SimpleClass2 sc2 = new SimpleClass2();
		String s1 = sc2.myName("Bertil", "Groda");
		Integer i1 = sc2.addMeTwo(22);
		System.out.println("namn: "+s1+", ålder: "+ i1);
	}
	
	/**
	 * info
	 */
	private static void info(){
		System.out.println("\n****info start****");
		SimpleClass sc1 = new SimpleClass();
		SimpleClass2 sc2 = new SimpleClass2();
		
		//klass 1
		System.out.println("****klass1****");
		System.out.println(sc1.getClass().getSimpleName());
		System.out.println("HashSum: " +sc1.getClass().hashCode());
		System.out.println(sc1.getClass().getSuperclass());
		System.out.println("HashSum: "+sc1.getClass().getSuperclass().hashCode());
		System.out.println(sc1.getClass().getPackage());
		
		//klass2
		System.out.println("\n****klass2****");
		System.out.println(sc2.getClass().getSimpleName());
		System.out.println("HashSum: " +sc2.getClass().hashCode());
		System.out.println(sc2.getClass().getSuperclass());
		System.out.println("HashSum: "+sc2.getClass().getSuperclass().hashCode());
		System.out.println(sc1.getClass().getPackage());		
	}
}

