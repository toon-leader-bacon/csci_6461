package org.nocab.nocabmachine;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.nocab.nocabmachine.nocab.BinaryNumber;

public class HelloController {
    @FXML
    private Label welcomeText;

    //region General Purpose Registers
    @FXML
    private Label GRP0;
    @FXML
    private Label GRP1;
    @FXML
    private Label GRP2;
    @FXML
    private Label GRP3;
    //endregion

    //region Index Register
    @FXML
    private Label IXR1;
    @FXML
    private Label IXR2;
    @FXML
    private Label IXR3;
    //endregion

    @FXML
    private Label PC; // Program Counter

    //region Memory
    @FXML
    private Label MAR; // Memory Address Register
    @FXML
    private Label MBR; // Memory Buffer Register
    @FXML
    private Label MFR; // Memory Fetch Register
    //endregion

    @FXML
    private Label IR; // Instruction Register


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        BinaryNumber pcNumber = new BinaryNumber("0101_0101_0101_0101");
        PC.setText(pcNumber.toString_Binary());
    }
}