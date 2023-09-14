package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import java.lang.Math;

public class Drivetrain extends SubsystemBase {
  CANSparkMax rightB = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax rightF = new CANSparkMax(3, MotorType.kBrushless);
    CANSparkMax leftB = new CANSparkMax(4, MotorType.kBrushless);
    CANSparkMax leftF = new CANSparkMax(5, MotorType.kBrushless); //5
    
    

    

    
    Solenoid s1 = new Solenoid(PneumaticsModuleType.REVPH, 0);                        // Solenoid port
    Solenoid s2 = new Solenoid(PneumaticsModuleType.REVPH, 2);      

    MotorControllerGroup left = new MotorControllerGroup(leftB, leftF);
    MotorControllerGroup right = new MotorControllerGroup(rightB, rightF);
    
    SlewRateLimiter forFilter = new SlewRateLimiter(0.7);
    SlewRateLimiter turnFilter = new SlewRateLimiter(0.4);

    
    DifferentialDrive drive = new DifferentialDrive(left, right); 
   
    public Drivetrain(){
      leftB.setIdleMode(IdleMode.kBrake);
      leftF.setIdleMode(IdleMode.kBrake);
      rightB.setIdleMode(IdleMode.kBrake);
      rightF.setIdleMode(IdleMode.kBrake);
      left.setInverted(true);
      right.setInverted(true);

    }
    public void arcadeDrive(double RX, double LY){
      drive.arcadeDrive(turnFilter.calculate(RX), forFilter.calculate(LY));
        // if(RX > -0.05 && RX < 0.05){
        //   turnFilter.reset(0);
        // }
      
     if (LY < 0.15 && LY > 0.04) {
          forFilter.reset(0.3);
        } else if (LY > -0.15 && LY < -0.04) {
          forFilter.reset(-0.3);
        } else if (LY < 0.04 && LY > -0.04) {
          forFilter.reset(0);
        }
      if (RX < 0.1 && RX > 0) {
        turnFilter.reset(0.1);
      } else if (RX > -0.1 && RX < 0) {
        turnFilter.reset(-0.1);
      }
      /* if (RX > 0){
          drive.arcadeDrive(turnFilter.calculate(RX), forFilter.calculate(LY));
        } else if (RX < 0){
          drive.arcadeDrive(-1 * turnFilter.calculate(Math.abs(RX)), forFilter.calculate(LY));
        } else {
          drive.arcadeDrive(0, forFilter.calculate(LY));
        } */

    }

    public void sneakDrive(double LY){
      arcadeDrive(0, LY/5);
    }

    public void gearShift() {
        if (s1.get()) {
          s1.set(false);
          s2.set(false);
        } else {
          s1.set(true);
          s2.set(true);
        }
      }



}
