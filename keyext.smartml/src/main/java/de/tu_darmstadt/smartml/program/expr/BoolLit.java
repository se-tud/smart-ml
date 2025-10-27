package de.tu_darmstadt.smartml.program.expr;

import java.util.List;
import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class BoolLit extends AbstractSmartMLElement implements Expr {
    private final boolean value;
    public BoolLit(boolean value) { super(List.of()); this.value = value; }
    public boolean value() { return value; }
    @Override public void visit(Visitor v) { v.performActionOnBoolLit(this); }
}
