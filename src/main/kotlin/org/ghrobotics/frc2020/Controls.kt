package org.ghrobotics.frc2020

import org.ghrobotics.lib.wrappers.hid.xboxController

object Controls {
    /**
     * List of buttons and their usage on this robot:
     * X: curvature drive quick turn
     * Y: toggle clamp cubes
     * Left Bumper: wheel outtake
     * Right Bumper: wheel intake
     * Left Trigger: winch outtake
     * Right Trigger: winch intake
     */
    // TODO winch subsystem and presets

    val driverController = xboxController(0) { }
}
