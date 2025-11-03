/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.decl;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class InterfaceDecl extends AbstractSmartMLElement implements Decl {
    private final String name;
    private final List<? extends SmartMLProgramElement> methods;

    public InterfaceDecl(String name, List<? extends SmartMLProgramElement> methods) {
        super(methods);
        this.name = name;
        this.methods = List.copyOf(methods);
    }

    public String name() { return name; }

    @Override
    public void visit(Visitor v) { v.performActionOnInterfaceDecl(this); }
}
