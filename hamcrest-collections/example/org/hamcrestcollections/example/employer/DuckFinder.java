package org.hamcrestcollections.example.employer;

import static org.hamcrestcollections.Selector.select;
import org.hamcrestcollections.Function;
import org.hamcrestcollections.FunctionMapper;
import org.hamcrestcollections.ReturnLessFunction;
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

        // hmmm... maybe there are some ducks in here.
        animals.addAll(EmployeeRecords.ALL);
        animals.add(new Me());
        animals.add(new You(28));
        animals.add(new Duck());

        // Find all entries in the list (maybe employees) with a property called age that
        // has a value of 28.
        Iterable<Object> chosenEmployees = new DuckFinder(animals).findWithAge(28);

        FunctionMapper.map(chosenEmployees, stdoutAnimalPrinter());


        Iterable<Object> quackers = new DuckFinder(animals).soundsLike("quack");
        // would be nice if this could give you a list of Ducks (or Quackables)
        FunctionMapper.map(quackers, duckShooter());


        Iterable<Object> lessChosenEmployees = reject(animals, hasProperty("age", equalTo(28)));
        FunctionMapper.map(lessChosenEmployees, animalRoaster());
    }

    private Iterable<Object> soundsLike(String noise) {
        return select(animals, hasProperty("soundsLike", equalTo(noise)));
    }

    private Iterable<Object> findWithAge(Integer idealDuckAge) {
        return select(animals, hasProperty("age", equalTo(idealDuckAge)));
    }

    private static Function<Object, Object> animalRoaster() {
        return new ReturnLessFunction<Object>() {
           public void applyWithoutReturn(Object animal) {
                System.out.println("roasting  " + animal);
            }
        };
    }

    private static Function<Object, Object> duckShooter() {
        return new ReturnLessFunction<Object>() {
           public void applyWithoutReturn(Object animal) {
                System.out.println("shooting something that sounds like a duck " + animal);
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

    public static class Duck {
        public String getSoundsLike(){
            return "quack";
        }
    }

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