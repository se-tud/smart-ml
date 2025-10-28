/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.parser.builder;

import java.util.List;

import de.tu_darmstadt.smartml.parser.KeYSmartMLDLParser;
import de.tu_darmstadt.smartml.parser.KeYSmartMLDLParserBaseVisitor;

public class ConfigurationBuilder extends KeYSmartMLDLParserBaseVisitor<Object> {
    public List<Object> visitCfile(KeYSmartMLDLParser.CfileContext ctx) {
        return null;
    }
}
