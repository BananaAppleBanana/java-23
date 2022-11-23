package com.example.java23.week3.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface MyRobot extends Robot {
    int set();
}
public class DynamicProxy {
    public static void main(String[] args) throws Exception{
        MyRobot originalRobot = new MyRobot() {
            @Override
            public int set() {
                System.out.println("this is set");
                return 5;
            }

            @Override
            public void get() {
                System.out.println("this is get");
            }
        };
        MyRobot proxy = (MyRobot) Proxy.newProxyInstance(
                DynamicProxy.class.getClassLoader(),
                new Class[]{MyRobot.class},
                new RobotInvocationHandler(originalRobot)
        );
        proxy.get();
        int res = proxy.set();
        System.out.println(res);
    }
}

class RobotInvocationHandler implements InvocationHandler {
    private Robot originalRobot;

    public RobotInvocationHandler(Robot robot) {
        this.originalRobot = robot;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        System.out.println(method);
        Object res = method.invoke(originalRobot, args);
        System.out.println("after");
        return res;
    }
}

/**
 *  MySQL / Postgre
 */
