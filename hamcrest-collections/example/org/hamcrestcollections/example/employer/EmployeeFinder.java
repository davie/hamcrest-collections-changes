package org.hamcrestcollections.example.employer;

import static org.hamcrestcollections.Selector.select;
import org.hamcrestcollections.Function;
import org.hamcrestcollections.FunctionMapper;
import org.hamcrest.beans.HasPropertyWithValue;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.ArrayList;

public class EmployeeFinder {
    private List<Object> employees;

    public static void main(String[] args) {

        List<Object> myEmployees = new ArrayList<Object>();

        myEmployees.addAll(EmployeeRecords.ALL);
        myEmployees.add(new Me());
        myEmployees.add(new You(28));

        Iterable<Object> chosenEmployees = new EmployeeFinder(myEmployees).findWithAge(28);
        FunctionMapper.map(chosenEmployees, stdoutEmployeePrinter);
    }

    private Iterable<Object> findWithAge(Integer idealEmployeeAge) {
        return select(employees, HasPropertyWithValue.hasProperty("age", equalTo(idealEmployeeAge)));
    }

    private static Function<Object, Object> stdoutEmployeePrinter = new Function<Object, Object>() {
        public Object apply(Object employee) {
            System.out.println("thing = " + employee);
            return null;
        }
    };

    public EmployeeFinder(List<Object> employees) {
        this.employees = employees;
    }

    private static class Me {}

    public static class You {
        private You(int age) {
            this.age = age;
        }

        public String toString() {
            return "You can't hide, your age is: " + age;
        }

        private final int age;

        public int getAge() {
            return age;

        }
    }
}
