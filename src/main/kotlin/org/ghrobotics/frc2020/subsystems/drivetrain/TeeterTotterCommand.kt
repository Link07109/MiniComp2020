package org.ghrobotics.frc2020.subsystems.drivetrain

import org.ghrobotics.frc2020.Constants
import org.ghrobotics.lib.commands.FalconCommand

/**
 * Command to auto-balance on the teeter totter
 */
class TeeterTotterCommand : FalconCommand(Drivetrain) {
    private val setpoint = 0.0

    var pitchVoltage = 0.0
    var previousPitchError = 0.0

    override fun initialize() = Drivetrain.navx.reset()

    override fun execute() {
        // Depending on navx orientation on the robot it can be either pitch or roll
        // Error will be negative going forwards, positive going backwards
        val pitchError = setpoint - Drivetrain.navx.pitch

        val pitchP = Constants.Drivetrain.kP * pitchError
        val pitchD = Constants.Drivetrain.kD * (pitchError - previousPitchError) / 0.02

        pitchVoltage = pitchP + pitchD
        previousPitchError = pitchError

        Drivetrain.setVoltage(-pitchVoltage, -pitchVoltage)

        println("pitch voltage: $pitchVoltage")
        println("navx pitch: ${Drivetrain.navx.pitch}")
    }

    override fun isFinished(): Boolean {
        return false // abs(pitchVoltage) <= 5
    }

    override fun end(interrupted: Boolean) = Drivetrain.setNeutral()

}