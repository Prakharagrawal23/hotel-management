package com.restapiProject.hotalMgmt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class junitconcept {
//	* open source testing framework for the java prohramming language 
//	* tool for developers to create tests - units of the code 
//	ensure the quality and manitainnability of the java codes 
	
//	light weight testing framework, we use it for unit testing 
//	write the automated test that validate small peices of code(units)(classes)
	
//	benefits - exception , enables to document expected expected behavior, enable test driven  develop ment  tod
	
//	junit5 - more modular approach, backward compatibility 
//	spring boot -> spring-boot-stater-test -> bundles junit and test utilies
	
//	? add junit5 dependancy -> org.junit.jupiter - scope -> test :
	
//	junit - test framework - architechture 
//	module -> platform - discovers and run tests
//			-> jupiter - new programming model(annotation,assertions
//			->vintage - rund junit 3/4 test on junits platform
	// concepts : test discorvery, execution, lifecycle method, assertion....
	
// junit - class - erite  test method - annotated with then @test 
//		pattern AAA -> A : Arrange -> prepare data/mocks
//					   A : act -> invoke method under test
//					   A : assert -> verify the outcoem using assertion 
	
	
	public class calculatoe{
		public int add(int a, int b) {
			return a+b;
		}
	}
	
	
	public class calculatorTEST{
		
		@Test
		void testAdd() {
			calculatoe cal = new calculatoe();
			int result = cal.add(12, 13);
			assertEquals(25, result, "12+13 should equal 25");
				// expected  actual 
			 // check - excepted == actual -> dn by assertEquals
		}
	}
	
	// test case : represent a specific scenario or condition to be tested 
//	test cases are implement as methods within a test class, annotation with @test
	
//	annotation
	// @test -> marks a method as a unit test
	// @beforeEach -> executes a method before each test method in a class
	// @afterEach -> executes a method after each test method in a class
	// @beforeAll -> executes a method once before all test method in a class
	// @afterAll -> executes a method once after all test method in a class
	/*
	Assertions :
		 *  -> are used within test methods to verify outcome.
		 *  assertEquals(expected, actual) : Check if two values are equal
		 *  assertTrue(condition) : Check if a condition is true
		 *  assertNotNull(object) : Check if object is not null
		 *  assertThrows(expectedException, executable) : Verify that a specific exception is thrown
		 *  
		 *  Assumptions :
		 *  -> skip tests under certain conditions
		 *  Assumptions.assumeTrue(...)
		 *   
		 *  Junit Test Runners : Junit includes test runners responsibe for executing test cases and reporting results
		 *  
		 *  Earlt bug detection
		 *  improved code quality and maintainance
		 *  
		 *  Junit - Suite Test
		 *  -> run a group of test classes together as one suite
		 *  -> logical groupings, integration test sets
		 *  -> Junit5 -> @Suite
		 *  
		 *  Junit - Parameterized Test
		 *  -> run same test logic with multiple inputs. Reduce duplication and edge cases
		 *  Junit 5 provides @ParamterizedTest - @ValueSource @MethodSource
		 *  define input set, the test run once per input - assert expect outcome per input
	*/
}
