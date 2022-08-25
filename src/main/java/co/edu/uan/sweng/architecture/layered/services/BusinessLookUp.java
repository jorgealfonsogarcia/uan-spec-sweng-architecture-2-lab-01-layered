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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Business lookup component to assist the {@link BusinessDelegate} to find the service to attend the client's requests.
 *
 * @author Jorge Garcia
 * @author Diego Poveda
 * @version 1.0.0
 * @since 17
 */
@Component
class BusinessLookUp {

    private final EmployeeService employeeService;

    @Autowired
    BusinessLookUp(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Gets the business service by the entity type.
     *
     * @param entityType the entity type class. It can't be {@code null}.
     * @param keyType    the key type class. It can't be {@code null}.
     * @param <E>        the entity type.
     * @param <K>        the key type.
     * @return the business service for the entity type.
     * @throws NullPointerException     if the {@code entityType} or the {@code keyType} are {@code null}.
     * @throws IllegalArgumentException if the {@code entityType} and the {@code keyType} aren't supported.
     */
    @SuppressWarnings("unchecked")
    <E, K> BusinessService<E, K> getBusinessService(final Class<E> entityType, final Class<K> keyType) {
        Objects.requireNonNull(entityType, "Entity type can't be null.");
        Objects.requireNonNull(keyType, "Key type can't be null.");

        if (Employee.class.equals(entityType) && Long.class.equals(keyType)) {
            return (BusinessService<E, K>) employeeService;
        }

        throw new IllegalArgumentException("There is no service supported for entity type %s and key type %s."
                .formatted(entityType.getSimpleName(), keyType.getSimpleName()));
    }
}
