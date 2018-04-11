package cn.ip.ipv4;

public class CheckIP{
	public static void main(String[] args) {
		
		CheckIP  cip=new CheckIP();
		System.out.println(cip.checkIPV4("192.168.1.1"));
		
	}
	
	public   boolean checkIPV4(String ipv4) {
		
		

		if (ipv4 == null || ipv4.length() == 0) {
			System.out.println("输入的为空!");
			
			


			return false;// 字符串为空或者空串

		}
		String[] parts = ipv4.split("\\.");// 按.对String进行分割
		if (parts.length != 4) {

		     try {
				throw new Exception("地址长度不够,请以.分隔");
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			System.out.println("地址长度不正确.");
			return false;// 分割开的数组根本就不是4个数字
		}

		for (int i = 0; i < parts.length; i++) {
			try {
				int n = Integer.parseInt(parts[i]); // 把String 转换为int

				if (n < 0 || n > 255) {
					return false;// 数字不在正确范围内E
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println("输入的地址含有非数字.");
				return false;// 转换数字不正确
			}
		}
		
		
		
		
		
		if (Integer.parseInt(parts[0]) == 10) {
			System.out.println("10.0.0.0到10.255.255.255是保留地址,仅允许局域网使用.");
		}

		else if ((Integer.parseInt(parts[0]) == 172) & (Integer.parseInt(parts[1]) >= 16)
				& (Integer.parseInt(parts[1]) <= 31)) {

			System.out.println("172.16.0.0到172.31.255.255是保留地址,仅允许局域网使用.");


		}

		else if ((Integer.parseInt(parts[0]) == 192) & ((Integer.parseInt(parts[1]) == 168))) {

			System.out.println("192.168.0.0到192.168.255.255是保留地址,仅允许局域网使用.");

		}
		
		
		

		if ((Integer.parseInt(parts[0]) == 0)&  (Integer.parseInt(parts[1]) == 0)& (Integer.parseInt(parts[2]) == 0)& (Integer.parseInt(parts[3]) == 0) ) {
			System.out.println("0.0.0.0不允许使用.");
			return false;
		}

		else if ((Integer.parseInt(parts[0]) == 127) & (Integer.parseInt(parts[1]) ==0)& (Integer.parseInt(parts[2]) ==0)& (Integer.parseInt(parts[3]) ==1)) {

			System.out.println("127.0.0.1不允许使用.");

			return false;

		}

		else if ((Integer.parseInt(parts[0]) == 255) & ((Integer.parseInt(parts[3]) == 255))) {

			System.out.println("255.*.*.255不允许使用");
			return false;

		}
		

		return true;

	}

	
	
	

}
