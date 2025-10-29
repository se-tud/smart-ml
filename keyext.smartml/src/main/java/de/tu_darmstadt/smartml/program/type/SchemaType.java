/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.type;

import java.util.List;

public final class SchemaType implements SmartType {
    private final String name;
    private final List<SmartType> typeArgs;

    public SchemaType(String name, List<SmartType> typeArgs) {
        this.name = name;
        this.typeArgs = List.copyOf(typeArgs);
    }

    public String name() { return name; }

    public List<SmartType> typeArgs() { return typeArgs; }

    @Override
    public String display() {
        if (typeArgs.isEmpty())
            return name;
        return name + "<"
            + typeArgs.stream().map(SmartType::display).reduce((a, b) -> a + "," + b).orElse("")
            + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SchemaType s))
            return false;
        return name.equals(s.name) && typeArgs.equals(s.typeArgs);
    }

    @Override
    public int hashCode() { return 31 * name.hashCode() + typeArgs.hashCode(); }
}
