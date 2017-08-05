package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
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
    private static final String TABLE_STUDENT = "student_data";
    private static final String TABLE_STUDENT_LOGIN = "student_login";
    private static final String COLUMN_STUDENT_NAME = "name";
    private static final String COLUMN_STUDENT_PHONE = "phone";
    private static final String COLUMN_ENROLLMENT_NUMBER = "eno";
    private static final String COLUMN_STUDENT_EMAIL = "email";
    private static final String COLUMN_STUDENT_DOB = "dob";
    private static final String COLUMN_STUDENT_GENDER = "gender";
    private static final String COLUMN_STUDENT_BRANCH = "branch";
    private static final String COLUMN_STUDENT_PASSWORD = "password";
    public static  Integer eNumberValue=100;
    private static Statement statement;
    private static Connection conn;

    public Controller() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(String.format("SELECT MAX(%s) FROM %s",COLUMN_ENROLLMENT_NUMBER,TABLE_STUDENT_LOGIN));
            eNumberValue=result.getInt(1)+1;
            result.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        enrollmentNumber.setText(eNumberValue.toString());
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
                                            " %s TEXT NOT NULL,%s TEXT NOT NULL)"
                    ,TABLE_STUDENT,COLUMN_ENROLLMENT_NUMBER, COLUMN_STUDENT_NAME, COLUMN_STUDENT_PHONE
                    ,COLUMN_STUDENT_EMAIL,COLUMN_STUDENT_DOB,COLUMN_STUDENT_GENDER,COLUMN_STUDENT_BRANCH));

            statement.execute(String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY NOT NULL,%s TEXT NOT NULL)"
                    ,TABLE_STUDENT_LOGIN,COLUMN_ENROLLMENT_NUMBER,COLUMN_STUDENT_PASSWORD));

            //validationCheck(name,phone,email);

            RadioButton radioGender = (RadioButton) gender.getSelectedToggle(); // casting radio button object
            String genderValue = radioGender.getText();
            StudentData studentData = new StudentData(enrollmentNumber.getText(),studentName.getText(),studentContactNumber.getText(),studentEmailID.getText() ,studentDob.getValue(),genderValue,branchChoiceBox.getValue().toString());
            StudentLogin studentLogin = new StudentLogin(enrollmentNumber.getText(),studentPassword.getText(),studentConfirmPassword.getText());
            insertStudentLogin(statement,studentLogin);
            insertStudent(statement,studentData);

            //insertStudent(statement, enrollmentNumber.getText(),studentName.getText(),studentContactNumber.getText() );
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
        branchChoiceBox.setValue(null);
        studentPassword.clear();
        studentConfirmPassword.clear();
        eNumberValue = eNumberValue +1;
        enrollmentNumber.setText(eNumberValue.toString());
    }


    public static void insertStudent(Statement statement,StudentData studentData) throws SQLException {
        String executionStatement;

        executionStatement = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s) VALUES (%d,'%s',%d,'%s','%s','%s','%s' );"
                ,TABLE_STUDENT,COLUMN_ENROLLMENT_NUMBER, COLUMN_STUDENT_NAME, COLUMN_STUDENT_PHONE
                ,COLUMN_STUDENT_EMAIL,COLUMN_STUDENT_DOB,COLUMN_STUDENT_GENDER,COLUMN_STUDENT_BRANCH
                ,Integer.parseInt(studentData.geteNumber()),studentData.getStudentName(),
                Integer.parseInt(studentData.getStudentPhone()),studentData.getStudentEmail(),
                studentData.getStudentDob(),studentData.getGender(),studentData.getBranch());

        statement.execute(executionStatement);
    }

    public static void insertStudentLogin(Statement statement,StudentLogin studentLogin) throws SQLException {
        String executionStatement;
        executionStatement = String.format("INSERT INTO %s (%s,%s) VALUES (%d,'%s' );"
                ,TABLE_STUDENT_LOGIN,COLUMN_ENROLLMENT_NUMBER,COLUMN_STUDENT_PASSWORD
                , Integer.parseInt(studentLogin.geteNumber()), studentLogin.getStudentPassword());
        statement.execute(executionStatement);
    }

    public void cancelButtonClick(MouseEvent mouseEvent) {
    }
}
