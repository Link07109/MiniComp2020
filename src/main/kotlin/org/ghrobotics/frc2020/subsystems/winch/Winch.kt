package org.ghrobotics.frc2020.subsystems.winch

import org.ghrobotics.frc2020.Constants
import org.ghrobotics.lib.commands.FalconSubsystem
import org.ghrobotics.lib.motors.ctre.FalconSRX

/**
 * Represents the winch of the robot.
 */
object Winch : FalconSubsystem() {
    private val winchMotor = FalconSRX(Constants.Winch.kWinchId, Constants.Winch.kNativeUnitModel)

    init {
        winchMotor.brakeMode = true

        defaultCommand = WinchCommand()
    }

    fun spinWinch(percent: Double) {
        winchMotor.setDutyCycle(percent)
    }

}