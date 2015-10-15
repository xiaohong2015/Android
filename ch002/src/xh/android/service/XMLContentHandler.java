package xh.android.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import xh.android.dao.Person;

public class XMLContentHandler extends DefaultHandler {

	private List<Person> persons = null;
	private Person currentPerson;
	private String tagName = null;//当前解析的元素标签

	public List<Person> getPersons() {
		return persons;
	}
	/*
	 * 接收文档的开始的通知。
	 */
	@Override public void startDocument() throws SAXException {
		persons = new ArrayList<Person>();
	}
	/*
	 * 接收字符数据的通知。
	 */
	@Override public void characters(char[] ch, int start, int length) throws SAXException {
		if(tagName!=null){
			String data = new String(ch, start, length);
			if(tagName.equals("name")){
				this.currentPerson.setName(data);
			}else if(tagName.equals("age")){
				this.currentPerson.setAge(Short.parseShort(data));
			}
		}
	}
	/*
	 * 接收元素开始的通知。
	 * 参数意义如下：
	 *    namespaceURI：元素的命名空间
	 *    localName ：元素的本地名称（不带前缀）
	 *    qName ：元素的限定名（带前缀）
	 *    atts ：元素的属性集合
	 */
	@Override public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		if(localName.equals("person")){
			currentPerson = new Person();
			currentPerson.setId(Integer.parseInt(atts.getValue("id")));
		}
		this.tagName = localName;
	}
	/*
	 * 接收文档的结尾的通知。
	 * 参数意义如下：
	 *    uri ：元素的命名空间
	 *    localName ：元素的本地名称（不带前缀）
	 *    name ：元素的限定名（带前缀）
	 * 
	 */
	@Override public void endElement(String uri, String localName, String name) throws SAXException {
		if(localName.equals("person")){
			persons.add(currentPerson);
			currentPerson = null;
		}
		this.tagName = null;
	}
}
