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

import co.edu.uan.sweng.architecture.layered.entities.Job;
import co.edu.uan.sweng.architecture.layered.services.BusinessDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST controller for {@link Job}
 *
 * @author Jorge Garcia
 * @author Diego Poveda
 * @version 1.0.0
 * @since 17
 */
@RestController
@RequestMapping("/jobs")
public class JobController {

    private final BusinessDelegate businessDelegate;

    /**
     * Constructor.
     *
     * @param businessDelegate the business delegate.
     */
    @Autowired
    public JobController(BusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @GetMapping
    public Iterable<Job> findAll() {
        return businessDelegate.findAll(Job.class);
    }

    @GetMapping("/{id}")
    public Job find(@PathVariable Long id) {
        return businessDelegate.find(Job.class, id).orElse(null);
    }

    @PostMapping
    public Job save(@RequestBody Job job) {
        return businessDelegate.save(job);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Optional<Job> employee = businessDelegate.find(Job.class, id);
        if (employee.isEmpty()) {
            return "Job Not Exists, Not able to delete.";
        }

        businessDelegate.delete(employee.get());
        return "Deleted Successfully.";
    }
}
