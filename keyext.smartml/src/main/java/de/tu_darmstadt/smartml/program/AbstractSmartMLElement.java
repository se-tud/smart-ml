/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Common base class for SmartML AST nodes.
 */
public abstract class AbstractSmartMLElement implements SmartMLProgramElement {
    private final List<? extends SmartMLProgramElement> children;

    protected AbstractSmartMLElement(List<? extends SmartMLProgramElement> children) {
        this.children = new ArrayList<>(children);
    }

    @Override
    public SmartMLProgramElement getChild(int index) {
        return children.get(index);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    public List<? extends SmartMLProgramElement> children() { return children; }

    public List<SmartMLProgramElement> getChildren() {
        return Collections.unmodifiableList(children);
    }
}
