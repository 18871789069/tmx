package com.tmx.ioc.springIOC;

import com.tmx.ioc.util.ClassUtil;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * springIOC注入容器
 * Created By Riven on 2020-10-9
 */
public class ClassPathApplicationContext {

    // 1、定义扫包范围和接收对象
    private String packageName;
    ConcurrentHashMap<String, Object> initBean = null;

    public ClassPathApplicationContext(String packageName) {
        this.packageName = packageName;
    }

    public Object getBean(String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)) {
            throw new Exception("beanId为空!");
        }
        // 需要先初始化 initBean，初始化则需要先找到有注解的类
        List<Class<?>> allExtServiceClass = findAllExtServiceClass();
        initBean = initBean(allExtServiceClass);
        Object object = initBean.get(beanId);
        // 赋值 注入
        simpleIoc(object);

        return object;
    }

    public void simpleIoc(Object object) throws IllegalAccessException {
        Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            Object o = initBean.get(name);
            if (o != null) {
                field.setAccessible(true);
                field.set(object, o);
            }
        }
    }

    // 初始化
    private ConcurrentHashMap<String, Object> initBean(List<Class<?>> allExtServiceClass) throws IllegalAccessException, InstantiationException {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        for (Class classInfo : allExtServiceClass) {
            String className = classInfo.getSimpleName();
            String bean = new StringBuilder().append(Character.toLowerCase(className.charAt(0))).append(className.substring(1)).toString();
            classInfo.newInstance();
            concurrentHashMap.put(bean, classInfo.newInstance());
        }
        return concurrentHashMap;
    }

    // 获取所有bean
    private List<Class<?>> findAllExtServiceClass() {
        List<Class<?>> classInfos = ClassUtil.getClasses(packageName);
        List<Class<?>> hasExtServiceClassInfos = new ArrayList<>();
        for (Class classInfo : classInfos) {
            ExtService extService = (ExtService) classInfo.getDeclaredAnnotation(ExtService.class);
            if (extService != null) {
                hasExtServiceClassInfos.add(classInfo);
                continue;
            }
        }
        return hasExtServiceClassInfos;
    }

}
