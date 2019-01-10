package com.manueldeveloper.usage;

import com.manueldeveloper.usage.config.EmployeeFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class StreamOperationsTest {

    private final EmployeeFactory employeeFactory = new EmployeeFactory();

    private List<Employee> employees = employeeFactory.getEmployees();
    private Stream<Employee> employeeStream = employeeFactory.getEmployeesAsStream();
    private EmployeeRepository repository = employeeFactory.getEmployeeRepository();

    /**
     * forEach > Consumer function & Terminal operation
     */
    @Test
    public void forEach_operation() {
        // When
        employeeStream.forEach(e -> e.salaryIncrement(10.0));

        // Then
        assertThat(employees, contains(
                hasProperty("salary", equalTo(110000.0)),
                hasProperty("salary", equalTo(220000.0)),
                hasProperty("salary", equalTo(330000.0))
        ));
    }

    /**
     * map > Function function & Lazy operation
     */
    @Test
    public void map_operation() {
        // Given
        Integer[] ids = {1, 2, 3};

        // When
        final List<Employee> result = Stream.of(ids)
                .map(repository::findById)
                .collect(Collectors.toList());

        // Then
        assertEquals(ids.length, result.size());
    }

    /**
     * collect > Function function & Terminal operation
     */
    @Test
    public void collect_operation() {
        // When
        final List<Employee> result = employees.stream().collect(Collectors.toList());

        // Then
        assertEquals(employees, result);
    }

    /**
     * filter > Predicate function & Lazy operation
     */
    @Test
    public void filter_operation() {
        // Given
        Integer[] ids = {1, 2, 3, 4};

        // When
        final List<Employee> result = Stream.of(ids)
                .map(repository::findById)
                .filter(e -> e != null)
                .filter(e -> e.getSalary() > 200000)
                .collect(Collectors.toList());

        // Then
        assertEquals(Arrays.asList(employees.get(2)), result);
    }

    /**
     * findFirst > Predicate function & Terminal operation
     */
    @Test
    public void findFirst_operation() {
        // Given
        Integer[] ids = {1, 2, 3, 4};

        // When
        final Employee result = Stream.of(ids)
                .map(repository::findById)
                .filter(e -> e != null)
                .filter(e -> e.getSalary() > 100000)
                .findFirst()
                .orElse(null);

        // Then
        assertNotNull(result);
        assertEquals(new Double(200000), result.getSalary());
    }

    /**
     * toArray > Function function & Terminal operation
     */
    @Test
    public void toArray_operation() {
        // When
        final Employee[] result = employees.stream().toArray(Employee[]::new);

        // Then
        assertThat(result, equalTo(employees.toArray()));
    }

    /**
     * flatMap > Operator function & Lazy operation
     */
    @Test
    public void flatMap_operation() {
        // Given
        final List<List<String>> nestedNames = Arrays.asList(
                Arrays.asList("Jeff", "Bezos"),
                Arrays.asList("Bill", "Gates"),
                Arrays.asList("Mark", "Zuckerberg")
        );

        // When
        final List<String> result = nestedNames.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // Then
        assertEquals(nestedNames.size() * 2, result.size());
    }

    /**
     * peek > Operator function & Lazy operation
     */
    @Test
    public void peek_operation() {
        // When
        final List<Employee> result = employees.stream()
                .peek(e -> e.salaryIncrement(10.0))
                .peek(System.out::println)
                .collect(Collectors.toList());

        // Then
        assertThat(employees, contains(
                hasProperty("salary", equalTo(110000.0)),
                hasProperty("salary", equalTo(220000.0)),
                hasProperty("salary", equalTo(330000.0))
        ));
    }
}
