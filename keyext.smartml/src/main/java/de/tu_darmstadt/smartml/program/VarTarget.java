package de.tu_darmstadt.smartml.program;

import de.tu_darmstadt.smartml.program.expr.LValue;
import de.tu_darmstadt.smartml.program.type.SmartType;
import de.tu_darmstadt.smartml.program.visitor.Visitor;
import org.jspecify.annotations.Nullable;

import java.util.List;

public final class VarTarget extends AbstractSmartMLElement {
    private final @Nullable SmartType type;
    private final boolean storage;
    private final LValue target;

    public VarTarget(@Nullable SmartType type, boolean storage, LValue target) {
        super(List.of(target));
        this.type = type;
        this.storage = storage;
        this.target = target;
    }
    public @Nullable SmartType type() { return type; }
    public boolean storage() { return storage; }
    public LValue target() { return target; }

    @Override public void visit(Visitor v) { v.performActionOnVarTarget(this); }

}
