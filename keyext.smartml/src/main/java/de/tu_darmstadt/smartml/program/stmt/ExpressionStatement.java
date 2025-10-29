package de.tu_darmstadt.smartml.program.stmt;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.expr.Expr;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.List;

public final class ExpressionStatement extends AbstractSmartMLElement implements Stmt {
    private final Expr expr;

    public ExpressionStatement(Expr expr) {
        super(List.of(expr));
        this.expr = expr;
    }

    public Expr expr() { return expr; }

    @Override
    public void visit(Visitor v) { v.performActionOnExpressionStatement(this); }

    @Override
    public String toString() { return "ExpressionStatement(" + expr + ")"; }
}
