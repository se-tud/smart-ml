/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.rules.execution;

import java.util.Map;

import org.key_project.prover.sequent.Sequent;
import org.key_project.prover.sequent.SequentChangeInfo;

import de.tu_darmstadt.smartml.calculus.proof.TacletIndex;
import de.tu_darmstadt.smartml.logic.op.ProgramVariable;
import de.tu_darmstadt.smartml.services.Services;

public class ProgVarReplacer {
    private final Map<ProgramVariable, ProgramVariable> renamingMap;
    private final Services services;

    public ProgVarReplacer(Map<ProgramVariable, ProgramVariable> renamingMap, Services services) {
        this.renamingMap = renamingMap;
        this.services = services;
    }

    public SequentChangeInfo replace(TacletIndex tacletIndex) {
        throw new RuntimeException("Not implemented yet");
    }

    public SequentChangeInfo replace(Sequent sequent) {
        throw new RuntimeException("Not implemented yet");
    }
}
