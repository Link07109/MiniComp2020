package org.ghrobotics.frc2020.subsystems.arm

import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.utils.DoubleSource
import org.ghrobotics.lib.utils.Source

class ManualArmCommand(private val percentOutput: DoubleSource) : FalconCommand(Arm) {
    constructor(percentOutput: Double) : this(Source(percentOutput))

    override fun initialize() {
        Arm.wantedState = Arm.ArmState.OpenLoop(percentOutput, true)
    }
}