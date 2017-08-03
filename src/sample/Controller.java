package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextField enrollmentNumber;

    @FXML
    public TextField studentName;

    @FXML
    public DatePicker studentDob;

    @FXML
    public TextField studentContactNumber;

    @FXML
    public ToggleGroup gender;

    @FXML
    private ChoiceBox branchChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enrollmentNumber.setText("100");
    }
    public Controller() {

    }

    public void onBranchChoiceBoxClick(ActionEvent actionEvent) {

        }
    public void submitButtonClick(MouseEvent mouseEvent) {
        System.out.println(enrollmentNumber.getText());
        System.out.println(studentDob.getValue());
        System.out.println(studentName.getText());
        System.out.println(studentContactNumber.getText());
        RadioButton radioGender = (RadioButton)gender.getSelectedToggle(); // casting radio button object
        String genderValue = radioGender.getText(); //extracting value of gender
        System.out.println(genderValue);
    }

    public void cancelButtonClick(MouseEvent mouseEvent) {
    }

}
