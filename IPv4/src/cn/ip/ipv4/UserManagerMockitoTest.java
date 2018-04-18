package cn.ip.ipv4;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;  

public class UserManagerMockitoTest {

	@Test
	public void testGetCityCode(){
		AddressService as = mock(AddressService.class);
		Mockito.when(as.findAddress("yld")).thenReturn("nanjing");
		UserManager um = new UserManager();
		um.addressService = as;
		assertEquals(1,um.getCityCode("yld"));
		verify(as,times(1)).findAddress("yld");

}
}