/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.services;

import de.tu_darmstadt.smartml.calculus.proof.BuiltInRuleIndex;
import de.tu_darmstadt.smartml.calculus.proof.ProofSettings;
import de.tu_darmstadt.smartml.calculus.proof.TacletIndex;
import de.tu_darmstadt.smartml.calculus.proof.mgt.RuleJustificationInfo;

public class InitConfig {
    private RuleJustificationInfo justifInfo = new RuleJustificationInfo();

    public RuleJustificationInfo getJustifInfo() {
        return justifInfo;
    }

    public ProofSettings getSettings() {
        throw new RuntimeException("Not yet implemented");
    }

    public Services getServices() {
        throw new RuntimeException("Not yet implemented");
    }

    public void setSettings(ProofSettings proofSettings) {
        throw new RuntimeException("Not yet implemented");
    }

    public TacletIndex createTacletIndex() {
        throw new RuntimeException("Not yet implemented");
    }

    public BuiltInRuleIndex createBuiltInRuleIndex() {
        throw new RuntimeException("Not yet implemented");
    }
}
