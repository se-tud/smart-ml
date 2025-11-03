/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.logic.op;

import java.util.Objects;

import org.key_project.logic.Name;
import org.key_project.logic.SyntaxElement;
import org.key_project.logic.op.AbstractSortedOperator;
import org.key_project.logic.op.Modifier;
import org.key_project.logic.op.UpdateableOperator;
import org.key_project.logic.sort.Sort;

import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.abstraction.KeYSmartMLType;
import de.tu_darmstadt.smartml.program.abstraction.Type;
import de.tu_darmstadt.smartml.program.visitor.Visitor;
import org.jspecify.annotations.NonNull;

public class ProgramVariable extends AbstractSortedOperator
        implements SmartMLProgramElement, UpdateableOperator {
    private final KeYSmartMLType type;

    public ProgramVariable(Name name, Sort s, KeYSmartMLType type) {
        super(name, s, Modifier.NONE);
        this.type = type;
    }

    public ProgramVariable(Name name, KeYSmartMLType type) {
        this(name, Objects.requireNonNull(type.getSort(), name.toString()), type);
    }

    @Override
    public @NonNull SyntaxElement getChild(int n) {
        throw new IndexOutOfBoundsException("Program variable does not have a child");
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    public KeYSmartMLType getKeYSmartMLType() {
        return type;
    }


    public String proofToString() {
        final Type rt = type.getType();
        final String typeName;
        if (rt != null) {
            typeName = rt.toString();
        } else {
            typeName = type.getSort().name().toString();
        }
        return typeName + " " + name() + ";\n";
    }

    @Override
    public void visit(Visitor v) {
        // v.performActionOnProgramVariable(this);
        throw new RuntimeException(
            "Program variables in SmartML representation requires clarification");
    }
}
