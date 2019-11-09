package org.ghrobotics.frc2020.subsystems.intake

import org.ghrobotics.frc2020.Controls
import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.wrappers.hid.getRawButton
import org.ghrobotics.lib.wrappers.hid.kBumperLeft
import org.ghrobotics.lib.wrappers.hid.kBumperRight
import org.ghrobotics.lib.wrappers.hid.kY

/**
 * Default command that checks for input and calls other commands
 */
class DefaultIntakeCommand : FalconCommand(Intake) {
    override fun execute() {
        when {
            // Intake cubes
            shouldWheelIntake() -> IntakeCubesCommand(true).schedule()
            shouldWheelOuttake() -> IntakeCubesCommand(false).schedule()

            // Clamp cubes
            shouldClampCubes() -> ClampCubesCommand().schedule()
        }
    }

    companion object {
        val shouldWheelIntake = Controls.driverController.getRawButton(kBumperLeft)
        val shouldWheelOuttake = Controls.driverController.getRawButton(kBumperRight)

        val shouldClampCubes = Controls.driverController.getRawButton(kY)
    }
}