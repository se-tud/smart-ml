/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.stmt;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.VarTarget;
import de.tu_darmstadt.smartml.program.expr.Expr;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class Assign extends AbstractSmartMLElement implements Stmt {
    private final VarTarget lhs;
    private final Expr rhs;

    public Assign(VarTarget lhs, Expr rhs) {
        super(List.of(lhs, rhs));
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public VarTarget lhs() { return lhs; }

    public Expr rhs() { return rhs; }

    @Override
    public void visit(Visitor v) { v.performActionOnAssign(this); }
}
