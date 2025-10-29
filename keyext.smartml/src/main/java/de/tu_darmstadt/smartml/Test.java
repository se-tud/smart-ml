/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml;

import java.io.IOException;
import java.nio.file.Path;

import de.tu_darmstadt.smartml.parser.SmartMLLexer;
import de.tu_darmstadt.smartml.parser.SmartMLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Test {
    public static void main(String[] args) {
        try {
            String filename = "keyext.smartml/src/tests/resources/example.smartml";
            CharStream input = CharStreams.fromPath(Path.of(filename));


            SmartMLLexer lexer = new SmartMLLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SmartMLParser parser = new SmartMLParser(tokens);

            ParseTree tree = parser.program();

            System.out.println(" Parsed successfully: " + filename);
            System.out.println(tree.toStringTree(parser));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
