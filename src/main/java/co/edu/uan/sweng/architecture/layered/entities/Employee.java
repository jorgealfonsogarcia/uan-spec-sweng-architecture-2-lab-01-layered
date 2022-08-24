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

/**
 * Employee.
 *
 * @author Jorge Garcia
 * @author Diego Poveda
 * @version 1.0.0
 * @since 17
 */
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String sex;

    /**
     * Empty constructor.
     */
    public Employee() {
    }

    /**
     * Constructor.
     *
     * @param name    the name.
     * @param address the address.
     * @param sex     the sex.
     */
    public Employee(String name, String address, String sex) {
        this.name = name;
        this.address = address;
        this.sex = sex;
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
     * Gets the address.
     *
     * @return the address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address the address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the sex.
     *
     * @return the sex.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the sex.
     *
     * @param sex the sex.
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Employee employee = (Employee) o;

        if (!id.equals(employee.id)) {
            return false;
        }

        if (!name.equals(employee.name)) {
            return false;
        }

        if (!address.equals(employee.address)) {
            return false;
        }
        return sex.equals(employee.sex);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + sex.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
