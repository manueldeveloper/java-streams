package com.manueldeveloper;

import com.manueldeveloper.config.EmployeeFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ComparisonOperationsTest {

    private final EmployeeFactory employeeFactory = new EmployeeFactory();
    private List<Employee> employees = employeeFactory.getEmployees();


    /**
     * sorted -> "Pedicate" function & Lazy operation
     */
    @Test
    public void sorted_operation() {
        // When
        final List<Employee> result = employees.stream()
                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                .collect(Collectors.toList());

        // Then
        assertEquals("Bill Gates", result.get(0).getName());
        assertEquals("Jeff Bezos", result.get(1).getName());
        assertEquals("Mark Zuckerberg", result.get(2).getName());
    }

    /**
     * sorted -> "Pedicate" function & Lazy operation
     */
    @Test
    public void sorted_with_comparing_operation() {
        // When
        final List<Employee> result = employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .collect(Collectors.toList());

        // Then
        assertEquals("Bill Gates", result.get(0).getName());
        assertEquals("Jeff Bezos", result.get(1).getName());
        assertEquals("Mark Zuckerberg", result.get(2).getName());
    }

    /**
     * min > "Predicate" function & Terminal operation
     */
    @Test
    public void min_operation() {
        // When
        final Employee result = employees.stream()
                .min(Comparator.comparingInt(Employee::getId))
                .orElseThrow(NoSuchElementException::new);

        // Then
        assertEquals(Integer.valueOf(1), result.getId());
    }

    /**
     * max > "Predicate" function & Terminal operation
     */
    @Test
    public void max_operation() {
        // When
        final Employee result = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);

        // Then
        assertEquals(Double.valueOf(300000.0), result.getSalary());
    }

    /**
     * allMatch > Predicate function & Terminal operation
     */
    @Test
    public void allMatch_operation() {
        // Given
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);

        // When
        final boolean result = intList.stream().allMatch(i -> i % 2 == 0);

        // Then
        assertEquals(false, result);
    }

    /**
     * anyMatch > Predicate function & Terminal operation
     */
    @Test
    public void anyMatch_operation() {
        // Given
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);

        // When
        final boolean result = intList.stream().anyMatch(i -> i % 2 == 0);

        // Then
        assertEquals(true, result);
    }

    /**
     * noneMatch > Predicate function & Terminal operation
     */
    @Test
    public void noneMatch_operation() {
        // Given
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);

        // When
        final boolean result = intList.stream().noneMatch(i -> i % 3 == 0);

        // Then
        assertEquals(false, result);
    }
}
