package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="auto", group = "Linear Opmode")
public class auto extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor, clawLifter, relicExtender, relicExtender2  = null;
    private Servo rightClawServo, leftClawServo, rightClawTopServo, leftClawTopServo, colorArm, relicRotate, relicGrabber = null;
    private NormalizedColorSensor colorSensor;


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

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();
        runtime.reset();

        base base1 = new base(leftFrontMotor, rightFrontMotor, leftBackMotor, rightBackMotor);
        claw claw1 = new claw(leftClawServo, rightClawServo, leftClawTopServo, rightClawTopServo, clawLifter);
        arm arm1 = new arm(relicExtender, relicExtender2, relicRotate, relicGrabber);
        colorSensor colorSensor1 = new colorSensor(colorSensor);


        claw1.close1();
        claw1.close2();
        colorArm.setPosition(1);
        char color = colorSensor1.sense();
        if(color=='R')
        {

            base1.newTartget(5,5);
            colorArm.setPosition(0);
            base1.newTartget(-5,-5);
        }
        else
        {
            base1.newTartget(-5,-5);
            colorArm.setPosition(0);
            base1.newTartget(5,5);
        }

        base1.newTartget(50,50);
        base1.turn();

        base1.newTartget(10,10);
        claw1.open1();
        claw1.open2();

        while(opModeIsActive())
        {
            telemetry.addLine("Color recongnized:"+color);
            telemetry.addLine(base1.toString());
            telemetry.addLine(claw1.toString());
            telemetry.addLine(arm1.toString());
            telemetry.update();

        }
    }
}