package org.ghrobotics.frc2020.subsystems.winch

import org.ghrobotics.frc2020.Controls
import org.ghrobotics.lib.commands.FalconCommand

/**
 * Command for the winch subsystem
 */
class WinchCommand : FalconCommand(Winch) {

    override fun execute() {
        val intake = (shouldWinchIntake() > 0)
        val outtake = (shouldWinchOuttake() > 0)
        Winch.spinWinch(if (intake) shouldWinchIntake() else if (outtake) -shouldWinchOuttake() else 0.0)
    }

    companion object {
        val shouldWinchIntake = Controls.driverController.getRawAxis(3) // hold
        val shouldWinchOuttake = Controls.driverController.getRawAxis(2) // hold
    }
}