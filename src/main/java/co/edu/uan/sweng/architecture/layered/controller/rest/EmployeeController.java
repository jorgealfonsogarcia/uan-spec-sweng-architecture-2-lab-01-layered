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

package co.edu.uan.sweng.architecture.layered.controller.rest;

import co.edu.uan.sweng.architecture.layered.entities.Employee;
import co.edu.uan.sweng.architecture.layered.services.BusinessDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * REST controller for {@link Employee}
 *
 * @author Jorge Garcia
 * @author Diego Poveda
 * @version 1.0.0
 * @since 17
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final BusinessDelegate businessDelegate;

    /**
     * Constructor.
     *
     * @param businessDelegate the business delegate.
     */
    @Autowired
    public EmployeeController(BusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @GetMapping
    public ResponseEntity<Iterable<Employee>> findAll() {
        return ResponseEntity.ok(businessDelegate.findAll(Employee.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> find(@PathVariable Long id) {
        final var employee = businessDelegate.find(Employee.class, id);
        return employee.isEmpty() ? ResponseEntity.status(NOT_FOUND).build() : ResponseEntity.ok(employee.get());
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        return ResponseEntity.ok(businessDelegate.save(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        final var employee = businessDelegate.find(Employee.class, id);
        if (employee.isEmpty()) {
            return ResponseEntity.status(NOT_FOUND).body("Employee Not Exists, Not able to delete.");
        }

        businessDelegate.delete(employee.get());
        return ResponseEntity.ok("Deleted Successfully.");
    }
}
