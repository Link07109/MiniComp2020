package org.ghrobotics.frc2020.subsystems.drivetrain

import org.ghrobotics.lib.commands.FalconCommand

class DriveUntilRollCommand(private val desiredRoll: Double, private val output: Double) : FalconCommand(Drivetrain) {
    override fun execute() {
        Drivetrain.arcadeDrive(output, 0.0)
    }

    override fun isFinished(): Boolean {
        return Drivetrain.navx.roll >= desiredRoll
    }

    override fun end(interrupted: Boolean) = Drivetrain.setNeutral()
}