/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.stmt;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.expr.Expr;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class Assign extends AbstractSmartMLElement implements Stmt {
    private final String name;
    private final Expr rhs;

    public Assign(String name, Expr rhs) {
        super(List.of(rhs));
        this.name = name;
        this.rhs = rhs;
    }

    public String name() { return name; }

    public Expr rhs() { return rhs; }

    @Override
    public void visit(Visitor v) { v.performActionOnAssign(this); }
}
