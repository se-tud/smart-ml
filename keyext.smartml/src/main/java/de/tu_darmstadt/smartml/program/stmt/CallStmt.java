/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.stmt;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.expr.Expr;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

// covers funCall ;
public final class CallStmt extends AbstractSmartMLElement implements Stmt {
    private final Expr call; // usually a Call/ADTFunctionCall, but Expr is fine

    public CallStmt(Expr call) {
        super(List.of(call));
        this.call = call;
    }

    public Expr call() { return call; }

    @Override
    public void visit(Visitor v) { v.performActionOnCallStmt(this); }
}
