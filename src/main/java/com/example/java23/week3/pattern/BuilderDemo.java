package com.example.java23.week3.pattern;


/**
 *   impl1
 */
class Emp1 {
    private int id;
    private String name;


    public Emp1() {}

    public Emp1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Emp1 setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Emp1 setName(String name) {
        this.name = name;
        return this;
    }
}

/**
 *   impl2
 */
class Emp1Builder {
    private String name;
    private int id;

    public Emp1Builder setName(String name) {
        this.name = name;
        return this;
    }

    public Emp1Builder setId(int id) {
        this.id = id;
        return this;
    }

    public Emp1 build() {
        return new Emp1(id, name);
    }
}


class BuilderTest {
    public static void main(String[] args) {
        Emp1 e1 = new Emp1().setId(1).setName("Tom");
        Emp1 e2 = new Emp1Builder().setId(1).setName("Tom").build();
    }
}