/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.decl;

import java.util.ArrayList;
import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.stmt.Block;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class FunctionDecl extends AbstractSmartMLElement implements Decl {
    private final String name;
    private final List<? extends SmartMLProgramElement> params;
    private final String returnType; // null if absent
    private final Block body;

    public FunctionDecl(String name,
            List<? extends SmartMLProgramElement> params,
            String returnType,
            Block body) {
        super(concat(params, body));
        this.name = name;
        this.params = List.copyOf(params);
        this.returnType = returnType;
        this.body = body;
    }

    public String name() { return name; }

    public List<? extends SmartMLProgramElement> params() { return params; }

    public String returnType() { return returnType; }

    public Block body() { return body; }

    @Override
    public void visit(Visitor v) { v.performActionOnFunctionDecl(this); }

    @Override
    public String toString() {
        return "fn " + name + "(" + params.size() + " params)"
            + (returnType != null ? " -> " + returnType : "")
            + (body != null ? " {…}" : " ;");
    }

    private static List<? extends SmartMLProgramElement> concat(
            List<? extends SmartMLProgramElement> ps, Block b) {
        if (ps.isEmpty() && b == null)
            return List.of();
        var out = new ArrayList<SmartMLProgramElement>(ps.size() + (b != null ? 1 : 0));
        out.addAll(ps);
        if (b != null)
            out.add(b);
        return out;
    }
}
