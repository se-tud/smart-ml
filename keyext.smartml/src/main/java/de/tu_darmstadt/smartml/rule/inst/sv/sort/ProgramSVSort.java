/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.rule.inst.sv.sort;

import org.key_project.logic.Name;

import de.tu_darmstadt.smartml.logic.sort.SortImpl;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.services.Services;

public abstract class ProgramSVSort extends SortImpl {
    public ProgramSVSort(Name name) {
        super(name);
    }

    public boolean canStandFor(SmartMLProgramElement pe, Services services) {
        throw new RuntimeException("Not implemented yet");
    }
}
