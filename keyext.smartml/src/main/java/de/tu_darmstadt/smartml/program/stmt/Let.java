package de.tu_darmstadt.smartml.program.stmt;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.VarTarget;
import de.tu_darmstadt.smartml.program.expr.Expr;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public final class Let extends AbstractSmartMLElement implements Stmt {
    private final List<VarTarget> bindings; // lhs
    private final List<Expr> inits;         // rhs
    private final Stmt body;                // statement after IN

    public Let(List<VarTarget> bindings, List<Expr> inits, Stmt body) {
        super(children(bindings, inits, body));
        this.bindings = List.copyOf(bindings);
        this.inits = List.copyOf(inits);
        this.body = body;
    }

    private static List<? extends SmartMLProgramElement> children(
            List<VarTarget> bindings, List<Expr> inits, Stmt body) {
        List<SmartMLProgramElement> ch = new ArrayList<>();
        ch.addAll(bindings);
        ch.addAll(inits);
        ch.add(body);
        return ch;
    }

    public List<VarTarget> bindings() { return bindings; }
    public List<Expr> inits() { return inits; }
    public Stmt body() { return body; }

    @Override public void visit(Visitor v) { v.performActionOnLet(this); }
}
