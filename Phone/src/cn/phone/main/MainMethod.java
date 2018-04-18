package cn.phone.main;

import java.io.IOException;

import cn.phone.utils.Mymethods;

public class MainMethod {

	public static void main(String[] args) throws IOException, InterruptedException {
		

//		String aa=Mymethods.GetMemory("com.yulore.yellowpage");
//		String aa =Mymethods.getPID("dscreate.net.lwp304290.hw");
		String aa=Mymethods.getUID("com.yulore.yellowpage");
//		double aa= Mymethods.getCPU("com.yulore.yellowpage");
//		double aa=Mymethods.getFlow("com.android.chrome");
		System.out.println(aa);
//		for(double a:aa){
//			
//			System.out.println(a);
//		}

		}

	}


