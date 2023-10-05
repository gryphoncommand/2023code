// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ArmMove;
import frc.robot.subsystems.Claw;

import com.revrobotics.jni.RevJNIWrapper;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CompressorConfigType;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
/**
 * Uses the CameraServer class to automatically capture video from a USB webcam and send it to the
 * FRC dashboard without doing any vision processing. This is the easiest way to get camera images
 * to the dashboard. Just add this to the robotInit() method in your program.
 */


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drivetrain drivetrain = new Drivetrain();
  private final ArmMove armMove = new ArmMove();
  private final Claw claw = new Claw();
  private final Compressor compress = new Compressor(PneumaticsModuleType.REVPH);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final PS4Controller controller = new PS4Controller(0);

  boolean driveable = false;

  private boolean open = false;
      

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureButtonBindings();
    configureDefaultCommands();



    CameraServer.startAutomaticCapture();


  }

  /**
   * Use this me
   * 
   * 
   
   
   thod to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureButtonBindings() {}

  private void configureDefaultCommands(){
    drivetrain.setDefaultCommand(drive);
    claw.setDefaultCommand(grab);
    armMove.setDefaultCommand(elevat);
  }

  //Teleop functionality
  private RunCommand drive = new RunCommand(
    () -> {

      if (controller.getCircleButton()){
        drivetrain.sneakDrive(controller.getLeftY());
      } else {
        drivetrain.arcadeDrive(-controller.getLeftY(), -controller.getRightX());
      }
      System.out.println(controller.getLeftY());
      System.out.println(controller.getRightX());

      if (controller.getTriangleButtonPressed()){
        drivetrain.gearShift();
      }

  
    },drivetrain);

    private RunCommand elevat = new RunCommand(
      () -> {
        if (controller.getPOV() == 0){
          armMove.armIn();
          System.out.println("lol");
        } else if (controller.getPOV() == 180){
          armMove.armOut();
          System.out.println("lollol");
        } else {
          armMove.stop();
        }

      },armMove);

    private RunCommand grab = new RunCommand(() -> {
      if (controller.getCrossButtonPressed()){
        if (open){
          claw.claw_open();
        } else {
          claw.claw_open();
        }
        open = !open;
      }
      
    }
    , claw);



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous

  return new SequentialCommandGroup(
     new RunCommand(() -> {
       drivetrain.arcadeDrive(0, -0.7);
     }, drivetrain). withTimeout(1.35),

     new WaitCommand(1),

     new RunCommand(() -> {
       drivetrain.arcadeDrive(0, 0.5);
     }, drivetrain). withTimeout(8)
    );

   /* if (driveable) {
      return new SequentialCommandGroup(
        new RunCommand(() -> {
          grabber.gogoextendo();
        }, grabber).withTimeout(1),

        new RunCommand(() -> {
          grabber.grabbber_open();
        }, grabber),

        new WaitCommand(1),

        new RunCommand(() -> {
          grabber.grabbbber_close();
          grabber.odnetxeogog();
        }, grabber).withTimeout(1.2),

        new RunCommand(() -> {
          drivetrain.arcadeDrive(0, -0.5);
        }, drivetrain). withTimeout(3)
      );
    } else {
      return new SequentialCommandGroup(
        new RunCommand(() -> {
          grabber.gogoextendo();
        }, grabber).withTimeout(0.2),

        new WaitCommand(0.1),

        new RunCommand(() -> {
          grabber.grabbber_open();
        }, grabber),

        new WaitCommand(0.2),

        new RunCommand(() -> {
          grabber.grabbbber_close();
          grabber.odnetxeogog();
        }, grabber).withTimeout(0.75)
      );
    }*/
  }
}

  
