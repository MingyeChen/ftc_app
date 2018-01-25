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

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
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

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
@Disabled
public class A2018L extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor leftBackMotor = null;
    private DcMotor rightBackMotor = null;
    private DcMotor clawMotor = null;
    private Servo rightClawServo = null;
    private Servo leftClawServo = null;
    private Servo JewelMotor = null;
    private ColorSensor jewelColorSensor;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFrontMotor = hardwareMap.dcMotor.get("left_front_motor");
        rightFrontMotor = hardwareMap.dcMotor.get("right_front_motor");
        leftBackMotor = hardwareMap.dcMotor.get("left_back_motor");
        rightBackMotor = hardwareMap.dcMotor.get("right_back_motor");
        rightClawServo = hardwareMap.servo.get("claw_right_servo");
        leftClawServo = hardwareMap.servo.get("claw_left_servo");
        clawMotor = hardwareMap.dcMotor.get("claw_Motor");
        JewelMotor = hardwareMap.dcMotor.get("Jewel_Motor");
        jewelColorSensor = hardwareMap.colorSensor.get("jewel_sensor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set team color
        char teamColor = 'R';

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        goToJewel();
        extendJewelPusher();
        // Detect color and setup variables for which side to Knock
        char sideToKnock = 'L';
        if ((hsvValues[0] <= 120) || (hsvValues[0] > 300)) {
            if (teamColor == 'R') {
                sideToKnock = 'R';
            } else {
                sideToKnock = 'L';
            }
        } else {
            if (teamColor == 'R') {
                sideToKnock = 'L';
            } else {
                sideToKnock = 'R';
            }
        }


        if (sideToKnock == 'L') {

        }


        retractJewelPusher();
        driveToPic();
        detectPic();
        driveToBox();
        putGlygh();
        parkInZone();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            Color.RGBToHSV((int) (jewelColorSensor.red() * SCALE_FACTOR),
                    (int) (jewelColorSensor.green() * SCALE_FACTOR),
                    (int) (jewelColorSensor.blue() * SCALE_FACTOR),
                    hsvValues);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }

    private void driveToPic() {

    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int leftFrontTarget;
        int rightFrontTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            leftFrontTarget = leftFrontMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            rightFrontTarget = rightFrontMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newLeftTarget = leftBackMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newRightTarget = rightBackMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            leftFrontMotor.setTargetPosition(leftFrontTarget);
            rightFrontMotor.setTargetPosition(rightFrontTarget);
            leftBackMotor.setTargetPosition(newLeftTarget);
            rightBackMotor.setTargetPosition(newRightTarget);


            // Turn On RUN_TO_POSITION
            leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            leftFrontMotor.setPower(Math.abs(speed));
            rightFrontMotor.setPower(Math.abs(speed));
            leftBackMotor.setPower(Math.abs(speed));
            rightBackMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftFrontMotor.isBusy() && rightFrontMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", leftFrontTarget, rightFrontTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        leftFrontMotor.getCurrentPosition(),
                        rightFrontMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            leftFrontMotor.setPower(0);
            rightFrontMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    public void goToJewel() {
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftFrontMotor.setPower(1);
        rightFrontMotor.setPower(1);
        leftBackMotor.setPower(1);
        rightBackMotor.setPower(1);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }

    public void extendJewelPusher() {
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        runtime.reset();
        JewelMotor.setPosition(1);
        JewelMotor.setPosition();
    }
    public void driveToBox() {
    telemetry.addData("Status", "Ready to run");
    telemetry.update();

    runtime.reset();
    while (opModeIsActive() && (runtime.seconds() < 1.0)) {
        telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
        telemetry.update();
        }
        leftFrontMotor.setPower(1);
        rightFrontMotor.setPower(1);
        leftBackMotor.setPower(1);
        rightBackMotor.setPower(1);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }

    public void parkInZone(){
        telemetry.addData("Status", "Ready to run");
        telemetry.update();


        runtime.reset();
        while( opModeIsActive() && (runtime.seconds))

    }
}