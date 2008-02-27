package org.hamcrestcollections.example.employer;

import static org.hamcrestcollections.Selector.select;
import org.hamcrestcollections.Function;
import org.hamcrestcollections.FunctionMapper;
import static org.hamcrestcollections.RejectMatcher.reject;
import org.hamcrest.beans.HasPropertyWithValue;
import static org.hamcrest.Matchers.equalTo;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

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
        return select(employees, fieldMatcher(new Getter<Employee>() {
            public Integer get(Employee employee) {
                return employee.getAge(); 
            }
        }, idealEmployeeAge));
    }

    private <T> FieldMatcher<T> fieldMatcher(Getter<T> getter, int value) {
        return new FieldMatcher<T>(getter, value);
    }

    private static class FieldMatcher<T> extends TypeSafeMatcher<T>{
        private Getter getter;
        private Integer value;

        public FieldMatcher(Getter<T> getter, Integer value) {
            this.getter = getter;
            this.value = value;
        }

        public boolean matchesSafely(T employee) {
            Object value = getter.get(employee);
            if(this.value.equals(value)){
                return true;
            }
            return false;  
        }

        public void describeTo(Description description) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    public interface Getter<T>{
        Object get(T from);
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
