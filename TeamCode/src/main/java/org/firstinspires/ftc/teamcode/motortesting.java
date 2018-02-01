package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="motortesttts", group="Linear Opmode")
//@Disabled
public class motortesting extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor, clawLifter, relicExtender, relicExtender2  = null;
    private Servo rightClawServo = null;
    private Servo leftClawServo = null;
    private Servo rightClawTopServo = null;
    private Servo leftClawTopServo, colorArm, relicRotate, relicGrabber = null;
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
        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        clawLifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightClawServo = hardwareMap.servo.get("claw_right_servo");
        leftClawServo = hardwareMap.servo.get("claw_left_servo");
        rightClawTopServo = hardwareMap.servo.get("claw_right_top_servo");
        leftClawTopServo = hardwareMap.servo.get("claw_left_top_servo");
        colorArm = hardwareMap.servo.get("color_arm");
        relicRotate = hardwareMap.servo.get("relic_rotate");
        relicGrabber = hardwareMap.servo.get("relic_grabber");

//        relic = hardwareMap.servo.get("color_arm");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        boolean clawPosition = true;
        boolean clawPosition2 = true;

        boolean relicGrabbing = false;
        double relicGrab = 0.5;
        double relicLift = 0.8;



        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

//            MecMethods.forward(leftFrontMotor, rightFrontMotor, leftBackMotor, rightBackMotor, gamepad1.right_stick_y);
//            MecMethods.strafeleft(leftFrontMotor,rightFrontMotor,leftBackMotor,rightBackMotor,gamepad1.left_stick_x);
//            MecMethods.straferight(leftFrontMotor,rightFrontMotor,leftBackMotor,rightBackMotor,gamepad1.right_stick_x);

            double FrontLeft, FrontRight, RearLeft, RearRight;

            double Ch1 = -gamepad1.right_stick_x;
            double Ch3 = gamepad1.right_stick_y;
            double Ch4 = gamepad1.left_stick_x;

            FrontLeft =  Ch3 + Ch1 + Ch4;
            RearLeft =   Ch3 + Ch1 - Ch4;
            FrontRight = Ch3 - Ch1 - Ch4;
            RearRight =  Ch3 - Ch1 + Ch4;

            leftFrontMotor.setPower(Range.clip(FrontLeft, -1,1));
            leftBackMotor.setPower(Range.clip(RearLeft,-1, 1));
            rightFrontMotor.setPower(Range.clip(FrontRight,-1,1));
            rightBackMotor.setPower(Range.clip(RearRight,-1, 1));

            
            // Declare claw variables
            double leftClawPosition=0;
            double rightClawPosition=0;
            double leftClawPosition2=0;
            double rightClawPosition2=0;

//            boolean clawPositionSwitch = !clawPosition;
//            boolean clawPositionSwitch2 = !clawPosition2;

            // Setup claw variables
//            if(gamepad1.b)
//            {
//                clawPosition = clawPositionSwitch;
//            }
//
//            if(gamepad1.x)
//            {
//                clawPosition2 = clawPositionSwitch2;
//            }
            if (gamepad1.x)
            {
                clawPosition=true;
            }
            if (gamepad1.b)
            {
                clawPosition=false;
            }
            if (gamepad1.y)
            {
                clawPosition2=true;
            }
            if (gamepad1.a)
            {
                clawPosition2=false;
            }

            if (!clawPosition) {leftClawPosition=0.2;} else if (clawPosition) {leftClawPosition=0.7;}
            if (!clawPosition) {rightClawPosition=0.8;} else if (clawPosition) {rightClawPosition=0.3;}

            if (!clawPosition2) {leftClawPosition2=0.2;} else if (clawPosition2) {leftClawPosition2=0.7;}
            if (!clawPosition2) {rightClawPosition2=0.8;} else if (clawPosition2) {rightClawPosition2=0.3;}

            // Send positions to claw
            leftClawServo.setPosition(leftClawPosition);
            rightClawServo.setPosition(rightClawPosition);
            leftClawTopServo.setPosition(leftClawPosition2);
            rightClawTopServo.setPosition(rightClawPosition2);

            if(gamepad1.dpad_up)
            {
                clawLifter.setPower(0.5);
            }
            else if(gamepad1.dpad_down)
            {
                clawLifter.setPower(-0.5);
            }
            else
            {
                clawLifter.setPower(0);
            }

            double leftExtendPower = 0;
            double rightExtendPower = 0;

            if(gamepad1.left_bumper)
            {
                leftExtendPower = 0.5;
            }
            else if (gamepad1.right_bumper)
            {
                rightExtendPower = 0.5;
            }

            if(gamepad1.left_trigger>0)
            {
                leftExtendPower = -0.5;
            }
            else if(gamepad1.right_trigger>0)
            {
                rightExtendPower = -0.5;
            }

            relicExtender.setPower(leftExtendPower);
            relicExtender2.setPower(rightExtendPower);


            double colorArmPosition = 0;


//            if (!colorArmServo) {colorArmPosition=0;} else if (colorArmServo) {colorArmPosition=1;}

            colorArm.setPosition(colorArmPosition);




            if(gamepad1.left_stick_button)
            {
                relicGrabbing = true;
            }

            if(gamepad1.right_stick_button)
            {
                relicGrabbing = false;
            }

            if (!relicGrabbing) {relicGrab=1;} else if (relicGrabbing) {relicGrab=0;}

            if(gamepad1.dpad_left)
            {
                relicLift += 0.002;
            }
            if(gamepad1.dpad_right)
            {
                relicLift -= 0.002;
            }



            relicGrabber.setPosition(relicGrab);
            relicRotate.setPosition(Range.clip(relicLift,0,1));





            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addLine("lf: "+leftFrontMotor.getPower()+"rf: "+rightFrontMotor.getPower());
            telemetry.addLine("lb: "+leftBackMotor.getPower()+"rb: "+rightBackMotor.getPower());
            telemetry.addLine("Grabber: "+clawPosition);
            telemetry.addLine("Lifter: "+clawLifter.getPower());
            telemetry.addLine("relicExtender, left: " +leftExtendPower+" right: "+rightExtendPower);
            telemetry.addLine("colorArm: "+colorArmPosition);
            telemetry.update();
        }
    }
}
