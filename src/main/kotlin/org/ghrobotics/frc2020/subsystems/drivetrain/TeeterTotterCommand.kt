package org.ghrobotics.frc2020.subsystems.drivetrain

import org.ghrobotics.lib.commands.FalconCommand

/**
 * Command to auto-balance on the teeter totter
 */
class TeeterTotterCommand : FalconCommand(Drivetrain) {
    override fun execute() {
        println(Drivetrain.navx.pitch)
    }

}