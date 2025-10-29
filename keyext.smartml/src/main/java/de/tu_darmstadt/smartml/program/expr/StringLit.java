/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.expr;


import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class StringLit extends AbstractSmartMLElement implements Expr {
    private final String value;

    public StringLit(String value) { super(List.of()); this.value = value; }

    public String value() { return value; }

    @Override
    public void visit(Visitor v) { v.performActionOnStringLit(this); }
}
