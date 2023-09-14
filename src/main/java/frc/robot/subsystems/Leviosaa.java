package frc.robot.subsystems;
import java.net.CacheRequest;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Leviosaa extends SubsystemBase {
    CANSparkMax elevatupL = new CANSparkMax(8, MotorType.kBrushless);
    CANSparkMax elevatupR = new CANSparkMax(9, MotorType.kBrushless);

    MotorControllerGroup elevagroup = new MotorControllerGroup(elevatupL, elevatupR);
    public Leviosaa(){
        elevatupL.setInverted(true);
        elevatupR.setInverted(true);
        elevatupR.setIdleMode(CANSparkMax.IdleMode.kBrake);
        elevatupL.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }
    public void levo(){
        elevagroup.set(-0.3);
    }
    public void stillabunt(){
        elevagroup.set(0.2);
    }
    public void stop(){
        elevagroup.stopMotor();
    }
    
}

