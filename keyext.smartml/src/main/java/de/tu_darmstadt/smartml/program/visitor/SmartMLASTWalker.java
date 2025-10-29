/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.visitor;

import de.tu_darmstadt.smartml.program.SmartMLProgramElement;

/** Minimal DFS walker like Rusty's RustyASTWalker. */
public abstract class SmartMLASTWalker {
    protected final SmartMLProgramElement root;

    /// the current visited level
    private int depth = -1;

    protected SmartMLASTWalker(SmartMLProgramElement root) {
        this.root = root;
    }

    /** Start the traversal. */
    public final void run() {
        walk(root);
    }

    /// returns the current visited level
    public int depth() {
        return depth;
    }

    /** Depth-left-first: descend into children, then call doAction(node). */
    protected void walk(SmartMLProgramElement node) {
        final int n = node.getChildCount();
        depth++;
        for (int i = 0; i < n; i++) {
            var child = (SmartMLProgramElement) node.getChild(i);
            walk(child);
        }
        depth--;
        doAction(node);
    }

    /** Called just before leaving the node the last time. */
    protected abstract void doAction(SmartMLProgramElement node);
}
