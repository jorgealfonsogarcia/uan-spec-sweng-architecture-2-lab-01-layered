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

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Business delegate using the Proxy design pattern.
 *
 * @author Jorge Garcia
 * @author Diego Poveda
 * @version 1.0.0
 * @since 17
 */
@Component
public class BusinessDelegate {

    private final BusinessLookUp businessLookUp;

    /**
     * Constructor.
     *
     * @param businessLookUp the business lookup.
     */
    @Autowired
    public BusinessDelegate(BusinessLookUp businessLookUp) {
        this.businessLookUp = businessLookUp;
    }


    /**
     * Saves an entity.
     *
     * @param entity the entity.
     * @param <E>    the entity type.
     * @return the saved entity.
     */
    @SuppressWarnings("unchecked")
    public <E> E save(E entity) {
        return businessLookUp.getBusinessService((Class<E>) entity.getClass(), getKeyType(entity.getClass()))
                .save(entity);
    }

    /**
     * Finds an entity by its id.
     *
     * @param entityType the entity type class.
     * @param id         the id.
     * @param <E>        the entity type.
     * @param <K>        the key type.
     * @return the requested entity.
     */
    @SuppressWarnings("unchecked")
    public <E, K> Optional<E> find(Class<E> entityType, K id) {
        return businessLookUp.getBusinessService(entityType, (Class<K>) id.getClass()).find(id);
    }

    /**
     * Finds all the entities.
     *
     * @param entityType the entity type class.
     * @param <E>        the entity type.
     * @return all the entities.
     */
    public <E> Iterable<E> findAll(Class<E> entityType) {
        return businessLookUp.getBusinessService(entityType, getKeyType(entityType)).findAll();
    }

    /**
     * Deletes an entity.
     *
     * @param entity the entity.
     * @param <E>    the entity type.
     */
    @SuppressWarnings("unchecked")
    public <E> void delete(E entity) {
        businessLookUp.getBusinessService((Class<E>) entity.getClass(), getKeyType(entity.getClass())).delete(entity);
    }

    private <E> Class<?> getKeyType(Class<E> entityType) {
        return FieldUtils.getFieldsListWithAnnotation(entityType, Id.class).stream()
                .findFirst().<Class<?>>map(Field::getType).orElse(null);
    }
}
