package cn.phone.data;

public class Data {

	// 指定是usb连接还是wifi连接 值为 w为wifi u 为usb

	public static final String HOWTOCONNECT = "u";
	// WIFI连接使用的端口

	public static final String WIFICONNECTPORT = "5555";

	// 指定对那部手机进行操作!
//	public static final String PhoneDevice = "H8023X6060123456";
	//public static final String PhoneDevice = "ZX1G22B7LM";
	
	public static final String PhoneDevice = "039d1cf114ea741e";
	// 通过wifi连接手机的手机IP

	public static final String PhoneIP = "10.0.10.49";

	// 截图保存文件路径 !!!! 路径 路径 路径 就OK!!------------------

	public static final String PATHSCREENSAVEPATH = "D:\\Result\\";

	// Excel 送文件的信息!!

	// Excel文件的路径--------------------------------
	public static final String EXCELPATH = "C:\\Users\\Administrator\\Desktop\\data.xlsx";

	// 需要的数据在哪个sheet
	public static final String EXCELSHEET = "sheet1";

	// ExceL是否执行的列
	public static final int EXCELISRUN = 1;

	// Excel 中的电话号码列
	public static final int EXCELPHONENUM = 0;

	//

	// 滑动解锁的坐标
	public static final String BEFOREWIDE = "550"; // 350 1200 400 800 400
	public static final String BEFOREHIGH = "1350";   // 470   2100  1100  1300  400 
	public static final String AFTERWIDE = "520";
	public static final String AFTERHIGH = "820";
	public static final String SWIPETIME = "400";

	// 获取桌面控件到手的路径

	public static final String ANDROIDPATH = "/sdcard/temp2.xml";

	//  手机xml布局文件复制到电脑的临时路径

	public static final String PCPATH = "D:/temp/temp2.xml";

	// 解锁后寻找电话
	public static final String FINDPHONEATTRIBUTE = "content-desc";

	public static final String FINDPHONEVALUS = "电话";

	public static final String NEEDPHONEATTRIBUTE = "bounds";

	// 打开电话洁面后的打开拨号键盘

	public static final String FINDKEYBOARDATTRIBUTE = "content-desc";

	public static final String FINDKEYBOARDVALUE = "拨号键盘";

	public static final String NEEDKEYBOARDATTRIBUT = "bounds";

	// 用来寻找拨号键盘的按钮!!

	public static final String FINDKEYBORDNUMATTRIBUTE = "content-desc";

	// 数字键0
	public static final String FINDKEYBOARDNUM0VALUE = "0,+";
	// 数字1
	public static final String FINDKEYBOARDNUM1VALUE = "1,";

	// 数字2
	public static final String FINDKEYBOARDNUM2VALUE = "2,ABC";

	// 数字3
	public static final String FINDKEYBOARDNUM3VALUE = "3,DEF";

	// 数字4
	public static final String FINDKEYBOARDNUM4VALUE = "4,GHI";

	// 数字5
	public static final String FINDKEYBOARDNUM5VALUE = "5,JKL";

	// 数字6
	public static final String FINDKEYBOARDNUM6VALUE = "6,MNO";

	// 数字7
	public static final String FINDKEYBOARDNUM7VALUE = "7,PQRS";
	// 数字8
	public static final String FINDKEYBOARDNUM8VALUE = "8,TUV";
	// 数字9
	public static final String FINDKEYBOARDNUM9VALUE = "9,WXYZ";
	// *号键
	public static final String FINDKEYBOARDNUMxinghaoVALUE = "*";
	// #号键
	public static final String FINDKEYBOARDNUMjinghaoVALUE = "#";

	public static final String NEEDKEYBOARDNUMVALUE = "bounds";

	// 输入完之后的拨号键

	public static final String BOHAOATTIBUTE = "content-desc";

	public static final String BOHAOVALUE = "拨打"; // 拨号 拨打

	public static final String NEEDBOATTRIBUTE = "bounds";

	// 寻找扬声器判断是否

	public static final String YANGSHENGQIATTRIBUTE = "content-desc";

	public static final String YANGSHENGQIVALUE = "扬声器"; // 拨号 拨打

	public static final String NEEDYANGSHENGQIVALUE = "bounds";

	// 点击结束通话按钮

	public static final String DOEXCEPTIONATTRIBUTE = "content-desc";

	public static final String DOEXCEPTIONVALUE = "结束通话";

	public static final String NEEDDOEXCEPTION = "bounds";

	// 重启电话应用!!

	public static final String APPRstare = "com.android.dialer/.app.DialtactsActivity";

	// 执行结束结束电话APP

	public static final String SHUTDOWNAPPNAME = "com.android.dialer";

}
