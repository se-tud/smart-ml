/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.parser;

import java.util.HashMap;

import org.key_project.logic.Name;
import org.key_project.logic.Term;
import org.key_project.logic.op.Function;
import org.key_project.util.collection.ImmutableArray;

import de.tu_darmstadt.smartml.logic.SmartMLDLTheory;
import de.tu_darmstadt.smartml.logic.op.Junctor;
import de.tu_darmstadt.smartml.logic.op.SFunction;
import de.tu_darmstadt.smartml.services.Services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParsingFacadeTest {

    private Services services;
    private HashMap<String, Function> predicates;


    @BeforeEach
    void setup() {
        services = new Services();
        predicates = new HashMap<>();
        final Function[] atoms = { delcareAtom("A"), delcareAtom("B"),
            delcareAtom("C"), delcareAtom("D") };

        for (Function atom : atoms) {
            predicates.put(atom.name().toString(), atom);
        }

        services.getNamespaces().functions().addSafely(predicates.values());
    }

    private SFunction delcareAtom(String name) {
        return new SFunction(new Name(name), SmartMLDLTheory.FORMULA, new ImmutableArray<>(), true);
    }

    @Test
    void parseExpression() {
        KeYIO io = new KeYIO(services);
        final Term term = io.parseExpression("A & (!B -> (C | D))");
        assertSame(Junctor.AND, term.op());
        assertSame(predicates.get("A"), term.sub(0).op());
        assertSame(Junctor.IMP, term.sub(1).op());
        assertSame(Junctor.NOT, term.sub(1).sub(0).op());
        assertSame(predicates.get("B"), term.sub(1).sub(0).sub(0).op());
        assertSame(Junctor.OR, term.sub(1).sub(1).op());
        assertSame(predicates.get("C"), term.sub(1).sub(1).sub(0).op());
        assertSame(predicates.get("D"), term.sub(1).sub(1).sub(1).op());
    }
}
