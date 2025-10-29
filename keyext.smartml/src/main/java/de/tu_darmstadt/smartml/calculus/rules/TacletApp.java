/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.rules;

import org.key_project.logic.Namespace;
import org.key_project.logic.op.Function;
import org.key_project.prover.rules.Rule;
import org.key_project.prover.rules.RuleApp;
import org.key_project.prover.rules.instantiation.AssumesFormulaInstantiation;
import org.key_project.prover.sequent.PosInOccurrence;
import org.key_project.util.collection.ImmutableList;

import de.tu_darmstadt.smartml.calculus.rules.matching.inst.MatchConditions;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class TacletApp implements RuleApp {
    @Override
    public Rule rule() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public void checkApplicability() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public void registerSkolemConstants(Namespace<@NonNull Function> fns) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public boolean complete() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    @Nullable
    public PosInOccurrence posInOccurrence() {
        throw new RuntimeException("Not implemented yet");
    }

    public MatchConditions matchConditions() {
        throw new RuntimeException("Not implemented yet");
    }

    public ImmutableList<? extends AssumesFormulaInstantiation> assumesFormulaInstantiations() {
        throw new RuntimeException("Not implemented yet");
    }
}
