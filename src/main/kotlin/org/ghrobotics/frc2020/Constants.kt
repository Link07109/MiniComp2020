/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * Copyright 2019, Green Hope Falcons
 */

package org.ghrobotics.frc2020

import org.ghrobotics.lib.mathematics.units.amps
import org.ghrobotics.lib.mathematics.units.inches
import org.ghrobotics.lib.mathematics.units.milli
import org.ghrobotics.lib.mathematics.units.nativeunit.NativeUnitLengthModel
import org.ghrobotics.lib.mathematics.units.nativeunit.nativeUnits

@Suppress("MemberVisibilityCanBePrivate", "unused")
/**
 * Contains all the constants for the robot.
 */
object Constants {
    /**
     * Constants for the intake.
     */
    object Intake {
        const val kPCMId = 41
        const val kIntakeSolenoid1Id = 1
        const val kIntakeSolenoid2Id = 2
        const val kIntakeSolenoid3Id = 3
        const val kIntakeSolenoid4Id = 4
        const val kIntakeSolenoid5Id = 5
        const val kIntakeSolenoid6Id = 6

        const val kLeftId = 5
        const val kRightId = 6
    }

    /**
     * Constants for the drivetrain.
     */
    object Drivetrain {
        // front left - 1	(3)	front right - 2 (2)
        //back left - 0	(1)	back right - 3 (4)
        const val kLeftMasterId = 3
        const val kLeftSlave1Id = 1
        const val kRightMasterId = 2
        const val kRightSlave1Id = 4

        val kWheelRadius = 3.inches
        val kTrackWidth = 27.75.inches
        val kNativeUnitModel = NativeUnitLengthModel(1440.nativeUnits, kWheelRadius)

        val kPeakCurrentLimit = 68.amps
        val kPeakCurrentLimitDuration = 250.milli.seconds
        val kContinuousCurrentLimit = 38.amps
    }
}
