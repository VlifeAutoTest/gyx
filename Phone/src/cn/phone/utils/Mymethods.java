package cn.phone.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Element;

import cn.phone.data.Data;

public class Mymethods {

	// ��ȡָ��device���豸����״̬!!

	public static Boolean GetStatus() {

		Boolean status = false;

		try {
			Process ss = Runtime.getRuntime().exec("adb devices");
			BufferedReader in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
			String line;
			String content = "";

			while ((line = in.readLine()) != null)
				content = content + line;

			if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")) {

				if (content.contains(Data.PhoneIP))
					status = true;

			}
			if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {

				if (content.contains(Data.PhoneDevice))
					status = true;
			}

			ss.destroy();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

	//

	// ��EXCEL���ص�String���͵ĵ绰��ת��ΪString�͵�����!!

	public static String[] StringToStringArray(String StringPhoneNum) {

		int NumLength = StringPhoneNum.length(); // 10086 5
		String Num[] = new String[NumLength]; // 4
		for (int i = 0; i < NumLength; i++) { // 0 1 2 3 4

			String temp = StringPhoneNum.substring(i, i + 1);

			Num[i] = temp.trim();

		}

		return Num;

	}

	// �ȴ��ӳ�
	public static void wait(int i) {

		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

	// �����ж��ƶ���ť�Ƿ����!

	public static Boolean elementExitYesOrNo(String elementExitYesOrNoAttrribute, String elementExitYesOrNoName) {

		Boolean boo = false;

		ADB.getJMXX(Data.ANDROIDPATH);

		Mymethods.wait(1000);
		File file = new File(Data.PCPATH);
		new File(file.getParent()).mkdirs();
		ADB.pull(Data.ANDROIDPATH, Data.PCPATH);
		Mymethods.wait(500);

		Element ele = XMLMethods.getNeedElement(XMLMethods.WhereisXML(Data.PCPATH), elementExitYesOrNoAttrribute,
				elementExitYesOrNoName);

		if (ele != null) {

			boo = true;

		}
		return boo;

	}
	
	
	
	
	
	
	//��ȡ�ڴ���Ϣ
	public static String GetMemory(String packageName){  

		String str3=null;
          try {
			Runtime runtime = Runtime.getRuntime();  
			  Process proc = runtime.exec(" adb shell dumpsys meminfo "+packageName);  
			  proc.waitFor();
			      BufferedReader in = new BufferedReader(new InputStreamReader( proc.getInputStream()));  
			      StringBuffer stringBuffer = new StringBuffer();  
			      String line = null;  
			      while ((line = in.readLine()) != null) {  
			          stringBuffer.append(line);  
  
			      }  
			      
			      String str1=stringBuffer.toString();
			      String str2=str1.substring(str1.indexOf("TOTAL")+5,str1.indexOf("TOTAL")+14).trim();
			    str3=str2;

		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
        return str3;  
    }  
	
	
	
	//ȡCPU
	
	
	//��ȡPID
	// ��ȡ���̵�pid
	public static String  getPID(String packageName) {
		
		String temp=null;

		try {
			Process ss = Runtime.getRuntime().exec("cmd.exe  /c adb shell ps | findstr "+ packageName);
			ss.waitFor();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
			String line;
			String content = "";

			while ((line = in.readLine()) != null){
				
				content = content + line+"%";
				
			}
			System.out.println(content);
			String[] str=content.split("\\%");
			System.out.println(str.length);
			for (int i = 0; i < str.length; i++) {
				String str2=str[i].trim();
				int len=packageName.trim().length();
				if (str2.length()-len==str2.indexOf(packageName.trim())) {
					String Pid =str[i].substring(10,16);
					temp=Pid.trim();
					break;
				}
			}
//			String Pid =content.substring(7,15);
			
			
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} 
		
		
		return temp;
		

		
	}
	
	
	//UID
	
	public static String getUID(String packageName){
		
		String pid=Mymethods.getPID(packageName);
		String uid=null;
		
		try {
			Process ss = Runtime.getRuntime().exec("cmd.exe  /c adb shell cat proc/"+pid+"/status");
			ss.waitFor();
			BufferedReader in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
			String line;
			String content = "";

			while ((line = in.readLine()) != null){
				
				content = content + line+" ";
				
			}
			String str1=content.substring(content.indexOf("Uid")+4,content.indexOf("Gid") ).trim();
			String str2=str1.substring(0, str1.length()/2);
			String str3=str2.substring(0, str2.length()/2);
			
			uid=str3.trim();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} 
		
		
		
	     return uid;	
		
	}
	
	
	//��ȡtcp������Ϣ   String [0] revieve String [1] send
	public static String [] getFlow(String packageName) {
			
			String str[] =new String[2];
			try {
				String uid=Mymethods.getUID(packageName);
				Runtime runtime = Runtime.getRuntime();
				Process proc = runtime.exec("adb shell cat proc/uid_stat/"+uid+"/tcp_rcv");
				BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				String line=null;
				String recieve = "";

				while ((line = in.readLine()) != null){
					
					recieve = recieve + line;
					
				}
				
				
				str[0]=recieve;
				
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			try {
				String uid=Mymethods.getUID(packageName);
				Runtime runtime = Runtime.getRuntime();
				Process proc = runtime.exec("adb shell cat proc/uid_stat/"+uid+"/tcp_snd");
				BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				String line=null;
				String send = "";

				while ((line = in.readLine()) != null){
					
					send = send + line;
					
				}
				
				str[1]=send;
				
				
				
				
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
			
			return str;
			
		}
	
		
		//��ȡCPU��Ϣ
		
		
		// ��ȡ�����cpuռ�����
		public static  String getCPU(String packageName) {

			String cpu = null;
			try {

				Runtime runtime = Runtime.getRuntime();
				Process proc = runtime.exec("adb shell dumpsys cpuinfo |findstr "+ packageName);
			
					BufferedReader in = new BufferedReader(new InputStreamReader(
							proc.getInputStream()));
					String str=null;
					String line = null;
					while ((line = in.readLine()) != null) {
						str=str+line;

					}

					
					String cp = str.substring(0, str.indexOf("%"));
					cpu=cp.trim();
				

				} catch (Exception e) {
					System.err.println(e);
				} 

			return cpu;

			
		}
	


/*idx  id
 * iface��������ӿ�
accttaghex����socket
uidtagint��UID
cnt_setʵ���Ͼ���һ����־λ��0����ǰ̨������1�����̨����
rx_bytes��r����receive���ǽ�������   6��
tx_bytes��t����transmit���Ǵ������� 8��
 */


//��ȡӦ��������������Ϣ 

	public static void getAllFlow(String packageName) {

		String uid = Mymethods.getUID(packageName);

		
			Process proc=null;
			try {
				Runtime runtime = Runtime.getRuntime();
				proc = runtime.exec("adb shell cat /proc/net/xt_qtaguid/stats | grep " + uid);
				proc.waitFor();
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}

			StringBuffer sb=null;
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				String str = "";
				sb = new StringBuffer();
				String line = null;
				while ((line = in.readLine()) != null) {
//				str = str + line;
					
					sb.append(line);
					sb.append("%");

				}
				in.close();
				proc.destroy();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
//			String temp[]=str.split("\\$");
//			System.out.println(temp[0]);
//			System.out.println(temp[1]);
			System.out.print(sb.toString());
		

	}
	
	
	//��ȡ֡����ƽ��ֵ
	
	public static  double getAvg(String packageName){
		
		List <Double>list =Mymethods.getzhenshu(packageName);
		double a=0;
		for(double temp:list){
			a=a+temp;
		}
		a=a/list.size();
		
		return a;
	}
	
	
	
	//��ȡ֡��
	
	public static List<Double>  getzhenshu(String packageName){
		
			new File("C:\\data").mkdirs();
		   List <Double> list =new LinkedList<>();
			String value="";	
			try {
				Process ss = Runtime.getRuntime().exec("cmd.exe  /c adb shell dumpsys gfxinfo  "+packageName);
//				ss.waitFor();
				
				BufferedInputStream  bis =new BufferedInputStream(ss.getInputStream());
				String str="";
				byte[] b=new byte [1024];
				int a=0;
				while ((a=bis.read(b))!=-1){
					String temp=new String (b,0,a);
					str=str+temp;
				}

				bis.close();
				ss.destroy();
				value=str;
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} 
			
			int firstDraw=value.lastIndexOf("Draw");
			int firstView=value.indexOf("View hierarchy:");
			
			String bb=value.substring(firstDraw,firstView).trim();
			
			
			
			
			
			
			
			FileOutputStream fos=null;
			
			DateFormat dateformat= new SimpleDateFormat("yyyyMMdd-HHmmss");  
            
	        //����Date()��ȡ��ǰʱ��  
	        Date date = new Date();  
	        
	                      
	        //��ʽ��ʱ��,����String����洢  
	        String time = dateformat.format(date);  
	        String name =time+".txt";
	       
	        File file =new File ("C://data//"+name);
			try {
//				if (!file.exists()) {
//					
//					file.mkdirs();
//				}
//				
			 fos =new FileOutputStream(file);
				fos.write(bb.getBytes());
				fos.flush();
			} catch (FileNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
			finally {
				
				try {
					fos.close();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
			
			try {
				
				BufferedReader  br =new BufferedReader(new FileReader(file));	
//				BufferedInputStream bis =new BufferedInputStream(new FileInputStream(file));
				String str=br.readLine();
				String str1[]=str.split("\\ |  |   |    |	");
				int need[] =new int [str1.length];		
				int k=0;
				for (int i = 0; i < str1.length; i++) {   //
					if ((str1[i].trim().equals("Draw") ) |  (str1[i].trim().equals("Prepare") ) |  (str1[i].trim().equals("Process") ) | (str1[i].trim().equals("Execute") )       ) {
						need[k]=i;
						++k;
					}
				}
//				
//				System.out.println("---------  need  -----------");
//				for(int c:need){
//					System.out.println(c);
//				}
				
//			if (need.length==3) {
//				System.out.println("����ֻ������е�!");
//			}
//			if (need.length==4) {
//				System.out.println("����ֻ������е�!");
//			}
//			if (need.length>4) {
//				System.out.println("����ֻ����صĴ�������!");
//			}
				
				String text="";
				String temp=null;
				while ((temp=br.readLine())!=null){
					
//					System.out.println(temp=br.readLine());
					if (temp.isEmpty()) {
						continue;
					}
				String data = temp.trim();
				String a1[]=data.split("\\ |  |   |    |	");
				

				double cc=0;
			
				for (int i = 0; i < need.length; i++) {
					int mm=need[i];
					cc=cc+Double.parseDouble(a1[mm].trim());
				}
//		System.out.println(cc);
				
	           list.add(cc);
				}
			} catch (FileNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		
			
			
			return list;
		
		}

}