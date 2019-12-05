package org.ghrobotics.frc2020

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard
import org.ghrobotics.frc2020.subsystems.drivetrain.DriveUntilRollCommand
import org.ghrobotics.frc2020.subsystems.drivetrain.Drivetrain
import org.ghrobotics.frc2020.subsystems.drivetrain.TeeterTotterCommand
import org.ghrobotics.frc2020.subsystems.intake.Intake
import org.ghrobotics.frc2020.subsystems.winch.Winch
import org.ghrobotics.lib.commands.sequential
import org.ghrobotics.lib.wrappers.FalconTimedRobot

object Robot : FalconTimedRobot() {

    private val autoCommand = sequential {
        +DriveUntilRollCommand(6.0, 0.35)
        +TeeterTotterCommand()
    }

    // Initialize Subsystems
    init {
        +Drivetrain
        +Intake
        +Winch
    }

    // Runs once when robot boots up
    override fun robotInit() {
        Drivetrain.navx.reset()
    }

    // Runs once when autonomous period starts
    override fun autonomousInit() {
        Drivetrain.navx.reset()

        autoCommand.schedule()
    }

    // Runs once when teleop period starts
    override fun teleopInit() {
        autoCommand.cancel()
    }

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
