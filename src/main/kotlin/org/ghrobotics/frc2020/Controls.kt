package org.ghrobotics.frc2020

import org.ghrobotics.lib.wrappers.hid.xboxController

object Controls {
    /**
     * List of buttons and their usage on this robot:
     * A:
     * B:
     * X: curvature drive quick turn
     * Y: toggle clamp cubes
     * Back: toggle emergency mode (?)
     * Start:
     * Left Bumper: wheel intake
     * Right Bumper: wheel outtake
     */
    // TODO arm subsystem and presets

    val driverController = xboxController(0) { }
}
