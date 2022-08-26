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

import co.edu.uan.sweng.architecture.layered.controller.dto.EmployeeDTO;
import co.edu.uan.sweng.architecture.layered.entities.Employee;
import co.edu.uan.sweng.architecture.layered.services.BusinessDelegate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

    /**
     * Finds all the employees.
     *
     * @return all the employees.
     */
    @Operation(summary = "Finds all the employees.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all the employees.", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))
            })
    })
    @GetMapping
    public ResponseEntity<Iterable<Employee>> findAll() {
        return ResponseEntity.ok(businessDelegate.findAll(Employee.class));
    }

    /**
     * Finds an employee by its id.
     *
     * @param id the employee's id.
     * @return the employee.
     */
    @Operation(summary = "Finds a employee by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the employee.", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found the employee")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Employee> find(@PathVariable Long id) {
        final var employee = businessDelegate.find(Employee.class, id);
        return employee.isEmpty() ? ResponseEntity.status(NOT_FOUND).build() : ResponseEntity.ok(employee.get());
    }

    /**
     * Saves an employee.
     *
     * @param employeeDTO the employee DTO.
     * @return the saved employee.
     */
    @Operation(summary = "Saves a employee.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved the employee.", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))
            })
    })
    @PostMapping
    public ResponseEntity<Employee> save(@Parameter(description = "The employee DTO")
                                         @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(businessDelegate.save(new Employee(employeeDTO.id(), employeeDTO.name(),
                employeeDTO.address(), employeeDTO.sex())));
    }

    /**
     * Deletes an employee.
     *
     * @param id the employee's id.
     * @return the deleted employee.
     */
    @Operation(summary = "Deletes a employee.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the employee and deleted it.", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found the employee")
    })
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
