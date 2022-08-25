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
import io.swagger.v3.oas.annotations.Operation;
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

    /**
     * Finds all the jobs.
     *
     * @return all the jobs.
     */
    @Operation(summary = "Finds all the jobs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all the jobs.", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Job.class))
            })
    })
    @GetMapping
    public ResponseEntity<Iterable<Job>> findAll() {
        return ResponseEntity.ok(businessDelegate.findAll(Job.class));
    }

    /**
     * Finds a job by its id.
     *
     * @param id the job's id.
     * @return the job.
     */
    @Operation(summary = "Finds a job by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the job.", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Job.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found the job")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Job> find(@PathVariable Long id) {
        final var job = businessDelegate.find(Job.class, id);
        return job.isEmpty() ? ResponseEntity.status(NOT_FOUND).build() : ResponseEntity.ok(job.get());
    }

    /**
     * Saves a job.
     *
     * @param job the job.
     * @return the saved job.
     */
    @Operation(summary = "Saves a job.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved the job.", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Job.class))
            })
    })
    @PostMapping
    public ResponseEntity<Job> save(@RequestBody Job job) {
        return ResponseEntity.ok(businessDelegate.save(job));
    }

    /**
     * Deletes a job.
     *
     * @param id the job's id.
     * @return the deleted job.
     */
    @Operation(summary = "Deletes a job.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the job and deleted it.", content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Job.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found the job")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        final var job = businessDelegate.find(Job.class, id);
        if (job.isEmpty()) {
            return ResponseEntity.status(NOT_FOUND).body("Job Not Exists, Not able to delete.");
        }

        businessDelegate.delete(job.get());
        return ResponseEntity.ok("Deleted Successfully.");
    }
}
