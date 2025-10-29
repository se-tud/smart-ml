/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.services;

import org.key_project.logic.LogicServices;
import org.key_project.logic.Name;
import org.key_project.logic.Term;

import de.tu_darmstadt.smartml.calculus.naming.VariableNamer;
import de.tu_darmstadt.smartml.logic.NamespaceSet;
import de.tu_darmstadt.smartml.logic.TermBuilder;
import de.tu_darmstadt.smartml.logic.TermFactory;
import de.tu_darmstadt.smartml.logic.op.ProgramVariable;
import de.tu_darmstadt.smartml.program.SmartMLModel;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.theory.TheoryInfo;

/// Service class providing access to
/// * namespaces
/// * program model
/// * creators (see [TermFactory] and [TermBuilder])
/// * theories/LDTs
public class Services implements LogicServices {

    private final NamespaceSet namespaces;
    private final TermFactory tf;
    private final TermBuilder tb;
    private final SmartMLModel smartMLModel;
    private final VariableNamer variableNamer = new VariableNamer(this);;

    public Services() {
        namespaces = new NamespaceSet();
        tf = new TermFactory();
        tb = new TermBuilder(tf, this);
        smartMLModel = null;
    }

    /// retrieves the namespaces for logic symbols like operators, functions, program variables as
    /// well as rulesets and more
    /// @return collection of namespaces
    public NamespaceSet getNamespaces() {
        return namespaces;
    }

    /// retrieves the low level factory for constructing terms
    /// @return the [TermFactory]
    public TermFactory getTermFactory() {
        return tf;
    }

    /// retrieves a high-level builder for constructing terms in a convenient and more readable
    /// manner
    /// @return the [TermBuilder]
    public TermBuilder getTermBuilder() {
        return tb;
    }

    /// this functionality should be moved to an external class
    public static Term convertToLogicElement(SmartMLProgramElement pe, Services services) {
        var tb = services.getTermBuilder();
        if (pe instanceof ProgramVariable pv) {
            return tb.var(pv);
        }
        throw new IllegalArgumentException(
            "Unknown or not convertible ProgramElement " + pe + " of type "
                + pe.getClass());
    }

    public SmartMLModel getSmartMLInfo() {
        return smartMLModel;
    }

    public TheoryInfo getLDTs() {
        throw new RuntimeException("Not implemented yet");
    }

    public void addNameProposal(Name name) {
        throw new RuntimeException("Not implemented yet");
    }

    public VariableNamer getVariableNamer() {
        return variableNamer;
    }
}
