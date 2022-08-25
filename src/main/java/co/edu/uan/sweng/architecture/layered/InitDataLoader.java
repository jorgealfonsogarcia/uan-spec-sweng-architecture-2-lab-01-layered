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
import co.edu.uan.sweng.architecture.layered.entities.Job;
import co.edu.uan.sweng.architecture.layered.services.BusinessDelegate;
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

    private final BusinessDelegate businessDelegate;

    /**
     * Constructor.
     *
     * @param businessDelegate the business delegate.
     */
    @Autowired
    public InitDataLoader(BusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    /**
     * Loads the initial data to be saved into the database.
     */
    public void loadData() {
        createEmployees().forEach(this::save);
        createJobs().forEach(this::save);
    }

    private <E> void save(final E entity) {
        businessDelegate.save(entity);
        LOGGER.log(Level.INFO, "Successfully saved ::{0}", entity);
    }

    private List<Employee> createEmployees() {
        return List.of(
                new Employee("Shamik Mitra", "BagBazar", "M"),
                new Employee("Samir Mitra", "BagBazar", "M"),
                new Employee("Swastika Basu", "Baranagar", "F")
        );
    }

    private List<Job> createJobs() {
        return List.of(
                new Job("Programmer", 10000d),
                new Job("Manager", 20000d),
                new Job("Designer", 9000d)
        );
    }
}
