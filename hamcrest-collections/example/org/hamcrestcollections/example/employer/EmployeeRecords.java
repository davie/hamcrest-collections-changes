package org.hamcrestcollections.example.employer;

import org.hamcrestcollections.ListUtils;

import java.util.List;

public class EmployeeRecords {
    public static Employee DEREK = new Employee(25, "Derek", 15000);
    public static Employee CLIVE = new Employee(28, "Clive", 25000);
    public static Employee NORMAN = new Employee(45, "Norman", 38000);
    public static List<Employee> ALL =
          ListUtils.listWith(EmployeeRecords.CLIVE, EmployeeRecords.DEREK, EmployeeRecords.NORMAN);
}
