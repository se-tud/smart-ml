/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.decl;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class ContractDecl extends AbstractSmartMLElement implements Decl {
    private final String name;
    private final List<? extends SmartMLProgramElement> state;
    private final List<? extends SmartMLProgramElement> members;
    private final List<? extends SmartMLProgramElement> invariants;

    public ContractDecl(String name,
                        List<? extends SmartMLProgramElement> state,
                        List<? extends SmartMLProgramElement> members,
                        List<? extends SmartMLProgramElement> invariants) {
        super(concat(state, members, invariants));   // <<< important
        this.name = name;
        this.state = List.copyOf(state);
        this.members = List.copyOf(members);
        this.invariants = List.copyOf(invariants);
    }

    public String name() { return name; }

    private static List<? extends SmartMLProgramElement> concat(
            List<? extends SmartMLProgramElement> a,
            List<? extends SmartMLProgramElement> b,
            List<? extends SmartMLProgramElement> c) {
        var out = new java.util.ArrayList<SmartMLProgramElement>(a.size()+b.size()+c.size());
        out.addAll(a); out.addAll(b); out.addAll(c);
        return out;
    }

    @Override
    public void visit(Visitor v) { v.performActionOnContractDecl(this);    }

    public List<? extends SmartMLProgramElement> state() { return state; }
    public List<? extends SmartMLProgramElement> members() { return members; }
    public List<? extends SmartMLProgramElement> invariants() { return invariants; }
}

