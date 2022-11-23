package com.example.java23.week3.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 *    client(public ip + port)  <->  server(public ip + port)
 *
 */
class Topic<T> {
    private final List<Subscriber<T>> subscriberList = new ArrayList<>();
    public void publish(T msg) {
        for(Subscriber sub: subscriberList) {
            sub.receive(msg);
        }
    }
}

class Subscriber<T> {
    public void receive(T msg) {
        System.out.println(msg);
    }
}
