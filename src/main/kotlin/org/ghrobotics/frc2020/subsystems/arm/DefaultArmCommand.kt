package org.ghrobotics.frc2020.subsystems.arm

import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.mathematics.units.derived.toRotation2d

class DefaultArmCommand : FalconCommand(Arm) {
//    private var lastHoldingCargoState = false

    override fun initialize() {
//        lastHoldingCargoState = Intake.isHoldingCargo

//        val currentState = ArmSubsystem.currentState
        val currentPosition = Arm.position
//        val wantedPosition = if (currentState is ArmSubsystem.ArmState.SetPointState
//            && (currentState.position - currentPosition).value.absoluteValue <= Constants.kArmClosedLoopTolerance.value
//        ) {
//            currentState.position
//        } else {
//            currentPosition
//        }
        if (Arm.currentState !is Arm.ArmState.SetPointState) {
            Arm.wantedState = Arm.ArmState.MotionMagic(currentPosition.toRotation2d())
        }
    }

    override fun execute() {
//        val isHoldingCargo = Intake.isHoldingCargo
//        if (!lastHoldingCargoState && isHoldingCargo) {
//            Arm.wantedState = Arm.ArmState.Position(75.degree)
//        }
//        lastHoldingCargoState = isHoldingCargo
    }

}