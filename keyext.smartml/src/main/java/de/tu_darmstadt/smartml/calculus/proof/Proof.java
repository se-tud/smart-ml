/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.calculus.proof;

import org.key_project.prover.proof.ProofObject;

import de.tu_darmstadt.smartml.services.Services;

/// TODO remove abstract and implement
public abstract class Proof implements ProofObject<Goal> {

    private final Services services;

    public Proof(Services services) {
        this.services = services;
    }

    @Override
    public Services getServices() {
        return services;
    }
}
