/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program;

import java.util.HashMap;
import java.util.Map;

import org.key_project.logic.Name;

import de.tu_darmstadt.smartml.program.abstraction.KeYSmartMLType;
import de.tu_darmstadt.smartml.program.abstraction.Type;

public class SmartMLModel {

    private final Map<Name, Type> typeMap = new HashMap<>();

    public Type getType(Name typeName) {
        return typeMap.get(typeName);
    }

    public KeYSmartMLType getKeYSmartMLType(String type) {
        throw new RuntimeException("Not implemented yet");
    }
}
