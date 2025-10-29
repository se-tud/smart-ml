package de.tu_darmstadt.smartml.program.expr;

import de.tu_darmstadt.smartml.program.visitor.Visitor;
import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;

import java.util.List;

public final class Var extends AbstractSmartMLElement implements Expr, LValue {
    private final String name;

    public Var(String name) {
        super(List.of());
        this.name = name;
    }

    public String name() { return name; }

    @Override public void visit(Visitor v) { v.performActionOnVar(this); }
}
