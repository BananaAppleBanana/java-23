package com.example.java23.week3.pattern;

import java.lang.reflect.Array;

public class BridgeDemo {
    private BridgeB b;

    public BridgeDemo(BridgeB b) {
        this.b = b;
    }
}

interface BridgeB {}
class BridgeBImpl1 implements BridgeB {}
class BridgeBImpl2 implements BridgeB {}

