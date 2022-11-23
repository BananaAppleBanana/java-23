package com.example.java23.week3.pattern;

/**
 *  loose coupling
 *  hide initialization
 *
 *  class A {
 *      B b = Factory.getB();
 *  }
 *  class .. {
 *      B b = Factory.getB();
 *  }
 */
class MyFactory {
    private MyFactory(){}
    public static Emp1 getEmp1(int id) {
        return new Emp1(id, "default");
    }
}