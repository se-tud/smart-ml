/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.rules.taclets.builder;


import org.key_project.prover.rules.ApplicationRestriction;
import org.key_project.prover.rules.TacletApplPart;
import org.key_project.prover.rules.tacletbuilder.TacletGoalTemplate;

import de.tu_darmstadt.smartml.calculus.rules.taclets.SMLNoFindTaclet;

public class NoFindTacletBuilder extends TacletBuilder<SMLNoFindTaclet> {

    /// builds and returns the RewriteTaclet that is specified by former set... / add... methods. If
    /// no name is specified then an Taclet with an empty string name is build. No specifications
    /// for
    /// variable conditions, goals or heuristics imply that the corresponding parts of the Taclet
    /// are
    /// empty. No specification for the if-sequent is represented as a sequent with two empty
    /// semisequences. No specification for the interactive or recursive flags imply that the flags
    /// are not set.
    public SMLNoFindTaclet getNoFindTaclet() {
        TacletPrefixBuilder prefixBuilder = new TacletPrefixBuilder(this);
        prefixBuilder.build();
        SMLNoFindTaclet t = new SMLNoFindTaclet(this.name,
            new TacletApplPart(ifseq,
                new ApplicationRestriction(ApplicationRestriction.IN_SEQUENT_STATE),
                varsNew, varsNotFreeIn, varsNewDependingOn,
                variableConditions),
            goals, ruleSets, attrs, prefixBuilder.getPrefixMap(), choices, false, tacletAnnotations,
            noFreeVarIns);
        // t.setOrigin(origin);
        return t;
    }


    /// adds a new goal descriptions to the goal descriptions of the Taclet.
    ///
    /// @param goal the TacletGoalTemplate specifying all the changes to be made to achieve one of
    /// the resulting goals
    @Override
    public void addTacletGoalTemplate(TacletGoalTemplate goal) {
        goals = goals.prepend(goal);
    }



    /// checks that a variableSV occurrs at most once in a quantifier of the ifs and finds and
    /// throws
    /// an exception otherwise
    protected void checkBoundInIfAndFind() {
        final BoundUniquenessChecker ch = new BoundUniquenessChecker(ifSequent());
        if (!ch.correct()) {
            throw new TacletBuilder.TacletBuilderException(this,
                "A bound SchemaVariable occurs twice in if.");
        }
    }


    /// builds and returns the Taclet that is specified by former set... / add... methods. If no
    /// name
    /// is specified then an Taclet with an empty string name is build. No specifications for
    /// variable conditions, goals or heuristics imply that the corresponding parts of the Taclet
    /// are
    /// empty. No specification for the if-sequent is represented as a sequent with two empty
    /// semisequences. No specification for the interactive or recursive flags imply that the flags
    /// are not set. May throw an TacletBuilderException if a bound SchemaVariable occurs more than
    /// once in if and find.
    public SMLNoFindTaclet getTaclet() {
        checkBoundInIfAndFind();
        return getNoFindTaclet();
    }
}
