/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program;

import java.util.ArrayList;
import java.util.List;

import de.tu_darmstadt.smartml.parser.SmartMLParser;
import de.tu_darmstadt.smartml.program.decl.*;
import de.tu_darmstadt.smartml.program.decl.Decl;
import de.tu_darmstadt.smartml.program.expr.*;
import de.tu_darmstadt.smartml.program.stmt.*;
import org.antlr.v4.runtime.Token;

public final class Converter {

    /* ===================== Entry points ===================== */

    public Program convertProgram(SmartMLParser.ProgramContext p) {
        List<Decl> decls = new ArrayList<>();
        // (datatypeDec | exceptionDec | resourceDec)*
        for (SmartMLParser.DatatypeDecContext d : p.datatypeDec()) {
            decls.add(convertDatatype(d));
        }
        for (SmartMLParser.ExceptionDecContext e : p.exceptionDec()) {
            decls.add(convertException(e));
        }
        for (SmartMLParser.ResourceDecContext r : p.resourceDec()) {
            decls.add(convertResource(r));
        }
        // (interfaceDec)*
        for (SmartMLParser.InterfaceDecContext i : p.interfaceDec()) {
            decls.add(convertInterface(i));
        }
        // (contractDec)+
        for (SmartMLParser.ContractDecContext c : p.contractDec()) {
            decls.add(convertContract(c));
        }
        return new Program(decls);
    }


    // PLACEHOLDERS //
    private Decl convertDatatype(SmartMLParser.DatatypeDecContext d) {
        String name = d.id().getText();
        // TODO: parse constructorgit s / ADT functions, etc.
        return new DatatypeDecl(name, List.of(), List.of());
    }

    private Decl convertException(SmartMLParser.ExceptionDecContext e) {
        String name = e.getText();
        // TODO: parse payload/fields
        return new ExceptionDecl(name);
    }

    private Decl convertResource(SmartMLParser.ResourceDecContext r) {
        String name = r.id().getText();
        // TODO: parse fields/invariants
        return new ResourceDecl(name, List.of(), List.of());
    }

    private Decl convertInterface(SmartMLParser.InterfaceDecContext i) {
        String name = i.id(0).getText();
        // TODO: parse method signatures
        return new InterfaceDecl(name, List.of());
    }

    private Decl convertContract(SmartMLParser.ContractDecContext c) {
        String name = c.contractId.getText();
        // TODO: parse state vars, constructor (if present inside the contract),
        // functions, invariants, events, etc.
        return new ContractDecl(name, /* state */ List.of(), /* members */ List.of(),
            /* invariants */ List.of());
    }


    /* ===================== Constructor body ===================== */

    private Block convertConstructorBody(SmartMLParser.ConstructorContext ctor) {
        List<Stmt> stmts = new ArrayList<>();
        for (SmartMLParser.AssignContext a : ctor.assign()) {
            stmts.add(convertAssignStmt(a));
        }
        for (SmartMLParser.InternalCallContext ic : ctor.internalCall()) {
            stmts.add(new CallStmt(convertInternalCall(ic)));
        }
        return new Block(stmts);
    }

    /* ===================== Blocks & statements ===================== */

    private Block convertBlock(SmartMLParser.StatBlockContext b) {
        List<Stmt> stmts = new ArrayList<>();
        for (SmartMLParser.StatementContext s : b.statement()) {
            stmts.add(convertStatement(s));
        }
        return new Block(stmts);
    }

    private Stmt convertStatement(SmartMLParser.StatementContext s) {
        if (s.ifStatement() != null) {
            return convertIf(s.ifStatement());
        }
        if (s.exprStat() != null) {
            return new ExpressionStatement(convertExpr(s.exprStat().expr()));
        }
        if (s.loop() != null) {
            return new While(convertExpr(s.loop().expr()), convertBlock(s.loop().statBlock()));
        }
        if (s.assign() != null) {
            return convertAssignStmt(s.assign());
        }
        if (s.funCall() != null) {
            return new CallStmt(convertFunCallAsExpr(s.funCall()));
        }
        if (s.assertError() != null) {
            return new AssertError(convertExpr(s.assertError().expr()));
        }
        if (s.transaction() != null) {
            SmartMLParser.TransactionContext t = s.transaction();
            Stmt tryStmt = convertStatement(t.statement());
            Block abortB = (t.ABORT() != null) ? convertBlock(t.abortStat) : null;
            Block succB = (t.SUCCESS() != null) ? convertBlock(t.successStat) : null;
            return new Transaction(tryStmt, abortB, succB);
        }
        if (s.returnStat() != null) {
            return new Return(convertExpr(s.returnStat().expr()));
        }
        if (s.tryStatement() != null) {
            SmartMLParser.TryStatementContext tr = s.tryStatement();
            Stmt tryStmt = convertStatement(tr.statement());
            VarTarget catchVar = convertVarTarget(tr.vardec());
            Block catchBlock = convertBlock(tr.statBlock());
            return new TryCatch(tryStmt, catchVar, catchBlock);
        }
        if (s.statBlock() != null) {
            return convertBlock(s.statBlock());
        }
        return new EmptyStatement();
    }

    private If convertIf(SmartMLParser.IfStatementContext i) {
        Expr cond = convertExpr(i.cond);
        Stmt thenB = convertBlock(i.block); // statBlock -> Block (implements Stmt)
        Stmt elseB = (i.elseBlock != null)
                ? convertBlock(i.elseBlock) // optional statBlock
                : new EmptyStatement();
        return new If(cond, thenB, elseB);
    }

    private Assign convertAssignStmt(SmartMLParser.AssignContext a) {
        VarTarget lhs = convertVarTarget(a.vardec());
        Expr rhs = (a.expr() != null) ? convertExpr(a.expr())
                : convertFunCallAsExpr(a.funCall());
        return new Assign(lhs, rhs);
    }

    private VarTarget convertVarTarget(SmartMLParser.VardecContext v) {
        LValue target = (v.qualifiedAccess() != null)
                ? convertQualifiedAccess(v.qualifiedAccess())
                : new Var(v.id().getText());
        boolean storage = (v.STORAGE() != null);
        return new VarTarget(/* type */ null, storage, target);
    }

    /* ===================== Calls ===================== */

    private Expr convertFunCallAsExpr(SmartMLParser.FunCallContext c) {
        if (c.internalCall() != null)
            return convertInternalCall(c.internalCall());
        if (c.externalCall() != null)
            return convertExternalCall(c.externalCall());
        if (c.adtCall() != null)
            return convertAdtCall(c.adtCall());
        return new Var(c.getText());
    }

    private QualifiedAccess convertInternalCall(SmartMLParser.InternalCallContext ic) {
        String base = ic.idName.getText(); // 'this' via thisVal
        String fun = ic.funName.getText();
        return new QualifiedAccess(base, List.of(fun));
    }

    private QualifiedAccess convertExternalCall(SmartMLParser.ExternalCallContext ec) {
        String base = ec.idName.getText();
        String fun = ec.funName.getText();
        return new QualifiedAccess(base, List.of(fun));
    }

    private QualifiedAccess convertAdtCall(SmartMLParser.AdtCallContext ac) {
        String fun = ac.funName.getText();
        return new QualifiedAccess(fun, List.of());
    }

    private QualifiedAccess convertQualifiedAccess(SmartMLParser.QualifiedAccessContext q) {
        List<SmartMLParser.IdContext> ids = q.id();
        String base = ids.get(0).getText();
        List<String> rest = new ArrayList<>();
        for (int i = 1; i < ids.size(); i++)
            rest.add(ids.get(i).getText());
        return new QualifiedAccess(base, rest);
    }

    /* ===================== Expressions ===================== */

    private Expr convertExpr(SmartMLParser.ExprContext e) {
        if (e.operator != null) {
            Expr l = convertTerm(e.left);
            Expr r = convertExpr(e.right);
            return mapBinaryByToken(e.operator.getType(), l, r);
        }
        return convertTerm(e.left);
    }

    private Expr convertTerm(SmartMLParser.TermContext t) {
        if (t.operator != null) {
            Expr l = convertFactor(t.left);
            Expr r = convertTerm(t.right);
            return mapBinaryByToken(t.operator.getType(), l, r);
        }
        return convertFactor(t.left);
    }

    private Expr convertFactor(SmartMLParser.FactorContext f) {
        if (f.operator != null) {
            Expr l = convertValue(f.left);
            Expr r = convertValue(f.right);
            return mapBinaryByToken(f.operator.getType(), l, r);
        }
        return convertValue(f.left);
    }

    private Expr convertValue(SmartMLParser.ValueContext v) {
        if (v.INTEGER() != null) {
            return new IntLit(parseIntToken(v.INTEGER().getSymbol()));
        }
        if (v.string() != null) {
            return convertString(v.string());
        }
        if (v.bool() != null) {
            return convertBool(v.bool());
        }
        if (v.address() != null) {
            return convertAddress(v.address());
        }
        if (v.LPAR() != null) { // '(' expr ')'
            return convertExpr(v.expr());
        }
        if (v.qualifiedAccess() != null) {
            return convertQualifiedAccess(v.qualifiedAccess());
        }
        if (v.id() != null) {
            return new Var(v.id().getText());
        }
        /*
         * if (v.newVal() != null) {
         * return convertNewVal(v.newVal());
         * }
         * if (v.adtFunCall() != null) {
         * return convertAdtFunCall(v.adtFunCall());
         * }
         * if (v.resourceAccess() != null) {
         * return convertResourceAccess(v.resourceAccess());
         * }
         */
        if (v.getChildCount() == 2 && v.value() != null) {
            String op = v.getChild(0).getText();
            Expr inner = convertValue(v.value());
            if ("-".equals(op))
                return new UnaryNeg(inner);
            if ("!".equals(op))
                return new UnaryNot(inner);
        }
        return new Var(v.getText());
    }

    private Expr convertString(SmartMLParser.StringContext s) {
        String raw = s.getText();
        String unquoted =
            raw.length() >= 2 && raw.charAt(0) == '"' && raw.charAt(raw.length() - 1) == '"'
                    ? raw.substring(1, raw.length() - 1)
                    : raw;
        return new StringLit(unquoted);
    }

    private Expr convertBool(SmartMLParser.BoolContext b) {
        return new BoolLit(b.TRUE() != null);
    }

    private Expr convertAddress(SmartMLParser.AddressContext a) {
        return new AddressLit(a.getText());
    }

    // TODO: convert in a different way
    /*
     * private Expr convertNewVal(SmartMLParser.NewValContext nv) {
     * String typeName = nv.id().getText();
     * List<Expr> args = (nv.params() != null) ? convertParams(nv.params()) : List.of();
     * return new NewValue(typeName, args);
     * }
     *
     * private Expr convertAdtFunCall(SmartMLParser.AdtFunCallContext ac) {
     * String fun = ac.id().getText();
     * List<Expr> args = (ac.params() != null) ? convertParams(ac.params()) : List.of();
     * return new ADTFunctionCall(fun, args);
     * }
     *
     * private Expr convertResourceAccess(SmartMLParser.ResourceAccessContext rac) {
     * String base = rac.id().getText();
     * String resSpec = rac.resources().getText();
     * return new ResourceAccess(base, resSpec);
     * }
     */

    private List<Expr> convertParams(SmartMLParser.ParamsContext ps) {
        List<Expr> out = new ArrayList<>();
        out.add(convertExpr(ps.expr(0)));
        for (int i = 1; i < ps.expr().size(); i++) {
            out.add(convertExpr(ps.expr(i)));
        }
        return out;
    }


    private Binary mapBinaryByToken(int tokenType, Expr l, Expr r) {
        switch (tokenType) {
            case SmartMLParser.PLUS:
                return new Binary(l, BinOp.ADD, r);
            case SmartMLParser.MINUS:
                return new Binary(l, BinOp.SUB, r);
            case SmartMLParser.OR:
                return new Binary(l, BinOp.OR, r);
            case SmartMLParser.ASM:
                return new Binary(l, BinOp.OR, r);

            case SmartMLParser.TIMES:
                return new Binary(l, BinOp.MUL, r);
            case SmartMLParser.DIV:
                return new Binary(l, BinOp.DIV, r);
            case SmartMLParser.AND:
                return new Binary(l, BinOp.AND, r);

            case SmartMLParser.EQ:
                return new Binary(l, BinOp.EQ, r);
            case SmartMLParser.LE:
                return new Binary(l, BinOp.LT, r);
            case SmartMLParser.GE:
                return new Binary(l, BinOp.GT, r);
            case SmartMLParser.LEQ:
                return new Binary(l, BinOp.LE, r);
            case SmartMLParser.GEQ:
                return new Binary(l, BinOp.GE, r);
            case SmartMLParser.NEQ:
                return new Binary(l, BinOp.NE, r);
        }
        return new Binary(l, BinOp.ADD, r);
    }

    /* ===================== Utilities ===================== */

    private static int parseIntToken(Token t) {
        return Integer.parseInt(t.getText());
    }
}
