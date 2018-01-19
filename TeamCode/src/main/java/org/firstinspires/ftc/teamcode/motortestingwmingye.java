/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="motortesttts", group="Linear Opmode")
//@Disabled
public class motortestingwmingye extends LinearOpMode {

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
        boolean clawPosition = false;

        boolean relicGrabbing = false;
        double relicGrab = 0.5;

        double colorArmServo = 0;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

//            MecMethods.forward(leftFrontMotor, rightFrontMotor, leftBackMotor, rightBackMotor, gamepad1.right_stick_y);
//            MecMethods.strafeleft(leftFrontMotor,rightFrontMotor,leftBackMotor,rightBackMotor,gamepad1.left_stick_x);
//            MecMethods.straferight(leftFrontMotor,rightFrontMotor,leftBackMotor,rightBackMotor,gamepad1.right_stick_x);

            double FrontLeft, FrontRight, RearLeft, RearRight;

            double Ch1 = gamepad1.right_stick_x;
            double Ch3 = gamepad1.left_stick_y;
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

            boolean clawPositionSwitch = !clawPosition;

            // Setup claw variables
            if(gamepad1.b)
            {
                clawPosition = clawPositionSwitch;
            }
//            if (gamepad1.x)
//            {
//                clawPosition=false;
//            }

            if (!clawPosition) {leftClawPosition=0;} else if (clawPosition) {leftClawPosition=1;}
            if (!clawPosition) {rightClawPosition=1;} else if (clawPosition) {rightClawPosition=0;}

            // Send positions to claw
            leftClawServo.setPosition(leftClawPosition);
            rightClawServo.setPosition(rightClawPosition);
            leftClawTopServo.setPosition(leftClawPosition);
            rightClawTopServo.setPosition(rightClawPosition);

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


            if(gamepad1.y)
            {
                colorArmServo = 1;
            }
            if(gamepad1.a)
            {
                colorArmServo = 0;
            }

            colorArm.setPosition(colorArmServo);



            if(gamepad1.dpad_left)
            {
                relicGrabbing = true;
            }

            if(gamepad1.dpad_right)
            {
                relicGrabbing = false;
            }

            if (!relicGrabbing) {relicGrab=0.5;} else if (relicGrabbing) {relicGrab=0;}

            relicGrabber.setPosition(relicGrab);
            relicRotate.setPosition(relicGrab);





            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addLine("lf: "+leftFrontMotor.getPower()+"rf: "+rightFrontMotor.getPower());
            telemetry.addLine("lb: "+leftBackMotor.getPower()+"rb: "+rightBackMotor.getPower());
            telemetry.addLine("Grabber: "+clawPosition);
            telemetry.addLine("Lifter: "+clawLifter.getPower());
            telemetry.addLine("relicExtender, left: " +leftExtendPower+" right: "+rightExtendPower);
            telemetry.addLine("colorArm: "+colorArmServo);
            telemetry.update();
        }
    }
}
