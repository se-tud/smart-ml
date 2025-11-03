/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.rules;

import org.key_project.prover.rules.Taclet;
import org.key_project.prover.sequent.PosInOccurrence;

import de.tu_darmstadt.smartml.calculus.rules.matching.inst.SVInstantiations;
import de.tu_darmstadt.smartml.services.Services;

public class NoPosTacletApp extends TacletApp {
    public static NoPosTacletApp createNoPosTacletApp(Taclet taclet) {
        return null;
    }

    public static NoPosTacletApp createFixedNoPosTacletApp(Taclet rule, SVInstantiations insts,
            Services services) {
        throw new RuntimeException("Not implemented");
    }

    public NoPosTacletApp matchFind(PosInOccurrence pos, Services services) {
        return null;
    }

    public PosTacletApp setPosInOccurrence(PosInOccurrence pos, Services services) {
        throw new RuntimeException("Not implemented");
    }
}
