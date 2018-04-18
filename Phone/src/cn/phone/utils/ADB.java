package cn.phone.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import cn.phone.data.Data;

public class ADB {
	
	public static String IPPort=Data.PhoneIP+":"+Data.WIFICONNECTPORT;

	// wifi����

	public static void WIFIConnect(String IP) {
		
		try {
			
			System.out.println("WIFI����ģʽ!����ֻ��������!!10���Ӻ�ִ�м��� TCP/IP ���Ӳ���!!");
			Mymethods.wait(10000);
			
			Process ss1 = Runtime.getRuntime().exec("adb tcpip " + " " + Data. WIFICONNECTPORT);
			ss1.waitFor();
			System.out.println("����ִ�����,��γ��ֻ����ֻ�wifi,ȷ���ֻ�������ͬһ����·.10��֮��������.");
			Mymethods.wait(10000);
			
			Process ss = Runtime.getRuntime().exec("adb connect " + " " + IP);
			ss.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ��ADB������ʵ�ֵ����Դ��ť
	public static void Clickpower() {
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
			try {
				Process ss = Runtime.getRuntime()
						.exec("adb -s " + "  " + Data.PhoneDevice + "  " + "shell input keyevent KEYCODE_POWER ");
				ss.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			try {
				Process ss = Runtime.getRuntime()
						.exec("adb  -s "+" "+IPPort+" "+"  shell input keyevent KEYCODE_POWER ");
				ss.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}

	// ʹ��adb������ʵ��һ����������

	public static void ADBswipe(String Beforewide, String Beforehigh, String Afterwide, String Afterhigh, String Time) {
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
		try {

			Process ss = Runtime.getRuntime().exec("adb -s " + "  " + Data.PhoneDevice + "  " + "shell input swipe"
					+ " " + Beforewide + " " + Beforehigh + " " + Afterwide + " " + Afterhigh + " " + Time);
			ss.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		else if(Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			
			try {

				Process ss = Runtime.getRuntime().exec("adb  -s "+" "+IPPort+" "+"shell input swipe"
						+ " " + Beforewide + " " + Beforehigh + " " + Afterwide + " " + Afterhigh + " " + Time);
				ss.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		
		
		
		
		
	}

	// ������⵱ǰ��Ļ�Ƿ��ǹ���״̬!
	public static boolean isScreenLock() {
		boolean flag = false;
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
		try {
			Runtime rt = Runtime.getRuntime();
			Process p = rt.exec("cmd.exe /c adb -s " + "  " + Data.PhoneDevice + "  "
					+ "shell dumpsys power | findstr \"Display Power:state=\"");
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			String content = "";

			while ((line = in.readLine()) != null)
				content = content + line;
			if (content.contains("Display Power: state=OFF"))
				flag = true;
			p.destroy();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		
		}
		
		else if(Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			
			try {
				Runtime rt = Runtime.getRuntime();
				Process p = rt.exec("cmd.exe /c adb -s " + "  " + IPPort+ "  "
						+ "shell dumpsys power | findstr \"Display Power:state=\"");
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				String content = "";

				while ((line = in.readLine()) != null)
					content = content + line;
				if (content.contains("Display Power: state=OFF"))
					flag = true;
				p.destroy();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
			
		}
		return flag;
	}

	// ������⵱ǰ��Ļ�Ƿ�������״̬!
	public static boolean lockOrUnlock() {
		boolean flag = false;
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
			
		
		try {
			Runtime rt = Runtime.getRuntime();
			// ��һ�ַ���
			Process p = rt.exec("cmd.exe /c adb -s " + "  " + Data.PhoneDevice + "  "
					+ "shell dumpsys window policy  | findstr \"mShowingLockscreen\"");

			//�ڶ��ַ���
			// Process p = rt.exec("cmd.exe /c adb -s "+" "+Data.PhoneDevice+" "
			// +"shell dumpsys window policy | findstr
			// \"isStatusBarKeyguard\"");

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			String content = "";

			while ((line = in.readLine()) != null)
				content = content + line;

			// ��һ�ֽ��
			if (content.contains("mShowingLockscreen=true"))

				// �ڶ��ֽ��

				// if (content.contains("isStatusBarKeyguard=true"))
				flag = true;
			p.destroy();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		}
		
		else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			

			try {
				Runtime rt = Runtime.getRuntime();
				// ��һ�ַ���
				Process p = rt.exec("cmd.exe /c adb -s " + "  " + IPPort + "  "
						+ "shell dumpsys window policy  | findstr \"mShowingLockscreen\"");

				//�ڶ��ַ���
				// Process p = rt.exec("cmd.exe /c adb -s "+" "+Data.PhoneDevice+" "
				// +"shell dumpsys window policy | findstr
				// \"isStatusBarKeyguard\"");

				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				String content = "";

				while ((line = in.readLine()) != null)
					content = content + line;

				// ��һ�ֽ��
				if (content.contains("mShowingLockscreen=true"))

					// �ڶ��ֽ��

					// if (content.contains("isStatusBarKeyguard=true"))
					flag = true;
				p.destroy();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
			
			
			
			
		}
		
		
		
		
		
		
		return flag;
	}

	// ���ط���
	public static void tap(int[] zuobiao) {
		
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
			


		try {
			Process ss = Runtime.getRuntime().exec("adb -s " + "  " + Data.PhoneDevice + "  " + "shell input tap  "
					+ " " + String.valueOf(zuobiao[0]) + " " + String.valueOf(zuobiao[1]));
			ss.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		
		
		else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			
			

			try {
				Process ss = Runtime.getRuntime().exec("adb -s " + "  " + IPPort + "  " + "shell input tap  "
						+ " " + String.valueOf(zuobiao[0]) + " " + String.valueOf(zuobiao[1]));
				ss.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			
			
			
		}
		

		
		

	// ����Ӧ��adb

	public static void resatrt(String APPmessage) {
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
			
	

		try {
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + Data.PhoneDevice + "  " + " shell am start -S" + " " + APPmessage);
			ss.waitFor();
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("����Ӧ�ó���");
		}
		}
		
		
		else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			
			try {
				Process ss = Runtime.getRuntime()
						.exec("adb -s " + "  " +IPPort+ "  " + " shell am start -S" + " " + APPmessage);
				ss.waitFor();
			} catch (Exception e) {
				e.printStackTrace();

				System.out.println("����Ӧ�ó���");
			}
			
		}
		
		
		
		
	}

	// ���Ž���

	public static void call() {
		
		
		
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
			
	
		try {
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + Data.PhoneDevice + "  " + "shell input keyevent 5");
			ss.waitFor();
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("call���� ���Ž������");
		}
		}
		
		
		
		else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			try {
				Process ss = Runtime.getRuntime()
						.exec("adb -s " + "  " + IPPort + "  " + "shell input keyevent 5");
				ss.waitFor();
			} catch (Exception e) {
				e.printStackTrace();

				System.out.println("call���� ���Ž������");
			}
			
			
			
		}

	}

	// ���ֻ��ļ����Ƶ�����PC��
	public static void pull(String phonePath, String PCPath) {
		
		
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
			
		

		try {
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + Data.PhoneDevice + "  " + "pull " + " " + phonePath + "   " + PCPath);
			ss.waitFor();
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("call���� ���Ž������");
		}
		}
		
		
		else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			
			try {
				Process ss = Runtime.getRuntime()
						.exec("adb -s " + "  " + IPPort + "  " + "pull " + " " + phonePath + "   " + PCPath);
				ss.waitFor();
			} catch (Exception e) {
				e.printStackTrace();

				System.out.println("call���� ���Ž������");
			}
			
			
		}
		
		
		
		
	}

	// �������鰴�����鲦��
	public static void clickNUM(String[] a) {

		for (int i = 0; i < a.length; i++) {

			switch (a[i]) {
			case "0":
				// ��xml������ָ����ť����� 0
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM0VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;
			case "1":
				// ��xml������ָ����ť����� 1
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM1VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "2":
				// ��xml������ָ����ť����� 2
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM2VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "3":
				// ��xml������ָ����ť����� 3
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM3VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "4":
				// ��xml������ָ����ť����� 4
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM4VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "5":
				// ��xml������ָ����ť����� 5
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM5VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "6":
				// ��xml������ָ����ť����� 6
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM6VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "7":
				// ��xml������ָ����ť����� 1
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM7VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "8":
				// ��xml������ָ����ť����� 1
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM8VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "9":
				// ��xml������ָ����ť����� 1
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM9VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;
			case "*":
				// ��xml������ָ����ť����� 1
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUMxinghaoVALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;
			case "#":
				// ��xml������ָ����ť����� 1
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUMjinghaoVALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			}

		}

	}

	public static void getJMXX(String Path) {

		
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
			
		
		try {
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + Data.PhoneDevice + "  " + "shell uiautomator dump " + " " + Path);
			ss.waitFor();

		} catch (Exception e) {

			System.out.println("��ȡ����ؼ�XML��������!");
			e.printStackTrace();
		}
		
		}
		
		
		else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			try {
				Process ss = Runtime.getRuntime()
						.exec("adb -s " + "  " + IPPort + "  " + "shell uiautomator dump " + " " + Path);
				ss.waitFor();

			} catch (Exception e) {

				System.out.println("��ȡ����ؼ�XML��������!");
				e.printStackTrace();
			}
			
			
		}
	}

	// ���ֻ���Ļ��ͼ�����͵������ļ��б���

	public static void SaveScreenShot(String PCSaveScreenshotPath) {

		try {
			
			if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
				
			
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + Data.PhoneDevice + "  " + "shell screencap -p /sdcard/screen.png");
			ss.waitFor();
			}
			else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")) {
				
			
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + IPPort + "  " + "shell screencap -p /sdcard/screen.png");
			ss.waitFor();
			}
			
			
			

			// ����һ��data format����
			DateFormat dateformat = new SimpleDateFormat("yyyyMMdd-HHmmss");

			// ����Date()��ȡ��ǰʱ��
			Date date = new Date();

			// ��ʽ��ʱ��,����String����洢
			String date1 = dateformat.format(date);

			// ��ӡ��ʽ��ʱ�䵽����̨
			String FileName = date1 + "screen.png";

			File file = new File(PCSaveScreenshotPath);
			if (!file.exists()) {

				file.mkdirs();

			}

			if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
				
			
			Process ss2 = Runtime.getRuntime().exec("adb -s " + "  " + Data.PhoneDevice + "  "
					+ "pull  /sdcard/screen.png" + "  " + PCSaveScreenshotPath + FileName);
			ss2.waitFor();
			}
			
			else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")) {
				
			
			Process ss2 = Runtime.getRuntime().exec("adb -s " + "  " + IPPort + "  "
					+ "pull  /sdcard/screen.png" + "  " + PCSaveScreenshotPath + FileName);
			ss2.waitFor();
			}
			System.out.println("ִ���˸�����Ƭ!!");

		} catch (Exception e) {

			System.out.println("�����ͼ��������!!");
			e.printStackTrace();
		}

	}

	// ���home���ص�����Ļ

	public static void ClickHome() {
		try {
			
			if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
				
			
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + Data.PhoneDevice + "  " + "shell input keyevent  3");
			ss.waitFor();
			}
			
			
			else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")) {
				
			
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + IPPort+ "  " + "shell input keyevent  3");
			ss.waitFor();
			}
		} catch (Exception e) {

			System.out.println("��ȡ����ؼ�XML��������!");
			e.printStackTrace();
		}

	}

	// ����shutdownAPP

	public static void ShutdownAPP(String APPName) {

		try {
			
			if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")){
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + Data.PhoneDevice + "  " + "shell am force-stop  " + "  " + APPName);
			ss.waitFor();
			
			}
			
			else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
				Process ss = Runtime.getRuntime()
						.exec("adb -s " + "  " + IPPort + "  " + "shell am force-stop  " + "  " + APPName);
				ss.waitFor();
				
				}

		} catch (Exception e) {

			System.out.println("����appʧ��!!");
			e.printStackTrace();
		}

	}

	// ���ָ��Ӧ�õĻ�����Ϣ!!

	public static void ClearAPP(String APPName) {

		try {
			
			if(Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")){
				
				
			
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + Data.PhoneDevice + "  " + "shell  pm clear  " + "  " + APPName);
			ss.waitFor();
			}
			
			
			else if(Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
				
				
			
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + IPPort + "  " + "shell  pm clear  " + "  " + APPName);
			ss.waitFor();
			}
		} catch (Exception e) {

			System.out.println("��ȡ����ؼ�XML��������!");
			e.printStackTrace();
		}

	}
	
	
	
	
	

	
	

}
