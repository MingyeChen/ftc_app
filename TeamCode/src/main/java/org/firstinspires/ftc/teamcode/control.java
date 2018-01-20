package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mingy on 1/20/2018.
 */

@TeleOp(name="control", group = "Linear Opmode")
public class control extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor, clawLifter, relicExtender, relicExtender2  = null;
    private Servo rightClawServo, leftClawServo, rightClawTopServo, leftClawTopServo, colorArm, relicRotate, relicGrabber = null;

    private base base1 = new base(leftFrontMotor, rightFrontMotor, leftBackMotor, rightBackMotor);
    private claw claw1 = new claw(leftClawServo, rightClawServo, leftClawTopServo, rightClawTopServo, clawLifter);
    private arm arm1 = new arm(relicExtender, relicExtender2, relicRotate, relicGrabber);

    @Override
    public void runOpMode()
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftFrontMotor = hardwareMap.dcMotor.get("lf");
        leftBackMotor = hardwareMap.dcMotor.get("lb");
        rightFrontMotor = hardwareMap.dcMotor.get("rf");
        rightBackMotor = hardwareMap.dcMotor.get("rb");
        clawLifter = hardwareMap.dcMotor.get("clawlift");
        relicExtender = hardwareMap.dcMotor.get("relic_extend");
        relicExtender2 = hardwareMap.dcMotor.get("relic_extend2");

        rightClawServo = hardwareMap.servo.get("claw_right_servo");
        leftClawServo = hardwareMap.servo.get("claw_left_servo");
        rightClawTopServo = hardwareMap.servo.get("claw_right_top_servo");
        leftClawTopServo = hardwareMap.servo.get("claw_left_top_servo");
        colorArm = hardwareMap.servo.get("color_arm");
        relicRotate = hardwareMap.servo.get("relic_rotate");
        relicGrabber = hardwareMap.servo.get("relic_grabber");

        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        clawLifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightClawServo.setDirection(Servo.Direction.REVERSE);
        rightClawTopServo.setDirection(Servo.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while(opModeIsActive())
        {
            base1.mecaDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            if(gamepad1.b)
            {
                claw1.close();
            }

            if (gamepad1.x)
            {
                claw1.open();
            }

            claw1.lift(gamepad1.dpad_up, gamepad1.dpad_down);

            arm1.extend(gamepad1.left_bumper, gamepad1.right_bumper, gamepad1.left_trigger, gamepad1.right_trigger);
            if (gamepad1.dpad_right)
            {
                arm1.grab();
            }
            if (gamepad1.dpad_left)
            {
                arm1.release();
            }

        }
    }
}