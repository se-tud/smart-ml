// File: src/main/java/de/tu_darmstadt/smartml/program/SourceData.java
package de.tu_darmstadt.smartml.program;

import org.jspecify.annotations.Nullable;

/**
 * Minimal stub so SmartMLProgramElement#match compiles.
 * Only implements the methods SmartMLProgramElement calls right now.
 */
public final class SourceData {
    private final SmartMLProgramElement element;
    private int childPos;
    private final @Nullable Object services;

    public SourceData(SmartMLProgramElement element, int startChildPos, @Nullable Object services) {
        this.element = element;
        this.childPos = startChildPos;
        this.services = services;
    }

    /** In Rusty this returns the current "source" node; we use the same single element. */
    public SmartMLProgramElement getSource() {
        return element;
    }

    /** In Rusty this returns the current element of the source stream; keep it identical. */
    public SmartMLProgramElement getElement() {
        return element;
    }

    /** Position into the children of the current element (used for compatibleBlockSize check). */
    public int getChildPos() {
        return childPos;
    }

    /** Advance the child position by one; SmartMLProgramElement calls next() after a match. */
    public void next() {
        childPos++;
    }

    /** Pass-through of the services object (type-agnostic for now). */
    public @Nullable Object getServices() {
        return services;
    }
}
