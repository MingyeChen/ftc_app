package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class base {

    private DcMotor leftFront, rightFront, leftBack, rightBack;
    private mouth baseMouth;

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     WHEEL_DIAMETER_CM   = 10.0;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     COUNTS_PER_CM         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_CM * 3.1415);
    static final double     DRIVE_SPEED             = 0.2;
    static final double     TURN_SPEED              = 0.5;

    public base(DcMotor leftFront, DcMotor rightFront, DcMotor leftBack, DcMotor rightBack, mouth baseMouth)
    {
        this.leftFront = leftFront;
        this.leftBack = leftBack;
        this.rightFront = rightFront;
        this.rightBack = rightBack;
        this.baseMouth = baseMouth;
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

    public void resetMotors()
    {
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void newTartget(double leftInches, double rightInches)
    {
        resetMotors();
        int leftTarget = (int)(leftInches * COUNTS_PER_INCH);
        int rightTarget = (int)(rightInches * COUNTS_PER_INCH);
        leftBack.setTargetPosition(leftTarget);
        leftFront.setTargetPosition(leftTarget);
        rightBack.setTargetPosition(rightTarget);
        rightFront.setTargetPosition(rightTarget);

        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftBack.setPower(DRIVE_SPEED);
        leftFront.setPower(DRIVE_SPEED);
        rightBack.setPower(DRIVE_SPEED);
        rightFront.setPower(DRIVE_SPEED);

        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy())
        {
            baseMouth.speak("Running, Left: "+leftFront.getCurrentPosition()+" Right: "+rightFront.getCurrentPosition());
        }

        resetMotors();
    }

    public void turn()
    {
        newTartget(10,-10);
    }

    public String toString()
    {
        return "Base: leftF: "+leftFront.getPower()+" leftB: "+leftBack.getPower()+" rightF: "+rightFront.getPower()+" rightBack: "+rightBack.getPower();
    }

}