package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by mingyech on 2/6/18.
 */

public class mouth {
    private Telemetry info;

    public mouth(Telemetry info)
    {
        this.info = info;
    }

    public void speak(String stuff)
    {
        info.addLine(stuff);
        info.update();
    }
}
