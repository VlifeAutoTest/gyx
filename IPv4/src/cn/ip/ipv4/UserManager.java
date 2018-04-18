package cn.ip.ipv4;

public class UserManager {
	
	public AddressService addressService;
	
	public int getCityCode(String userName){
	int cityCode=0;
	if("nanjing".equals(addressService.findAddress(userName)))
		cityCode = 1;
		return cityCode;
	}

}
