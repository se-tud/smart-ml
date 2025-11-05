/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program;

import org.key_project.logic.SyntaxElement;

import de.tu_darmstadt.smartml.program.visitor.Visitor;

/// interface at the root of any program element representing
/// SmartML constructs
public interface SmartMLProgramElement extends SyntaxElement {

    /// to be populated by methods for matching etc.

    default int computeHashCode() {
        // Cache for hashcode computation would be of advantage as it is for instance recomputed
        // for each modality creation and can be rather expensive as the whole AST is repeatedly
        // traversed
        // at the moment this has to be cached at each subclass
        /*
         * if (hashcode != -1) {
         * return hashcode;
         * }
         */
        int hash = 7;
        for (int i = 0; i < this.getChildCount(); i++) {
            hash = hash * 31 + this.getChild(i).hashCode();
        }
        // this.hashcode = hash;
        return hash;
    }

    void visit(Visitor v);

    default String pretty() { return new PrettyPrinter().print(this); }

}
