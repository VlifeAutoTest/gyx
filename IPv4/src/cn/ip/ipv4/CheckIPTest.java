package cn.ip.ipv4;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;   

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class CheckIPTest {

	boolean expected = true;
	String input = "";
	
	public CheckIPTest(boolean expected, String input) {
		this.expected = expected;
		this.input =input;
	}
	
	@Parameters
	public static Collection userData(){  
		return Arrays.asList(new Object[][]{  
	                {false,"10.0"},  
	                {false,"0.0.0.0"},
	                {false,""}
	        }) ;  
	}
	@Test
	public void testInvalidIP() {
//		fail("Not yet implemented");
		assertEquals(expected, new CheckIP().checkIPV4(input));
	}
}

