package cn.testlink.TestTestLink;

import java.net.MalformedURLException;
import java.net.URL;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;

public class TestLinkMyTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		   //项目名
		    String  projectName= "Test";
		   //计划名
		    String planName="yx"; 
		TestLinkAPI api = null;
		
		//TestLink api连接
	    String url = "http://testlink.vlife.com/lib/api/xmlrpc/v1/xmlrpc.php";
	    //key
	    String devKey = "b683e89cca796958b9481231f655261d";
//	    String devKey ="2137b4119c2783d3e3bbf7d19ff183e8";
	    URL testlinkURL = null;
	    try     {
            testlinkURL = new URL(url);
    } catch ( MalformedURLException mue )   {
            mue.printStackTrace( System.err );
            System.exit(-1);
    }
    try     {

           api = new TestLinkAPI(testlinkURL, devKey);

    } catch( Exception te) {

            te.printStackTrace( System.err );

            System.exit(-1);
    }
    System.out.println(api.ping());
 
    //获取Plan
    TestPlan plan =api.getTestPlanByName(planName, projectName);
    
    //获取版本
    Build[] build=api.getBuildsForTestPlan(plan.getId());
    //获取用用例id
    Integer  caseID = api.getTestCaseIDByName("12313", "test2", projectName, null);  
    //设置用例结果  通过
    api.reportTCResult(caseID, null, plan.getId(), ExecutionStatus.PASSED, null, build[1].getName(), "自动化上传结果的备注", null, null, null, null, null, null);
		
    
    
    
  //上床文件
    
    
//  File attFile = new File("C:\\temp.txt");
//  String fileContent = "";
//  try {
//  byte[] byteArray = FileUtils.readFileToByteArray(attFile);
//  fileContent = new String(Base64.encodeBase64(byteArray));
//  } catch (IOException e) {
//  }
//  api.uploadTestCaseAttachment(caseID, "自动化上传", "自动的", "测试用", ".txt", fileContent);

    
    
  //下载文件
    
    
//  String dir = "C:\\te";
//	 File file = new File(dir);
//	 if (!file.exists()) {
//	 file.mkdirs();
//	 }
//	 Attachment[] atts = api.getTestCaseAttachments(caseID, null);
//	 for (Attachment att : atts) {	
//	 byte[] decoded = Base64.decodeBase64(att.getContent().getBytes());
//	 FileOutputStream fos;
//	 try {
//	 fos = new FileOutputStream(new File(file + File.separator + att.getFileName())+ att.getFileType());
//	 fos.write(decoded);
//	 fos.flush();
//	 fos.close();
//	 } catch (FileNotFoundException e) {
//	 } catch (IOException e) {
//	 }
//	 }
		
		
	}

}
