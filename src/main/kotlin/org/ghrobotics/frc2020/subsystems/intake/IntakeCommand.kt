package org.ghrobotics.frc2020.subsystems.intake

import org.ghrobotics.frc2020.Controls
import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.wrappers.hid.getRawButton
import org.ghrobotics.lib.wrappers.hid.kBumperLeft
import org.ghrobotics.lib.wrappers.hid.kBumperRight
import org.ghrobotics.lib.wrappers.hid.kY

/**
 * Command for the intake subsystem
 */
class IntakeCommand : FalconCommand(Intake) {
    override fun execute() {
        val intakeOutput = if (shouldWheelIntake()) 1.0 else if (shouldWheelOuttake()) -1.0 else 0.0
        Intake.wheelIntake(intakeOutput)

        if (shouldClampCubes()) Intake.clampSolenoids()
    }

    companion object {
        val shouldWheelIntake = Controls.driverController.getRawButton(kBumperLeft) // hold
        val shouldWheelOuttake = Controls.driverController.getRawButton(kBumperRight) // hold

        val shouldClampCubes = Controls.driverController.getRawButton(kY) // press once
    }
}