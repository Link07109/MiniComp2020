package org.ghrobotics.frc2020.subsystems.drivetrain

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.ghrobotics.frc2020.Constants
import org.ghrobotics.lib.commands.FalconCommand

/**
 * Command to auto-balance on the teeter totter
 */
class TeeterTotterCommand : FalconCommand(Drivetrain) {
    private val setpoint = 0.0 // Drivetrain.navx.roll

    private var output = 0.0
    private var error = 0.0
    private var prevError = 0.0

    private var p = 0.0
//    private var d = 0.0

    override fun execute() {
        error = setpoint - Drivetrain.navx.roll

        p = Constants.Drivetrain.kP * error
//        d = Constants.Drivetrain.kD * (error - prevError) / 0.02 // 20ms

        output = p //+ d
        prevError = error

        Drivetrain.arcadeDrive(-output, 0.0)

        println("output: $output")
        println("error: $error")
        SmartDashboard.putNumber("navx roll:", Drivetrain.navx.roll.toDouble())
    }

    override fun isFinished(): Boolean {
        return false // abs(output) <= 2
    }

    override fun end(interrupted: Boolean) = Drivetrain.setNeutral()

}