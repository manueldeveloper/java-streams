package com.manueldeveloper;

import com.manueldeveloper.config.EmployeeFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ReduceOperationsTest {

    private final EmployeeFactory employeeFactory = new EmployeeFactory();
    private List<Employee> employees = employeeFactory.getEmployees();

    @Test
    public void reduce_operation() {
        // When
        final Double result = employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);

        // Then
        assertEquals(Double.valueOf(600000), result);
    }

    @Test
    public void joining_operation() {
        // Given
        String expected = employees.get(0).getName() + ", "
                + employees.get(1).getName() + ", "
                + employees.get(2).getName();
        // When
        final String result = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));

        // Then
        assertEquals(expected, result);
    }

    @Test
    public void toSet_operation() {
        // When
        final Set<Employee> result = employees.stream()
                .collect(Collectors.toSet());

        // Then
        assertEquals(3, result.size());
    }

    @Test
    public void toCollection_operation() {
        // When
        final Vector<Employee> result = employees.stream()
                .collect(Collectors.toCollection(Vector::new));

        // Then
        assertEquals(3, result.size());
    }

    @Test
    public void summarizingDouble_operation() {
        // When
        final DoubleSummaryStatistics result = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        // Then
        assertEquals(3, result.getCount());
        assertEquals(600000.0, result.getSum(), 0);
        assertEquals(100000.0, result.getMin(), 0);
        assertEquals(300000.0, result.getMax(), 0);
        assertEquals(200000.0, result.getAverage(), 0);
    }

    @Test
    public void partitioningBy_operation() {
        // Given
        final List<Integer> integerList = Arrays.asList(2, 4, 5, 6, 8);

        // When
        final Map<Boolean, List<Integer>> result = integerList.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));

        // Then
        assertEquals(4, result.get(true).size());
        assertEquals(1, result.get(false).size());
    }

    @Test
    public void groupingBy_operation() {
        // When
        final Map<Character, List<Employee>> result = employees.stream()
                .collect(Collectors.groupingBy(e -> new Character(e.getName().charAt(0))));

        // Then
        assertEquals("Bill Gates", result.get('B').get(0).getName());
        assertEquals("Jeff Bezos", result.get('J').get(0).getName());
        assertEquals("Mark Zuckerberg", result.get('M').get(0).getName());
    }

    @Test
    public void mapping_operation() {
        // When
        final Map<Character, List<Integer>> result = employees.stream()
                .collect(Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
                        Collectors.mapping(Employee::getId, Collectors.toList())));

        // Then
        assertEquals(Integer.valueOf(2), result.get('B').get(0));
        assertEquals(Integer.valueOf(1), result.get('J').get(0));
        assertEquals(Integer.valueOf(3), result.get('M').get(0));
    }

    @Test
    public void reducing_operation() {
        // Given
        Double percentage = 10.0;

        // When
        final Double result = employees.stream()
                .collect(Collectors.reducing(0.0, e -> e.getSalary() * (percentage / 100), (s1, s2) -> s1 + s2));

        // Then
        assertEquals(60000.0, result, 0);
    }
}
