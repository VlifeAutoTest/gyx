package cn.test.ont;

public class Test002 {

	public static void main(String[] args) {

		
		
		
		
		//2001:0410:0000: 0000:FB00:1400:5000:45FF 
		   //2001:0410 :: FB00:1400:5000:45FF 
		   
		   
		   //2001:0410:0000:0000:FB00:1400:5000:45FF 
		   //2001:410:: FB00:1400:5000:45FF 
		
		MyIP("2001:0410 :: FB00:1400:5000:45FF ");
		
		
		
		
		
}
	
	
	
	
	
	
	public static  void   MyIP(String ipv6){
		
		if (ipv6.contains("::")) {
			
			//判断是否含有1个以上::
			
			String [] temp=ipv6.split("\\::");
			
		if (temp.length>2) {
			System.out.println("地址不合法!含有多个::  ");
		
		}
		else{
			
		
			String[] temp1=temp[0].split("\\:");    // 2
			
			String [] temp2=temp[1].split("\\:");    // 4
			
			int num =8-temp1.length-temp2.length;//差几个字段      // 2
			
//			System.out.println(num);   //
			
			String  []  temp3  =new String [(temp1.length)+num];   //4
			
			for (int i = 0; i < temp1.length; i++) {

				temp3[i]=(temp1[i].trim()+":");
			}
			
			for (int i =temp1.length ; i < temp3.length; i++) {

				temp3[i]=":0";
				
			}
			
			String ip [] =new String [(temp3.length+temp2.length)];
			
			
			for (int i = 0; i < temp3.length; i++) {
				String string = temp3[i];
				
			}
			
			
			
			
			for(String aa:temp3){
				System.out.println(aa);
			}
			
		} 
		
		}
		
		
	}
	
	
	
}