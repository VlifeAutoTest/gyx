package cn.phone.main;

import cn.phone.data.Data;
import cn.phone.utils.ADB;
import cn.phone.utils.MyProcess;
import cn.phone.utils.Mymethods;

public class CallPhoneNum {
	//����绰
	public static void main(String[] args) {
		
	if (MyProcess.phoneConnectStatus()) {
		
		Mymethods.wait(5000);
		MyProcess.jiesuo();

		ADB.ClearAPP(Data.SHUTDOWNAPPNAME);
		ADB.ShutdownAPP(Data.SHUTDOWNAPPNAME);

		MyProcess.callPhoneNum();

		ADB.Clickpower();

	}

	else {

		System.out.println("���ӳ����쳣!��ȷ���Ƿ�����ֻ�!!");


}
}

}