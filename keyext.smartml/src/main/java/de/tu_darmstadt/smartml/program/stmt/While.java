package de.tu_darmstadt.smartml.program.stmt;

import java.util.List;
import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;
import de.tu_darmstadt.smartml.program.expr.Expr;

public final class While extends AbstractSmartMLElement implements Stmt {
    private final Expr cond;
    private final Stmt body;

    public While(Expr cond, Stmt body) {
        super(List.of(cond, body));
        this.cond = cond; this.body = body;
    }
    public Expr cond() { return cond; }
    public Stmt body() { return body; }

    @Override public void visit(Visitor v) { v.performActionOnWhile(this); }
}
