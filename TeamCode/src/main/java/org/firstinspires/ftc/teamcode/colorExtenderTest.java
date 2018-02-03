package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="colorExtenderTest", group="Linear Opmode")
//@Disabled
public class colorExtenderTest extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
//    private DcMotor leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor, clawLifter, relicExtender, relicExtender2  = null;
//    private Servo rightClawServo = null;
//    private Servo leftClawServo = null;
//    private Servo rightClawTopServo = null;
//    private Servo leftClawTopServo, colorArm, relicRotate, relicGrabber = null;
    private NormalizedColorSensor eye;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

//        leftFrontMotor = hardwareMap.dcMotor.get("lf");
//        leftBackMotor = hardwareMap.dcMotor.get("lb");
//        rightFrontMotor = hardwareMap.dcMotor.get("rf");
//        rightBackMotor = hardwareMap.dcMotor.get("rb");
//        clawLifter = hardwareMap.dcMotor.get("clawlift");
//        relicExtender = hardwareMap.dcMotor.get("relic_extend");
//        relicExtender2 = hardwareMap.dcMotor.get("relic_extend2");
//        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        clawLifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        rightClawServo = hardwareMap.servo.get("claw_right_servo");
//        leftClawServo = hardwareMap.servo.get("claw_left_servo");
//        rightClawTopServo = hardwareMap.servo.get("claw_right_top_servo");
//        leftClawTopServo = hardwareMap.servo.get("claw_left_top_servo");
//        colorArm = hardwareMap.servo.get("color_arm");
//        relicRotate = hardwareMap.servo.get("relic_rotate");
//        relicGrabber = hardwareMap.servo.get("relic_grabber");

        eye = hardwareMap.get(NormalizedColorSensor.class, "eye");

//        relic = hardwareMap.servo.get("color_arm");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        boolean clawPosition = true;
        boolean clawPosition2 = true;

        boolean relicGrabbing = false;
        double relicGrab = 0.5;
        double relicLift = 0.8;

        boolean colorArmServo = false;


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
/*

//            MecMethods.forward(leftFrontMotor, rightFrontMotor, leftBackMotor, rightBackMotor, gamepad1.right_stick_y);
//            MecMethods.strafeleft(leftFrontMotor,rightFrontMotor,leftBackMotor,rightBackMotor,gamepad1.left_stick_x);
//            MecMethods.straferight(leftFrontMotor,rightFrontMotor,leftBackMotor,rightBackMotor,gamepad1.right_stick_x);

            double FrontLeft, FrontRight, RearLeft, RearRight;

            double Ch1 = gamepad1.left_stick_x;
            double Ch3 = gamepad1.left_stick_y;
            double Ch4 = gamepad1.right_stick_x;

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
                leftExtendPower = 0.7;
            }
            else if (gamepad1.right_bumper)
            {
                rightExtendPower = 0.7;
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


            boolean readColor = !colorArmServo;
            double colorArmPosition = 0;

            if(gamepad1.y)
            {
                colorArmServo = readColor;
            }

            if (!colorArmServo) {colorArmPosition=0;} else if (colorArmServo) {colorArmPosition=1;}

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
                relicLift += 0.005;
            }
            if(gamepad1.dpad_right)
            {
                relicLift -= 0.005;
            }



            relicGrabber.setPosition(relicGrab);
            relicRotate.setPosition(relicLift);





            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addLine("lf: "+leftFrontMotor.getPower()+"rf: "+rightFrontMotor.getPower());
            telemetry.addLine("lb: "+leftBackMotor.getPower()+"rb: "+rightBackMotor.getPower());
            telemetry.addLine("Grabber: "+clawPosition);
            telemetry.addLine("Lifter: "+clawLifter.getPower());
            telemetry.addLine("relicExtender, left: " +leftExtendPower+" right: "+rightExtendPower);
*/

//            if(gamepad1.b)
//            {
//                colorArm.setPosition(1);
//            }
//            if(gamepad1.x)
//            {
//                colorArm.setPosition(0);
//            }

            telemetry.addLine(sense(eye)+"");
//            telemetry.addLine("colorArm: "+colorArm.getPosition());
            telemetry.update();
        }
    }
    public char sense(NormalizedColorSensor colorSensor) {

        // values is a reference to the hsvValues array.
        float[] hsvValues = new float[3];
        final float values[] = hsvValues;

        // bPrevState and bCurrState keep track of the previous and current state of the button
        boolean bPrevState = false;
        boolean bCurrState = false;


        // If possible, turn the light on in the beginning (it might already be on anyway,
        // we just make sure it is if we can).
        if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight) colorSensor).enableLight(true);
        }

        // Wait for the start button to be pressed.
//        waitForStart();

        // Loop until we are asked to stop
        while (true) {
            // Check the status of the x button on the gamepad


            // Read the sensor
            NormalizedRGBA colors = colorSensor.getNormalizedColors();

            /** Use telemetry to display feedback on the driver station. We show the conversion
             * of the colors to hue, saturation and value, and display the the normalized values
             * as returned from the sensor.
             * @see <a href="http://infohost.nmt.edu/tcc/help/pubs/colortheory/web/hsv.html">HSV</a>*/

            Color.colorToHSV(colors.toColor(), hsvValues);
//            telemetry.addLine()
//                    .addData("H", "%.3f", hsvValues[0])
//                    .addData("S", "%.3f", hsvValues[1])
//                    .addData("V", "%.3f", hsvValues[2]);
//            telemetry.addLine()
//                    .addData("a", "%.3f", colors.alpha)
//                    .addData("r", "%.3f", colors.red)
//                    .addData("g", "%.3f", colors.green)
//                    .addData("b", "%.3f", colors.blue);

            char result = 'N';
            if (colors.red > colors.blue) {
                result = 'R';
            } else if (colors.blue < colors.red) {
                result = 'B';
            }

            return result;

            /** We also display a conversion of the colors to an equivalent Android color integer.
             * @see Color */
//            int color = colors.toColor();
//            telemetry.addLine("raw Android color: ")
//                    .addData("a", "%02x", Color.alpha(color))
//                    .addData("r", "%02x", Color.red(color))
//                    .addData("g", "%02x", Color.green(color))
//                    .addData("b", "%02x", Color.blue(color));

            // Balance the colors. The values returned by getColors() are normalized relative to the
            // maximum possible values that the sensor can measure. For example, a sensor might in a
            // particular configuration be able to internally measure color intensity in a range of
            // [0, 10240]. In such a case, the values returned by getColors() will be divided by 10240
            // so as to return a value it the range [0,1]. However, and this is the point, even so, the
            // values we see here may not get close to 1.0 in, e.g., low light conditions where the
            // sensor measurements don't approach their maximum limit. In such situations, the *relative*
            // intensities of the colors are likely what is most interesting. Here, for example, we boost
            // the signal on the colors while maintaining their relative balance so as to give more
            // vibrant visual feedback on the robot controller visual display.
//            float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
//            colors.red /= max;
//            colors.green /= max;
//            colors.blue /= max;
//            color = colors.toColor();

//            telemetry.addLine("normalized color:  ")
//                    .addData("a", "%02x", Color.alpha(color))
//                    .addData("r", "%02x", Color.red(color))
//                    .addData("g", "%02x", Color.green(color))
//                    .addData("b", "%02x", Color.blue(color));
//            telemetry.update();

            // convert the RGB values to HSV values.
//            Color.RGBToHSV(Color.red(color), Color.green(color), Color.blue(color), hsvValues);

            // change the background color to match the color detected by the RGB sensor.
            // pass a reference to the hue, saturation, and value array as an argument
            // to the HSVToColor method.
//            relativeLayout.post(new Runnable() {
//                public void run() {
//                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
//                }
//            });
        }
    }
}
