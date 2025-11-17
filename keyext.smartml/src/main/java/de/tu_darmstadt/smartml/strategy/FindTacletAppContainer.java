/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.strategy;

import org.key_project.prover.sequent.PosInOccurrence;
import org.key_project.prover.strategy.costbased.RuleAppCost;

import de.tu_darmstadt.smartml.calculus.proof.Goal;
import de.tu_darmstadt.smartml.calculus.rules.NoPosTacletApp;
import org.jspecify.annotations.Nullable;

public class FindTacletAppContainer extends TacletAppContainer {
    public FindTacletAppContainer(NoPosTacletApp app, @Nullable PosInOccurrence pos,
            RuleAppCost cost, Goal goal, long localAge) {
        super(app, cost, goal.getTime());
    }

    @Override
    protected boolean isStillApplicable(Goal p_goal) {
        throw new RuntimeException("Not implemented yet");
    }
}
