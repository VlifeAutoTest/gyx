package cn.phone.utils;

import java.io.File;

import org.dom4j.Element;

import cn.phone.data.*;

public class MyProcess {

	// ��ȡ��Ļ�Ƿ�����

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

	// ���������ֻ�
	public static void jiesuo() {

		Boolean pmzt = ADB.isScreenLock(); // ��ǰ��Ļ�Ƿ����Ļ!!

		Mymethods.wait(1000);
		if (pmzt == true) {
			ADB.Clickpower();

			Mymethods.wait(500);
		}
		
		
		

		// ����Ƿ����!!����������������!!
		if (ADB.lockOrUnlock()) {

			ADB.ADBswipe(Data.BEFOREWIDE, Data.BEFOREHIGH, Data.AFTERWIDE, Data.AFTERHIGH, Data.SWIPETIME);

		}

		ADB.ClickHome();

	}

	// ��������Ѱ��ָ����ť���겡����

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

	// ����������ȡ��������������ִ�в���绰

	public static void callPhoneNum() {
		Mymethods.wait(1000);

		// ����ָ���ĸ�excel�ļ���ַ!!

		ExcelMethods.setExcel(Data.EXCELPATH);
		// ���Ȼ�ȡexcel���ܹ��ж���������!!

		int countNum = ExcelMethods.getExcelCountRow(Data.EXCELSHEET);

		for (int i = 1; i <= countNum; i++) {

			String temp = ExcelMethods.getCellData(Data.EXCELSHEET, i, Data.EXCELISRUN); // ��ȡ�Ƿ�ִ��

			// �ж��Ƿ�ִ��
			if (temp.trim().equalsIgnoreCase("y")) {

				MyProcess.findAndClick(Data.FINDPHONEATTRIBUTE, Data.FINDPHONEVALUS, Data.NEEDPHONEATTRIBUTE);// ����绰����
				Mymethods.wait(1000);

				if (Mymethods.elementExitYesOrNo(Data.FINDKEYBOARDATTRIBUTE, Data.FINDKEYBOARDVALUE)) {

					MyProcess.findAndClick(Data.FINDKEYBOARDATTRIBUTE, Data.FINDKEYBOARDVALUE,
							Data.NEEDKEYBOARDATTRIBUT);// ����绰�򿪼���

				}

				String PhoneNum = ExcelMethods.getCellData(Data.EXCELSHEET, i, Data.EXCELPHONENUM);// ��ȡ�绰�����ַ���

				// �Ѻ����ַ�������ַ�����!
				String[] TempNum = Mymethods.StringToStringArray(PhoneNum);

				ADB.clickNUM(TempNum);

				// ������绰����������

				MyProcess.findAndClick(Data.BOHAOATTIBUTE, Data.BOHAOVALUE, Data.NEEDBOATTRIBUTE);// ���������������

				System.out.println("���в��ŵȴ���ͨ!!");

				Mymethods.wait(8000);
				

				ADB.SaveScreenShot(Data.PATHSCREENSAVEPATH);

				// �Ҷϵ绰

				MyProcess.findAndClick(Data.DOEXCEPTIONATTRIBUTE, Data.DOEXCEPTIONVALUE, Data.NEEDDOEXCEPTION);// �Ҷ�

				ADB.ClearAPP(Data.SHUTDOWNAPPNAME); // ��ջᵼ��APP�ر�
				ADB.ShutdownAPP(Data.SHUTDOWNAPPNAME);

				ADB.ClickHome();

			} else {
				continue;
			}

		}

		ADB.ShutdownAPP(Data.SHUTDOWNAPPNAME);

	}

}
