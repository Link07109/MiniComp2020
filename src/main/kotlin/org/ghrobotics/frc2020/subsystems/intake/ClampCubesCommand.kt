package org.ghrobotics.frc2020.subsystems.intake

import org.ghrobotics.frc2020.Controls
import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.wrappers.hid.getRawButton
import org.ghrobotics.lib.wrappers.hid.kY

/**
 * Command to hold cubes
 */
class ClampCubesCommand : FalconCommand(Intake) {
    override fun execute() {
        var solenoidToggle = false
        if (shouldClampCubes()) solenoidToggle = !solenoidToggle

        Intake.clampSolenoids(solenoidToggle)
    }

    override fun end(interrupted: Boolean) {

    }

    companion object {
        val shouldClampCubes = Controls.driverController.getRawButton(kY)
    }
}
