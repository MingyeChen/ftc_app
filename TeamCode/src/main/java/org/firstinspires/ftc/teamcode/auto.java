package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="auto", group = "Linear Opmode")
public class auto extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor, clawLifter, relicExtender, relicExtender2 = null;
    private Servo rightClawServo, leftClawServo, rightClawTopServo, leftClawTopServo, colorArm, relicRotate, relicGrabber = null;
    private ColorSensor eye;


    @Override
    public void runOpMode() {
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

        eye = hardwareMap.colorSensor.get("eye");

        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        clawLifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightClawServo.setDirection(Servo.Direction.REVERSE);
        rightClawTopServo.setDirection(Servo.Direction.REVERSE);

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();
        runtime.reset();

        mouth mouth1 = new mouth(telemetry);
        base base1 = new base(leftFrontMotor, rightFrontMotor, leftBackMotor, rightBackMotor, mouth1);
        claw claw1 = new claw(leftClawServo, rightClawServo, leftClawTopServo, rightClawTopServo, clawLifter);
        arm arm1 = new arm(relicExtender, relicExtender2, relicRotate, relicGrabber);
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
        eye eye1 = new eye(eye);
//        colorSensor colorSensor1 = new colorSensor(colorSensor);


        claw1.close1();
        claw1.close2();
        mouth1.speak("Claws closed");
        claw1.lift(true, false);
        mouth1.speak("Lifting...");
        sleep(1000);
        claw1.lift(false, false);
        mouth1.speak("Lifted");
        colorArm.setPosition(1);
        sleep(3000);
        char color = eye1.sense();
        mouth1.speak("Color recongnized:"+color);

        if (color == 'R') {
            base1.newTartget(2, 2);
            colorArm.setPosition(0);
            base1.newTartget(-2, -2);
        } else {
            base1.newTartget(-2, -2);
            colorArm.setPosition(0);
            base1.newTartget(2, 2);
        }

        base1.newTartget(12,12);
        base1.newTartget(3,-3);
        base1.newTartget(2,2);
        claw1.open1();
        claw1.open2();
        mouth1.speak("Claws opened");
        base1.newTartget(-2,-2);
        claw1.close1();
        claw1.close2();
        mouth1.speak("Claws closed");
        base1.newTartget(2,2);


//        base1.newTartget(50,50);
//        base1.turn();
//
//        base1.newTartget(10,10);
//        claw1.open1();
//        claw1.open2();

    }



}