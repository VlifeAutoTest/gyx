package cn.phone.utils;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLMethods {

	public static Element element2 = null;

	// ����ָ��XML

	public static Element WhereisXML(String XMLPath) {

		SAXReader reader = new SAXReader();
		Document document = null;
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

	// ��һ������ֵ��ȷ��һ����Ҫ��elelment
	public static Element getNeedElement(Element element, String attributeName, String value) {

		Iterator<Element> itea = element.elementIterator();
		while (itea.hasNext()) {

			Element e = itea.next();

			//

			List<Attribute> list2 = e.attributes();

			for (Attribute attr : list2) {

				if (attr.getName().equalsIgnoreCase(attributeName)) {

					if (attr.getText().equalsIgnoreCase(value)) {

						return e;

					}
				}

			}

			List<Element> list = e.elements();
			if (list.isEmpty()) {
				continue;
			} else {

				element2 = getNeedElement(e, attributeName, value);
			}

		}
		return element2;

	}

	// ������ȡXML����Ķ�Ӧ����������ֵ

	public static String getMyValue(Element element, String attributeName) {

		if (element == null) {
			return "elementΪnull";
		}

		List<Attribute> list = element.attributes();

		for (Attribute attr : list) {
			if (attr.getName().equalsIgnoreCase(attributeName)) {

				return attr.getText();

			}

		}

		return "δ�ҵ�!";

	}

	// ���������ͼ������ĵ�

	public static int[] centre(String zuobiao) {

		String str = zuobiao.trim();

		int i = str.indexOf("[");
		int j = str.lastIndexOf("]");
		String temp = str.substring(i + 1, j);
		int q = str.indexOf(",");
		int w = str.lastIndexOf(",");

		int k = temp.indexOf("[");
		int l = temp.indexOf("]");

		String a = temp.substring(0, q - 1);
		String b = temp.substring(q, l);

		String c = temp.substring(k + 1, w - 1);
		String d = temp.substring(w, temp.length());

		int x = Integer.parseInt(a);
		int y = Integer.parseInt(b);
		int x1 = Integer.parseInt(c);
		int y1 = Integer.parseInt(d);

		int centrex = (x1 - x) / 2 + x;
		int centrey = (y1 - y) / 2 + y;

		int arr[] = new int[] { centrex, centrey };
		return arr;

	}

}
