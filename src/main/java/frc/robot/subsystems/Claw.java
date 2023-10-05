package frc.robot.subsystems;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase {
    DoubleSolenoid grab = new DoubleSolenoid(PneumaticsModuleType.REVPH, 9, 8);
    CANSparkMax extendo = new CANSparkMax(7, MotorType.kBrushed);
// change to 7
    public Claw(){
        
    }

    // graber pneumatics control
    public void claw_open(){
        grab.set(Value.kForward);
        System.out.println("open");
    }
    public void claw_close(){
        grab.set(Value.kReverse);
        System.out.println("close");
    }
}

