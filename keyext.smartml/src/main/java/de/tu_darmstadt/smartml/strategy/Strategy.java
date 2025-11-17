/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.strategy;

import org.key_project.logic.Name;
import org.key_project.logic.Named;
import org.key_project.prover.proof.ProofGoal;
import org.key_project.prover.sequent.PosInOccurrence;

import de.tu_darmstadt.smartml.calculus.proof.Goal;
import de.tu_darmstadt.smartml.calculus.rules.NoPosTacletApp;
import org.jspecify.annotations.Nullable;

public class Strategy<T> implements Named {
    public Name name() {
        throw new RuntimeException("Not implemented yet");
    }

    public T computeCost(NoPosTacletApp noPosTacletApp, @Nullable PosInOccurrence pos,
            ProofGoal<Goal> goal) {
        throw new RuntimeException("Not implemented yet");
    }
}
