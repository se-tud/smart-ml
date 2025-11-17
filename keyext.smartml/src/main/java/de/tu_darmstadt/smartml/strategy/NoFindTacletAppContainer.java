/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.strategy;

import de.tu_darmstadt.smartml.calculus.proof.Goal;
import de.tu_darmstadt.smartml.calculus.rules.NoPosTacletApp;

public class NoFindTacletAppContainer extends RuleAppContainer {
    public NoFindTacletAppContainer(NoPosTacletApp noPosTacletApp, Goal cost, long localAge) {
        super();
    }
}
