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
     * Constants for general use.
     */
    const val kAccelerationDueToGravity = 9.80665
    const val kCTRETimeout = 10

    /**
     * Constants for the arm.
     */
    object Arm {
        // Motor Ids
        const val kArmId = 7

        // Motion Magic
        val kCruiseVelocity = 260.156 * 1.0.degrees.velocity
        val kAcceleration = 300.0 * 1.0.degrees.acceleration

        val kNativeUnitModel = NativeUnitRotationModel(1024.nativeUnits)

        // PID
        const val kP = 3.5
        const val kD = 700.0
        const val kF = 0.0

        // Current Limiting
        val kPeakCurrentLimit = 0.amps
        val kPeakCurrentLimitDuration = 0.milli.seconds
        val kContinuousCurrentLimit = 15.amps
    }

    /**
     * Constants for the intake.
     */
    object Intake {
        // Pneumatic Ids
        const val kPCMId = 41
        const val kIntakeSolenoid1Id = 1
        const val kIntakeSolenoid2Id = 2

        const val kIntakeSolenoid3Id = 3
        const val kIntakeSolenoid4Id = 4

        const val kIntakeSolenoid5Id = 5
        const val kIntakeSolenoid6Id = 6

        // Motor Ids
        const val kLeftWheelId = 5
        const val kRightWheelId = 6

        // Current Limiting
        val kPeakCurrentLimit = 58.amps // crossfire has no data on this
        val kPeakCurrentLimitDuration = 250.milli.seconds // crossfire has no data on this
        val kContinuousCurrentLimit = 25.amps
    }

    /**
     * Constants for the drivetrain.
     */
    object Drivetrain {
        // front left - 1 (3)	front right - 2 (2)
        // back left - 0 (1)    back right - 3 (4)

        // Motor Ids
        const val kLeftMasterId = 3
        const val kLeftSlaveId = 1
        const val kRightMasterId = 2
        const val kRightSlaveId = 4

        val kWheelRadius = 3.inches
        val kTrackWidth = 27.75.inches
        val kNativeUnitModel = NativeUnitLengthModel(1440.nativeUnits, kWheelRadius)

        // PID
        const val kP = 0.0
        const val kD = 0.0

        // Current Limiting
        val kPeakCurrentLimit = 68.amps
        val kPeakCurrentLimitDuration = 250.milli.seconds // crossfire's is 700 ms
        val kContinuousCurrentLimit = 38.amps
    }
}
