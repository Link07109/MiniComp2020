package org.ghrobotics.frc2020.subsystems.arm
//
//import edu.wpi.first.wpilibj.geometry.Rotation2d
//import org.ghrobotics.frc2020.Constants
//import org.ghrobotics.lib.commands.FalconCommand
//import org.ghrobotics.lib.mathematics.units.derived.radians
//import org.ghrobotics.lib.mathematics.units.derived.toRotation2d
//
//class ClosedLoopArmCommand(private val target: Rotation2d) : FalconCommand(Arm) {
//    init {
//        finishCondition += {
//            (Arm.position - target.radians.radians).absoluteValue < Constants.Arm.kArmClosedLoopTolerance &&
//                    Arm.velocity.absoluteValue < Constants.Arm.kArmClosedLoopVelocityTolerance
//        }
//    }
//
//    override fun initialize() {
//        Arm.wantedState = Arm.ArmState.MotionMagic(target)
//    }
//}