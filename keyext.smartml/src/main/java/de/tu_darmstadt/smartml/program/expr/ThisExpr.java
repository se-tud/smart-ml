package de.tu_darmstadt.smartml.program.expr;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.List;

public final class ThisExpr extends AbstractSmartMLElement implements Expr, LValue {
    public ThisExpr(){ super(List.of()); }
    @Override public void visit(Visitor v){ v.performActionOnThisExpr(this); }
}

