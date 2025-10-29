/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program;

import de.tu_darmstadt.smartml.program.decl.Decl;
import de.tu_darmstadt.smartml.program.visitor.Visitor;


public final class Program extends AbstractSmartMLElement implements SmartMLProgramElement {
    private final java.util.List<Decl> decls;

    public Program(java.util.List<Decl> decls) {
        super(decls);
        this.decls = java.util.List.copyOf(decls);
    }

    public java.util.List<Decl> decls() { return decls; }

    @Override
    public void visit(Visitor v) { v.performActionOnProgram(this); }

    @Override
    public String toString() { return "Program" + decls; }
}
