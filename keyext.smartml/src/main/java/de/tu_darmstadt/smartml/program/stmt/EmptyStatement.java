package de.tu_darmstadt.smartml.program.stmt;

import java.util.List;
import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class EmptyStatement extends AbstractSmartMLElement implements Stmt {
    public EmptyStatement() { super(List.of()); }
    @Override public void visit(Visitor v) { v.performActionOnEmptyStatement(this); }
}
