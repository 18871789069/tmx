package com.tmx.pojo;

import com.tmx.annotation.RivenAddAnnotation;
import com.tmx.annotation.RivenFileAnnotation;
import com.tmx.annotation.RivenLocalAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created By Riven on 2020-9-18
 */
public class TrUser {

    @RivenFileAnnotation(id = "321321")
    public String id;

    public String account;

    @RivenAddAnnotation(id = "123123", account = "Riven", password = "next", name = "Riven", age = "18")
    public void add() {

    };

    public void del() {
        @RivenLocalAnnotation(password = "123123")
        String password;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // 反射
        Class classInfo = Class.forName("com.tmx.pojo.TrUser");

        Annotation[] annotations = classInfo.getAnnotations();
        for (Annotation annotation: annotations) {
            System.out.println("annotation:" + annotation);
        }
        // 获取字段属性
        Field[] fields = classInfo.getFields();
        for (Field field: fields) {
            System.out.println("属性:" + field);
            RivenFileAnnotation declaredAnnotation = field.getDeclaredAnnotation(RivenFileAnnotation.class);
            if (declaredAnnotation == null) {
                continue;
            }
            System.out.println("id:" + declaredAnnotation.id());
        }
        // 获取方法
        Method[] declaredMethods = classInfo.getDeclaredMethods();
        for (Method method: declaredMethods) {
            System.out.println(method);
            RivenAddAnnotation declaredAnnotation = method.getDeclaredAnnotation(RivenAddAnnotation.class);
            if (declaredAnnotation == null) {
                // 结束本次循环
                continue;
            }
            System.out.println("id:" + declaredAnnotation.id());
            System.out.println("account:" + declaredAnnotation.account());
            System.out.println("password:" + declaredAnnotation.password());
            System.out.println("name:" + declaredAnnotation.name());
            System.out.println("age:" + declaredAnnotation.age());
        }
    }
}
