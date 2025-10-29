package de.tu_darmstadt.smartml.program.decl;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.stmt.Block;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.List;

public final class FunctionDecl extends AbstractSmartMLElement implements Decl {
    private final String name;
    private final Block body; // can be null if only a signature

    public FunctionDecl(String name, Block body) {
        super(body != null ? List.of(body) : List.of());
        this.name = name;
        this.body = body;
    }

    public String name() { return name; }
    public Block body() { return body; }

    @Override public void visit(Visitor v) { v.performActionOnFunctionDecl(this); }

    @Override public String toString() { return "fn " + name + (body != null ? " {…}" : " ;"); }
}
