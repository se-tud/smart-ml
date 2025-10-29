/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.proof.mgt;

import org.key_project.prover.rules.RuleApp;

import de.tu_darmstadt.smartml.services.Services;

public interface ComplexRuleJustification extends RuleJustification {
    RuleJustification getSpecificJustification(RuleApp app, Services services);
}
