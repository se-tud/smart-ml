/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.strategy;

import org.key_project.prover.sequent.PosInOccurrence;

import de.tu_darmstadt.smartml.calculus.proof.Goal;
import de.tu_darmstadt.smartml.calculus.rules.NoPosTacletApp;
import org.jspecify.annotations.Nullable;

public class FindTacletAppContainer extends RuleAppContainer {
    public FindTacletAppContainer(NoPosTacletApp noPosTacletApp, @Nullable PosInOccurrence pos,
            Goal cost, Goal goal, long localAge) {
        super();
    }
}
