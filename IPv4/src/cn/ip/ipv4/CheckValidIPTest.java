package cn.ip.ipv4;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CheckValidIPTest {

	boolean expected = true;
	String input = "";
	
	public CheckValidIPTest(boolean expected, String input) {
		this.expected = expected;
		this.input =input;
	}
	
	@Parameters
	public static Collection userData(){  
		return Arrays.asList(new Object[][]{  
	                {true,"192.168.0.1"},  
	                {false,"0.0.0.0"},
	                {true,"10.0.11.0"},
	                {false,"320.176.266.256"},  
	                {false,"256.0.0.0"},
	                {false,"166.258.369.428"},
	                { true,"156.222.220.1"}
	        }) ;  
	}
	@Test
	public void testValidIP() {
//		fail("Not yet implemented");
		assertEquals(expected, new CheckIP().checkIPV4(input));
	}
}
