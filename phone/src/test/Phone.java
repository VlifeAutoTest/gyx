
package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Phone {

 public static void main(String[] args){
  // TODO Auto-generated method stub
  String[ ]  arr = {"adb shell input keyevent 26",
		  "adb shell input swipe 700 2400 700 1500",
		  "adb shell am start -a android.intent.action.CALL -d tel:10086",
		  "adb shell screencap -p /sdcard/x.png",
		  "adb shell input keyevent 6",
		  "adb pull /sdcard/x.png d:/test","adb shell input keyevent 3"};
  String s;

          StringBuilder sb = new StringBuilder();
          InputStream fis = null;
          for(int x=0; x<7; x=x+1)
        	  
         {
        	  while(x==3)
        	  {
        		  try   
        		  {   
        		  Thread.currentThread().sleep(3000);//毫秒   
        		  }   
        		  catch(Exception e){}  
        		  break;
        	  }
        	  try {
               Process process = Runtime.getRuntime().exec(arr[x]);
                 //Process process = Runtime.getRuntime().exec(new String[]{"sh", "-c", XXXX});
                 // 注意，我将原来的15行注释掉了，变成了下面的写法。声明，我调用的command是Lunix下的命令，如果你用的是windows的话，不需要这么写。
                 // 为什么要使用这样的写法，因为项目需要考虑到单引号双引号等，转换加/的原因。
              fis = process.getInputStream();
              BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis)); 
              while((s=bufferedReader.readLine()) != null) {
                 sb.append(s);
                     //sb.append(\n);
              }
             System.out.println(sb.toString());
               process.waitFor();
               System.out.println(process.exitValue());
           } catch (InterruptedException e) {
              e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           } finally {
               try {
                  fis.close();
               } catch (IOException e) {
                  e.printStackTrace();
              }
           }
         }
      }
}