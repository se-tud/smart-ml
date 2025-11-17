/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.program;

import org.key_project.logic.Namespace;

import de.tu_darmstadt.smartml.logic.NamespaceSet;
import de.tu_darmstadt.smartml.logic.SmartMLBlock;
import de.tu_darmstadt.smartml.logic.op.ProgramVariable;
import de.tu_darmstadt.smartml.parser.SmartMLLexer;
import de.tu_darmstadt.smartml.parser.SmartMLParser;
import de.tu_darmstadt.smartml.program.stmt.Block;
import de.tu_darmstadt.smartml.services.Services;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jspecify.annotations.NonNull;

/**
 * Reader that parses SmartML source text into a SmartMLBlock (Program AST).
 * It mirrors RustyReader but uses SmartML's grammar and converter.
 */
public class SmartMLReader {
    private final Services services;
    private final NamespaceSet nss;

    public SmartMLReader(Services services, NamespaceSet nss) {
        this.services = services;
        this.nss = nss;
    }

    public Services getServices() {
        return services;
    }

    /**
     * Parses SmartML text using the provided program variable namespace.
     */
    public SmartMLBlock readBlockWithProgramVariables(
            Namespace<@NonNull ProgramVariable> programVariableNamespace,
            String smartML) {

        var lexer = new SmartMLLexer(CharStreams.fromString(smartML));
        var tokens = new CommonTokenStream(lexer);
        var parser = new SmartMLParser(tokens);

        SmartMLParser.StatBlockContext ctx = parser.statBlock();

        // Convert the parse tree into a Program AST
        var converter = new Converter(services);
        Block program = converter.convertBlock(ctx);

        return new SmartMLBlock(program);
    }

    /**
     * Parses SmartML text with an empty ProgramVariable namespace.
     */
    public SmartMLBlock readBlockWithEmptyContext(String smartML) {
        return readBlockWithProgramVariables(new Namespace<>(), smartML);
    }
}
