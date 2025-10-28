/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.abstraction;

import java.util.Objects;

import org.key_project.logic.Name;
import org.key_project.logic.sort.Sort;

import de.tu_darmstadt.smartml.services.Services;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/// Mapping between a SmartML type and the corresponding sort in the logic
public class KeYSmartMLType implements Type {
    /// the AST type
    private @Nullable Type type = null;
    /// the logic sort
    private @Nullable Sort sort = null;

    public KeYSmartMLType() {
    }

    public KeYSmartMLType(Type smartMLType, Sort sort) {
        this.type = smartMLType;
        this.sort = sort;
    }

    public KeYSmartMLType(Type smartMLType) {
        this.type = smartMLType;
    }

    public KeYSmartMLType(Sort sort) {
        this.sort = sort;
    }

    public @Nullable Sort getSort(Services services) {
        return sort;
    }

    public @Nullable Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public @Nullable Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public @NonNull Name name() {
        return type == null ? Objects.requireNonNull(sort).name() : type.name();
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        try {
            return Objects.equals(type, ((KeYSmartMLType) o).type)
                    && Objects.equals(sort, ((KeYSmartMLType) o).sort);
        } catch (Exception e) {
            return false;
        }
    }

}
