// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  //public static CTREConfigs ctreConfigs;

  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private final XboxController ControllerSecondary = new XboxController(1);
  public ChassisSpeeds m_speeds;
  


  //elevator motors are brushed
  CANSparkMax elevatorRight = new CANSparkMax(Constants.ELEVATOR_RIGHT_MOTOR,MotorType.kBrushed);
  CANSparkMax elevatorLeft = new CANSparkMax(Constants.ELEVATOR_LEFT_MOTOR,MotorType.kBrushed);

  CANSparkMax clawRight = new CANSparkMax(Constants.CLAW_RIGHT_MOTOR, MotorType.kBrushless);
  CANSparkMax clawLeft = new CANSparkMax(Constants.CLAW_LEFT_MOTOR, MotorType.kBrushless);

  CANSparkMax clawRotation = new CANSparkMax(Constants.CLAW_ROTATION_MOTOR, MotorType.kBrushless);

  /*
  CANSparkMax drivetrainFrontLeft = new CANSparkMax(Constants.FRONT_LEFT_MODULE_DRIVE_MOTOR, MotorType.kBrushless);
  CANSparkMax drivetrainBackLeft = new CANSparkMax(Constants.BACK_LEFT_MODULE_DRIVE_MOTOR, MotorType.kBrushless);
  CANSparkMax drivetrainFrontRight = new CANSparkMax(Constants.FRONT_RIGHT_MODULE_DRIVE_MOTOR, MotorType.kBrushless);
  CANSparkMax drivetrainBackRight = new CANSparkMax(Constants.BACK_RIGHT_MODULE_DRIVE_MOTOR, MotorType.kBrushless);  
*/

  // DrivetrainSubsystem driver = new DrivetrainSubsystem();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    //ctreConfigs = new CTREConfigs();

    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

/*
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
*/
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setValue(1);
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    
    
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
  
  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    /*
    drivetrainFrontLeft.set(0.1);
    drivetrainFrontRight.set(0.1);
    drivetrainBackLeft.set(0.1);
    drivetrainBackRight.set(0.1);
    */
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    /*
    m_speeds.omegaRadiansPerSecond = XControl.getRightX()*MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;
    m_speeds.vxMetersPerSecond = XControl.getLeftX()*MAX_VELOCITY_METERS_PER_SECOND;
    m_speeds.vyMetersPerSecond = XControl.getLeftY()*MAX_VELOCITY_METERS_PER_SECOND;
    */
    // driver.drive(m_speeds);

    //Move Elevator UP - Y
    /*
    if (RobotContainer.controller.getRawButtonPressed(Constants.Y_BTN)) {
      elevatorRight.set(0.3);
      elevatorLeft.set(-0.3);
    }
    if (RobotContainer.controller.getRawButtonReleased(Constants.Y_BTN)) {
      elevatorRight.set(0.0);
      elevatorLeft.set(0.0);
    }
    */


    if (ControllerSecondary.getRawButtonPressed(Constants.Y_BTN)) {
      elevatorRight.set(0.3);
      elevatorLeft.set(-0.3);
    }
    if (ControllerSecondary.getRawButtonReleased(Constants.Y_BTN)) {
      elevatorRight.set(0.0);
      elevatorLeft.set(0.0);
    }
    
    

    //Move Elevator DOWN - A
    /*
    if (RobotContainer.controller.getRawButtonPressed(Constants.A_BTN)) {
      elevatorRight.set(-0.3);
      elevatorLeft.set(0.3);
    }
    if (RobotContainer.controller.getRawButtonReleased(Constants.A_BTN)) {
      elevatorRight.set(0.0);
      elevatorLeft.set(0.0);
    }
    */
    
    if (ControllerSecondary.getRawButtonPressed(Constants.A_BTN)) {
      elevatorRight.set(-0.3);
      elevatorLeft.set(0.3);
    }
    if (ControllerSecondary.getRawButtonReleased(Constants.A_BTN)) {
      elevatorRight.set(0.0);
      elevatorLeft.set(0.0);
    }
    

    //Claw Outtake - Button B
    /*
    if (RobotContainer.controller.getRawButtonPressed(Constants.B_BTN)) {
      clawRight.set(0.6);
      clawLeft.set(0.6);
    }
    if (RobotContainer.controller.getRawButtonReleased(Constants.B_BTN)) {
      clawRight.set(0.0);
      clawLeft.set(0.0);
    }
    */
    
    if (ControllerSecondary.getRawButtonPressed(Constants.B_BTN)) {
      clawRight.set(0.6);
      clawLeft.set(0.6);
    }
    if (ControllerSecondary.getRawButtonReleased(Constants.B_BTN)) {
      clawRight.set(0.0);
      clawLeft.set(0.0);
    }
    

    //Claw Intake - Button X
    /*
    if (RobotContainer.controller.getRawButtonPressed(Constants.X_BTN)) {
      clawRight.set(-0.6);
      clawLeft.set(-0.6);
    }
    if (RobotContainer.controller.getRawButtonReleased(Constants.X_BTN)) {
      clawRight.set(0.0);
      clawLeft.set(0.0);
    }
    */

    
    if (ControllerSecondary.getRawButtonPressed(Constants.X_BTN)) {
      clawRight.set(-0.6);
      clawLeft.set(-0.6);
    }
    if (ControllerSecondary.getRawButtonReleased(Constants.X_BTN)) {
      clawRight.set(0.0);
      clawLeft.set(0.0);
    }
    

    //Claw Rotate (Up) - Right Bumper
    /*
    if(RobotContainer.controller.getRawButtonPressed(Constants.RIGHT_BUMPER_BTN)) {
      //check to make sure clockwise
      clawRotation.set(0.3);
    }
    if(RobotContainer.controller.getRawButtonReleased(Constants.RIGHT_BUMPER_BTN)) {
      clawRotation.set(0.0);
    }
    */

    
    if(ControllerSecondary.getRawButtonPressed(Constants.RIGHT_BUMPER_BTN)) {
      clawRotation.set(0.3);
    }
    if(ControllerSecondary.getRawButtonReleased(Constants.RIGHT_BUMPER_BTN)) {
      clawRotation.set(0.0);
    }
    
    //Claw Rotate (Down) - Left Bumper
    /*
    if(RobotContainer.controller.getRawButtonPressed(Constants.LEFT_BUMPER_BTN)) {
      //check to make sure counterclockwise
      clawRotation.set(-0.3);
      //clawRight.set(-0.15);
      //clawLeft.set(-0.15);
    }
    if(RobotContainer.controller.getRawButtonReleased(Constants.LEFT_BUMPER_BTN)) {
      //set rotate motor to counterclockwise rotation
      clawRotation.set(0.0);
      //clawRight.set(0.0);
      //clawLeft.set(0.0);
    }
    */

    
    if(ControllerSecondary.getRawButtonPressed(Constants.LEFT_BUMPER_BTN)) {
      clawRotation.set(-0.3);
    }
    if(ControllerSecondary.getRawButtonReleased(Constants.LEFT_BUMPER_BTN)) {
      clawRotation.set(0.0);
    }
    

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic()
  {
  }
}
