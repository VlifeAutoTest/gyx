package cn.test.ont;

public class Test2 {
	
	public static int [] main(String[] args) {
		
		
		
		
		String str ="[477,1220][711,1344]";
		
		int i =str.indexOf("[");
		int j =str.lastIndexOf("]");
		String temp =str.substring(i+1, j);
		int q=str.indexOf(",");
		int w=str.lastIndexOf(",");
	
		int k =temp.indexOf("[");   
		int l =temp.indexOf("]");

		String  a=temp.substring(0,q-1);
		String b=temp.substring(q,l );
		
		String c=temp.substring(k+1, w-1);
		String d=temp.substring(w, temp.length());
		
		
		
		int x = Integer.parseInt(a);
		int y = Integer.parseInt(b);
		int x1 = Integer.parseInt(c);
		int y1 = Integer.parseInt(d);
		
		int centrex=(x1-x)/2;
		int centrey=(y1-y)/2;
		
		int arr[]=new int[] {centrex,centrey};
		return arr;
		
		
	}
	
}
