package com.example.java23;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Junit 5 : @BeforeEach + @BeforeAll
 */
public class UnitTestExample {
    private static A a;
    private static B b;
//    @BeforeEach
//    public void init() {
//        b = Mockito.mock(B.class);
//        a = new A(b);
//        System.out.println("this is init()");
//    }

    @BeforeAll
    public static void init() {
        b = Mockito.mock(B.class);
        a = new A(b);
        System.out.println("this is init()");
    }

    @Test
    public void testCalculatePositive() {
        Mockito.when(b.get()).thenReturn(20);
        assertTrue(a.calculate() == 40);
        assertTrue(a.calculate() == 40);
        Mockito.verify(b, Mockito.times(2)).get();
    }

    @Test
    public void testCalculateNegative() {
        Mockito.when(b.get()).thenReturn(-10);
        assertTrue(a.calculate() == -20);
    }

    @Test
    public void testCalculateZero() {
        Mockito.when(b.get()).thenReturn(0);
        assertTrue(a.calculate() == 0);
    }
}


class A {
    private B b;

    public A(B b) {
        this.b = b;
    }

    public int calculate() {
        int res = b.get();
        return res * 2;
    }
}

class B {
    public int get() {
        return 10;
    }
}