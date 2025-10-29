package de.tu_darmstadt.smartml.program.type;

import de.tu_darmstadt.smartml.program.visitor.Visitor;

public interface SmartType {
    default void visit(Visitor v) { v.performActionOnSmartType(this); }

    String display();
}
