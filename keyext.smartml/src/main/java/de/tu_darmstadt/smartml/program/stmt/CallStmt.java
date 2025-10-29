package de.tu_darmstadt.smartml.program.stmt;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.expr.Expr;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.List;

// covers funCall ;
public final class CallStmt extends AbstractSmartMLElement implements Stmt {
    private final Expr call; // usually a Call/ADTFunctionCall, but Expr is fine

    public CallStmt(Expr call) {
        super(List.of(call));
        this.call = call;
    }

    public Expr call() { return call; }

    @Override public void visit(Visitor v) { v.performActionOnCallStmt(this); }
}
