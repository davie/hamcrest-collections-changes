package org.hamcrestcollections.example.employer;

import static org.hamcrestcollections.Selector.select;
import org.hamcrestcollections.Function;
import org.hamcrestcollections.FunctionMapper;
import static org.hamcrestcollections.RejectMatcher.reject;
import org.hamcrest.beans.HasPropertyWithValue;
import static org.hamcrest.Matchers.equalTo;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Matcher;

import java.util.List;
import java.util.ArrayList;

public class EmployeeFinder {
    private List<Employee> employees;

    public EmployeeFinder(List<Employee> employees) {
        this.employees = employees;
    }

    public static void main(String[] args) {
        List<Employee> myEmployees = new ArrayList<Employee>();

        myEmployees.addAll(EmployeeRecords.ALL);

        // Find all entries in the list (maybe employees) with a property called age that
        // has a value of 28.
        Iterable<Employee> chosenEmployees = new EmployeeFinder(myEmployees).findWithAge(28);
        FunctionMapper.map(chosenEmployees, stdoutEmployeePrinter());


        Iterable<Employee> lessChosenEmployees = reject(myEmployees, HasPropertyWithValue.<Employee>hasProperty("age", equalTo(28)));
        FunctionMapper.map(lessChosenEmployees, employeeFirer());


    }

    private Iterable<Employee> findWithAge(Integer idealEmployeeAge) {
//        return select(employees, HasPropertyWithValue.<Employee>hasProperty("age", equalTo(idealEmployeeAge)));
        Getter<Employee, Integer> ageGetter = new Getter<Employee, Integer>() {
            public Integer get(Employee employee) {
                return employee.getAge();
            }
        };
        return select(employees, fieldMatcher(ageGetter, equalTo(idealEmployeeAge)));
    }

    private static <T, R> FieldMatcher<T, R> fieldMatcher(Getter<T, R> getter, Matcher<R> value) {
        return new FieldMatcher<T, R>(getter, value);
    }

    private static class FieldMatcher<T, R> extends TypeSafeMatcher<T>{
        private Getter<T, R> getter;
        private Matcher<R> matcher;

        public FieldMatcher(Getter<T, R> getter, Matcher<R> matcher) {
            this.getter = getter;
            this.matcher = matcher;
        }

        public boolean matchesSafely(T employee) {
            R value = getter.get(employee);
            if(this.matcher.matches(value)){
                return true;
            }
            return false;  
        }

        public void describeTo(Description description) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    public interface Getter<T, R>{
        R get(T from);
    }

    private static Function<Employee, Object> employeeFirer() {
        return new Function<Employee, Object>() {
           public Object apply(Employee employee) {
                System.out.println("firing " + employee);
                return null;
            }
        };
    }

    private static Function<Employee, Object> stdoutEmployeePrinter() {
        return new Function<Employee, Object>() {
           public Object apply(Employee employee) {
              System.out.println("thing = " + employee);
              return null;
          }
        };
    }

}
