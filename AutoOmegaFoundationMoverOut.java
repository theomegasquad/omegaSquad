package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name = "Omega:Auto Move Foundation Attachment(out)", group = "Autonomous")
public class AutoOmegaFoundationMoverOut extends LinearOpMode {
    OmegaSquadRobot robot = new OmegaSquadRobot();
    private ElapsedTime runtime = new ElapsedTime();
    //@Override

    public void runOpMode() {
        // Put initialization blocks here.
        robot.init(hardwareMap);

        telemetry.addData("Autonomous Mode Status", "Ready to Run");
        telemetry.update();

        waitForStart();

        robot.foundationDC.setPower(0.75);
        runtime.reset();
            while (opModeIsActive() && runtime.seconds() < 0.5) {
            telemetry.addData("Condition", "Epically");
            telemetry.update();
        }

    } }
