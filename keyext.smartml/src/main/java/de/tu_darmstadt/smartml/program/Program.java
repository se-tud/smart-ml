/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program;

import java.util.List;

import de.tu_darmstadt.smartml.program.stmt.Stmt;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

/**
 * Root node of a SmartML program.
 */
public class Program extends AbstractSmartMLElement implements SmartMLProgramElement {
    private final List<Stmt> statements;

    public Program(List<Stmt> statements) {
        super(statements);
        this.statements = statements;
    }

    public List<Stmt> getStatements() {
        return statements;
    }

    @Override
    public void visit(Visitor v) {
        v.performActionOnProgram(this);
    }

    @Override
    public String toString() {
        return "Program" + statements;
    }
}
