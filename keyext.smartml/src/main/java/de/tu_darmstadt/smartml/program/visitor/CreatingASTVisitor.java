/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.visitor;

import java.util.*;

import org.key_project.util.ExtList;
import org.key_project.util.collection.ImmutableArray;

import de.tu_darmstadt.smartml.program.Program;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.VarTarget;
import de.tu_darmstadt.smartml.program.expr.*;
import de.tu_darmstadt.smartml.program.stmt.*;
import de.tu_darmstadt.smartml.services.Services;

public abstract class CreatingASTVisitor extends SmartMLASTVisitor {
    protected static final Boolean CHANGED = Boolean.TRUE;
    protected final Deque<ExtList> stack = new ArrayDeque<>();

    boolean preservesPositionInfo = true;

    protected CreatingASTVisitor(SmartMLProgramElement root, boolean preservesPos,
            Services services) {
        super(root, services);
        this.preservesPositionInfo = preservesPos;
    }

    public boolean preservesPositionInfo() { return preservesPositionInfo; }

    @Override
    protected void walk(SmartMLProgramElement node) {
        ExtList l = new ExtList();
        // If you have PositionInfo: l.add(node.getPositionInfo());
        stack.push(l);
        super.walk(node);
    }

    protected ExtList getTop() { return Objects.requireNonNull(stack.peek()); }

    /* ===== default action when unchanged ===== */
    @Override
    protected void doDefaultAction(SmartMLProgramElement x) { addChild(x); }

    protected void changed() {
        ExtList list = getTop();
        if (list.isEmpty() || list.getFirst() != CHANGED)
            list.addFirst(CHANGED);
    }

    protected void addToTopOfStack(SmartMLProgramElement x) {
        if (x != null)
            getTop().add(x);
    }

    protected void addChild(SmartMLProgramElement x) {
        stack.pop();
        addToTopOfStack(x);
    }

    protected void addChildren(ImmutableArray<? extends SmartMLProgramElement> arr) {
        stack.pop();
        for (int i = 0, sz = arr.size(); i < sz; i++)
            addToTopOfStack(arr.get(i));
    }

    /* ===================== Top-level ===================== */

    @Override
    public void performActionOnProgram(Program x) {
        ExtList cl = getTop();
        if (!cl.isEmpty() && cl.getFirst() == CHANGED) {
            cl.removeFirst();
            @SuppressWarnings("unchecked")
            java.util.List<de.tu_darmstadt.smartml.program.decl.Decl> ds =
                new java.util.ArrayList<>(java.util.Arrays
                        .asList(cl.collect(de.tu_darmstadt.smartml.program.decl.Decl.class)));
            addChild(new Program(ds));
            changed();
        } else {
            doDefaultAction(x);
        }
    }


    /* ===================== Statements ===================== */

    @Override
    public void performActionOnBlock(Block x) {
        ExtList changeList = getTop();
        if (!changeList.isEmpty() && changeList.getFirst() == CHANGED) {
            changeList.removeFirst();
            if (!preservesPositionInfo) {
                // changeList.removeFirstOccurrence(PositionInfo.class);
            }
            @SuppressWarnings("unchecked")
            List<Stmt> stmts = new ArrayList<>(Arrays.asList(changeList.collect(Stmt.class)));
            addChild(new Block(stmts));
            changed();
        } else {
            doDefaultAction(x);
        }
    }

    @Override
    public void performActionOnAssign(Assign x) {
        DefaultAction def = new DefaultAction(x) {
            @Override
            SmartMLProgramElement createNewElement(ExtList changeList) {
                VarTarget lhs = changeList.get(VarTarget.class);
                Expr rhs = changeList.get(Expr.class);
                return new Assign(lhs, rhs);
            }
        };
        def.doAction(x);
    }

    @Override
    public void performActionOnIf(If x) {
        DefaultAction def = new DefaultAction(x) {
            @Override
            SmartMLProgramElement createNewElement(ExtList changeList) {
                Expr cond = changeList.get(Expr.class);
                Stmt thenB = changeList.get(Stmt.class);
                Stmt elseB = changeList.get(Stmt.class);
                return new If(cond, thenB, elseB);
            }
        };
        def.doAction(x);
    }

    @Override
    public void performActionOnWhile(While x) {
        DefaultAction def = new DefaultAction(x) {
            @Override
            SmartMLProgramElement createNewElement(ExtList changeList) {
                Expr cond = changeList.get(Expr.class);
                Stmt body = changeList.get(Stmt.class);
                return new While(cond, body);
            }
        };
        def.doAction(x);
    }

    @Override
    public void performActionOnEmptyStatement(EmptyStatement x) {
        doDefaultAction(x);
    }

    /* ===================== Expressions ===================== */

    @Override
    public void performActionOnVar(Var x) { doDefaultAction(x); }

    @Override
    public void performActionOnIntLit(IntLit x) { doDefaultAction(x); }

    @Override
    public void performActionOnBoolLit(BoolLit x) { doDefaultAction(x); }

    @Override
    public void performActionOnUnaryNot(UnaryNot x) {
        DefaultAction def = new DefaultAction(x) {
            @Override
            SmartMLProgramElement createNewElement(ExtList changeList) {
                return new UnaryNot(changeList.get(Expr.class));
            }
        };
        def.doAction(x);
    }

    @Override
    public void performActionOnBinary(Binary x) {
        DefaultAction def = new DefaultAction(x) {
            @Override
            SmartMLProgramElement createNewElement(ExtList changeList) {
                Expr l = changeList.get(Expr.class);
                Expr r = changeList.get(Expr.class);
                return new Binary(l, x.op(), r);
            }
        };
        def.doAction(x);
    }

    /* ===================== DefaultAction helper ===================== */

    protected abstract class DefaultAction {
        protected final SmartMLProgramElement pe;

        protected DefaultAction(SmartMLProgramElement pe) { this.pe = pe; }

        abstract SmartMLProgramElement createNewElement(ExtList changeList);

        public void doAction(SmartMLProgramElement x) {
            ExtList changeList = stack.peek();
            assert changeList != null;
            if (changeList.isEmpty()) {
                doDefaultAction(x);
                return;
            }
            if (changeList.getFirst() == CHANGED) {
                changeList.removeFirst();
                // if (!preservesPositionInfo) changeList.removeFirstOccurrence(PositionInfo.class);
                addNewChild(changeList);
            } else {
                doDefaultAction(x);
            }
        }

        protected void addNewChild(ExtList changeList) {
            addChild(createNewElement(changeList));
            changed();
        }
    }
}
