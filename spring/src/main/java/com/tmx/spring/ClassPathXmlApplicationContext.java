package com.tmx.spring;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * Created By Riven on 2020-9-24
 */
public class ClassPathXmlApplicationContext {

    private String xmlPath;

    public ClassPathXmlApplicationContext(String xmlPath) {
        this.xmlPath = xmlPath;
    }
    // 读取xml文件
    List<Element> readXml() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read(getResourceAsStream(xmlPath));
        Element rootElement = read.getRootElement();
        List<Element> elements = rootElement.elements();
        return elements;
    };

    // 匹配beanId
    public Object getBean(String beanId) throws Exception {
        List<Element> elements = readXml();
        if (beanId == null) {
            throw new Exception("beanId不允许为空");
        }
        if (elements == null) {
            throw new Exception("配置文件为空");
        }
        String aClass = getClass(elements, beanId);
        Object obj = getObj(aClass);
        return obj;
    }

    private String getClass(List<Element> elements, String beanId) {
        for (Element element: elements) {
            if (beanId.equals(element.attributeValue("id"))) {
                String objectClass = element.attributeValue("class");
                return objectClass;
            }
        }
        return null;
    }

    // 反射生成对象
    private Object getObj(String objectClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> classInfo = Class.forName(objectClass);
        return classInfo.newInstance();
    }

    private InputStream getResourceAsStream(String xmlPath) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        return resourceAsStream;
    }

}
