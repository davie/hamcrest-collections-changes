package org.hamcrestcollections.example.employer;

import static org.hamcrestcollections.Selector.select;
import org.hamcrestcollections.Function;
import org.hamcrestcollections.FunctionMapper;
import org.hamcrest.beans.HasPropertyWithValue;import static org.hamcrest.Matchers.equalTo;

public class EmployeeFinder {
    private static Function<Employee, Object> stdoutEmployeePrinter = new Function<Employee, Object>() {
        public Object apply(Employee employee) {
            System.out.println("employee = " + employee);
            return null;
        }
    };

    public static void main(String[] args) {
        Iterable<Employee> employees = new EmployeeFinder().findWithAge(28);
        FunctionMapper.map(employees, stdoutEmployeePrinter);
    }

    private Iterable<Employee> findWithAge(Integer idealEmployeeAge) {
        return select(EmployeeRecords.ALL, HasPropertyWithValue.<Employee>hasProperty("age", equalTo(idealEmployeeAge)));
    }
}
