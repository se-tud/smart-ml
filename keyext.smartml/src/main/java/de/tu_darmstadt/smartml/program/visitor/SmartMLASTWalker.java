/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.visitor;

import de.tu_darmstadt.smartml.program.SmartMLProgramElement;

/** Minimal DFS walker like Rusty's RustyASTWalker. */
public abstract class SmartMLASTWalker {
    protected final SmartMLProgramElement root;

    protected SmartMLASTWalker(SmartMLProgramElement root) {
        this.root = root;
    }

    /** Start the traversal. */
    public final void run() {
        walk(root);
    }

    protected void walk(SmartMLProgramElement node) {
        if (node == null) return;
        for (int i = 0; i < node.getChildCount(); i++) {
            walk((SmartMLProgramElement) node.getChild(i));
        }
        doAction(node);
    }

    /** Called just before leaving the node the last time. */
    protected abstract void doAction(SmartMLProgramElement node);
}
