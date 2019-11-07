package org.ghrobotics.frc2020.subsystems.intake

import org.ghrobotics.frc2020.Controls
import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.wrappers.hid.getRawButton
import org.ghrobotics.lib.wrappers.hid.kBumperLeft
import org.ghrobotics.lib.wrappers.hid.kBumperRight

/**
 * Command to intake cubes
 */
class IntakeCubesCommand : FalconCommand(Intake) {
    override fun execute() {
        val speed = if (shouldWheelIntake()) 1.0 else if (shouldWheelOuttake()) -1.0 else 0.0

        Intake.wheelIntake(speed)
    }

    companion object {
        val shouldWheelIntake = Controls.driverController.getRawButton(kBumperLeft)
        val shouldWheelOuttake = Controls.driverController.getRawButton(kBumperRight)
    }
}
