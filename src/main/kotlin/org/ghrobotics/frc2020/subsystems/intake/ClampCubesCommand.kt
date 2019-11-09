package org.ghrobotics.frc2020.subsystems.intake

import org.ghrobotics.lib.commands.FalconCommand

/**
 * Command to hold cubes
 */
class ClampCubesCommand : FalconCommand(Intake) {
    override fun execute() {
        Intake.clampSolenoids()
    }

    override fun isFinished(): Boolean {
        return true
    }
}
