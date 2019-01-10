package com.manueldeveloper;

import java.util.List;

public class EmployeeRepository {

    private List<Employee> employeeList;

    public EmployeeRepository(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Employee findById(Integer id) {
        for (Employee employee : employeeList) {
            if(employee.getId().equals(id)) {
                return employee;
            }
        }

        return null;
    }
}
