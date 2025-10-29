/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.rules.matching.inst;

import org.key_project.logic.SyntaxElement;
import org.key_project.util.collection.ImmutableArray;

import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import org.jspecify.annotations.NonNull;

public record ProgramList(ImmutableArray<SmartMLProgramElement> list) implements SyntaxElement {
    public ProgramList {
        assert list != null
                : "Constructor of ProgramList must" + " not be called with null argument";
    }

    public boolean equals(Object o) {
        if (!(o instanceof ProgramList)) {
            return false;
        }
        return list.equals(((ProgramList) o).list);
    }


    @Override
    public @NonNull SyntaxElement getChild(int n) { return list.get(n); }

    @Override
    public int getChildCount() { return list.size(); }
}
