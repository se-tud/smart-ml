/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.type;

import de.tu_darmstadt.smartml.program.visitor.Visitor;

public interface SmartType {
    default void visit(Visitor v) { v.performActionOnSmartType(this); }

    String display();
}
