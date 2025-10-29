package de.tu_darmstadt.smartml.program.decl;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.List;

public final class InterfaceDecl extends AbstractSmartMLElement implements Decl {
    private final String name;
    private final List<? extends SmartMLProgramElement> methods;

    public InterfaceDecl(String name, List<? extends SmartMLProgramElement> methods) {
        super(methods);
        this.name = name;
        this.methods = List.copyOf(methods);
    }

    public String name() { return name; }

    @Override public void visit(Visitor v) { v.performActionOnInterfaceDecl(this); }
}
