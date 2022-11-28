package com.example.java23.week4;

import java.lang.annotation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *  metadata
 *
 *  getDeclaredField / Method
 */


public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        Class<Employee1> employee1Class = Employee1.class;
        Field field = employee1Class.getDeclaredFields()[0];

        Constructor constructor = employee1Class.getDeclaredConstructors()[0];
        Employee1 employee1 = (Employee1) constructor.newInstance();
        field.setAccessible(true);
        field.set(employee1, 5);

        Method method = employee1Class.getDeclaredMethods()[0];
        Annotation annotation = method.getAnnotations()[0];
        if(annotation.annotationType() == MyAnnotation.class) {
            MyAnnotation myAnnotation = (MyAnnotation) annotation;
            System.out.println(myAnnotation.value());
        }
        int age = (int) method.invoke(employee1);
        System.out.println(age);
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String value() default "abc";
}

class Employee1 {
    private int age;

    @MyAnnotation(value = "cdf")
    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "Employee1{" +
                "age=" + age +
                '}';
    }
}
