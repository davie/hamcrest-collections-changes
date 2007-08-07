package org.hamcrestcollections.example.employer;

import java.util.Date;

public class Employee {
    public final int age;
    public final String name;
    public final int pay;

    public Employee(int age, String name, int pay) {
        this.age = age;
        this.name = name;
        this.pay = pay;
    }

    public String toString() {
        return name;
    }
}
