package org.ghrobotics.frc2020

import org.ghrobotics.lib.mathematics.units.amps
import org.ghrobotics.lib.mathematics.units.derived.acceleration
import org.ghrobotics.lib.mathematics.units.derived.degrees
import org.ghrobotics.lib.mathematics.units.derived.velocity
import org.ghrobotics.lib.mathematics.units.inches
import org.ghrobotics.lib.mathematics.units.milli
import org.ghrobotics.lib.mathematics.units.nativeunit.NativeUnitLengthModel
import org.ghrobotics.lib.mathematics.units.nativeunit.NativeUnitRotationModel
import org.ghrobotics.lib.mathematics.units.nativeunit.nativeUnits
import org.ghrobotics.lib.mathematics.units.operations.times

@Suppress("MemberVisibilityCanBePrivate", "unused")
/**
 * Contains all the constants for the robot.
 */
object Constants {
    /**
     * Constants for the winch.
     */
    object Winch {
        // Motor Ids
        const val kWinchId = 5

        val kNativeUnitModel = NativeUnitRotationModel(1024.nativeUnits) // temp

        // Current Limiting
        // based on falcon heavy climber
//        val kPeakCurrentLimit = 0.amps
//        val kPeakCurrentLimitDuration = 0.milli.seconds
//        val kContinuousCurrentLimit = 40.amps
    }

    /**
     * Constants for the intake.
     */
    object Intake {
        // Pneumatic Ids
        const val kPCMId = 41
        const val kIntakeSolenoidId = 0

        // Motor Ids
        const val kIntakeId = 6
        // Current Limiting
        val kPeakCurrentLimit = 58.amps // crossfire has no data on this
        val kPeakCurrentLimitDuration = 250.milli.seconds // crossfire has no data on this
        val kContinuousCurrentLimit = 25.amps
    }

    /**
     * Constants for the drivetrain.
     */
    object Drivetrain {
        // Motor Ids
        const val kLeftMasterId = 3
        const val kLeftSlaveId = 1
        const val kRightMasterId = 2
        const val kRightSlaveId = 4

        val kWheelRadius = 3.inches
        val kTrackWidth = 27.75.inches
        val kNativeUnitModel = NativeUnitLengthModel(1440.nativeUnits, kWheelRadius)

        // PID
        const val kP = 0.025
        const val kD = 0.001

        // Current Limiting
        val kPeakCurrentLimit = 68.amps
        val kPeakCurrentLimitDuration = 250.milli.seconds // crossfire's is 700 ms
        val kContinuousCurrentLimit = 38.amps
    }
}
