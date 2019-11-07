package org.ghrobotics.frc2020.subsystems

import org.ghrobotics.frc2020.Constants
import org.ghrobotics.frc2020.commands.IntakeCommand
import org.ghrobotics.lib.commands.FalconSubsystem
import org.ghrobotics.lib.mathematics.units.nativeunit.DefaultNativeUnitModel
import org.ghrobotics.lib.motors.ctre.FalconSRX
import org.ghrobotics.lib.wrappers.FalconSingleSolenoid
import org.ghrobotics.lib.wrappers.FalconSolenoid

object Intake : FalconSubsystem() {
    private val leftMotor = FalconSRX(Constants.Intake.kLeftId, DefaultNativeUnitModel)
    private val rightMotor = FalconSRX(Constants.Intake.kRightId, DefaultNativeUnitModel)

    private val solenoid1 = FalconSingleSolenoid(Constants.Intake.kIntakeSolenoid1Id, Constants.Intake.kPCMId)

    fun wheelIntake(percent: Double) {
        leftMotor.setDutyCycle(percent)
        rightMotor.setDutyCycle(percent)
    }

    fun shouldClampSolenoids(intake: Boolean) {
        solenoid1.state = if (intake) FalconSolenoid.State.Forward else FalconSolenoid.State.Reverse
    }

    init {
        leftMotor.outputInverted = true
        rightMotor.outputInverted = false

        defaultCommand = IntakeCommand()
    }
}