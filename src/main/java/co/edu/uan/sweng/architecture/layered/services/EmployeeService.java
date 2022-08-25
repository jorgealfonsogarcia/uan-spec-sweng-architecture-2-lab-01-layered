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
class EmployeeService implements BusinessService<Employee, Long> {

    private static final Logger LOGGER = Logger.getLogger(EmployeeService.class.getName());

    private final EmployeeRepository employeeRepository;

    /**
     * Constructor.
     *
     * @param employeeRepository the employee repository.
     */
    @Autowired
    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        employee = employeeRepository.save(employee);
        LOGGER.log(Level.INFO, "Employee saved::{0}", employee);
        return employee;
    }

    @Override
    public Optional<Employee> find(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        LOGGER.log(Level.INFO, "Employee found::{0}", employee);
        return employee;
    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
        LOGGER.log(Level.INFO, "Employee deleted::{0}", employee);
    }
}
