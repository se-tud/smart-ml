/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.expr;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class QualifiedAccess extends AbstractSmartMLElement implements Expr, LValue {
    private final String base;
    private final List<String> path;

    public QualifiedAccess(String base, List<String> path) {
        super(List.of());
        this.base = base;
        this.path = List.copyOf(path);
    }

    public String base() { return base; }

    public List<String> path() { return path; }

    @Override
    public void visit(Visitor v) { v.performActionOnQualifiedAccess(this); }
}
