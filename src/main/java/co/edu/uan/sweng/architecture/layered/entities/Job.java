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

package co.edu.uan.sweng.architecture.layered.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;

/**
 * Job.
 *
 * @author Jorge Garcia
 * @author Diego Poveda
 * @version 1.0.0
 * @since 17
 */
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private Double usdSalary;

    /**
     * Empty constructor.
     */
    public Job() {
    }

    /**
     * Constructor. The {@code id} is set as zero.
     *
     * @param name      the name.
     * @param usdSalary the USD salary.
     */
    public Job(String name, Double usdSalary) {
        this(LONG_ZERO, name, usdSalary);
    }

    /**
     * Constructor.
     *
     * @param id        the id.
     * @param name      the name.
     * @param usdSalary the USD salary.
     */
    public Job(Long id, String name, Double usdSalary) {
        this.id = id;
        this.name = name;
        this.usdSalary = usdSalary;
    }

    /**
     * Gets the id.
     *
     * @return the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the USD salary.
     *
     * @return the USD salary.
     */
    public Double getUsdSalary() {
        return usdSalary;
    }

    /**
     * Sets the USD salary.
     *
     * @param usdSalary the USD salary.
     */
    public void setUsdSalary(Double usdSalary) {
        this.usdSalary = usdSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        var job = (Job) o;
        return id.equals(job.id) && name.equals(job.name) && usdSalary.equals(job.usdSalary);

    }

    @Override
    public int hashCode() {
        var result = id.hashCode();
        var factor = 31;
        result = factor * result + name.hashCode();
        result = factor * result + usdSalary.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", usdSalary=" + usdSalary +
                '}';
    }
}
