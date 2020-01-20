/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autos.CenterHatchCargoDepo;
import frc.robot.autos.CenterHatchFeederStation;
import frc.robot.autos.CenterHatchPlace;
import frc.robot.autos.CrossHabline;
import frc.robot.autos.SidePanelAuto;
import frc.robot.easypath.EasyPath;
import frc.robot.easypath.EasyPathConfig;
import frc.robot.easypath.FollowPath;
import frc.robot.easypath.Path;
import frc.robot.easypath.PathUtil;
import frc.robot.easypath.Paths;
import frc.robot.subsystems.CargoMechArm;
import frc.robot.subsystems.CargoMechIntake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchMech;
import frc.robot.utils.Limelight;
import frc.robot.utils.Limelight.CamMode;
import frc.robot.utils.Limelight.LedMode;
import frc.robot.utils.Limelight.Pipeline;
import frc.robot.utils.Limelight.StreamMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static Drivetrain drivetrain;
  public static HatchMech hatchMech;
  public static CargoMechArm cargoMechArm;
  public static CargoMechIntake cargoMechIntake;
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
    cargoMechArm =  CargoMechArm.getInstance();
    cargoMechIntake = CargoMechIntake.getInstance();
    drivetrain = Drivetrain.getInstance();
    hatchMech = HatchMech.getInstance();
    climber = Climber.getInstance();

    config = new EasyPathConfig(
      drivetrain, 
      drivetrain::setLeftRightSpeeds,
      () -> PathUtil.defaultLengthDrivenEstimator(drivetrain::getLeftDistance, drivetrain::getRightDistance),
      drivetrain::getAngle,
      drivetrain::reset,
      0.037
      // 0.015
    );
    config.setSwapDrivingDirection(true);
    config.setSwapTurningDirection(false); //false for cargo mech 

    EasyPath.configure(config);

    Limelight.setLedMode(LedMode.FORCE_OFF);

    //Config Limelight
    Limelight.setPipeline(Pipeline.PIPELINE0);
    Limelight.setCamMode(CamMode.VISION_CAM); //TODO add a config funtion that incudes these
    Limelight.setLedMode(LedMode.PIPELINE);
    Limelight.setStreamMode(StreamMode.STANDARD);

    
    m_oi = new OI(); // This one MUST be last 

    SmartDashboard.putBoolean("init", true);
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
    // Double[] camtran = Limelight.getCamtran(); // TODO enable high res mode
    // SmartDashboard.putNumber("translationX", camtran[0]);
    // SmartDashboard.putNumber("translationy1", camtran[1]);
    // SmartDashboard.putNumber("translationy2", camtran[2]);
    // SmartDashboard.putNumber("pitch", camtran[3]);
    // SmartDashboard.putNumber("yaw", camtran[4]);
    // SmartDashboard.putNumber("roll", camtran[5]);
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    drivetrain.setDisabledDrive();
    Limelight.setLedMode(LedMode.FORCE_OFF);
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

    drivetrain.setAutoDrive();
    Limelight.setCamMode(CamMode.VISION_CAM); //TODO add a config funtion that incudes these
    Limelight.setLedMode(LedMode.PIPELINE);
    Limelight.setStreamMode(StreamMode.STANDARD);
    Limelight.setPipeline(Pipeline.PIPELINE0);


    //right
    // m_autonomousCommand = new SidePanelAuto();

    // m_autonomousCommand = new CrossHabline(0.0, Paths.LEVEL_1_CROSS_HABLINE);// TODO why do two paths show up?
    // m_autonomousCommand = new CrossHabline(0.0, Paths.LEVEL_2_CROSS_HABLINE);

    // left
    // m_autonomousCommand = new CenterHatchPlace(Pipeline.PIPELINE1, Paths.LEVEL_1_CROSS_HABLINE, Paths.CENTER_HATCH_DRIVE);
    // right
    m_autonomousCommand = new CenterHatchPlace(Pipeline.PIPELINE2, Paths.LEVEL_1_CROSS_HABLINE, Paths.CENTER_HATCH_DRIVE);

    // m_autonomousCommand = new CenterHatchCargoDepo(Pipeline.PIPELINE1, Paths.LEVEL_1_CROSS_HABLINE, Paths.CENTER_HATCH_DRIVE, Paths.CENTER_LEFT_HATCH_TO_LEFT_CARGO_DEPO, Paths.LEFT_CARGO_DEPO_TO_CLOSE_CARGOSHIP);
    // m_autonomousCommand = new CenterHatchCargoDepo(Pipeline.PIPELINE2, Paths.LEVEL_1_CROSS_HABLINE, Paths.CENTER_HATCH_DRIVE, Paths.CENTER_RIGHT_HATCH_TO_RIGHT_CARGO_DEPO,Paths.RIGHT_CARGO_DEPO_TO_CLOSE_CARGOSHIP);

    // m_autonomousCommand = new CenterHatchFeederStation(Paths.LEVEL_1_CROSS_HABLINE, Paths.CENTER_HATCH_DRIVE, Paths.CENTER_RIGHT_HATCH_TO_RIGHT_FEEDER_STATION, Paths.CENTER_RIGHT_HATCH_TO_RIGHT_FEEDER_STATION_2, Paths.RIGHT_FEEDER_STATION_TO_CARGOSHIP, Paths.RIGHT_FEEDER_STATION_TO_CARGOSHIP_2);
    // m_autonomousCommand = new CenterHatchFeederStation();
    
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

    SmartDashboard.putNumber("encoder left", drivetrain.getLeftDistance());
    SmartDashboard.putNumber("encoder right", drivetrain.getRightDistance());
    SmartDashboard.putNumber("gyro", drivetrain.getAngle());

    SmartDashboard.putNumber("ver off", Limelight.getVerticalOffset());

    Scheduler.getInstance().run();

    boolean driverCancelButton = OI.driver.dpad.down.get();
    boolean operatorCncelButton = OI.operator.dpad.down.get();

    //TODO does this work
    if(driverCancelButton && operatorCncelButton) {
      m_autonomousCommand.cancel();
    }

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
    drivetrain.setTeleDrive();
    Limelight.setPipeline(Pipeline.PIPELINE0);
    Limelight.setCamMode(CamMode.VISION_CAM);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    // SmartDashboard.putNumber("Intake current", cargoMechIntake.getIntakeCurrent());
    
    SmartDashboard.putNumber("encoder left", drivetrain.getLeftDistance());
    SmartDashboard.putNumber("encoder right", drivetrain.getRightDistance());
    SmartDashboard.putNumber("gyro tele", drivetrain.getAngle());

    SmartDashboard.putBoolean("armlimit for", cargoMechArm.atBottomLimit());
    SmartDashboard.putBoolean("amrlimit back", cargoMechArm.atTopLimit());
    SmartDashboard.putBoolean("Ball Limit", cargoMechIntake.ballIntook());
    
    SmartDashboard.putNumber("Cargo Arm tick", cargoMechArm.getPosition());
    SmartDashboard.putNumber("Cargo Arm angle", cargoMechArm.getAngle());
    
    SmartDashboard.putNumber("intake", cargoMechIntake.getIntakeCurrent());

    SmartDashboard.putBoolean("Climber sw", climber.limit.get());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
