/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.visitor;

import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.expr.*;
import de.tu_darmstadt.smartml.program.stmt.*;
import de.tu_darmstadt.smartml.program.type.PrimitiveType;
import de.tu_darmstadt.smartml.program.type.SchemaType;
import de.tu_darmstadt.smartml.program.type.SmartType;
import de.tu_darmstadt.smartml.services.Services;

public final class LoggingVisitor extends SmartMLASTVisitor {
    private final StringBuilder log = new StringBuilder();

    public LoggingVisitor(SmartMLProgramElement root, Services services) {
        super(root, services);
    }

    public String log() { return log.toString(); }

    private void mark(String s) {
        if (log.length() > 0)
            log.append(" ");
        log.append(s);
    }


    @Override
    protected void doDefaultAction(SmartMLProgramElement x) {
        mark(x.getClass().getSimpleName());
    }

    // statements
    @Override
    public void performActionOnBlock(Block x) { mark("Block"); }

    @Override
    public void performActionOnIf(If x) { mark("If"); }

    @Override
    public void performActionOnWhile(While x) { mark("While"); }

    @Override
    public void performActionOnAssign(Assign x) { mark("Assign"); }

    @Override
    public void performActionOnEmptyStatement(EmptyStatement x) { mark("Empty"); }

    // expressions
    @Override
    public void performActionOnVar(Var x) { mark("Var(" + x.name() + ")"); }

    @Override
    public void performActionOnIntLit(IntLit x) { mark("Int(" + x.value() + ")"); }

    @Override
    public void performActionOnBoolLit(BoolLit x) { mark("Bool(" + x.value() + ")"); }

    @Override
    public void performActionOnUnaryNot(UnaryNot x) { mark("Not"); }

    @Override
    public void performActionOnStringLit(StringLit x) { mark("String(" + x.value() + ")"); }

    @Override
    public void performActionOnAddressLit(AddressLit x) { mark("Address(" + x.value() + ")"); }

    @Override
    public void performActionOnThisExpr(ThisExpr x) { mark("This"); }

    @Override
    public void performActionOnSmartType(SmartType x) { mark("SmartType"); }

    @Override
    public void performActionOnPrimitiveType(PrimitiveType x) { mark("PrimitiveType"); }

    @Override
    public void performActionOnSchemaType(SchemaType x) { mark("SchemaType"); }

    @Override
    public void performActionOnBinary(Binary x) { mark("Bin(" + x.op() + ")"); }
}
