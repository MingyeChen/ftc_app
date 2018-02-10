package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by mingyech on 2/8/18.
 */

public class scanner {
    private VuforiaLocalizer vuforia;
    private int cameraMonitorViewId;
    public scanner(VuforiaLocalizer vuforia, int cameraMonitorViewId)
    {
        this.vuforia = vuforia;
        this.cameraMonitorViewId = cameraMonitorViewId;
    }

    public String scan() {

        /*
         * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
         * If no camera monitor is desired, use the parameterless constructor instead (commented out below).
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View, to save power
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = "AY9iCtb/////AAAAGVKvyHh0skU+ve8abRN3rwsGrlQcn3zuW7iKW7jmZD8sYOSSn11HNNqb1VIIt3Jsicv295VE4ROUc6/Y+gZtGBqHA56pK30qdkYT8rcn6HP4sCEmaeyca+Rldq3HZZR1mepRFVbCJYdsvirFMf1pdK+szeLdEhT0kKzRCIZ5by/BjfeYH+a3dKZTsLrYT3447Z6FKMeAK6wxzEYlv9rQ5h+oPrwTAFKjW2DVt8LMa3dRTGkzgtQNPtBjawrwA3Cp0UwDGUKyAkJemLo4+f+PpGVTUukiLNjGjmM1B9EX5ASFSSklagUNzzvZ1+xuR+9zFmTHf5gHJJA+PEszarV8LaQJb2MOC9op8Gr+PwGtOCoa";

        /*
         * We also indicate which camera on the RC that we wish to use.
         * Here we chose the back (HiRes) camera (for greater range), but
         * for a competition robot, the front camera might be more convenient.
         */
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        /**
         * Load the data set containing the VuMarks for Relic Recovery. There's only one trackable
         * in this data set: all three of the VuMarks in the game were created from this one template,
         * but differ in their instance id information.
         * @see VuMarkInstanceId
         */
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();

        while (true) {

            /**
             * See if any of the instances of {@link relicTemplate} are currently visible.
             * {@link RelicRecoveryVuMark} is an enum which can have the following values:
             * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
             * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
             */
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                if (vuMark == RelicRecoveryVuMark.LEFT)
                {
                    return "LEFT";
                }
                else if (vuMark == RelicRecoveryVuMark.CENTER)
                {
                    return "CENTRE";
                }
                else
                {
                    return "RIGHT";
                }

            }
        }
    }
}
