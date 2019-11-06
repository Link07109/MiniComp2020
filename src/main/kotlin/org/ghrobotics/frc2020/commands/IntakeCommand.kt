package org.ghrobotics.frc2020.commands

import org.ghrobotics.frc2020.Controls
import org.ghrobotics.frc2020.subsystems.Intake
import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.wrappers.hid.*

class IntakeCommand : FalconCommand(Intake) {
    override fun execute() {
        val speed = if (shouldWheelIntake()) 0.5 else 0.0

        Intake.wheelIntake(speed)
    }

    companion object {
        val shouldWheelIntake = Controls.driverController.getRawButton(kX)
    }
}
