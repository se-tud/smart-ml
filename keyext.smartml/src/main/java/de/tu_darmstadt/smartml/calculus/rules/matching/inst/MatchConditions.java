/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.rules.matching.inst;

import org.key_project.prover.rules.instantiation.MatchResultInfo;
import org.key_project.prover.rules.instantiation.SVInstantiations;

public class MatchConditions extends MatchResultInfo {

    public MatchConditions(SVInstantiations pInstantiations) {
        super(pInstantiations);
    }

    @Override
    public de.tu_darmstadt.smartml.calculus.rules.matching.inst.SVInstantiations getInstantiations() {
        return (de.tu_darmstadt.smartml.calculus.rules.matching.inst.SVInstantiations) super.getInstantiations();
    }

    @Override
    public MatchConditions setInstantiations(SVInstantiations p_instantiations) {
        return new MatchConditions(p_instantiations);
    }
}
