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

import java.util.Optional;

/**
 * Represents a basic CRUD business service.
 *
 * @param <E> the entity type.
 * @param <K> the primary key type.
 * @author Jorge Garcia
 * @author Diego Poveda
 * @version 1.0.0
 * @since 17
 */
public interface BusinessService<E, K> {

    /**
     * Saves an entity.
     *
     * @param entity the entity.
     * @return the saved entity.
     */
    E save(E entity);

    /**
     * Finds an entity by its id.
     *
     * @param id the id.
     * @return the requested entity.
     */
    Optional<E> find(K id);

    /**
     * Finds all the entities.
     *
     * @return all the entities.
     */
    Iterable<E> findAll();

    /**
     * Deletes an entity.
     *
     * @param entity the entity.
     */
    void delete(E entity);
}
