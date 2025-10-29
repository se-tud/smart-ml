package de.tu_darmstadt.smartml.program.expr;


import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.List;

public final class StringLit extends AbstractSmartMLElement implements Expr {
    private final String value;
    public StringLit(String value){ super(List.of()); this.value = value; }
    public String value(){ return value; }
    @Override public void visit(Visitor v){ v.performActionOnStringLit(this); }
}

