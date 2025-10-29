/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.naming;

import java.util.HashMap;

import org.key_project.prover.sequent.PosInOccurrence;

import de.tu_darmstadt.smartml.calculus.proof.Goal;
import de.tu_darmstadt.smartml.logic.op.ProgramVariable;
import de.tu_darmstadt.smartml.services.Services;

public class VariableNamer {
    private final Services services;

    public VariableNamer(Services services) {
        this.services = services;
    }

    public ProgramVariable rename(ProgramVariable inst, Goal goal, PosInOccurrence posOfFind) {
        throw new RuntimeException("Not implemented yet");
    }

    public HashMap<ProgramVariable, ProgramVariable> getRenamingMap() {
        throw new RuntimeException("Not implemented yet");
    }
}
