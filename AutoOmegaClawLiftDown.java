package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name = "Omega:Auto Claw Lift(down)", group = "Autonomous")
public class AutoOmegaClawLiftDown extends LinearOpMode {

    OmegaSquadRobot robot = new OmegaSquadRobot();
    private ElapsedTime runtime = new ElapsedTime();
    //@Override

    public void runOpMode() {
        // Put initialization blocks here.
        robot.init(hardwareMap);

        telemetry.addData("Autonomous Mode Status", "Ready to Run");
        telemetry.update();

        waitForStart();

        robot.foundationDC.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1) {
            telemetry.addData("Condition", "Epically");
            telemetry.update();
        }

    } }

