/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program.expr;

import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.abstraction.Type;
import de.tu_darmstadt.smartml.services.Services;

public interface Expr extends SmartMLProgramElement {
    default Type type(Services services) {
        throw new RuntimeException("Not implemented yet");
    }
}
