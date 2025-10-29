package de.tu_darmstadt.smartml.program.visitor;

import de.tu_darmstadt.smartml.program.SmartMLProgramElement;

public final class ProgramSVCollector extends SmartMLASTWalker {
    private final java.util.LinkedHashSet<org.key_project.logic.op.sv.SchemaVariable> s = new java.util.LinkedHashSet<>();
    public ProgramSVCollector(SmartMLProgramElement root) { super(root); }
    @Override protected void doAction(SmartMLProgramElement n) {
        if (n instanceof org.key_project.logic.op.sv.SchemaVariable sv) s.add(sv);
    }
    public java.util.Set<org.key_project.logic.op.sv.SchemaVariable> result() { return java.util.Collections.unmodifiableSet(s); }
}
