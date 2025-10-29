package de.tu_darmstadt.smartml.program.decl;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.List;

public final class ContractDecl extends AbstractSmartMLElement implements Decl {
    private final String name;
    private final List<? extends SmartMLProgramElement> stateMembers;
    private final List<? extends SmartMLProgramElement> members;
    private final List<? extends SmartMLProgramElement> invariants;

    public ContractDecl(String name,
                        List<? extends SmartMLProgramElement> stateMembers,
                        List<? extends SmartMLProgramElement> members,
                        List<? extends SmartMLProgramElement> invariants) {
        super(concat(stateMembers, concat(members, invariants)));
        this.name = name;
        this.stateMembers = List.copyOf(stateMembers);
        this.members = List.copyOf(members);
        this.invariants = List.copyOf(invariants);
    }

    public String name() { return name; }

    @Override public void visit(Visitor v) { v.performActionOnContractDecl(this); }

    private static List<? extends SmartMLProgramElement> concat(
            List<? extends SmartMLProgramElement> a,
            List<? extends SmartMLProgramElement> b) {
        var out = new java.util.ArrayList<SmartMLProgramElement>(a.size()+b.size());
        out.addAll(a); out.addAll(b); return out;
    }
}
