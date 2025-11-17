/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.rules;

import org.key_project.logic.Term;
import org.key_project.logic.op.QuantifiableVariable;
import org.key_project.logic.op.sv.SchemaVariable;
import org.key_project.prover.rules.Taclet;
import org.key_project.prover.rules.instantiation.AssumesFormulaInstantiation;
import org.key_project.prover.rules.instantiation.MatchResultInfo;
import org.key_project.prover.sequent.PosInOccurrence;
import org.key_project.util.collection.ImmutableList;
import org.key_project.util.collection.ImmutableSet;

import de.tu_darmstadt.smartml.calculus.rules.matching.inst.SVInstantiations;
import de.tu_darmstadt.smartml.services.Services;
import org.jspecify.annotations.Nullable;

public class PosTacletApp extends TacletApp {

    PosTacletApp(Taclet taclet) {
        super(taclet);
    }

    @Override
    public TacletApp setMatchConditions(MatchResultInfo mc, Services services) {
        return null;
    }

    @Override
    protected TacletApp setAllInstantiations(MatchResultInfo mc,
            ImmutableList<AssumesFormulaInstantiation> ifInstantiations, Services services) {
        return null;
    }

    @Override
    public TacletApp addInstantiation(SchemaVariable sv, Term term, boolean interesting,
            Services services) {
        return null;
    }

    @Override
    protected ImmutableSet<QuantifiableVariable> contextVars(SchemaVariable sv) {
        return null;
    }

    @Override
    public TacletApp addInstantiation(SVInstantiations svi, Services services) {
        return null;
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

    @Override
    boolean complete() {
        return false;
    }

    @Override
    @Nullable
    PosInOccurrence posInOccurrence() {
        return null;
    }
}
