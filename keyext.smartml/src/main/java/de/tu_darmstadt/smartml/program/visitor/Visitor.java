/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.visitor;

import de.tu_darmstadt.smartml.program.Program;
import de.tu_darmstadt.smartml.program.expr.*;
import de.tu_darmstadt.smartml.program.stmt.*;

public interface Visitor {
    // top-level
    void performActionOnProgram(Program x);

    void performActionOnBlock(Block x);

    // statements
    void performActionOnAssign(Assign x);

    void performActionOnIf(If x);

    void performActionOnWhile(While x);

    void performActionOnEmptyStatement(EmptyStatement x);

    // expressions
    void performActionOnVar(Var x);

    void performActionOnIntLit(IntLit x);

    void performActionOnBoolLit(BoolLit x);

    void performActionOnBinary(Binary x);

    void performActionOnUnaryNot(UnaryNot x);

    void performActionOnProgramVariable(Visitor v);
}
