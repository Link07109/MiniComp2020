package org.ghrobotics.frc2020.subsystems.drivetrain

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.controller.RamseteController
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry
import org.ghrobotics.frc2020.Constants
import org.ghrobotics.lib.mathematics.units.Meter
import org.ghrobotics.lib.mathematics.units.SIUnit
import org.ghrobotics.lib.mathematics.units.nativeunit.DefaultNativeUnitModel
import org.ghrobotics.lib.motors.ctre.FalconSRX
import org.ghrobotics.lib.physics.MotorCharacterization
import org.ghrobotics.lib.subsystems.drive.FalconWestCoastDrivetrain
import org.ghrobotics.lib.utils.asSource

/**
 * Represents the drivetrain of the robot.
 */
object Drivetrain : FalconWestCoastDrivetrain() {
    // Create motors
    override val leftMotor = FalconSRX(Constants.Drivetrain.kLeftMasterId, Constants.Drivetrain.kNativeUnitModel)
    override val rightMotor = FalconSRX(Constants.Drivetrain.kRightMasterId, Constants.Drivetrain.kNativeUnitModel)

    // Path following
    override val controller = RamseteController(2.0, 0.7)
    override val kinematics = DifferentialDriveKinematics(Constants.Drivetrain.kTrackWidth.value)
    override val odometry = DifferentialDriveOdometry(kinematics)

    // Gyro
    val navx = AHRS(SPI.Port.kMXP)
    override val gyro = navx.asSource()

    // Motor characterization
    override val leftCharacterization = MotorCharacterization<Meter>(SIUnit(0.0), SIUnit(0.0), SIUnit(0.0))
    override val rightCharacterization = MotorCharacterization<Meter>(SIUnit(0.0), SIUnit(0.0), SIUnit(0.0))

    // Initialize follower motors and other motor configs
    init {
        val leftSlave = FalconSRX(Constants.Drivetrain.kLeftSlaveId, DefaultNativeUnitModel)
        val rightSlave = FalconSRX(Constants.Drivetrain.kRightSlaveId, DefaultNativeUnitModel)

        leftSlave.follow(leftMotor)
        rightSlave.follow(rightMotor)

        rightMotor.outputInverted = true
        rightSlave.outputInverted = true

        listOf(leftMotor, leftSlave, rightMotor, rightSlave).forEach {
            it.brakeMode = true

            it.configCurrentLimit(
                    true,
                    FalconSRX.CurrentLimitConfig(
                            Constants.Drivetrain.kPeakCurrentLimit,
                            Constants.Drivetrain.kPeakCurrentLimitDuration,
                            Constants.Drivetrain.kContinuousCurrentLimit
                    )
            )
        }

        defaultCommand = ManualDriveCommand()
    }

    // Emergency management
    override fun activateEmergency() {}

    override fun recoverFromEmergency() {}
}
