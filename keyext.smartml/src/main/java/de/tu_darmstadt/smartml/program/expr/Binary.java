/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.expr;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;


public final class Binary extends AbstractSmartMLElement implements Expr {
    private final Expr left, right;
    private final BinOp op;

    public Binary(Expr left, BinOp op, Expr right) {
        super(List.of(left, right));
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public Expr left() { return left; }

    public Expr right() { return right; }

    public BinOp op() { return op; }

    @Override
    public void visit(Visitor v) { v.performActionOnBinary(this); }
}
