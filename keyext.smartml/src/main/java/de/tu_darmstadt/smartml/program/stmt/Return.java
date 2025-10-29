package de.tu_darmstadt.smartml.program.stmt;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.expr.Expr;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.List;

public final class Return extends AbstractSmartMLElement implements Stmt {
    private final Expr value;

    public Return(Expr value) {
        super(List.of(value));
        this.value = value;
    }

    public Expr value() { return value; }

    @Override public void visit(Visitor v) { v.performActionOnReturn(this); }
}
