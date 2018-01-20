package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by mingy on 1/18/2018.
 */

public class motorMethod {

    public void mecaDrive(DcMotor leftFront, DcMotor rightFront, DcMotor leftBack, DcMotor rightBack, double drive, double strafe, double turn)
    {
        leftFront.setPower(drive + turn + strafe);
        leftBack.setPower(drive + turn - strafe);
        rightFront.setPower(drive - turn - strafe);
        rightBack.setPower(drive - turn + strafe);
    }

    public void tankDrive(DcMotor leftFront, DcMotor rightFront, DcMotor leftBack, DcMotor rightBack, double drive, double turn)
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
