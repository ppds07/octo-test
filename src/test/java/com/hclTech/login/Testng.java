package com.hclTech.login;


import org.testng.annotations.*;
 
public class Testng {
	
	
		@BeforeSuite
	    public void beforeSuite() {
	        System.out.println("Before Suite: Setup global resources.");
	    }
	 
		@BeforeClass
	    public void beforeClass() {
	        System.out.println("Before Class: Setup for BasicTest.");
	    }
	 
		@Test
	    public void testExample() {
	        System.out.println("Running testExample...");
	        assert "Hello".equals("Hello");
	    }
	 
		@AfterClass
	    public void afterClass() {
	        System.out.println("After Class: Cleanup for BasicTest.");
	    }
	 
		@AfterSuite
	    public void afterSuite() {
	        System.out.println("After Suite: Cleanup global resources.");
	    }
	 
	 
	}
	 


