package org.ghrobotics.frc2020.subsystems.drivetrain

import org.ghrobotics.frc2020.Constants
import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.mathematics.units.derived.acceleration
import org.ghrobotics.lib.mathematics.units.derived.velocity
import org.ghrobotics.lib.mathematics.units.meters
import kotlin.math.absoluteValue

/**
 * Command to auto-balance on the teeter totter
 */
class TeeterTotterCommand : FalconCommand(Drivetrain) {
    private val setpoint = 0.6 // Drivetrain.navx.roll

    private var output = 0.0
    private var error = 0.0
    private var prevError = 0.0

    override fun execute() {
        error = setpoint - Drivetrain.navx.roll
        output = (Constants.Drivetrain.kP * error) + (Constants.Drivetrain.kD * ((error - prevError) / .02))
        prevError = error

        println("output: $output")
        Drivetrain.arcadeDrive(-output, 0.0)
    }

    override fun isFinished(): Boolean {
//        return output.absoluteValue <= 1
        return false
    }

    override fun end(interrupted: Boolean) = Drivetrain.setNeutral()

}