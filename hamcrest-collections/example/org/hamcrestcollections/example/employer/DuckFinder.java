package org.hamcrestcollections.example.employer;

import static org.hamcrestcollections.Selector.select;
import org.hamcrestcollections.Function;
import org.hamcrestcollections.FunctionMapper;
import static org.hamcrestcollections.RejectMatcher.reject;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.ArrayList;

public class DuckFinder {
    private List<Object> employees;

    public DuckFinder(List<Object> employees) {
        this.employees = employees;
    }

    public static void main(String[] args) {
        List<Object> myEmployees = new ArrayList<Object>();

        myEmployees.addAll(EmployeeRecords.ALL);
        myEmployees.add(new Me());
        myEmployees.add(new You(28));

        // Find all entries in the list (maybe employees) with a property called age that
        // has a value of 28.
        Iterable<Object> chosenEmployees = new DuckFinder(myEmployees).findWithAge(28);
        FunctionMapper.map(chosenEmployees, stdoutEmployeePrinter());


        Iterable<Object> lessChosenEmployees = reject(myEmployees, hasProperty("age", equalTo(28)));
        FunctionMapper.map(lessChosenEmployees, employeeFirer());


    }

    private Iterable<Object> findWithAge(Integer idealEmployeeAge) {
        return select(employees, hasProperty("age", equalTo(idealEmployeeAge)));
    }

    private static Function<Object, Object> employeeFirer() {
        return new Function<Object, Object>() {
           public Object apply(Object employee) {
                System.out.println("firing " + employee);
                return null;
            }
        };
    }

    private static Function<Object, Object> stdoutEmployeePrinter() {
        return new Function<Object, Object>() {
           public Object apply(Object employee) {
              System.out.println("thing = " + employee);
              return null;
          }
        };
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