package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="clawTest", group = "Linear Opmode")
public class clawTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor clawLifter;
    private Servo rightClawServo, leftClawServo, rightClawTopServo, leftClawTopServo;
    private claw claw1 = new claw(leftClawServo, rightClawServo, leftClawTopServo, rightClawTopServo, clawLifter);

    @Override
    public void runOpMode()
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        clawLifter = hardwareMap.dcMotor.get("clawlift");

        rightClawServo = hardwareMap.servo.get("claw_right_servo");
        leftClawServo = hardwareMap.servo.get("claw_left_servo");
        rightClawTopServo = hardwareMap.servo.get("claw_right_top_servo");
        leftClawTopServo = hardwareMap.servo.get("claw_left_top_servo");

        clawLifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightClawServo.setDirection(Servo.Direction.REVERSE);
        rightClawTopServo.setDirection(Servo.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while(opModeIsActive())
        {
            if (gamepad1.b)
            {
                claw1.close1();
            }
            if (gamepad1.x)
            {
                claw1.open1();
            }
            if (gamepad1.a)
            {
                claw1.close2();
            }
            if (gamepad1.y)
            {
                claw1.open2();
            }
            claw1.lift(gamepad1.dpad_up, gamepad1.dpad_down);

            telemetry.addLine(claw1.toString());
            telemetry.update();

        }
    }
}
