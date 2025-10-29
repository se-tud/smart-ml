/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.stmt;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.expr.Expr;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class AssertError extends AbstractSmartMLElement implements Stmt {
    private final Expr condition;

    public AssertError(Expr condition) {
        super(List.of(condition));
        this.condition = condition;
    }

    public Expr condition() { return condition; }

    @Override
    public void visit(Visitor v) { v.performActionOnAssertError(this); }
}
