/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.proof;

import org.key_project.prover.proof.ProofGoal;
import org.key_project.prover.rules.RuleApp;
import org.key_project.prover.rules.Taclet;
import org.key_project.prover.sequent.Sequent;
import org.key_project.prover.sequent.SequentChangeInfo;
import org.key_project.prover.strategy.RuleApplicationManager;
import org.key_project.util.collection.ImmutableList;

import de.tu_darmstadt.smartml.calculus.rules.matching.inst.SVInstantiations;
import de.tu_darmstadt.smartml.logic.NamespaceSet;
import de.tu_darmstadt.smartml.logic.op.ProgramVariable;
import de.tu_darmstadt.smartml.services.Services;
import de.tu_darmstadt.smartml.strategy.Strategy;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class Goal implements ProofGoal<Goal> {
    public Goal(Node rootNode, TacletIndex tacletIndex, BuiltInRuleAppIndex builtInRuleAppIndex,
            Services services) {
    }

    @Override
    public Proof proof() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public Sequent sequent() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    @Nullable
    public ImmutableList<Goal> apply(RuleApp ruleApp) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public RuleApplicationManager<Goal> getRuleAppManager() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public long getTime() {
        throw new RuntimeException("Not implemented yet");
    }

    public Services getOverlayServices() {
        throw new RuntimeException("Not implemented yet");
    }

    public Node getNode() {
        throw new RuntimeException("Not implemented yet");
    }

    public void addTaclet(Taclet rule, SVInstantiations instantiations, boolean isAxiom) {
        throw new RuntimeException("Not implemented yet");
    }

    public NamespaceSet getLocalNamespaces() {
        throw new RuntimeException("Not implemented yet");
    }

    public void addProgramVariable(ProgramVariable renamedInst) {
        throw new RuntimeException("Not implemented yet");
    }

    public RuleAppIndex ruleAppIndex() {
        throw new RuntimeException("Not implemented yet");
    }

    public void setSequent(SequentChangeInfo newSequent) {
        throw new RuntimeException("Not implemented yet");
    }

    public void setBranchLabel(@Nullable String name) {
        throw new RuntimeException("Not implemented yet");
    }

    public ImmutableList<Goal> split(int nrGoals) {
        throw new RuntimeException("Not implemented yet");
    }

    public void makeLocalNamespacesFrom(NamespaceSet ns) {
        throw new RuntimeException("Not implemented yet");
    }

    public void setGoalStrategy(Strategy<@NonNull Goal> ourStrategy) {
        throw new RuntimeException("Not implemented yet");
    }

    public boolean isAutomatic() {
        throw new RuntimeException("Not implemented yet");
    }

    public void removeGoalListener(GoalListener listener) {
        throw new RuntimeException("Not implemented yet");
    }
}
