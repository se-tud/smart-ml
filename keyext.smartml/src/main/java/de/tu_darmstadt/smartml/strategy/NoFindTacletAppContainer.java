/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.strategy;

import org.key_project.prover.strategy.costbased.RuleAppCost;

import de.tu_darmstadt.smartml.calculus.proof.Goal;
import de.tu_darmstadt.smartml.calculus.rules.NoPosTacletApp;

public class NoFindTacletAppContainer extends TacletAppContainer {
    public NoFindTacletAppContainer(NoPosTacletApp app, RuleAppCost cost, long localAge) {
        super(app, cost, 0);
    }

    @Override
    protected boolean isStillApplicable(Goal p_goal) {
        throw new RuntimeException("Not implemented yet");
    }
}
