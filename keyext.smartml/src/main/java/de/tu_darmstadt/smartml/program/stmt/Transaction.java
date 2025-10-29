package de.tu_darmstadt.smartml.program.stmt;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class Transaction extends AbstractSmartMLElement implements Stmt {
    private final Stmt tryStmt;
    private final @Nullable Block abortBlock;
    private final @Nullable Block successBlock;

    public Transaction(Stmt tryStmt, @Nullable Block abortBlock, @Nullable Block successBlock) {
        super(children(tryStmt, abortBlock, successBlock));
        this.tryStmt = tryStmt;
        this.abortBlock = abortBlock;
        this.successBlock = successBlock;
    }

    private static List<? extends SmartMLProgramElement> children(
            Stmt tryStmt, Block abortBlock, Block successBlock) {
        List<SmartMLProgramElement> ch = new ArrayList<>();
        ch.add(tryStmt);
        if (abortBlock != null) ch.add(abortBlock);
        if (successBlock != null) ch.add(successBlock);
        return ch;
    }

    public Stmt tryStmt() { return tryStmt; }
    public @Nullable Block abortBlock() { return abortBlock; }
    public @Nullable Block successBlock() { return successBlock; }

    @Override public void visit(Visitor v) { v.performActionOnTransaction(this); }
}
