/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.decl;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.stmt.Block;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class ConstructorDecl extends AbstractSmartMLElement implements Decl {
    private final List<? extends SmartMLProgramElement> parameters;
    private final Block body;

    public ConstructorDecl(List<? extends SmartMLProgramElement> parameters, Block body) {
        super(concat(parameters, body));
        this.parameters = List.copyOf(parameters);
        this.body = body;
    }

    public List<? extends SmartMLProgramElement> parameters() { return parameters; }
    public Block body() { return body; }

    public void visit(Visitor v) { v.performActionOnConstructorDecl(this); }

    private static List<? extends SmartMLProgramElement> concat(
            List<? extends SmartMLProgramElement> params, Block body) {
        var out = new java.util.ArrayList<SmartMLProgramElement>(params.size() + 1);
        out.addAll(params);
        if (body != null) out.add(body);
        return out;
    }
}
