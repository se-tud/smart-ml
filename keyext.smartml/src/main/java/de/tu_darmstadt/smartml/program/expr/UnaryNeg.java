package de.tu_darmstadt.smartml.program.expr;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.List;

public final class UnaryNeg extends AbstractSmartMLElement implements Expr {
    private final Expr expr;
    public UnaryNeg(Expr expr) { super(List.of(expr)); this.expr = expr; }
    public Expr expr() { return expr; }
    @Override public void visit(Visitor v) { v.performActionOnUnaryNeg(this); }
    @Override public String toString(){ return "-(" + expr + ")"; }
}
