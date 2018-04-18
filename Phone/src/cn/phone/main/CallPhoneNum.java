package cn.phone.main;

import cn.phone.data.Data;
import cn.phone.utils.ADB;
import cn.phone.utils.MyProcess;
import cn.phone.utils.Mymethods;

public class CallPhoneNum {
	//拨打电话
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

		System.out.println("连接出现异常!请确认是否插入手机!!");


}
}

}