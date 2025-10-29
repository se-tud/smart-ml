/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.rules.execution;

import org.key_project.prover.rules.RuleApp;
import org.key_project.prover.rules.tacletbuilder.TacletGoalTemplate;
import org.key_project.prover.sequent.PosInOccurrence;
import org.key_project.prover.sequent.Sequent;
import org.key_project.prover.sequent.SequentChangeInfo;

import de.tu_darmstadt.smartml.calculus.proof.Goal;
import de.tu_darmstadt.smartml.calculus.rules.SMLTaclet;
import de.tu_darmstadt.smartml.calculus.rules.matching.inst.MatchConditions;
import de.tu_darmstadt.smartml.calculus.rules.taclets.AntecSuccTacletGoalTemplate;
import de.tu_darmstadt.smartml.services.Services;

/// Executes a Taclet which matches on a formula in the antecedent
///
/// @author Richard Bubel
public class AntecTacletExecutor extends FindTacletExecutor {

    public AntecTacletExecutor(SMLTaclet taclet) {
        super(taclet);
    }

    @Override
    protected void applyAdd(Sequent add,
            SequentChangeInfo currentSequent,
            PosInOccurrence whereToAdd, PosInOccurrence posOfFind, MatchConditions matchCond,
            Goal goal, RuleApp ruleApp, Services services) {
        addToAntec(add.antecedent(), currentSequent, whereToAdd,
            posOfFind, matchCond, goal, ruleApp, services);
        addToSucc(add.succedent(), currentSequent, null,
            posOfFind, matchCond, goal, ruleApp, services);
    }

    @Override
    protected void applyReplacewith(TacletGoalTemplate gt, SequentChangeInfo currentSequent,
            PosInOccurrence posOfFind, MatchConditions matchCond, Goal goal, RuleApp ruleApp,
            Services services) {
        if (gt instanceof AntecSuccTacletGoalTemplate astgt) {
            final Sequent replWith = astgt.replaceWith();
            replaceAtPos(replWith.antecedent(), currentSequent, posOfFind,
                matchCond,
                goal, ruleApp, services);
            if (!replWith.succedent().isEmpty()) {
                addToSucc(replWith.succedent(),
                    currentSequent, null, posOfFind, matchCond, goal, ruleApp, services);
            }
        } else {
            // Then there was no replacewith...
        }
    }
}
