/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.rules;

import org.key_project.prover.rules.Taclet;
import org.key_project.prover.rules.instantiation.AssumesFormulaInstantiation;
import org.key_project.prover.sequent.PosInOccurrence;
import org.key_project.util.collection.ImmutableList;

import de.tu_darmstadt.smartml.calculus.rules.matching.inst.SVInstantiations;
import de.tu_darmstadt.smartml.services.Services;

public class PosTacletApp extends TacletApp {

    PosTacletApp(Taclet taclet) {
        super(taclet);
    }

    PosTacletApp(Taclet taclet, SVInstantiations instantiations,
            ImmutableList<AssumesFormulaInstantiation> ifInstantiations) {
        super(taclet, instantiations, ifInstantiations);
    }

    public static PosTacletApp createPosTacletApp(SMLTaclet taclet, SVInstantiations instantiations,
            ImmutableList<AssumesFormulaInstantiation> assumesFormulaInstantiations,
            PosInOccurrence pos, Services services) {
        throw new RuntimeException("Not implemented");
    }
}
