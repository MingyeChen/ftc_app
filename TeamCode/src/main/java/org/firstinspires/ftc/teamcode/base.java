package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by mingy on 1/18/2018.
 */

public class base {

    private DcMotor leftFront, rightFront, leftBack, rightBack;

    public base(DcMotor leftFront, DcMotor rightFront, DcMotor leftBack, DcMotor rightBack)
    {
        this.leftFront = leftFront;
        this.leftBack = leftBack;
        this.rightFront = rightFront;
        this.rightBack = rightBack;
//        this.drive = drive;
//        this.strafe = strafe;
//        this.turn = turn;
    }

    public void mecaDrive(double drive, double strafe, double turn)
    {
        double lf, lb, rf, rb;

        lf = drive + turn + strafe;
        lb = drive + turn - strafe;
        rf = drive - turn - strafe;
        rb = drive - turn + strafe;

        leftFront.setPower(Range.clip(lf, -1, 1));
        leftBack.setPower(Range.clip(lb, -1, 1));
        rightFront.setPower(Range.clip(rf, -1, 1));
        rightBack.setPower(Range.clip(rb, -1, 1));
    }

    public void tankDrive(double drive, double turn)
    {
        double leftWheel,rightWheel;

        leftWheel    = Range.clip(drive + turn, -1.0, 1.0) ;
        rightWheel   = Range.clip(drive - turn, -1.0, 1.0) ;

        leftFront.setPower(leftWheel);
        leftBack.setPower(leftWheel);
        rightFront.setPower(rightWheel);
        rightBack.setPower(rightWheel);
    }
}
