package org.ghrobotics.frc2020

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard
import org.ghrobotics.frc2020.subsystems.arm.Arm
import org.ghrobotics.frc2020.subsystems.drivetrain.DriveUntilRollCommand
import org.ghrobotics.frc2020.subsystems.drivetrain.Drivetrain
import org.ghrobotics.frc2020.subsystems.drivetrain.TeeterTotterCommand
import org.ghrobotics.frc2020.subsystems.intake.Intake
import org.ghrobotics.lib.commands.sequential
import org.ghrobotics.lib.wrappers.FalconTimedRobot

object Robot : FalconTimedRobot() {

    // Initialize Subsystems
    init {
        +Drivetrain
        +Intake
        +Arm
    }

    // Runs once when robot boots up
    override fun robotInit() {
        Drivetrain.navx.reset()
    }

    // Runs once when autonomous period starts
    override fun autonomousInit() {
        Drivetrain.navx.reset()

        sequential {
            +DriveUntilRollCommand(6.0, 0.35)
            +TeeterTotterCommand()
        }
    }

    // Runs once when teleop period starts
    override fun teleopInit() {}

    // Runs once when robot is disabled
    override fun disabledInit() {}

    // Runs every 20 ms when robot is on
    override fun robotPeriodic() {
        Shuffleboard.update()
    }

    // Runs every 20 ms when autonomous is enabled
    override fun autonomousPeriodic() {}

    // Runs every 20 ms when teleop is enabled
    override fun teleopPeriodic() {}

    // Runs every 20 ms when robot is disabled
    override fun disabledPeriodic() {}
}
