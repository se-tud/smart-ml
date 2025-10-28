/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.stmt;

import java.util.List;

import de.tu_darmstadt.smartml.program.AbstractSmartMLElement;
import de.tu_darmstadt.smartml.program.visitor.Visitor;

public final class Block extends AbstractSmartMLElement implements Stmt {
    public Block(List<? extends Stmt> statements) { super(statements); }

    @SuppressWarnings("unchecked")
    public List<Stmt> statements() { return (List<Stmt>) (List<?>) children(); }

    @Override
    public void visit(Visitor v) { v.performActionOnBlock(this); }
}
