package com.manueldeveloper;

import com.manueldeveloper.config.EmployeeFactory;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

public class ParallelStreamTest {

    private final EmployeeFactory employeeFactory = new EmployeeFactory();
    private List<Employee> employees = employeeFactory.getEmployees();

    @Test
    public void parallel_stream() {
        // When
        employees.stream()
                .parallel()
                .forEach(e -> e.salaryIncrement(10.0));

        // Then
        assertThat(employees, contains(
                hasProperty("salary", equalTo(110000.0)),
                hasProperty("salary", equalTo(220000.0)),
                hasProperty("salary", equalTo(330000.0))
        ));
    }
}
