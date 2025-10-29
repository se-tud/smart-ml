package de.tu_darmstadt.smartml.program.stmt;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.expr.Expr;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.List;

public final class AssertError extends AbstractSmartMLElement implements Stmt {
    private final Expr condition;

    public AssertError(Expr condition) {
        super(List.of(condition));
        this.condition = condition;
    }

    public Expr condition() { return condition; }

    @Override public void visit(Visitor v) { v.performActionOnAssertError(this); }
}
