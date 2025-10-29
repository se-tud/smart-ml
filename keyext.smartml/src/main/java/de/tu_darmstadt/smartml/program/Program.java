package de.tu_darmstadt.smartml.program;

import de.tu_darmstadt.smartml.program.decl.Decl;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.List;

public final class Program extends AbstractSmartMLElement implements SmartMLProgramElement {
    private final java.util.List<Decl> decls;

    public Program(java.util.List<Decl> decls) {
        super(decls);
        this.decls = java.util.List.copyOf(decls);
    }

    public java.util.List<Decl> decls() { return decls; }

    @Override public void visit(Visitor v) { v.performActionOnProgram(this); }

    @Override public String toString() { return "Program" + decls; }
}
