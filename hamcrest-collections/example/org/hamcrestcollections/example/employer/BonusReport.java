package org.hamcrestcollections.example.employer;

import org.hamcrestcollections.*;
import static org.hamcrestcollections.FunctionMapper.map;
import static org.hamcrestcollections.Zipper.zip;

import java.util.List;
import java.util.Map;

public class BonusReport {
    private List<Employee> employees;

    public BonusReport(List<Employee> employees) {
        this.employees = employees;
    }

    public Map<String, Double> calculateBonuses(Double bonusPercentage) {
        Iterable<Double> bonuses = map(employees, new BonusCalculator(bonusPercentage));
        Iterable<String> names = map(employees, Functions.<Employee>asString());
        return zip(names, bonuses);
    }

    public static void main(String args[]) {
        double fivePercent = 0.05;
        Map<String, Double> map = new BonusReport(EmployeeRecords.ALL).calculateBonuses(fivePercent);

        for (String name : map.keySet()) {
            System.out.println(name + " gets $" + map.get(name) + " bonus");
        }
    }

    class BonusCalculator implements Function<Employee, Double> {
        private Double percentageBonus;

        public BonusCalculator(Double percentageBonus) {
            this.percentageBonus = percentageBonus;
        }

        public Double apply(Employee employee) {
            return (double)employee.pay * percentageBonus;
        }
    }
}
