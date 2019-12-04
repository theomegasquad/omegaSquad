package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "TeleOpsOmega", group = "")
public class TeleOpsOmega extends LinearOpMode
{

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor clawDC;
    private DcMotor foundationDC;

    private Servo clawDrop;
    private Servo clawGripper;
    private Servo foundationLeft;
    private Servo foundationRight;

    double foundationLeftPosition;
    double foundationRightPosition;

    double FOUNDATION_INITIAL_POSITION = 1;

    double MIN_POSITION = 0, MAX_POSITION = 1;
    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode()
    {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");

        clawDC = hardwareMap.dcMotor.get("clawDC");
        foundationDC = hardwareMap.dcMotor.get("foundationDC");
        clawDrop = hardwareMap.servo.get("clawDrop");
        clawGripper = hardwareMap.servo.get("clawGripper");
        foundationLeft = hardwareMap.servo.get("foundationLeft");
        foundationRight = hardwareMap.servo.get("foundationRight");
        float speed = 0.0f;
        double turnspeed = 0.0f;

        telemetry.addData("Mode", "waiting");

        waitForStart();
        foundationLeftPosition = FOUNDATION_INITIAL_POSITION;
        foundationRightPosition = FOUNDATION_INITIAL_POSITION;

        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                speed = gamepad1.left_stick_y;
                leftFront.setPower(-speed);
                leftBack.setPower(-speed);
                rightFront.setPower(speed);
                rightBack.setPower(speed);

                turnspeed = gamepad1.left_stick_x;
                leftFront.setPower(-turnspeed);
                leftBack.setPower(-turnspeed);
                rightFront.setPower(-turnspeed);
                rightBack.setPower(-turnspeed);

//gamepad1.dpad_right is shuffling right when you press the right button on the dpad

                if (gamepad1.dpad_right) {
                    leftFront.setPower(0.75);
                    leftBack.setPower(-0.75);
                    rightFront.setPower(0.75);
                    rightBack.setPower(-0.75);
                }

    //gamepad1.dpad_left is shuffling left when you press the left button on the dpad

                if (gamepad1.dpad_left) {
                    leftFront.setPower(-0.75);
                    leftBack.setPower(0.75);
                    rightFront.setPower(-0.75);
                    rightBack.setPower(0.75);
                }

                //gamepad2.dpad_up is moving the claw up when you press up on the dpad
                if (gamepad2.dpad_up) {
                    clawDC.setPower(1);
                    telemetry.addData("G2 D-Up", gamepad1.dpad_up);
                    telemetry.update();
                }
                else if(!gamepad2.dpad_up) {
                    clawDC.setPower(0);
                }

                //gamepad2.dpad_down is moving the claw down when you press down on the dpad
                if (gamepad2.dpad_down){
                    clawDC.setPower(-0.5);
                    telemetry.addData("G2 D-Down", gamepad1.dpad_down);
                    telemetry.update();
                }
                else if(!gamepad2.dpad_down) {
                    clawDC.setPower(0);
                }
                //gamepad2.right_stick_x is moving the foundation motors forward when you move the right stick right
                if (gamepad2.right_stick_x == 1.0f){
                    foundationDC.setPower(0.5);
                    telemetry.addData("G2 RS-x forward", gamepad1.right_stick_x);
                    telemetry.update();
                }
                else if(gamepad2.right_stick_x != 1.0f) {
                    foundationDC.setPower(0);
                }
                //gamepad2.right_stick_x is moving the foundation motors backward when you move the right stick left
                if (gamepad2.right_stick_x == -1.0f) {
                    foundationDC.setPower(-0.5);
                    telemetry.addData("G2 R  S-x backward", gamepad1.right_stick_x);
                    telemetry.update();
                }
                else if(gamepad2.right_stick_x != -1.0f){
                    foundationDC.setPower(0);
                }

                if (gamepad2.a){
                    clawDrop.setPosition(-180);
                    sleep(1000);
                    clawGripper.setPosition(250);
                    telemetry.addData("Grabbing", gamepad2.a);
                    telemetry.update();
                }
                if (gamepad2.b){
                    clawGripper.setPosition(-45);
                    sleep(1000);
                    clawDrop.setPosition(180);
                    telemetry.addData("Grabbing", gamepad2.b);
                    telemetry.update();
                }

                if (gamepad2.left_bumper){
                    foundationDC.setPower(-0.75);

                }
                else if(!gamepad2.left_bumper) {
                    foundationDC.setPower(0);
                }

                if (gamepad2.right_bumper){
                        foundationDC.setPower(0.75);

                }
                else if(!gamepad2.right_bumper) {
                        foundationDC.setPower(0);
                }

                if (gamepad2.x && foundationLeftPosition > MIN_POSITION) {
                    foundationLeftPosition -= .02;
                    //foundationRight.setPosition (-5);
                    foundationLeft.setPosition(Range.clip(foundationLeftPosition, MIN_POSITION, MAX_POSITION));
                    telemetry.addData("Foundation Left",
                            "  Actual=" +foundationLeft.getPosition()
                            + "  Position=" + foundationLeftPosition);
                    //telemetry.addData("Left", foundationLeft.getPosition());
                    telemetry.update();
                }
                if (gamepad2.y && foundationLeftPosition < MAX_POSITION) {
                    foundationLeftPosition += .02;
                    foundationLeft.setPosition(Range.clip(foundationLeftPosition, MIN_POSITION, MAX_POSITION));
                    telemetry.addData("Foundation Left",
                            "  Actual=" +foundationLeft.getPosition()
                                    + "  Position=" + foundationLeftPosition);
                    //telemetry.addData("Left", foundationLeft.getPosition());
                    telemetry.update();
                    //foundationRight.setPosition (180);
                    //foundationLeft.setPosition (-130);
                    //telemetry.addData("Right", foundationRight.getPosition());
                    //telemetry.addData("Left", foundationLeft.getPosition());
                    //telemetry.update();
                }

                if (gamepad1.x && foundationRightPosition > MIN_POSITION) {
                    foundationRightPosition -= .02;
                    //foundationRight.setPosition (-5);
                    foundationRight.setPosition(Range.clip(foundationRightPosition, MIN_POSITION, MAX_POSITION));
                    telemetry.addData("Foundation Right",
                            "  Actual=" +foundationRight.getPosition()
                                    + "  Position=" + foundationRightPosition);
                    //telemetry.addData("Left", foundationLeft.getPosition());
                    telemetry.update();
                }
                if (gamepad1.y && foundationRightPosition < MAX_POSITION) {
                    foundationRightPosition += .02;
                    foundationRight.setPosition(Range.clip(foundationRightPosition, MIN_POSITION, MAX_POSITION));
                    telemetry.addData("Foundation Right",
                            "  Actual=" +foundationRight.getPosition()
                                    + "  Position=" + foundationRightPosition);
                    //telemetry.addData("Left", foundationLeft.getPosition());
                    telemetry.update();
                    //foundationRight.setPosition (180);
                    //foundationLeft.setPosition (-130);
                    //telemetry.addData("Right", foundationRight.getPosition());
                    //telemetry.addData("Left", foundationLeft.getPosition());
                    //telemetry.update();
                }

                idle();
            }
        }
    }
}
