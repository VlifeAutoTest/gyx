package cn.phone.utils;

import java.io.File;

import org.dom4j.Element;

import cn.phone.data.*;

public class MyProcess {

	// 获取屏幕是否锁定

	public static Boolean phoneConnectStatus() {
		Boolean status = false;

		if (Data.HOWTOCONNECT.equalsIgnoreCase("w")) {
			ADB.WIFIConnect(Data.PhoneIP);
			Mymethods.wait(1000);
			status = Mymethods.GetStatus();
			return status;

		} else if (Data.HOWTOCONNECT.equalsIgnoreCase("u")) {

			status = Mymethods.GetStatus();
			return status;

		}
		return false;

	}

	// 用来解锁手机
	public static void jiesuo() {

		Boolean pmzt = ADB.isScreenLock(); // 当前屏幕是否关屏幕!!

		Mymethods.wait(1000);
		if (pmzt == true) {
			ADB.Clickpower();

			Mymethods.wait(500);
		}
		
		
		

		// 检测是否解锁!!锁定触发解锁操作!!
		if (ADB.lockOrUnlock()) {

			ADB.ADBswipe(Data.BEFOREWIDE, Data.BEFOREHIGH, Data.AFTERWIDE, Data.AFTERHIGH, Data.SWIPETIME);

		}

		ADB.ClickHome();

	}

	// 根据名称寻找指定按钮坐标病触摸

	public static void findAndClick(String attributeName, String attributeValue, String needValueAttribute) {
		ADB.getJMXX(Data.ANDROIDPATH);

		Mymethods.wait(1000);
		File file = new File(Data.PCPATH);
		new File(file.getParent()).mkdirs();
		ADB.pull(Data.ANDROIDPATH, Data.PCPATH);
		Mymethods.wait(500);
		Element element = XMLMethods.WhereisXML(Data.PCPATH);

		Element element2 = XMLMethods.getNeedElement(element, attributeName, attributeValue);

		String temp = XMLMethods.getMyValue(element2, needValueAttribute);

		System.out.println(temp);
		int a[] = XMLMethods.centre(temp);
		for (int i : a) {
			System.out.println(i);
		}
		Mymethods.wait(500);
		ADB.tap(a);

		file.delete();

	}

	// 根据用例读取到的数据来进行执行拨打电话

	public static void callPhoneNum() {
		Mymethods.wait(1000);

		// 首先指定哪个excel文件地址!!

		ExcelMethods.setExcel(Data.EXCELPATH);
		// 首先获取excel的总共有多少行数据!!

		int countNum = ExcelMethods.getExcelCountRow(Data.EXCELSHEET);

		for (int i = 1; i <= countNum; i++) {

			String temp = ExcelMethods.getCellData(Data.EXCELSHEET, i, Data.EXCELISRUN); // 获取是否执行

			// 判断是否执行
			if (temp.trim().equalsIgnoreCase("y")) {

				MyProcess.findAndClick(Data.FINDPHONEATTRIBUTE, Data.FINDPHONEVALUS, Data.NEEDPHONEATTRIBUTE);// 点击电话界面
				Mymethods.wait(1000);

				if (Mymethods.elementExitYesOrNo(Data.FINDKEYBOARDATTRIBUTE, Data.FINDKEYBOARDVALUE)) {

					MyProcess.findAndClick(Data.FINDKEYBOARDATTRIBUTE, Data.FINDKEYBOARDVALUE,
							Data.NEEDKEYBOARDATTRIBUT);// 进入电话打开键盘

				}

				String PhoneNum = ExcelMethods.getCellData(Data.EXCELSHEET, i, Data.EXCELPHONENUM);// 获取电话号码字符串

				// 把号码字符串编程字符数组!
				String[] TempNum = Mymethods.StringToStringArray(PhoneNum);

				ADB.clickNUM(TempNum);

				// 输入完电话号码点击拨号

				MyProcess.findAndClick(Data.BOHAOATTIBUTE, Data.BOHAOVALUE, Data.NEEDBOATTRIBUTE);// 输入完号码点击拨号

				System.out.println("进行拨号等待接通!!");

				Mymethods.wait(8000);
				

				ADB.SaveScreenShot(Data.PATHSCREENSAVEPATH);

				// 挂断电话

				MyProcess.findAndClick(Data.DOEXCEPTIONATTRIBUTE, Data.DOEXCEPTIONVALUE, Data.NEEDDOEXCEPTION);// 挂断

				ADB.ClearAPP(Data.SHUTDOWNAPPNAME); // 清空会导致APP关闭
				ADB.ShutdownAPP(Data.SHUTDOWNAPPNAME);

				ADB.ClickHome();

			} else {
				continue;
			}

		}

		ADB.ShutdownAPP(Data.SHUTDOWNAPPNAME);

	}

}
