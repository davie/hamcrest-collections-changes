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
    private List<Object> animals;

    public DuckFinder(List<Object> animals) {
        this.animals = animals;
    }

    public static void main(String[] args) {
        List<Object> animals = new ArrayList<Object>();

        animals.addAll(EmployeeRecords.ALL);
        animals.add(new Me());
        animals.add(new You(28));

        // Find all entries in the list (maybe employees) with a property called age that
        // has a value of 28.
        Iterable<Object> chosenEmployees = new DuckFinder(animals).findWithAge(28);
        FunctionMapper.map(chosenEmployees, stdoutAnimalPrinter());


        Iterable<Object> lessChosenEmployees = reject(animals, hasProperty("age", equalTo(28)));
        FunctionMapper.map(lessChosenEmployees, animalRoaster());
    }

    private Iterable<Object> findWithAge(Integer idealDuckAge) {
        return select(animals, hasProperty("age", equalTo(idealDuckAge)));
    }

    private static Function<Object, Object> animalRoaster() {
        return new Function<Object, Object>() {
           public Object apply(Object animal) {
                System.out.println("firing " + animal);
                return null;
            }
        };
    }

    private static Function<Object, Object> stdoutAnimalPrinter() {
        return new Function<Object, Object>() {
           public Object apply(Object animal) {
              System.out.println("thing = " + animal);
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