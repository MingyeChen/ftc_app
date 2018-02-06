package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class claw {
    private Servo left1, right1, left2, right2;
    private DcMotor lifter;

    public claw(Servo left1, Servo right1, Servo left2, Servo right2)
    {
        this.left1 = left1;
        this.right1 = right1;
        this.left2 = left2;
        this.right2 = right2;
    }

    public claw(Servo left1, Servo right1, Servo left2, Servo right2, DcMotor lifter)
    {
        this.left1 = left1;
        this.right1 = right1;
        this.left2 = left2;
        this.right2 = right2;
        this.lifter = lifter;
    }

    public void close1()
    {
        left1.setPosition(0.7);
        right1.setPosition(0.7);
    }

    public void open1()
    {
        left1.setPosition(0.3);
        right1.setPosition(0.3);
    }

    public void close2()
    {
        left2.setPosition(0.7);
        right2.setPosition(0.7);
    }

    public void open2()
    {
        left2.setPosition(0.3);
        right2.setPosition(0.3);
    }

    public void lift(boolean up, boolean down)
    {
        double liftSpeed = 0;
        if (up)
        {
            liftSpeed = 0.5;
        }
        if (down)
        {
            liftSpeed = -0.5;
        }
        lifter.setPower(Range.clip(liftSpeed, -0.5, 0.5));
    }

    public String toString()
    {
        return "Claw: left1: "+left1.getPosition()+", left2: "+left2.getPosition()+", right1: "+right1.getPosition()+", right2: "+right2.getPosition()+", lifter: "+lifter.getPower();
    }
}
