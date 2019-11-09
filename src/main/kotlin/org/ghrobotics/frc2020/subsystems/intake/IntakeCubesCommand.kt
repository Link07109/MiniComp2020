package org.ghrobotics.frc2020.subsystems.intake

import org.ghrobotics.lib.commands.FalconCommand

/**
 * Command to intake cubes
 */
class IntakeCubesCommand(private val intakeCubes: Boolean) : FalconCommand(Intake) {
    override fun execute() {
        val output = if (intakeCubes) 1.0 else -1.0

        Intake.wheelIntake(output)
    }

    override fun isFinished(): Boolean {
        return true
    }

    override fun end(interrupted: Boolean) {
        Intake.wheelIntake(0.0)
    }
}
