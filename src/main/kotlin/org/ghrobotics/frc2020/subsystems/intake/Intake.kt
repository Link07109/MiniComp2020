package org.ghrobotics.frc2020.subsystems.intake

import org.ghrobotics.frc2020.Constants
import org.ghrobotics.lib.commands.FalconSubsystem
import org.ghrobotics.lib.mathematics.units.nativeunit.DefaultNativeUnitModel
import org.ghrobotics.lib.motors.ctre.FalconSRX
import org.ghrobotics.lib.wrappers.FalconSingleSolenoid
import org.ghrobotics.lib.wrappers.FalconSolenoid

/**
 * Represents the intake of the robot.
 */
object Intake : FalconSubsystem() {
    private val intakeMotor = FalconSRX(Constants.Intake.kIntakeId, DefaultNativeUnitModel)
    private val intakeSolenoid = FalconSingleSolenoid(Constants.Intake.kIntakeSolenoidId, Constants.Intake.kPCMId)

    // Spins the green compliant wheels to intake the cubes
    fun wheelIntake(percent: Double) {
        intakeMotor.setDutyCycle(percent)
    }

    // Sets the solenoids to hold the cubes in place
    fun clampSolenoids() {
        intakeSolenoid.state = when (intakeSolenoid.state) {
            FalconSolenoid.State.Forward -> FalconSolenoid.State.Reverse
            FalconSolenoid.State.Reverse -> FalconSolenoid.State.Forward
            FalconSolenoid.State.Off -> FalconSolenoid.State.Forward
        }
    }

    // Initialize follower motors and other motor configs
    init {
        with(intakeMotor) {
            brakeMode = true

            configCurrentLimit(
                    true,
                    FalconSRX.CurrentLimitConfig(
                            Constants.Intake.kPeakCurrentLimit,
                            Constants.Intake.kPeakCurrentLimitDuration,
                            Constants.Intake.kContinuousCurrentLimit
                    )
            )
        }

        defaultCommand = IntakeCommand()
    }
}