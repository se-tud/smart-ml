/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import de.tu_darmstadt.smartml.logic.NamespaceSet;
import de.tu_darmstadt.smartml.logic.SmartMLBlock;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import de.tu_darmstadt.smartml.program.SmartMLReader;
import de.tu_darmstadt.smartml.services.Services;

public class Test {
    public static void main(String[] args) throws IOException {

        String filename = "keyext.smartml/src/tests/resources/example.smartml";
        String src = Files.readString(Path.of(filename));

        Services services = new Services();
        NamespaceSet nss = services.getNamespaces();

        SmartMLReader reader = new SmartMLReader(services, nss);
        SmartMLBlock block = reader.readBlockWithEmptyContext(src);

        SmartMLProgramElement root = block.program();
        System.out.println("OK: program parsed");
        System.out.println(root.pretty());

    }
}
