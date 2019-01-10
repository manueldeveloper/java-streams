package com.manueldeveloper;

import com.manueldeveloper.config.EmployeeFactory;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class StreamSpecializationsTest {

    private final EmployeeFactory employeeFactory = new EmployeeFactory();
    private List<Employee> employees = employeeFactory.getEmployees();

    /**
     * There are three specializations: IntStream, LongStream and DoubleStream. All of them are
     * specializations of the class BaseStream, which is the same for Stream, and therefore,
     * functions and operations of Stream class are not available for previous mentioned streams
     * but have handy others like (sum, average, range, ...)
     */
    @Test
    public void mapToInt_operation() {
        // When
        final Integer result = employees.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElseThrow(NoSuchElementException::new);

        // Then
        assertEquals(Integer.valueOf(3), result);
    }

    @Test
    public void sum_operation() {
        // Given
        final IntStream intStream = IntStream.of(1, 2, 3);

        // When
        final int result = intStream.sum();

        // Then
        assertEquals(6, result);
    }

    @Test
    public void average_operation() {
        // Given
        final IntStream intStream = IntStream.of(1, 2, 3);

        // When
        final double result = intStream.average().orElseThrow(RuntimeException::new);

        // Then
        assertEquals(2, result, 0);
    }

    @Test
    public void range_operation() {
        // Given
        final IntStream range = IntStream.range(1, 4);

        // When
        final int result = range.sum();

        // Then
        assertEquals(6, result);
    }
}
