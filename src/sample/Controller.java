package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextField enrollmentNumber;
    public TextField studentName;
    public DatePicker studentDob;
    public TextField studentContactNumber;
    public ToggleGroup gender;
    @FXML
    public ChoiceBox branchChoiceBox;
    public TextField studentEmailID;
    public PasswordField studentPassword;
    public PasswordField studentConfirmPassword;

    private static final String DB_NAME = "studentDatabase.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:.\\databases\\" + DB_NAME;
    private static final String TABLE_STUDENT = "student";
    private static final String COLUMN_STUDENT_NAME = "name";
    private static final String COLUMN_STUDENT_PHONE = "phone";
    private static final String COLUMN_ENROLLMENT_NUMBER = "eno";
    private static final String COLUMN_STUDENT_EMAIL = "email";
    private static final String COLUMN_STUDENT_DOB = "dob";
    private static final String COLUMN_STUDENT_GENDER = "gender";
    private static final String COLUMN_STUDENT_BRANCH = "branch";
    private static final String COLUMN_STUDENT_PASSWORD = "password";

    private static Statement statement;
    private static Connection conn;

    public Controller() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enrollmentNumber.setText("100");
    }

    public void onBranchChoiceBoxClick(ActionEvent actionEvent) {

    }

    public void submitButtonClick(MouseEvent mouseEvent) {


//        System.out.println(enrollmentNumber.getText());
//        System.out.println(studentDob.getValue());
//        System.out.println(studentName.getText());
//        System.out.println(studentContactNumber.getText());
//        RadioButton radioGender = (RadioButton) gender.getSelectedToggle(); // casting radio button object
//        String genderValue = radioGender.getText(); //extracting value of gender
//        System.out.println(genderValue);
//        //System.out.println(branchChoiceBox.getSelectionModel().getSelectedItem().toString());
//        System.out.println(branchChoiceBox.getValue().toString());
//        System.out.println(studentPassword.getText());
//        System.out.println(studentConfirmPassword.getText());

        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            statement = conn.createStatement();
            statement.execute(String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY NOT NULL ," +
                                            " %s TEXT NOT NULL ,%s INTEGER NOT NULL , %s TEXT NOT NULL,%s DATE NOT NULL," +
                                            " %s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL)"
                    ,TABLE_STUDENT,COLUMN_ENROLLMENT_NUMBER, COLUMN_STUDENT_NAME, COLUMN_STUDENT_PHONE
                    ,COLUMN_STUDENT_EMAIL,COLUMN_STUDENT_DOB,COLUMN_STUDENT_GENDER,COLUMN_STUDENT_BRANCH,COLUMN_STUDENT_PASSWORD));

            
            /*
            validationCheck(name,phone,email);
            insertSTUDENT(statement, name.getText(),Integer.parseInt(phone.getText()), email.getText());
            try (ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_STUDENT)) {
            while (result.next()) {
            System.out.println(result.getString(COLUMN_STUDENT_NAME) + " " + result.getInt(COLUMN_STUDENT_PHONE));
            }
            result.close();
            }
            */

            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        catch (NumberFormatException nfe){
            System.out.println("Wrong Number Format");
        }

        // clearing the field after entering the details
        studentName.clear();
        studentContactNumber.clear();
        studentEmailID.clear();
        studentDob.setValue(null);
        gender.selectToggle(null);

    }

    public void cancelButtonClick(MouseEvent mouseEvent) {
    }
}
