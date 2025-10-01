package de.tu_darmstadt.smartml.logic;

import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.key_project.logic.Program;
import org.key_project.logic.SyntaxElement;

public record SmartMLBlock(@NonNull SmartMLProgramElement program) implements Program {
        @Override
        public @NonNull SyntaxElement getChild(int n) {
            if (n == 0)
                return program;
            throw new IndexOutOfBoundsException("RustyBlock " + this + " has only one child");
        }

        @Override
        public int getChildCount() {
            return 1;
        }

        public boolean isEmpty() {
            return false;
        }

        @Override
        public @NonNull String toString() {
            return program.toString();
        }

        @Override
        public boolean equals(@Nullable Object o) {
            if (o == this)
                return true;
            if (!(o instanceof SmartMLBlock(final SmartMLProgramElement otherProgram)))
                return false;
            return otherProgram.equals(program());
        }

        /// returns the hashCode
        @Override
        public int hashCode() {
            return 17 + program().hashCode();
        }
    }