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

import co.edu.uan.sweng.architecture.layered.entities.Job;
import co.edu.uan.sweng.architecture.layered.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Business service for {@link Job}
 *
 * @author Jorge Garcia
 * @author Diego Poveda
 * @version 1.0.0
 * @since 17
 */
@Service
class JobService implements BusinessService<Job, Long> {

    private static final Logger LOGGER = Logger.getLogger(JobService.class.getName());

    private final JobRepository jobRepository;

    /**
     * Constructor.
     *
     * @param jobRepository the job repository.
     */
    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job save(Job job) {
        job = jobRepository.save(job);
        LOGGER.log(Level.INFO, "Job saved::{0}", job);
        return job;
    }

    @Override
    public Optional<Job> find(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        LOGGER.log(Level.INFO, "Job found::{0}", job);
        return job;
    }

    @Override
    public Iterable<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void delete(Job job) {
        jobRepository.delete(job);
        LOGGER.log(Level.INFO, "Job deleted::{0}", job);
    }
}
