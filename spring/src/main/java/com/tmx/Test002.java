package com.tmx;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created By Riven on 2020-9-24
 */
public class Test002 {

    public static void main(String[] args) throws DocumentException {
        new Test002().test();
    }

    private void test() throws DocumentException {
        // 创建SAXReader对象
        SAXReader reader = new SAXReader();
        // xml 文本
        Document read = reader.read(getResourceAsStream("student.xml"));
        // 获取节点
        Element rootElement = read.getRootElement();

        this.getNode(rootElement);
    }

    private void getNode(Element rootElement) {
        System.out.println("根节点名称：" + rootElement.getName());
        // 获取属性方法
        List<Attribute> attributes = rootElement.attributes();
        for (Attribute attribute: attributes) {
            System.out.println(attribute.getName() + "-------" + attribute.getText());
        }
        // 获取属性值
        String textTrim = rootElement.getTextTrim();
        if (StringUtils.isEmpty(textTrim)) {
            System.out.println("textTrim:" + textTrim);
        }
        Iterator<Element> iterator = rootElement.elementIterator();
        while (iterator.hasNext()) {
            Element next = iterator.next();
            getNode(next);
        }
    }

    private InputStream getResourceAsStream(String xmlPath) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        return resourceAsStream;
    }
}
