/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.decl;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class DatatypeDecl extends AbstractSmartMLElement implements Decl {
    private final String name;
    private final List<? extends SmartMLProgramElement> ctors;
    private final List<? extends SmartMLProgramElement> functions;

    public DatatypeDecl(String name, List<? extends SmartMLProgramElement> ctors,
            List<? extends SmartMLProgramElement> functions) {
        super(concat(ctors, functions));
        this.name = name;
        this.ctors = List.copyOf(ctors);
        this.functions = List.copyOf(functions);
    }

    public String name() { return name; }

    @Override
    public void visit(Visitor v) { v.performActionOnDatatypeDecl(this); }

    private static List<? extends SmartMLProgramElement> concat(
            List<? extends SmartMLProgramElement> a,
            List<? extends SmartMLProgramElement> b) {
        var out = new java.util.ArrayList<SmartMLProgramElement>(a.size() + b.size());
        out.addAll(a);
        out.addAll(b);
        return out;
    }
}
