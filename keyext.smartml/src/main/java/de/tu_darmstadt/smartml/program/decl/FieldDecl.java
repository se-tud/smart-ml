/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
// de.tu_darmstadt.smartml.program.decl.FieldDecl
package de.tu_darmstadt.smartml.program.decl;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class FieldDecl extends AbstractSmartMLElement implements Decl {
    private final String type;
    private final String name;

    public FieldDecl(String type, String name) {
        super(java.util.List.of());
        this.type = type;
        this.name = name;
    }

    public String type() { return type; }

    public String name() { return name; }

    @Override
    public void visit(Visitor v) { /* optional */ }

    @Override
    public String toString() { return type + " " + name; }
}
