/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.rules;

import org.key_project.logic.ChoiceExpr;
import org.key_project.logic.Name;
import org.key_project.logic.SyntaxElement;
import org.key_project.logic.op.QuantifiableVariable;
import org.key_project.logic.op.sv.SchemaVariable;
import org.key_project.prover.rules.RuleSet;
import org.key_project.prover.rules.Taclet;
import org.key_project.prover.rules.TacletAnnotation;
import org.key_project.prover.rules.TacletApplPart;
import org.key_project.prover.rules.TacletAttributes;
import org.key_project.prover.rules.TacletPrefix;
import org.key_project.prover.rules.tacletbuilder.TacletGoalTemplate;
import org.key_project.util.collection.DefaultImmutableSet;
import org.key_project.util.collection.ImmutableList;
import org.key_project.util.collection.ImmutableMap;
import org.key_project.util.collection.ImmutableSet;

import de.tu_darmstadt.smartml.calculus.rules.matching.VMTacletMatcher;
import de.tu_darmstadt.smartml.calculus.rules.taclets.TacletSchemaVariableCollector;
import de.tu_darmstadt.smartml.logic.visitor.BoundVarsVisitor;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.jspecify.annotations.NonNull;

public abstract class SMLTaclet extends Taclet {


    /// creates a Taclet (originally known as Schematic Theory Specific Rules)
    ///
    /// @param name the name of the Taclet
    /// @param find the Term or Sequent that is the pattern that has to be found in a sequent and
    /// the places where it matches the Taclet can be applied
    /// @param applPart contains the application part of a Taclet that is the if-sequence, the
    /// variable conditions
    /// @param goalTemplates a list of goal descriptions.
    /// @param attrs attributes for the Taclet; these are boolean values indicating a noninteractive
    /// or recursive use of the Taclet.
    @EnsuresNonNull({ "matcher", "executor" })
    protected SMLTaclet(Name name, SyntaxElement find, TacletApplPart applPart,
            ImmutableList<TacletGoalTemplate> goalTemplates,
            ImmutableList<RuleSet> ruleSets,
            TacletAttributes attrs,
            ImmutableMap<@NonNull SchemaVariable, TacletPrefix> prefixMap, ChoiceExpr choices,
            ImmutableSet<TacletAnnotation> tacletAnnotations) {
        super(name, find, applPart, goalTemplates, ruleSets, attrs, prefixMap, choices,
            tacletAnnotations);
    }

    @EnsuresNonNull("matcher")
    @Override
    protected void createAndInitializeMatcher() {
        this.matcher = new VMTacletMatcher(this);
    }

    @EnsuresNonNull("executor")
    @Override
    protected abstract void createAndInitializeExecutor();

    @Override
    public ImmutableSet<QuantifiableVariable> getBoundVariables() {
        if (boundVariables == null) {
            ImmutableSet<QuantifiableVariable> result =
                DefaultImmutableSet.nil();

            for (final TacletGoalTemplate tgt : goalTemplates()) {
                result = result.union(tgt.getBoundVariables());
            }

            final BoundVarsVisitor bvv = new BoundVarsVisitor();
            bvv.visit(assumesSequent());
            result = result.union(bvv.getBoundVariables()).union(getBoundVariablesHelper());

            boundVariables = result;
        }

        return boundVariables;
    }


    @Override
    /// collects bound variables in taclet entities others than goal templates
    ///
    /// @return set of variables that occur bound in taclet entities others than goal templates
    protected abstract ImmutableSet<QuantifiableVariable> getBoundVariablesHelper();

    /// returns the set of schemavariables of the taclet's if-part
    ///
    /// @return Set of schemavariables of the if part
    protected ImmutableSet<SchemaVariable> getAssumesVariables() {
        // should be synchronized
        if (assumesVariables == null) {
            TacletSchemaVariableCollector svc = new TacletSchemaVariableCollector();
            svc.visitAssumes(assumesSequent());
            assumesVariables =
                DefaultImmutableSet.fromCollection(svc.getCollectedSchemaVariables());
        }

        return assumesVariables;
    }

    @Override
    public ImmutableSet<SchemaVariable> getAssumesAndFindVariables() {
        return null;
    }

    @Override
    public Taclet setName(String name) {
        return null;
    }
}
