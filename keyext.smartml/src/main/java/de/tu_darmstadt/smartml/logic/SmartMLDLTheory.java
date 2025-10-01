package de.tu_darmstadt.smartml.logic;

import de.tu_darmstadt.smartml.logic.sort.SortImpl;
import org.key_project.logic.Name;
import org.key_project.logic.sort.Sort;

public class SmartMLDLTheory {
    /// Formulas are represented as "terms" of this sort.
    public static final Sort FORMULA = new SortImpl(new Name("Formula"));
    /// Updates are represented as "terms" of this sort.
    public static final Sort UPDATE = new SortImpl(new Name("Update"));
    /// Any is a supersort of all sorts.
    public static final Sort ANY = new SortImpl(new Name("any"));
}
