package cn.ip.ipv4;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;   

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class CheckTest {

	boolean expected = true;
	String input = "";
	
	public CheckTest(boolean expected, String input) {
		this.expected = expected;
		this.input =input;
	}
	
	@Parameters
	public static Collection userData(){  
		return Arrays.asList(new Object[][]{  
	                {false,"320.176.266.256"},  
	                {false,"256.0.0.0"},
	                {false,"166.258.369.428"},
	                { true,"167.222.220.1"}
	        }) ;    
	}
	@Test
	public void testIP() {
//		fail("Not yet implemented");
		assertEquals(expected, new CheckIP().checkIPV4(input));
	}
}
	
