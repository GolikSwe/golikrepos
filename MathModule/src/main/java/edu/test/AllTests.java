package edu.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCalcPrimes.class, TestCalcStat.class, TestCalcArithmetric.class})
public class AllTests {}
