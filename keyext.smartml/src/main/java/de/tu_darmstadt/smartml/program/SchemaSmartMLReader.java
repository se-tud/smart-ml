/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program;

import org.key_project.logic.Namespace;
import org.key_project.logic.op.sv.SchemaVariable;

import de.tu_darmstadt.smartml.logic.NamespaceSet;
import de.tu_darmstadt.smartml.services.Services;
import org.jspecify.annotations.NonNull;

public class SchemaSmartMLReader extends SmartMLReader {

    private Namespace<@NonNull SchemaVariable> schemaVariables;

    public SchemaSmartMLReader(Services services, NamespaceSet nss) {
        super(services, nss);
    }

    public void setSVNamespace(Namespace<@NonNull SchemaVariable> schemaVariables) {
        this.schemaVariables = schemaVariables;
    }
}
