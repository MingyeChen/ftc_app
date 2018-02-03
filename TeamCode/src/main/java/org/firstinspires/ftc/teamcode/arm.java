package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class arm {

    private DcMotor extend1, extend2;
    private Servo rotate, grab;

    public arm(DcMotor extend1, DcMotor extend2, Servo rotate, Servo grab)
    {
        this.extend1 = extend1;
        this.extend2 = extend2;
        this.rotate = rotate;
        this.grab = grab;
    }

    public void extend(boolean go1, boolean go2, float back1, float back2)
    {
        if(go1)
        {
            extend1.setPower(0.5);
        }
        else if(back1 >0)
        {
            extend1.setPower(-0.5);
        }
        else
        {
            extend1.setPower(0);
        }

        if(go2)
        {
            extend2.setPower(0.5);
        }
        else if(back2 > 0)
        {
            extend2.setPower(-0.5);
        }
        else
        {
            extend2.setPower(0);
        }
    }

    public void grab()
    {
        grab.setPosition(1);
    }

    public void release()
    {
        grab.setPosition(0);
    }

    public void rotate(Gamepad gamepad1)
    {
        double relicLift = 0.8;
        if(gamepad1.dpad_left)
        {
            relicLift += 0.002;
        }
        if(gamepad1.dpad_right)
        {
            relicLift -= 0.002;
        }
        rotate.setPosition(relicLift);
    }

    public String toString()
    {
        return "Arm: extend1: "+extend1.getPower()+", extend2: "+extend2.getPower()+", rotate: "+rotate.getPosition()+", grab: "+grab.getPosition();
    }
}
