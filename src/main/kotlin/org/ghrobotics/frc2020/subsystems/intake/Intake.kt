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
    // Create Motor
    private val intakeMaster = FalconSRX(Constants.Intake.kLeftWheelId, DefaultNativeUnitModel)

    // Create Solenoids
    private val solenoid1 = FalconSingleSolenoid(Constants.Intake.kIntakeSolenoid1Id, Constants.Intake.kPCMId)
    private val solenoid2 = FalconSingleSolenoid(Constants.Intake.kIntakeSolenoid2Id, Constants.Intake.kPCMId)
    private val solenoid3 = FalconSingleSolenoid(Constants.Intake.kIntakeSolenoid3Id, Constants.Intake.kPCMId)

    // Spins the green compliant wheels to intake the cubes
    fun wheelIntake(percent: Double) {
        intakeMaster.setDutyCycle(percent)
    }

    // Sets the solenoids to hold the cubes in place
    fun clampSolenoids() {
        listOf(solenoid1, solenoid2, solenoid3).forEach { solenoid ->
            // Set them to their opposites
            solenoid.state = when (solenoid.state) {
                FalconSolenoid.State.Forward -> FalconSolenoid.State.Reverse
                FalconSolenoid.State.Reverse -> FalconSolenoid.State.Forward
                FalconSolenoid.State.Off -> FalconSolenoid.State.Forward
            }
        }
    }

    // Initialize follower motors and other motor configs
    init {
        val intakeSlave = FalconSRX(Constants.Intake.kRightWheelId, DefaultNativeUnitModel)
        intakeSlave.follow(intakeMaster)

        intakeMaster.outputInverted = true // Assuming master is the right one
        intakeSlave.outputInverted = false

        listOf(intakeMaster, intakeSlave).forEach { motor ->
            motor.brakeMode = true

            motor.configCurrentLimit(
                    true,
                    FalconSRX.CurrentLimitConfig(
                            Constants.Intake.kPeakCurrentLimit,
                            Constants.Intake.kPeakCurrentLimitDuration,
                            Constants.Intake.kContinuousCurrentLimit
                    )
            )
        }

        defaultCommand = DefaultIntakeCommand()
    }
}