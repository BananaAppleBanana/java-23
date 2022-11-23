package com.example.java23.week3.pattern;

interface Human {
    void get();
}
interface Robot {
    void get();
}

class AdaptorDemo {
    public static void main(String[] args) {
        Robot robot = () -> System.out.println("i'm robot");
        entry(new HumanAdaptor(robot));
    }
    public static void entry(Human human) {
        human.get();
    }
}
class HumanAdaptor implements Human {
    private Robot robot;

    public HumanAdaptor(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void get() {
        robot.get();
    }
}
/**
 * ******************************************
 */

class RobotProxy implements Robot {
    private Robot robot;

    public RobotProxy(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void get() {
        System.out.println("before");
        robot.get();
        System.out.println("after");
    }
}
class StaticProxyTest {
    public static void main(String[] args) {
        Robot robot = () -> System.out.println("i'm robot");
        Robot proxyRobot = new RobotProxy(robot);
        proxyRobot.get();
    }
}