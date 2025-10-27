package de.tu_darmstadt.smartml.program.stmt;

import java.util.List;
import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class Block extends AbstractSmartMLElement implements Stmt {
    public Block(List<? extends Stmt> statements) { super(statements); }
    @SuppressWarnings("unchecked")
    public List<Stmt> statements() { return (List<Stmt>)(List<?>)children(); }
    @Override public void visit(Visitor v) { v.performActionOnBlock(this); }
}
