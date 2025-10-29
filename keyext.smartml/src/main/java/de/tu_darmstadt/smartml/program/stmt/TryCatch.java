/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.stmt;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.VarTarget;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class TryCatch extends AbstractSmartMLElement implements Stmt {
    private final Stmt tryStmt;
    private final VarTarget catchVar; // vardec
    private final Block catchBlock;

    public TryCatch(Stmt tryStmt, VarTarget catchVar, Block catchBlock) {
        super(List.of(tryStmt, catchVar, catchBlock));
        this.tryStmt = tryStmt;
        this.catchVar = catchVar;
        this.catchBlock = catchBlock;
    }

    public Stmt tryStmt() { return tryStmt; }

    public VarTarget catchVar() { return catchVar; }

    public Block catchBlock() { return catchBlock; }

    @Override
    public void visit(Visitor v) { v.performActionOnTryCatch(this); }
}
