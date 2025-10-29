/* This file is part of KeY - https://key-project.org
 * KeY is licensed under the GNU General Public License Version 2
 * SPDX-License-Identifier: GPL-2.0-only */
package de.tu_darmstadt.smartml.strategy;

import org.key_project.logic.Name;
import org.key_project.logic.Named;

public class Strategy<T> implements Named {
    public Name name() {
        throw new RuntimeException("Not implemented yet");
    }
}
