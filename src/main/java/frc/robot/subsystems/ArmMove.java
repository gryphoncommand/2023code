package frc.robot.subsystems;
import java.net.CacheRequest;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmMove extends SubsystemBase {
    CANSparkMax armL = new CANSparkMax(8, MotorType.kBrushless);
    CANSparkMax armR = new CANSparkMax(9, MotorType.kBrushless);

    MotorControllerGroup armgroup = new MotorControllerGroup(armL, armR);
    public ArmMove(){
        armL.setInverted(true);
        armR.setInverted(true);
        armR.setIdleMode(CANSparkMax.IdleMode.kBrake);
        armL.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }
    public void armIn(){
        armgroup.set(-0.3);
    }
    public void armOut(){
        armgroup.set(0.2);
    }
    public void stop(){
        armgroup.stopMotor();
    }
    
}

