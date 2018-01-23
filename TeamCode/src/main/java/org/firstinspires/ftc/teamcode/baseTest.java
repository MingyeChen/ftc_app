package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mingy on 1/20/2018.
 */

@TeleOp(name="baseTest", group = "Linear Opmode")
public class baseTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor = null;

    private base base1 = new base(leftFrontMotor, rightFrontMotor, leftBackMotor, rightBackMotor);


    @Override
    public void runOpMode()
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftFrontMotor = hardwareMap.dcMotor.get("lf");
        leftBackMotor = hardwareMap.dcMotor.get("lb");
        rightFrontMotor = hardwareMap.dcMotor.get("rf");
        rightBackMotor = hardwareMap.dcMotor.get("rb");


        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while(opModeIsActive())
        {
            base1.mecaDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            telemetry.addLine(base1.toString());
            telemetry.update();

        }
    }
}
