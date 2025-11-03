/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.visitor;

import de.tu_darmstadt.smartml.program.Program;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.VarTarget;
import de.tu_darmstadt.smartml.program.decl.ContractDecl;
import de.tu_darmstadt.smartml.program.decl.DatatypeDecl;
import de.tu_darmstadt.smartml.program.decl.ExceptionDecl;
import de.tu_darmstadt.smartml.program.decl.FunctionDecl;
import de.tu_darmstadt.smartml.program.decl.InterfaceDecl;
import de.tu_darmstadt.smartml.program.decl.ResourceDecl;
import de.tu_darmstadt.smartml.program.expr.*;
import de.tu_darmstadt.smartml.program.stmt.*;
import de.tu_darmstadt.smartml.services.Services;


public abstract class SmartMLASTVisitor extends SmartMLASTWalker implements Visitor {
    protected final Services services;

    protected SmartMLASTVisitor(SmartMLProgramElement root, Services services) {
        super(root);
        this.services = services;
    }

    @Override
    protected void walk(SmartMLProgramElement node) {
        super.walk(node);
    }

    @Override
    protected void doAction(SmartMLProgramElement node) {
        node.visit(this);
    }

    protected abstract void doDefaultAction(SmartMLProgramElement node);

    @Override
    public void performActionOnProgram(Program x) { doDefaultAction(x); }

    @Override
    public void performActionOnBlock(Block x) { doDefaultAction(x); }

    @Override
    public void performActionOnVarTarget(VarTarget x) { doDefaultAction(x); }

    @Override
    public void performActionOnDatatypeDecl(DatatypeDecl x) { doDefaultAction(x); }

    @Override
    public void performActionOnFunctionDecl(FunctionDecl x) { doDefaultAction(x); }

    @Override
    public void performActionOnExpressionStatement(ExpressionStatement x) { doDefaultAction(x); }

    @Override
    public void performActionOnExceptionDecl(ExceptionDecl x) {
        doDefaultAction(x);
    }

    @Override
    public void performActionOnResourceDecl(ResourceDecl x) {
        doDefaultAction(x);
    }

    @Override
    public void performActionOnInterfaceDecl(InterfaceDecl x) {
        doDefaultAction(x);
    }

    @Override
    public void performActionOnContractDecl(ContractDecl x) {
        doDefaultAction(x);
    }

    @Override
    public void performActionOnAssign(Assign x) { doDefaultAction(x); }

    @Override
    public void performActionOnIf(If x) { doDefaultAction(x); }

    @Override
    public void performActionOnWhile(While x) { doDefaultAction(x); }

    @Override
    public void performActionOnReturn(Return x) { doDefaultAction(x); }

    @Override
    public void performActionOnLet(Let x) { doDefaultAction(x); }

    @Override
    public void performActionOnCallStmt(CallStmt x) { doDefaultAction(x); }

    @Override
    public void performActionOnEmptyStatement(EmptyStatement x) { doDefaultAction(x); }

    @Override
    public void performActionOnTryCatch(TryCatch x) { doDefaultAction(x); }

    @Override
    public void performActionOnTransaction(Transaction x) { doDefaultAction(x); }

    @Override
    public void performActionOnAssertError(AssertError x) { doDefaultAction(x); }


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
    public void performActionOnUnaryNeg(UnaryNeg x) { doDefaultAction(x); }

    @Override
    public void performActionOnQualifiedAccess(QualifiedAccess x) { doDefaultAction(x); }

    @Override
    public void performActionOnStringLit(StringLit x) { doDefaultAction(x); }


    @Override
    public void performActionOnAddressLit(AddressLit x) {
        doDefaultAction(x);
    }

    @Override
    public void performActionOnThisExpr(ThisExpr x) {
        doDefaultAction(x);
    }

}
