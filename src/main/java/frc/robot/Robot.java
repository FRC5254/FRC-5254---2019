/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.easypath.EasyPath;
import frc.robot.easypath.EasyPathConfig;
import frc.robot.easypath.PathUtil;
import frc.robot.subsystems.CargoMech;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchMech;
import frc.robot.utils.Limelight;
import frc.robot.utils.Limelight.CamMode;
import frc.robot.utils.Limelight.LedMode;
import frc.robot.utils.Limelight.SnapshotMode;
import frc.robot.utils.Limelight.StreamMode;
import frc.robot.subsystems.HatchFloorIntake;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  NetworkTable table;
  NetworkTableEntry tx;
  NetworkTableEntry ty;
  NetworkTableEntry ta;
  public static Drivetrain drivetrain;
  public static HatchMech hatchMech;
  public static CargoMech cargoMech;
  public static Climber climber;

  public static EasyPathConfig config;

  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    cargoMech =  CargoMech.getInstance();
    drivetrain = Drivetrain.getInstance();
    hatchMech = HatchMech.getInstance();
    climber = Climber.getInstance();

    config = new EasyPathConfig(
      drivetrain, 
      drivetrain::setLeftRightSpeeds,
      () -> PathUtil.defaultLengthDrivenEstimator(drivetrain::getLeftDistance, drivetrain::getRightDistance),
      drivetrain::getAngle,
      drivetrain::reset,
      0.07
    );
    config.setSwapDrivingDirection(false);
    config.setSwapTurningDirection(false);

    EasyPath.configure(config);
    
    m_oi = new OI(); // This one MUST be last 

    
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    //Config Limelight
    Limelight.setCamMode(CamMode.VISION_CAM); //TODO add a config funtion that incudes these
    Limelight.setLedMode(LedMode.PIPELINE);
    Limelight.setStreamMode(StreamMode.STANDARD);

    // Putting Limelight numbers onto smartdash
    SmartDashboard.putBoolean("Limelight has target?" , Limelight.hasValidTargets());
    SmartDashboard.putNumber("Limelight X", Limelight.getHorizontalOffset());
    SmartDashboard.putNumber("limelight Y", Limelight.getVerticalOffset());
    SmartDashboard.putNumber("Limelight Area", Limelight.getTargetArea());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    
    //TODO move
    limelight.setLedMode(LedMode.FORCE_OFF);
    limelight.setCamMode(CamMode.DRIVER_CAM);
    limelight.setStreamMode(StreamMode.STANDARD);

    CameraServer.getInstance().startAutomaticCapture(0);

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    SmartDashboard.putNumber("encoder left", drivetrain.leftEncoder.getDistance());
    SmartDashboard.putNumber("encoder right", drivetrain.rightEncoder.getDistance());
    SmartDashboard.putNumber("Cargo Arm tick", cargoMech.getPosition());
    SmartDashboard.putNumber("Cargo Arm angle", cargoMech.getAngle());
    SmartDashboard.putBoolean("armlimit for", cargoMech.pivotMotor.getSensorCollection().isFwdLimitSwitchClosed());
    SmartDashboard.putBoolean("amrlimit back", cargoMech.pivotMotor.getSensorCollection().isRevLimitSwitchClosed());

    SmartDashboard.putNumber("gyro", drivetrain.getAngle());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
