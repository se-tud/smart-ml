/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.parser.builder;


import de.tu_darmstadt.smartml.logic.NamespaceSet;
import de.tu_darmstadt.smartml.services.Services;

public class ProblemFinder extends ExpressionBuilder {
    public ProblemFinder(Services services, NamespaceSet nss) {
        super(services, nss);
    }
}
