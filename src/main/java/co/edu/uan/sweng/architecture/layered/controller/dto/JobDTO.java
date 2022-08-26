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

package co.edu.uan.sweng.architecture.layered.controller.dto;

import co.edu.uan.sweng.architecture.layered.entities.Job;

/**
 * DTO for {@link Job}
 *
 * @param id        the id.
 * @param name      the name.
 * @param usdSalary the USD salary.
 * @author Jorge Garcia
 * @author Diego Poveda
 * @version 1.0.0
 * @since 17
 */
public record JobDTO(Long id, String name, Double usdSalary) {
}
