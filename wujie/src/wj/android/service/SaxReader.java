package wj.android.service;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import wj.android.dao.Person;

public class SaxReader {

	public List<Person> readXML(InputStream inStream) {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser(); // 创建解析器
			// 设置解析器的相关特性，http://xml.org/sax/features/namespaces = true
			// 表示开启命名空间特性
			//saxParser.setProperty("http://xml.org/sax/features/namespaces",true);
			XMLContentHandler handler = new XMLContentHandler();
			saxParser.parse(inStream, handler);
			inStream.close();
			return handler.getPersons();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
