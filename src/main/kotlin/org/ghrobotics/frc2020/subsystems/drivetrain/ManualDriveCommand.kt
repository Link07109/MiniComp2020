package org.ghrobotics.frc2020.subsystems.drivetrain

import edu.wpi.first.wpilibj.GenericHID
import org.ghrobotics.frc2020.Controls
import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.wrappers.hid.getRawButton
import org.ghrobotics.lib.wrappers.hid.getX
import org.ghrobotics.lib.wrappers.hid.getY
import org.ghrobotics.lib.wrappers.hid.kX

/**
 * Command to drive the robot using the Xbox controller in teleop.
 */
class ManualDriveCommand : FalconCommand(Drivetrain) {
    override fun execute() {
        Drivetrain.curvatureDrive(-linearSource(), curvatureSource(), quickTurnSource())
    }

    companion object {
        val linearSource = Controls.driverController.getY(GenericHID.Hand.kLeft)
        val curvatureSource = Controls.driverController.getX(GenericHID.Hand.kLeft)
        val quickTurnSource = Controls.driverController.getRawButton(kX)
    }
}
