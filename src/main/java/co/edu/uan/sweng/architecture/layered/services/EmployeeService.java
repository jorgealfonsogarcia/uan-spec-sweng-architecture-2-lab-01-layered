/*
 * Copyright (C) 2022 Jorge Garcia, Diego Poveda, UAN.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package co.edu.uan.sweng.architecture.layered.services;

import co.edu.uan.sweng.architecture.layered.entities.Employee;
import co.edu.uan.sweng.architecture.layered.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Business service for {@link Employee}
 *
 * @author Jorge Garcia
 * @author Diego Poveda
 * @version 1.0.0
 * @since 17
 */
@Service
public class EmployeeService {

    private static final Logger LOGGER = Logger.getLogger(EmployeeService.class.getName());

    private final EmployeeRepository employeeRepository;

    /**
     * Constructor.
     *
     * @param employeeRepository the employee repository.
     */
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Adds a employee.
     *
     * @param employee the employee.
     * @return the added employee with its new id.
     */
    public Employee addEmployee(Employee employee) {
        employee = employeeRepository.save(employee);
        LOGGER.log(Level.INFO, "Employee saved::{0}", employee);
        return employee;
    }

    /**
     * Finds an employee by its id.
     *
     * @param id the id.
     * @return the requested employee.
     */
    public Optional<Employee> findEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        LOGGER.log(Level.INFO, "Employee found::{0}", employee);
        return employee;
    }

    /**
     * Finds all the employees.
     *
     * @return all the employees.
     */
    public Iterable<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    /**
     * Deletes an employee.
     *
     * @param employee the employee.
     */
    public void deleteEmployeeById(Employee employee) {
        employeeRepository.delete(employee);
        LOGGER.log(Level.INFO, "Employee deleted::{0}", employee);
    }
}
