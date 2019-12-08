package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name = "Omega:Claw Grip", group = "Autonomous")
public class AutoOmegaTestClawGrip extends LinearOpMode {

    OmegaSquadRobot robot = new OmegaSquadRobot();
    private ElapsedTime runtime = new ElapsedTime();

    //@Override
    public void runOpMode() {
        // Put initialization blocks here.
        robot.init(hardwareMap);

        telemetry.addData("Autonomous Mode Status", "Ready to Run");
        telemetry.update();

        waitForStart();
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1.3)
        {
            robot.clawGripper.setPosition(250);
            telemetry.addData("Claw Grip", "250");
            telemetry.update();
        }
    }


}
