package de.tu_darmstadt.smartml.program;

import de.tu_darmstadt.smartml.program.visitor.Visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Common base class for SmartML AST nodes.
 */
public abstract class AbstractSmartMLElement implements SmartMLProgramElement {
    private final List<SmartMLProgramElement> children;

    protected AbstractSmartMLElement(List<? extends SmartMLProgramElement> children) {
        this.children = new ArrayList<>(children);
    }

    @Override
    public SmartMLProgramElement getChild(int index) {
        return children.get(index);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }
    public List<SmartMLProgramElement> children() { return children; }
    public List<SmartMLProgramElement> getChildren() {
        return Collections.unmodifiableList(children);
    }
}
