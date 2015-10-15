package wj.android.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import wj.android.dao.Person;

public class DomReader {

	public List<Person> getPersons(InputStream inStream) throws Exception {
		List<Person> persons = new ArrayList<Person>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(inStream);
		Element root = (Element) document.getDocumentElement();
		NodeList personNodes = ((Document) root).getElementsByTagName("person");// 查找所有person节点
		for (int i = 0; i < personNodes.getLength(); i++) {
			Person person = new Person();
			// 得到第一个person节点
			Element personNode = (Element) personNodes.item(i);
			// 获取person节点的id属性值
			int id = new Integer(((org.w3c.dom.Element) personNode).getAttribute("id"));
			person.setId(id);
			// 获取person节点下的所有子节点(标签之间的空白节点和name/age元素)
			NodeList childNodes = ((Node) personNode).getChildNodes();
			for (int y = 0; y < childNodes.getLength(); y++) {
				Node childNode = childNodes.item(y);
				// 判断是否为元素类型
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {
					// 判断是否name元素
					if ("name".equals(childNode.getNodeName())) {
						String name = childNode.getFirstChild().getNodeValue();
						person.setName(name);
						// 获取name元素下Text节点,然后从Text节点获取数据
					} else if ("age".equals(childNode.getNodeName())) {
						String age = childNode.getFirstChild().getNodeValue();
						person.setAge(new Short(age));
					}
				}
			}
			persons.add(person);
		}
		return persons;
	}

}
