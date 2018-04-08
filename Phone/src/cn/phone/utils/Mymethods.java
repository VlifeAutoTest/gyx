package cn.phone.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

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

}
