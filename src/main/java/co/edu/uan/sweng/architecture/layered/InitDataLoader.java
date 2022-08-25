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

package co.edu.uan.sweng.architecture.layered;

import co.edu.uan.sweng.architecture.layered.entities.Employee;
import co.edu.uan.sweng.architecture.layered.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Component to insert employees into the H2 database.
 *
 * @author Jorge Garcia
 * @author Diego Poveda
 * @version 1.0.0
 * @since 17
 */
@Component
public class InitDataLoader {

    private static final Logger LOGGER = Logger.getLogger(InitDataLoader.class.getName());

    private final EmployeeRepository employeeRepository;

    /**
     * Constructor.
     *
     * @param employeeRepository the employee repository.
     */
    @Autowired
    public InitDataLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Loads the employees' data to be saved into the database.
     */
    public void loadData() {
        createData().forEach(this::save);
    }

    private void save(final Employee employee) {
        employeeRepository.save(employee);
        LOGGER.log(Level.INFO, "Successfully saved ::{0}", employee);
    }

    private List<Employee> createData() {
        return List.of(
                new Employee("Shamik Mitra", "BagBazar", "M"),
                new Employee("Samir Mitra", "BagBazar", "M"),
                new Employee("Swastika Basu", "Baranagar", "F")
        );
    }


}
