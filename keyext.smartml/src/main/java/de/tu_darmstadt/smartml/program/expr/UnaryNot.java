/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.expr;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class UnaryNot extends AbstractSmartMLElement implements Expr {
    private final Expr expr;

    public UnaryNot(Expr expr) { super(List.of(expr)); this.expr = expr; }

    public Expr expr() { return expr; }

    @Override
    public void visit(Visitor v) { v.performActionOnUnaryNot(this); }
}
