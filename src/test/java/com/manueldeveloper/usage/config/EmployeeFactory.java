package com.manueldeveloper.usage.config;

import com.manueldeveloper.usage.Employee;
import com.manueldeveloper.usage.EmployeeRepository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EmployeeFactory {

    private final List<Employee> employees;
    private final EmployeeRepository repository;

    public EmployeeFactory() {
        employees = new ArrayList();
        employees.add(createEmployee(1, "Jeff Bezos", 100000.0));
        employees.add(createEmployee(2, "Bill Gates", 200000.0));
        employees.add(createEmployee(3, "Mark Zuckerberg", 300000.0));

        repository = new EmployeeRepository(employees);
    }

    private Employee createEmployee(@NotNull Integer id, @NotNull String name, @NotNull Double salary) {
        return new Employee(id, name, salary);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Stream<Employee> getEmployeesAsStream() {
        return employees.stream();
    }

    public EmployeeRepository getEmployeeRepository() {
        return repository;
    }
}
