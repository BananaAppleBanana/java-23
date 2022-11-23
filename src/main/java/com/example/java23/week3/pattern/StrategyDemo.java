package com.example.java23.week3.pattern;

public class StrategyDemo {
    public static void main(String[] args) {
        get(() -> System.out.println("this is my lambda"));
    }

    public static void get(Strategy strategy) {
        strategy.get();
    }
}
interface Strategy {
    void get();
}
class StrategyImpl1 implements Strategy {
    @Override
    public void get() {
        System.out.println("this is one");
    }
}
class StrategyImpl2 implements Strategy {
    @Override
    public void get() {
        System.out.println("this is two");
    }
}


