package edu.mytest.main;

/**
 * superMe
 * superclass
 * @author Goran Lindqvist
 *
 */
public class SuperMe {

	/**
	 * kan ändras av klass som ärver vi morf
	 * @param iNr1
	 * @return
	 */
	public int addMeOne(int iNr1){
		int iLnr;
		iLnr = iNr1+1;
		return iLnr;
	}

	/**
	 * kan inte ändras av någon (final), körs default
	 * @param iNr1
	 * @return
	 */
	protected final int addMeTwo(int iNr1){
		int iLnr;
		iLnr = iNr1+2;
		return iLnr;
	}

	protected String myName(String fNamn, String eNamn) {
		return fNamn +", "+ eNamn + " :SUPER";
	}
	

}
