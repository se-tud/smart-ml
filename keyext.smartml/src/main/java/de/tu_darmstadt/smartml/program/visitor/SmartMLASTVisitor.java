/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.visitor;

import de.tu_darmstadt.smartml.logic.op.ProgramVariable;
import de.tu_darmstadt.smartml.program.Program;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.expr.*;
import de.tu_darmstadt.smartml.program.stmt.*;
import de.tu_darmstadt.smartml.services.Services;


public abstract class SmartMLASTVisitor extends SmartMLASTWalker implements Visitor {
    protected final Services services;

    protected SmartMLASTVisitor(SmartMLProgramElement root, Services services) {
        super(root);
        this.services = services;
    }

    /** Hook point: subclasses may add extra behavior after super.walk(node). */
    @Override
    protected void walk(SmartMLProgramElement node) {
        super.walk(node);
        // If SmartML eventually has loop specs or similar, inspect services here.
        // (Rusty checks loop invariants here.)
    }

    /** Dispatch into node-specific visitor method. */
    @Override
    protected void doAction(SmartMLProgramElement node) {
        node.visit(this);
    }

    /** Default action used by all performActionOnX methods. */
    protected abstract void doDefaultAction(SmartMLProgramElement node);

    /* ====== Implement Visitor with defaults that call doDefaultAction ====== */

    // top-level
    @Override
    public void performActionOnProgram(Program x) { doDefaultAction(x); }

    @Override
    public void performActionOnBlock(Block x) { doDefaultAction(x); }

    // statements
    @Override
    public void performActionOnAssign(Assign x) { doDefaultAction(x); }

    @Override
    public void performActionOnIf(If x) { doDefaultAction(x); }

    @Override
    public void performActionOnWhile(While x) { doDefaultAction(x); }

    @Override
    public void performActionOnEmptyStatement(EmptyStatement x) { doDefaultAction(x); }

    // expressions
    @Override
    public void performActionOnVar(Var x) { doDefaultAction(x); }

    @Override
    public void performActionOnIntLit(IntLit x) { doDefaultAction(x); }

    @Override
    public void performActionOnBoolLit(BoolLit x) { doDefaultAction(x); }

    @Override
    public void performActionOnBinary(Binary x) { doDefaultAction(x); }

    @Override
    public void performActionOnUnaryNot(UnaryNot x) { doDefaultAction(x); }

    @Override
    public void performActionOnProgramVariable(ProgramVariable x) { doDefaultAction(x); }

}
