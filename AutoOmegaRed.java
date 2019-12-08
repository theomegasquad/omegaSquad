package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name = "Omega:Auto Drive (red)", group = "Autonomous")
public class AutoOmegaRed extends LinearOpMode  {

        //1.5 seconds of spinning at 0.75 = 2 ft.
        OmegaSquadRobot robot = new OmegaSquadRobot();
        private ElapsedTime runtime = new ElapsedTime();

        static final double FORWARD_SPEED = 0.5;
        static final double REVERSE_SPEED = -0.5;

        double foundationLeftPosition;
        double foundationRightPosition;

        double FOUNDATION_INITIAL_POSITION = 0;

        double MIN_POSITION = 0, MAX_POSITION = 1;
        float speed = 0.0f;
        double turnspeed = 0.0f;
        //foundationLeftPosition = FOUNDATION_INITIAL_POSITION;
        //foundationRightPosition = FOUNDATION_INITIAL_POSITION;

        /**
         * This function is executed when this Op Mode is selected from the Driver Station.
         */
        @Override
        public void runOpMode() {
            // Put initialization blocks here.
            robot.init(hardwareMap);

            telemetry.addData("Autonomous Mode Status", "Ready to Run");
            telemetry.update();


            Boolean Tf = true;

            waitForStart();

            //Go towards the block
            robot.leftFront.setPower(FORWARD_SPEED);
            robot.leftBack.setPower(FORWARD_SPEED);
            robot.rightFront.setPower(FORWARD_SPEED);
            robot.rightBack.setPower(FORWARD_SPEED);
            runtime.reset();

            while (opModeIsActive() && (runtime.seconds() < 1.4)) {
                telemetry.addData("Path", "Towards Block: %2.5f S  Elapsed", runtime.seconds());
                telemetry.update();
            }

            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);


            //claw Drop
            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < 1.1)
            {
                robot.clawDrop.setPosition(-180);
                telemetry.addData("Claw Drop", "-180");
                telemetry.update();
            }

            //Move ClawGripper servo
            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < 1.5)
            {
                robot.clawGripper.setPosition(250);
                telemetry.addData("Claw Grip", "250");
                telemetry.update();
            }




            //After grabbing the block, come backward
            robot.leftFront.setPower(REVERSE_SPEED);
            robot.leftBack.setPower(REVERSE_SPEED);
            robot.rightFront.setPower(REVERSE_SPEED);
            robot.rightBack.setPower(REVERSE_SPEED);
            runtime.reset();

            while (opModeIsActive() && (runtime.seconds() < 0.9)) {
                telemetry.addData("Path", "Grabbed Block going back: %2.5f S  Elapsed", runtime.seconds());
                telemetry.update();
            }
            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);



            //Turn 90 Degrees to go under the alliance bridge
            robot.leftFront.setPower(-0.5);
            robot.leftBack.setPower(-0.5);
            robot.rightFront.setPower(0.5);
            robot.rightBack.setPower(0.5);
            runtime.reset();

            while (opModeIsActive() && (runtime.seconds() < 1.3)) {
                telemetry.addData("Path", "Turning 90 Deg to go under bridge: %2.5f S  Elapsed", runtime.seconds());
                telemetry.update();
            }


            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);


            //Pass through the alliance bridge
            robot.leftFront.setPower(FORWARD_SPEED);
            robot.leftBack.setPower(FORWARD_SPEED);
            robot.rightFront.setPower(FORWARD_SPEED);
            robot.rightBack.setPower(FORWARD_SPEED);
            runtime.reset();

            while (opModeIsActive() && (runtime.seconds() < 5.25)) {
                telemetry.addData("Path", "Passing under alliance bridge: %2.5f S  Elapsed", runtime.seconds());
                telemetry.update();
            }

            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);


            //Turn towards foundation


            robot.leftFront.setPower(FORWARD_SPEED);
            robot.leftBack.setPower(FORWARD_SPEED);
            robot.rightFront.setPower(-FORWARD_SPEED);
            robot.rightBack.setPower(-FORWARD_SPEED);
            runtime.reset();

            while (opModeIsActive() && (runtime.seconds() < 1.45)) {
                telemetry.addData("Path", "Turn towards Foundation Stone: %2.5f S  Elapsed", runtime.seconds());
                telemetry.update();
            }

            robot.leftFront.setPower(0.2);
            robot.leftBack.setPower(0.2);
            robot.rightFront.setPower(0.2);
            robot.rightBack.setPower(0.2);

            while (opModeIsActive() && Tf) {

                robot.clawDrop.setPosition(-180);
                sleep(500);
                robot.clawGripper.setPosition(250);
                telemetry.addData("Grabbing", true);
                telemetry.update();

                robot.clawDC.setPower(1);

                sleep(1500);

                Tf = false;

                robot.clawDC.setPower(0);

            }

            sleep(1000);
            robot.leftFront.setPower(0.0);
            robot.leftBack.setPower(0.0);
            robot.rightFront.setPower(0.0);
            robot.rightBack.setPower(0.0);

            robot.clawDC.setPower(-0.5);
            sleep(750);
            robot.clawDC.setPower(0);


            robot.clawGripper.setPosition(-45);
            sleep(1000);
            robot.clawDrop.setPosition(180);
            telemetry.addData("Grabbing", true);
            telemetry.update();


            //Go backward to reposition foundation

            robot.leftFront.setPower(-FORWARD_SPEED);
            robot.leftBack.setPower(-FORWARD_SPEED);
            robot.rightFront.setPower(-FORWARD_SPEED);
            robot.rightBack.setPower(-FORWARD_SPEED);
            runtime.reset();

            while (opModeIsActive() && (runtime.seconds() < 1.0)) {
                telemetry.addData("Path", "Go Back to Spin 180 deg: %2.5f S  Elapsed", runtime.seconds());
                telemetry.update();
            }

            //shuffle right :)
            robot.leftFront.setPower(-0.75);
            robot.leftBack.setPower(0.75);
            robot.rightFront.setPower(0.75);
            robot.rightBack.setPower(-0.75);

            sleep(2500);
            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);



/*
        //Spin 180 Degrees to move foundation
        robot.leftFront.setPower(-FORWARD_SPEED);
        robot.leftBack.setPower(-FORWARD_SPEED);
        robot.rightFront.setPower(FORWARD_SPEED);
        robot.rightBack.setPower(FORWARD_SPEED);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 2.3)) {
            telemetry.addData("Path", "Spin 180 Degress: %2.5f S  Elapsed", runtime.seconds());
            telemetry.update();
        }


        robot.foundationDC.setPower(0.75);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.5) {
            telemetry.addData("Condition", "Epically");
            telemetry.update();
        }

        robot.foundationDC.setPower(0);


        while(foundationLeftPosition < MAX_POSITION && opModeIsActive() && foundationRightPosition < MAX_POSITION) {
            foundationLeftPosition += .01;
            robot.foundationLeft.setPosition(Range.clip(foundationLeftPosition, MIN_POSITION, MAX_POSITION));
            telemetry.addData("Foundation Left",
                    "  Actual(left)=" + robot.foundationLeft.getPosition()
                            + "  Position(left)=" + foundationLeftPosition);
            telemetry.update();

            foundationRightPosition -= .01;
            robot.foundationRight.setPosition(Range.clip(foundationRightPosition, MIN_POSITION, MAX_POSITION));
            telemetry.addData("Foundation Right",
                    "  Actual(right)=" + robot.foundationRight.getPosition()
                            + "  Position(right)=" + foundationRightPosition);
            telemetry.update();


        }


        robot.leftFront.setPower(-0.5);
        robot.leftBack.setPower(-0.5);
        robot.rightFront.setPower(-0.5);
        robot.rightBack.setPower(-0.5);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Go Back to Spin 180 deg: %2.5f S  Elapsed", runtime.seconds());
            telemetry.update();
        }

        robot.leftFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightFront.setPower(0);
        robot.rightBack.setPower(0);


        while(foundationLeftPosition > MIN_POSITION && opModeIsActive() && foundationRightPosition > MIN_POSITION) {
            foundationLeftPosition -= .01;
            robot.foundationLeft.setPosition(Range.clip(foundationLeftPosition, MIN_POSITION, MAX_POSITION));
            telemetry.addData("Foundation Left",
                    "  Actual(left)=" + robot.foundationLeft.getPosition()
                            + "  Position(left)=" + foundationLeftPosition);
            telemetry.update();

            foundationRightPosition += .01;
            robot.foundationRight.setPosition(Range.clip(foundationRightPosition, MIN_POSITION, MAX_POSITION));
            telemetry.addData("Foundation Right",
                    "  Actual(right)=" + robot.foundationRight.getPosition()
                            + "  Position(right)=" + foundationRightPosition);
            telemetry.update();


        }

        //Go forward to reposition foundation
        robot.leftFront.setPower(FORWARD_SPEED);
        robot.leftBack.setPower(FORWARD_SPEED);
        robot.rightFront.setPower(FORWARD_SPEED);
        robot.rightBack.setPower(FORWARD_SPEED);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Path", "Reposition Foundation: %2.5f S  Elapsed", runtime.seconds());
            telemetry.update();
        }
/*
        //Move further after repositioning foundation
        robot.leftFront.setPower(FORWARD_SPEED);
        robot.leftBack.setPower(-FORWARD_SPEED);
        robot.rightFront.setPower(FORWARD_SPEED);
        robot.rightBack.setPower(-FORWARD_SPEED);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Move after repositioning foundation: %2.5f S  Elapsed", runtime.seconds());
            telemetry.update();
        }

        //Spin 90 Degrees to park under alliance bridge
        robot.leftFront.setPower(-FORWARD_SPEED);
        robot.leftBack.setPower(FORWARD_SPEED);
        robot.rightFront.setPower(FORWARD_SPEED);
        robot.rightBack.setPower(-FORWARD_SPEED);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Spin 90 Degrees to park under bridge: %2.5f S  Elapsed", runtime.seconds());
            telemetry.update();
        }

        //Move to park under bridge
        robot.leftFront.setPower(FORWARD_SPEED);
        robot.leftBack.setPower(FORWARD_SPEED);
        robot.rightFront.setPower(FORWARD_SPEED);
        robot.rightBack.setPower(FORWARD_SPEED);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Move to park under bridge: %2.5f S  Elapsed", runtime.seconds());
            telemetry.update();
        }
*/
        }
    }
