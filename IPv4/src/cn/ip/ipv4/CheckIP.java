package cn.ip.ipv4;

public class CheckIP{
	public static void main(String[] args) {
		
		CheckIP  cip=new CheckIP();
		System.out.println(cip.checkIPV4("192.168.1.1"));
		
	}
	
	public   boolean checkIPV4(String ipv4) {
		
		

		if (ipv4 == null || ipv4.length() == 0) {
			System.out.println("�����Ϊ��!");
			
			


			return false;// �ַ���Ϊ�ջ��߿մ�

		}
		String[] parts = ipv4.split("\\.");// ��.��String���зָ�
		if (parts.length != 4) {

		     try {
				throw new Exception("��ַ���Ȳ���,����.�ָ�");
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			System.out.println("��ַ���Ȳ���ȷ.");
			return false;// �ָ����������Ͳ���4������
		}

		for (int i = 0; i < parts.length; i++) {
			try {
				int n = Integer.parseInt(parts[i]); // ��String ת��Ϊint

				if (n < 0 || n > 255) {
					return false;// ���ֲ�����ȷ��Χ��E
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println("����ĵ�ַ���з�����.");
				return false;// ת�����ֲ���ȷ
			}
		}
		
		
		
		
		
		if (Integer.parseInt(parts[0]) == 10) {
			System.out.println("10.0.0.0��10.255.255.255�Ǳ�����ַ,�����������ʹ��.");
		}

		else if ((Integer.parseInt(parts[0]) == 172) & (Integer.parseInt(parts[1]) >= 16)
				& (Integer.parseInt(parts[1]) <= 31)) {

			System.out.println("172.16.0.0��172.31.255.255�Ǳ�����ַ,�����������ʹ��.");


		}

		else if ((Integer.parseInt(parts[0]) == 192) & ((Integer.parseInt(parts[1]) == 168))) {

			System.out.println("192.168.0.0��192.168.255.255�Ǳ�����ַ,�����������ʹ��.");

		}
		
		
		

		if ((Integer.parseInt(parts[0]) == 0)&  (Integer.parseInt(parts[1]) == 0)& (Integer.parseInt(parts[2]) == 0)& (Integer.parseInt(parts[3]) == 0) ) {
			System.out.println("0.0.0.0������ʹ��.");
			return false;
		}

		else if ((Integer.parseInt(parts[0]) == 127) & (Integer.parseInt(parts[1]) ==0)& (Integer.parseInt(parts[2]) ==0)& (Integer.parseInt(parts[3]) ==1)) {

			System.out.println("127.0.0.1������ʹ��.");

			return false;

		}

		else if ((Integer.parseInt(parts[0]) == 255) & ((Integer.parseInt(parts[3]) == 255))) {

			System.out.println("255.*.*.255������ʹ��");
			return false;

		}
		

		return true;

	}

	
	
	

}
