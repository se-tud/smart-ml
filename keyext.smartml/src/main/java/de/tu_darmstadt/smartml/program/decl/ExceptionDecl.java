package de.tu_darmstadt.smartml.program.decl;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.List;

public final class ExceptionDecl extends AbstractSmartMLElement implements Decl {
    private final String name;

    public ExceptionDecl(String name) {
        super(List.of());
        this.name = name;
    }

    public String name() { return name; }

    @Override public void visit(Visitor v) { v.performActionOnExceptionDecl(this); }
}
