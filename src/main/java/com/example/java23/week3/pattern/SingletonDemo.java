package com.example.java23.week3.pattern;

/**
 *  Eager loading
 *
 */
class EagerLoading {
    private final static EagerLoading instance = new EagerLoading();
    private EagerLoading() {}
    public static EagerLoading getInstance() {
        return instance;
    }
}

/**
 *  Lazy loading
 *
 */
class LazyLoading {
    private volatile static LazyLoading instance;
    private LazyLoading() {}
    public static LazyLoading getInstance() {
        if(instance == null) {
            synchronized (LazyLoading.class) {
                if(instance == null) {
                    instance = new LazyLoading();
                }
            }
        }
        return instance;
    }
}
/**
 * enum
 */
enum EnumSingleton {
    INSTANCE1,INSTANCE2;
}

/**
 *  class Emp {
 *      name
 *      Address(id, street)
 *  }
 *  Emp e1 = new Emp();
 *  Emp e2 = shallowCopy(e1);
 *  e1.getAddress() == e2.getAddress()
 *
 *  singleton issue
 *      1. cloneable : override clone() => throw exception
 *      2. serializable + de-serializable : deep copy
 *      3. reflection
 *
 *  example
 *      properties
 *      logger
 *      ..
 */

