package cn.test.ont;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MyXML {
	
	
	Element element2=null;

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

		MyXML mx=new MyXML();
		Element ele=mx.WhereisXML("./temp.xml");
		
	
//		System.out.println(ele.getName());
		
	     Element elem=mx.listNodes(ele, "content-desc","���ż���");
//		
		System.out.println(mx.getMyValue(elem, "bounds"));
//		
		
	}

	
	
	
	public  Element  WhereisXML(String XMLPath ){
		
		SAXReader reader = new SAXReader();  
		Document document=null;
		try {
			document = reader.read(new File(XMLPath));
		} catch (DocumentException e) {
			// TODO �Զ����ɵ� catch ��
			
			System.out.println("XML�ļ����س���!�����ļ�·��!");
			e.printStackTrace();
		}
		Element node = document.getRootElement();  
		
		return node;
		
		
	}
	
	
	public Element listNodes(Element element ,String attributeName,String value){

		
			Iterator<Element> itea= element.elementIterator();  
						while (itea.hasNext()) {  
			         
			            Element e = itea.next(); 
			            
//			            
			            
			             List<Attribute> list2 = e.attributes();  
					       
					       for (Attribute attr : list2) {  
					    	   
					    	   
					            if(attr.getName().equalsIgnoreCase(attributeName)) {
					            	
					            	if (attr.getText().equalsIgnoreCase(value)) {
										
					            		 return e;
					            		
									}
					      }  
					            
					       }
					           
					       List list=e.elements();
					       if(list.isEmpty()){
					    	   continue;
					       }
					       else{
					    	   
					    	   element2= listNodes(e, attributeName, value);
					       }
					          
					       
					            
					           
					            
					       
					       
					       

			            	 
			            	 
			            	 
			             }
						return element2;
			             
			            
			            
			       }  
						
						
						

	public String  getMyValue(Element element ,String attributeName){
		
		if(element ==null){
		return "elementΪnull";
		}
		
		
		List<Attribute> list = element.attributes();  
	       
	       for (Attribute attr : list) {  
	            if(attr.getName().equalsIgnoreCase(attributeName)) {
	            	
	            	
	            	return attr.getText();
	            		
					}
	            
		
		
	
		}
		
		return "δ�ҵ�!";
	       
	
	       
	       
	}
	}
	

	
	

