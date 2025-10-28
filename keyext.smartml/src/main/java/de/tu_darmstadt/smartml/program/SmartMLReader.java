/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program;

import org.key_project.logic.Namespace;

import de.tu_darmstadt.smartml.logic.NamespaceSet;
import de.tu_darmstadt.smartml.logic.SmartMLBlock;
import de.tu_darmstadt.smartml.logic.op.ProgramVariable;
import de.tu_darmstadt.smartml.services.Services;
import org.jspecify.annotations.NonNull;

public class SmartMLReader {
    protected final Services services;
    protected final NamespaceSet nss;

    public SmartMLReader(Services services, NamespaceSet nss) {
        this.services = services;
        this.nss = nss;
    }

    public SmartMLBlock readBlockWithProgramVariables(
            Namespace<@NonNull ProgramVariable> programVariableNamespace, String solidity) {
        throw new RuntimeException("Not implemented yet");
    }

    public SmartMLBlock readBlockWithEmptyContext(String solidity) {
        throw new RuntimeException("Not implemented yet");
    }
}
