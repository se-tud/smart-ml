/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.visitor;

import de.tu_darmstadt.smartml.program.Program;
import de.tu_darmstadt.smartml.program.VarTarget;
import de.tu_darmstadt.smartml.program.decl.FunctionDecl;
import de.tu_darmstadt.smartml.program.expr.*;
import de.tu_darmstadt.smartml.program.stmt.*;
import de.tu_darmstadt.smartml.program.type.*;

public interface Visitor {
    // top-level
    void performActionOnProgram(Program x);
    void performActionOnBlock(Block x);
    void performActionOnVarTarget(VarTarget x);

    // declarations
    void performActionOnFunctionDecl(FunctionDecl x);

    // statements
    void performActionOnAssign(Assign x);
    void performActionOnIf(If x);
    void performActionOnWhile(While x);
    void performActionOnReturn(Return x);
    void performActionOnLet(Let x);
    void performActionOnCallStmt(CallStmt x);
    void performActionOnEmptyStatement(EmptyStatement x);
    void performActionOnTryCatch(TryCatch x);
    void performActionOnTransaction(Transaction x);
    void performActionOnAssertError(AssertError x);

    // expressions
    void performActionOnVar(Var x);
    void performActionOnIntLit(IntLit x);
    void performActionOnBoolLit(BoolLit x);
    void performActionOnBinary(Binary x);
    void performActionOnUnaryNot(UnaryNot x);
    void performActionOnQualifiedAccess(QualifiedAccess x);

    // literals & names
    void performActionOnStringLit(StringLit x);
    void performActionOnAddressLit(AddressLit x);
    void performActionOnThisExpr(ThisExpr x);

    // types
    void performActionOnSmartType(SmartType x);
    void performActionOnPrimitiveType(PrimitiveType x);
    void performActionOnSchemaType(SchemaType x);

}