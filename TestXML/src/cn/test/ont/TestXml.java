package cn.test.ont;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestXml {

	public static void main(String[] args) throws DocumentException {
		// TODO 自动生成的方法存根

		
		SAXReader reader = new SAXReader();  
		Document document = reader.read(new File("./test.xml"));
		Element node = document.getRootElement();  
//			ite(node);
//		Iterator<Element> list = node.elementIterator();  
//		
//		while (list.hasNext()) {  
//			            // 获取某个子节点对象  
//			            Element e = list.next();  
//			            // 对子节点进行遍历  
//			           
//			            System.out.println( e.getName());
//			       }  
//			   }  
	
	
	
	
//	public static  void ite(Element element){
//		
//		Iterator<Element> list = element.elementIterator();  
//		while (list.hasNext()) {  
//            // 获取某个子节点对象  
//            Element e = list.next();  
//            // 对子节点进行遍历  
//           
//            System.out.println( e.getName());
//            ite(e);
//       }  
//	}
	
		

		Element element = node.element("红楼梦");  
		Attribute attr = element.attribute("id"); 
		System.out.println(attr.getName());
		System.out.println(attr.getText());
		
	
		Element  ele2 =element.element("作者");
		System.out.println(ele2.getText());
		
		List list=ele2.elements();
		
		System.out.println(list.isEmpty());
		
		
		
		
		
	}

}
