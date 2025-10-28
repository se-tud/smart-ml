/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.expr;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class Var extends AbstractSmartMLElement implements Expr {
    private final String name;

    public Var(String name) { super(List.of()); this.name = name; }

    public String name() { return name; }

    @Override
    public void visit(Visitor v) { v.performActionOnVar(this); }
}
