package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by mingy on 1/20/2018.
 */

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

    public void close()
    {
        left1.setPosition(1);
        left2.setPosition(1);
        right1.setPosition(1);
        right2.setPosition(1);
    }

    public void open()
    {
        left1.setPosition(0.5);
        left2.setPosition(0.5);
        right1.setPosition(0.5);
        right2.setPosition(0.5);
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
}
