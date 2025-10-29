/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.logic.equality;

import java.util.HashMap;
import java.util.Map;

import org.key_project.logic.Name;
import org.key_project.logic.Property;
import org.key_project.logic.SyntaxElement;
import org.key_project.logic.SyntaxElementCursor;

import de.tu_darmstadt.smartml.logic.NameAbstractionTable;
import de.tu_darmstadt.smartml.logic.op.ProgramVariable;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import org.jspecify.annotations.NonNull;

public class RenamingProgramElementProperty implements Property<@NonNull SmartMLProgramElement> {
    /// The single instance of this property.
    public static final RenamingProgramElementProperty RENAMING_PROGRAM_ELEMENT_PROPERTY =
        new RenamingProgramElementProperty();

    /// This constructor is private as a single instance of this class should be shared. The
    /// instance
    /// can be accessed through
    /// [#RENAMING_PROGRAM_ELEMENT_PROPERTY].
    private RenamingProgramElementProperty() {}

    /// Checks if `rpe2` is a [SmartMLProgramElement] syntactically equal to `rpe1`
    /// modulo
    /// renaming.
    ///
    /// When this method is supplied with a [NameAbstractionTable], it will use this table to
    /// compare the abstract names of the source elements. If no [NameAbstractionTable] is
    /// supplied, a new one will be created.
    ///
    /// @param rpe1 the first element of the equality check
    /// @param rpe2 the second element of the equality check
    /// @param v can be a single [NameAbstractionTable] for this equality check
    /// @return `true` iff `rpe2` is a source element syntactically equal to `rpe1`
    /// modulo renaming
    /// @param <V> is supposed to be [NameAbstractionTable] for this equality check
    @Override
    public <V> boolean equalsModThisProperty(SmartMLProgramElement rpe1, SmartMLProgramElement rpe2,
            V... v) {
        NameAbstractionTable nat;
        if (v.length > 0 && (v[0] instanceof NameAbstractionTable n)) {
            nat = n;
        } else {
            nat = new NameAbstractionTable();
        }

        SyntaxElementCursor c1 = rpe1.getCursor(), c2 = rpe2.getCursor();
        SyntaxElement next1, next2;
        boolean hasNext1, hasNext2; // Check at the end if both cursors have reached the end

        do {
            // First nodes can never be null as cursor is initialized with 'this'
            next1 = c1.getCurrentNode();
            next2 = c2.getCurrentNode();
            if (next1 instanceof ProgramVariable || next1 instanceof Name) {
                if (!handleProgramVariableOrIdentifier(next1, next2, nat)) {
                    return false;
                }
            } else if (next1.getChildCount() > 0) {
                if (!handleSmartMLNonTerminalProgramElement(next1,
                    next2)) {
                    return false;
                }
            } else {
                if (!handleStandard(next1, next2)) {
                    return false;
                }
            }
            // walk to the next nodes in the tree
        } while ((hasNext1 = c1.goToNext()) & (hasNext2 = c2.goToNext()));

        return hasNext1 == hasNext2;
    }

    // TODO: hashCodeModThisProperty currently does not take a NameAbstractionTable as an argument.
    // This is because the current implementation of hashCodeModThisProperty is not parameterized
    // with a vararg. Variables occurring in multiple formulas and SmartMLBlocks are considered in
    // isolation as a newly created NameAbstractionTable that does not contain entries from previous
    // SmartMLBlocks is used. This could possibly lead to more collisions but if this is a concern,
    // the
    // method can be changed to also take a generic vararg. That way, the NameAbstractionTable can
    // be passed to the method and hash codes can take previous usage of variables into account.
    @Override
    public int hashCodeModThisProperty(SmartMLProgramElement pe) {
        NameAbstractionMap absMap = new NameAbstractionMap();

        int hashCode = 1;
        SyntaxElementCursor c = pe.getCursor();
        SyntaxElement next;

        do {
            // First node can never be null as cursor is initialized with 'this'
            next = c.getCurrentNode();
            // Handle special cases so that hashCodeModThisProperty follows equalsModThisProperty
            if (next instanceof ProgramVariable || next instanceof Name) {
                Name name =
                    next instanceof ProgramVariable pv ? pv.name() : (Name) next;
                hashCode = 31 * hashCode + absMap.getAbstractName(name);
            } else if (next.getChildCount() > 0) {
                hashCode = 31 * hashCode + next.getChildCount();
            } else {
                hashCode = 31 * hashCode + next.hashCode();
            }
            // walk to the next nodes in the tree
        } while (c.goToNext());

        return hashCode;
    }

    /*------------- Helper methods for special cases in equalsModThisProperty --------------*/
    /// Handles the standard case of comparing two [SyntaxElement]s modulo renaming.
    ///
    /// @param se1 the first [SyntaxElement] to be compared
    /// @param se2 the second [SyntaxElement] to be compared
    /// @return `true` iff the two source elements are equal under the standard `equals`
    /// method
    private boolean handleStandard(SyntaxElement se1, SyntaxElement se2) {
        return se1.equals(se2);
    }

    /// Handles the special case of comparing a [] to a [SyntaxElement].
    ///
    /// @param firstSE the SmartML program element with children to be compared
    /// @param secondSE the [SyntaxElement] to be compared
    /// @return `true` iff `secondSE` is of the same class and has the same number of children
    /// as `firstSE`
    private boolean handleSmartMLNonTerminalProgramElement(SyntaxElement firstSE,
            SyntaxElement secondSE) {
        /*
         * In the case of non-terminal SmartMLProgramElements, we must not traverse the children
         * recursively through the normal equals method. This is the case as we might have to
         * add some entries of children nodes to a NameAbstractionTable so that they can be
         * compared later on.
         */
        if (secondSE == firstSE) {
            return true;
        }
        if (secondSE.getClass() != firstSE.getClass()) {
            return false;
        }
        return firstSE.getChildCount() == secondSE.getChildCount();
    }

    /// Handles the special case of comparing a [ProgramVariable] or an
    /// [Name] to a [SyntaxElement].
    ///
    /// @param se1 the first [SyntaxElement] which is either a [ProgramVariable] or an
    /// [Name]
    /// @param se2 the second [SyntaxElement] to be compared
    /// @param nat the [NameAbstractionTable] that should be used to check whether `se1`
    /// and `se2` have the same abstract name
    /// @return `true` iff `se1` and `se2` have the same abstract name
    private boolean handleProgramVariableOrIdentifier(SyntaxElement se1, SyntaxElement se2,
            NameAbstractionTable nat) {
        if (se1 == se2) {
            return true;
        }
        if (se1.getClass() != se2.getClass()) {
            return false;
        }

        Name name1, name2;
        if (se1 instanceof ProgramVariable pv) {
            name1 = pv.name();
            name2 = ((ProgramVariable) se2).name();
        } else {
            name1 = (Name) se1;
            name2 = (Name) se2;
        }

        return nat.sameAbstractName(name1, name2);
    }


    /* ---------- End of helper methods for special cases in equalsModThisProperty ---------- */

    /// A helper class to map [Name]s to an abstract name.
    ///
    /// As names are abstracted from in this property, we need to give named elements abstract names
    /// for them to be used in the hash code. This approach is similar to
    /// [NameAbstractionTable], where we collect elements with names in the order they are
    /// declared. Each element is associated with the number of previously added elements, which is
    /// then used as the abstract name.
    private static class NameAbstractionMap {
        private int nextAbstractName = 0;

        /// The map that associates [Name]s with their abstract names.
        private final Map<Name, Integer> map = new HashMap<>();

        /// Adds a [Name] to the map.
        ///
        /// @param name the [Name] to be added
        public void add(Name name) {
            map.put(name, nextAbstractName++);
        }

        /// Returns the abstract name of a [Name] or `-1` if the element
        /// is not in the map.
        /// ee
        ///
        /// @param name the [Name] whose abstract name should be returned
        /// @return the abstract name of the [Name] or `-1` if the element
        /// is
        /// not in the map
        public int getAbstractName(Name name) {
            final Integer result = map.get(name);
            return result != null ? result : -1;
        }
    }
}
