package de.tu_darmstadt.smartml.logic.op;
/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import de.tu_darmstadt.smartml.logic.SmartMLBlock;
import de.tu_darmstadt.smartml.logic.SmartMLDLTheory;
import de.tu_darmstadt.smartml.program.SmartMLProgramElement;
import org.key_project.logic.Name;
import org.key_project.logic.TermCreationException;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/// This class is used to represent a dynamic logic modality like diamond and box (but also
/// extensions of DL like preserves and throughout are possible in the future).
public class SModality extends org.key_project.logic.op.Modality {
    /// keeps track of created modalities
    private static final Map<SmartMLProgramElement, WeakHashMap<SmartMLModalityKind, WeakReference<SModality>>> modalities =
            new WeakHashMap<>();

    /// Retrieves the modality of the given useKind and program.
    ///
    /// @param kind the useKind of the modality such as diamond or box
    /// @param smartMLBlock the program of this modality
    /// @return the modality of the given useKind and program.
    public static synchronized SModality getModality(SmartMLModalityKind kind, SmartMLBlock smartMLBlock) {
        var kind2mod = modalities.get(smartMLBlock.program());
        final SModality mod;
        WeakReference<SModality> modRef;
        if (kind2mod == null) {
            kind2mod = new WeakHashMap<>();
            mod = new SModality(smartMLBlock, kind);
            modRef = new WeakReference<>(mod);
            kind2mod.put(kind, modRef);
            modalities.put(smartMLBlock.program(), kind2mod);
        } else {
            modRef = kind2mod.get(kind);
            if (modRef == null || modRef.get() == null) {
                mod = new SModality(smartMLBlock, kind);
                modRef = new WeakReference<>(mod);
                kind2mod.put(kind, modRef);
                modalities.put(smartMLBlock.program(), kind2mod);
            } else {
                mod = modRef.get();
                assert mod != null;
            }
        }
        return mod;
    }

    private final SmartMLBlock block;

    /// Creates a modal operator with the given name
    /// **Creation must only be done by ???!**
    private SModality(SmartMLBlock prg, SmartMLModalityKind kind) {
        super(kind.name(), SmartMLDLTheory.FORMULA, kind);
        this.block = prg;
    }

    @Override
    public @NonNull SmartMLBlock programBlock() {
        return block;
    }

    @Override
    public void validTopLevelException(org.key_project.logic.Term term)
            throws TermCreationException {
        if (1 != term.arity()) {
            throw new TermCreationException(this, term);
        }

        if (1 != term.subs().size()) {
            throw new TermCreationException(this, term);
        }

        if (!term.boundVars().isEmpty()) {
            throw new TermCreationException(this, term);
        }

        if (term.sub(0) == null) {
            throw new TermCreationException(this, term);
        }
    }

    public static class SmartMLModalityKind extends Kind {
        private static final Map<String, SmartMLModalityKind> kinds = new HashMap<>();
        /// The diamond operator of dynamic logic. A formula <alpha;>Phi can be read as after
        /// processing the program alpha there exists a state such that Phi holds.
        public static final SmartMLModalityKind DIA = new SmartMLModalityKind(new Name("diamond"));
        /// The box operator of dynamic logic. A formula \[alpha;]Phi can be read as 'In all states
        /// reachable processing the program alpha the formula Phi holds'.
        public static final SmartMLModalityKind BOX = new SmartMLModalityKind(new Name("box"));

        @SuppressWarnings("argument.type.incompatible")
        public SmartMLModalityKind(Name name) {
            super(name);
            kinds.put(name.toString(), this);
        }

        public static SModality.@Nullable SmartMLModalityKind getKind(String name) {
            return kinds.get(name);
        }

        /// Whether this modality is termination sensitive, i.e., it is a "diamond-useKind"
        /// modality.
        public boolean terminationSensitive() {
            return (this == DIA);
        }
    }
}
