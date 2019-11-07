package org.ghrobotics.frc2020.subsystems.intake

import org.ghrobotics.frc2020.Controls
import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.wrappers.hid.getRawButton
import org.ghrobotics.lib.wrappers.hid.kBumperLeft
import org.ghrobotics.lib.wrappers.hid.kBumperRight
import org.ghrobotics.lib.wrappers.hid.kY

/**
 * Command to intake and hold cubes
 */
class IntakeAndClampCommand : FalconCommand(Intake) {
    override fun execute() {
        // intake cubes part
        val speed = if (shouldWheelIntake()) 1.0 else if (shouldWheelOuttake()) -1.0 else 0.0

        Intake.wheelIntake(speed)

        // clamp cubes part
        var solenoidToggle = false
        if (shouldClampCubes()) solenoidToggle = !solenoidToggle

        Intake.clampSolenoids(solenoidToggle)
    }

    companion object {
        val shouldWheelIntake = Controls.driverController.getRawButton(kBumperLeft)
        val shouldWheelOuttake = Controls.driverController.getRawButton(kBumperRight)

        val shouldClampCubes = Controls.driverController.getRawButton(kY)
    }
}