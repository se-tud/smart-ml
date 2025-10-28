/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.stmt;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.expr.Expr;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class If extends AbstractSmartMLElement implements Stmt {
    private final Expr cond;
    private final Stmt thenBranch;
    private final Stmt elseBranch; // may be EmptyStatement

    public If(Expr cond, Stmt thenBranch, Stmt elseBranch) {
        super(List.of(cond, thenBranch, elseBranch));
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public Expr cond() { return cond; }

    public Stmt thenBranch() { return thenBranch; }

    public Stmt elseBranch() { return elseBranch; }

    @Override
    public void visit(Visitor v) { v.performActionOnIf(this); }
}
