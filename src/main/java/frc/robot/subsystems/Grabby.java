package frc.robot.subsystems;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Grabby extends SubsystemBase {
    DoubleSolenoid grab = new DoubleSolenoid(PneumaticsModuleType.REVPH, 9, 8);
    CANSparkMax extendo = new CANSparkMax(7, MotorType.kBrushed);

    public Grabby(){
        
    }

    // graber pneumatics control
    public void grabbber_open(){
        grab.set(Value.kForward);
        System.out.println("open");
    }
    public void grabbbber_close(){
        grab.set(Value.kReverse);
        System.out.println("close");
    }


    // extendo controller
    public void gogoextendo(){
        extendo.set(-0.6);
    }
    public void odnetxeogog(){
        extendo.set(0.8);
    }
    public void stopstopextendo(){
        extendo.stopMotor();
    }
}

