package org.ghrobotics.frc2020.subsystems.arm

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.ctre.phoenix.motorcontrol.StatusFrame
import edu.wpi.first.wpilibj.geometry.Rotation2d
import org.ghrobotics.frc2020.Constants
import org.ghrobotics.lib.commands.FalconSubsystem
import org.ghrobotics.lib.mathematics.units.derived.degrees
import org.ghrobotics.lib.mathematics.units.derived.radians
import org.ghrobotics.lib.mathematics.units.derived.velocity
import org.ghrobotics.lib.mathematics.units.derived.volts
import org.ghrobotics.lib.motors.ctre.FalconSRX
import org.ghrobotics.lib.subsystems.EmergencyHandleable
import org.ghrobotics.lib.utils.Source

/**
 * Represents the arm of the robot.
 */
object Arm : FalconSubsystem(), EmergencyHandleable { // this is based on crossfire's arm code
    private val armMotor = FalconSRX(Constants.Arm.kArmId, Constants.Arm.kNativeUnitModel)

    var wantedState: ArmState = ArmState.Nothing
    var currentState: ArmState = ArmState.Nothing
        private set

    // PERIODIC
    var position = 0.degrees
        private set
    var velocity = 0.degrees.velocity
        private set
    var arbitraryFeedForward = 0.0.volts
        private set

    // Initialize motor configs
    init {
        with(armMotor) {
            outputInverted = true
            brakeMode = true

            // Motion magic
            motionProfileCruiseVelocity = Constants.Arm.kCruiseVelocity
            motionProfileAcceleration = Constants.Arm.kAcceleration

            // Configure Encoder
//            feedbackSensor = FeedbackDevice.Analog
//            talonSRX.setSensorPhase(false)

            // Analog encoder hackery
            armMotor.talonSRX.configFeedbackNotContinuous(true, Constants.kCTRETimeout)

            armMotor.talonSRX.configForwardSoftLimitThreshold(220)
            armMotor.talonSRX.configForwardSoftLimitEnable(false)

            armMotor.talonSRX.configReverseSoftLimitThreshold (-40)
            armMotor.talonSRX.configReverseSoftLimitEnable(false)

            talonSRX.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 10)
            talonSRX.selectProfileSlot(0, 0)

            configCurrentLimit(
                    true,
                    FalconSRX.CurrentLimitConfig(
                            Constants.Arm.kPeakCurrentLimit,
                            Constants.Arm.kPeakCurrentLimitDuration,
                            Constants.Arm.kContinuousCurrentLimit
                    )
            )
        }

        defaultCommand = DefaultArmCommand()
    }

    /**
     * Runs periodically.
     * Used to calculate the acceleration of the arm.
     */
    override fun periodic() {
//        position = armMotor.talonSRX.selectedSensorPosition.radians
//        velocity = armMotor.talonSRX.selectedSensorVelocity.radians.velocity

//        arbitraryFeedForward = if (!Robot.emergencyActive) {
//            val experiencedAcceleration = Constants.kAccelerationDueToGravity
//
//            val Kg = if (Intake.isHoldingHatch) {
//                Constants.kArmHatchKg
//            } else Constants.kArmEmptyKg
//
//            Kg * position.cos * experiencedAcceleration
//        } else {
//            0.0.volts
//        }

        // UPDATE STATE
        val wantedState = this.wantedState
        this.currentState = wantedState
        when (wantedState) {
            is ArmState.Nothing -> armMotor.setNeutral()
            is ArmState.MotionMagic -> armMotor.setPosition(wantedState.position.radians.radians, arbitraryFeedForward)
            is ArmState.OpenLoop -> {
                if (wantedState.useFeedForward) armMotor.setDutyCycle(wantedState.output(), arbitraryFeedForward)
                else armMotor.setDutyCycle(wantedState.output())
            }
        }
    }

    // Emergency Management
    override fun activateEmergency() {}

    override fun recoverFromEmergency() {}

    sealed class ArmState {
        object Nothing : ArmState()
        abstract class SetPointState(val position: Rotation2d) : ArmState()
        class MotionMagic(position: Rotation2d) : SetPointState(position)
        class OpenLoop(val output: Source<Double>, val useFeedForward: Boolean) : ArmState()
    }

}