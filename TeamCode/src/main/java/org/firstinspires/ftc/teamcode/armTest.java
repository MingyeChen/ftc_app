package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="armTest", group = "Linear Opmode")
public class armTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor relicExtender, relicExtender2  = null;
    private Servo relicRotate, relicGrabber = null;

    private arm arm1 = new arm(relicExtender, relicExtender2, relicRotate, relicGrabber);

    @Override
    public void runOpMode()
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        relicExtender = hardwareMap.dcMotor.get("relic_extend");
        relicExtender2 = hardwareMap.dcMotor.get("relic_extend2");

        relicRotate = hardwareMap.servo.get("relic_rotate");
        relicGrabber = hardwareMap.servo.get("relic_grabber");


        waitForStart();
        runtime.reset();

        while(opModeIsActive())
        {

            arm1.extend(gamepad1.left_bumper, gamepad1.right_bumper, gamepad1.left_trigger, gamepad1.right_trigger);
            if (gamepad1.dpad_right)
            {
                arm1.grab();
            }
            if (gamepad1.dpad_left)
            {
                arm1.release();
            }

            telemetry.addLine(arm1.toString());
            telemetry.update();

        }
    }
}
