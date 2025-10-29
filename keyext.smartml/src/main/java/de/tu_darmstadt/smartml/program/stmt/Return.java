/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.stmt;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.expr.Expr;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class Return extends AbstractSmartMLElement implements Stmt {
    private final Expr value;

    public Return(Expr value) {
        super(List.of(value));
        this.value = value;
    }

    public Expr value() { return value; }

    @Override
    public void visit(Visitor v) { v.performActionOnReturn(this); }
}
