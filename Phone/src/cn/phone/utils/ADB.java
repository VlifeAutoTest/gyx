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

	// wifi连接

	public static void WIFIConnect(String IP) {
		
		try {
			
			System.out.println("WIFI连接模式!请把手机插入电脑!!10秒钟后执行监听 TCP/IP 连接操作!!");
			Mymethods.wait(10000);
			
			Process ss1 = Runtime.getRuntime().exec("adb tcpip " + " " + Data. WIFICONNECTPORT);
			ss1.waitFor();
			System.out.println("命令执行完毕,请拔出手机打开手机wifi,确保手机电脑在同一个网路.10秒之后尝试连接.");
			Mymethods.wait(10000);
			
			Process ss = Runtime.getRuntime().exec("adb connect " + " " + IP);
			ss.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 用ADB命令来实现点击电源按钮
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

	// 使用adb命令来实现一个滑屏操作

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

	// 用来检测当前屏幕是否是关屏状态!
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
			// TODO 自动生成的 catch 块
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			
		}
		return flag;
	}

	// 用来检测当前屏幕是否是锁屏状态!
	public static boolean lockOrUnlock() {
		boolean flag = false;
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
			
		
		try {
			Runtime rt = Runtime.getRuntime();
			// 第一种方法
			Process p = rt.exec("cmd.exe /c adb -s " + "  " + Data.PhoneDevice + "  "
					+ "shell dumpsys window policy  | findstr \"mShowingLockscreen\"");

			//第二种方法
			// Process p = rt.exec("cmd.exe /c adb -s "+" "+Data.PhoneDevice+" "
			// +"shell dumpsys window policy | findstr
			// \"isStatusBarKeyguard\"");

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			String content = "";

			while ((line = in.readLine()) != null)
				content = content + line;

			// 第一种结果
			if (content.contains("mShowingLockscreen=true"))

				// 第二种结果

				// if (content.contains("isStatusBarKeyguard=true"))
				flag = true;
			p.destroy();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		}
		
		else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			

			try {
				Runtime rt = Runtime.getRuntime();
				// 第一种方法
				Process p = rt.exec("cmd.exe /c adb -s " + "  " + IPPort + "  "
						+ "shell dumpsys window policy  | findstr \"mShowingLockscreen\"");

				//第二种方法
				// Process p = rt.exec("cmd.exe /c adb -s "+" "+Data.PhoneDevice+" "
				// +"shell dumpsys window policy | findstr
				// \"isStatusBarKeyguard\"");

				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				String content = "";

				while ((line = in.readLine()) != null)
					content = content + line;

				// 第一种结果
				if (content.contains("mShowingLockscreen=true"))

					// 第二种结果

					// if (content.contains("isStatusBarKeyguard=true"))
					flag = true;
				p.destroy();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			
			
			
			
		}
		
		
		
		
		
		
		return flag;
	}

	// 触控方法
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
		

		
		

	// 重启应用adb

	public static void resatrt(String APPmessage) {
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
			
	

		try {
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + Data.PhoneDevice + "  " + " shell am start -S" + " " + APPmessage);
			ss.waitFor();
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("重启应用出错");
		}
		}
		
		
		else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			
			try {
				Process ss = Runtime.getRuntime()
						.exec("adb -s " + "  " +IPPort+ "  " + " shell am start -S" + " " + APPmessage);
				ss.waitFor();
			} catch (Exception e) {
				e.printStackTrace();

				System.out.println("重启应用出错");
			}
			
		}
		
		
		
		
	}

	// 拨号界面

	public static void call() {
		
		
		
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
			
	
		try {
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + Data.PhoneDevice + "  " + "shell input keyevent 5");
			ss.waitFor();
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("call方法 拨号界面出错");
		}
		}
		
		
		
		else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			try {
				Process ss = Runtime.getRuntime()
						.exec("adb -s " + "  " + IPPort + "  " + "shell input keyevent 5");
				ss.waitFor();
			} catch (Exception e) {
				e.printStackTrace();

				System.out.println("call方法 拨号界面出错");
			}
			
			
			
		}

	}

	// 把手机文件复制到本地PC上
	public static void pull(String phonePath, String PCPath) {
		
		
		
		if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("u")) {
			
		

		try {
			Process ss = Runtime.getRuntime()
					.exec("adb -s " + "  " + Data.PhoneDevice + "  " + "pull " + " " + phonePath + "   " + PCPath);
			ss.waitFor();
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("call方法 拨号界面出错");
		}
		}
		
		
		else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			
			try {
				Process ss = Runtime.getRuntime()
						.exec("adb -s " + "  " + IPPort + "  " + "pull " + " " + phonePath + "   " + PCPath);
				ss.waitFor();
			} catch (Exception e) {
				e.printStackTrace();

				System.out.println("call方法 拨号界面出错");
			}
			
			
		}
		
		
		
		
	}

	// 出入数组按照数组拨号
	public static void clickNUM(String[] a) {

		for (int i = 0; i < a.length; i++) {

			switch (a[i]) {
			case "0":
				// 在xml中搜索指定按钮并点击 0
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM0VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;
			case "1":
				// 在xml中搜索指定按钮并点击 1
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM1VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "2":
				// 在xml中搜索指定按钮并点击 2
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM2VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "3":
				// 在xml中搜索指定按钮并点击 3
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM3VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "4":
				// 在xml中搜索指定按钮并点击 4
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM4VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "5":
				// 在xml中搜索指定按钮并点击 5
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM5VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "6":
				// 在xml中搜索指定按钮并点击 6
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM6VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "7":
				// 在xml中搜索指定按钮并点击 1
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM7VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "8":
				// 在xml中搜索指定按钮并点击 1
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM8VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;

			case "9":
				// 在xml中搜索指定按钮并点击 1
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUM9VALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;
			case "*":
				// 在xml中搜索指定按钮并点击 1
				MyProcess.findAndClick(Data.FINDKEYBORDNUMATTRIBUTE, Data.FINDKEYBOARDNUMxinghaoVALUE,
						Data.NEEDKEYBOARDNUMVALUE);

				break;
			case "#":
				// 在xml中搜索指定按钮并点击 1
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

			System.out.println("获取桌面控件XML出现问题!");
			e.printStackTrace();
		}
		
		}
		
		
		else if (Data.HOWTOCONNECT.trim().equalsIgnoreCase("w")){
			try {
				Process ss = Runtime.getRuntime()
						.exec("adb -s " + "  " + IPPort + "  " + "shell uiautomator dump " + " " + Path);
				ss.waitFor();

			} catch (Exception e) {

				System.out.println("获取桌面控件XML出现问题!");
				e.printStackTrace();
			}
			
			
		}
	}

	// 把手机屏幕截图并推送到电脑文件夹保存

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
			
			
			

			// 创建一个data format对象
			DateFormat dateformat = new SimpleDateFormat("yyyyMMdd-HHmmss");

			// 利用Date()获取当前时间
			Date date = new Date();

			// 格式化时间,并用String对象存储
			String date1 = dateformat.format(date);

			// 打印格式化时间到控制台
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
			System.out.println("执行了复制照片!!");

		} catch (Exception e) {

			System.out.println("保存截图出现问题!!");
			e.printStackTrace();
		}

	}

	// 点击home键回到主屏幕

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

			System.out.println("获取桌面控件XML出现问题!");
			e.printStackTrace();
		}

	}

	// 结束shutdownAPP

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

			System.out.println("结束app失败!!");
			e.printStackTrace();
		}

	}

	// 清空指定应用的缓存信息!!

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

			System.out.println("获取桌面控件XML出现问题!");
			e.printStackTrace();
		}

	}
	
	
	
	
	

	
	

}
