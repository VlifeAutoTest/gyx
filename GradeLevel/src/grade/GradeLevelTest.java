package grade;



import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;



	@RunWith(Parameterized.class)
	public class GradeLevelTest {

		String expected = "A";
		int input = 98;
		
		public GradeLevelTest(String expected, int input) {
			this.expected = expected;
			this.input =input;
		}
		
		@Parameters
		public static Collection userData(){  
			return Arrays.asList(new Object[][]{  
				{"A",93},
				{"B",88},
				{"C",78},
				{"C",63},
				{"D",38},
				{"B",58}
		        }) ;  
		}
	
	@Test
	public void test() {
		assertEquals(expected, new Gradelevel().studentLevel(input));
	}

}
