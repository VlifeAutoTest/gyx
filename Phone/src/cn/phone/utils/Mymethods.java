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

	// 获取指定device的设备连接状态!!

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

	// 把EXCEL返回的String类型的电话号转化为String型的数组!!

	public static String[] StringToStringArray(String StringPhoneNum) {

		int NumLength = StringPhoneNum.length(); // 10086 5
		String Num[] = new String[NumLength]; // 4
		for (int i = 0; i < NumLength; i++) { // 0 1 2 3 4

			String temp = StringPhoneNum.substring(i, i + 1);

			Num[i] = temp.trim();

		}

		return Num;

	}

	// 等待延迟
	public static void wait(int i) {

		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	// 用于判断制定按钮是否存在!

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
	
	
	
	
	
	
	//获取内存信息
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        return str3;  
    }  
	
	
	
	//取CPU
	
	
	//获取PID
	// 获取进程的pid
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
			// TODO 自动生成的 catch 块
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
		
		
		
	     return uid;	
		
	}
	
	
	//获取tcp流量信息   String [0] revieve String [1] send
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
				// TODO 自动生成的 catch 块
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			
			return str;
			
		}
	
		
		//获取CPU信息
		
		
		// 获取程序的cpu占用情况
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
 * iface代表网络接口
accttaghex代表socket
uidtagint是UID
cnt_set实际上就是一个标志位，0代表前台流量，1代表后台流量
rx_bytes，r代表receive，是接收数据   6列
tx_bytes，t代表transmit，是传输数据 8列
 */


//获取应用所有流量的信息 

	public static void getAllFlow(String packageName) {

		String uid = Mymethods.getUID(packageName);

		
			Process proc=null;
			try {
				Runtime runtime = Runtime.getRuntime();
				proc = runtime.exec("adb shell cat /proc/net/xt_qtaguid/stats | grep " + uid);
				proc.waitFor();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
//			String temp[]=str.split("\\$");
//			System.out.println(temp[0]);
//			System.out.println(temp[1]);
			System.out.print(sb.toString());
		

	}
	
	
	//获取帧数的平均值
	
	public static  double getAvg(String packageName){
		
		List <Double>list =Mymethods.getzhenshu(packageName);
		double a=0;
		for(double temp:list){
			a=a+temp;
		}
		a=a/list.size();
		
		return a;
	}
	
	
	
	//获取帧数
	
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} 
			
			int firstDraw=value.lastIndexOf("Draw");
			int firstView=value.indexOf("View hierarchy:");
			
			String bb=value.substring(firstDraw,firstView).trim();
			
			
			
			
			
			
			
			FileOutputStream fos=null;
			
			DateFormat dateformat= new SimpleDateFormat("yyyyMMdd-HHmmss");  
            
	        //利用Date()获取当前时间  
	        Date date = new Date();  
	        
	                      
	        //格式化时间,并用String对象存储  
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			finally {
				
				try {
					fos.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
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
//				System.out.println("这个手机的三列的!");
//			}
//			if (need.length==4) {
//				System.out.println("这个手机的四列的!");
//			}
//			if (need.length>4) {
//				System.out.println("这个手机返回的大于四列!");
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		
			
			
			return list;
		
		}

}