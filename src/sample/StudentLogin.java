package sample;

public class StudentLogin {
    private String eNumber;
    private String studentPassword;
    private String studentConfirmPassword;

    public StudentLogin(String eNumber, String studentPassword, String studentConfirmPassword) {
        this.eNumber = eNumber;
        this.studentPassword = studentPassword;
        this.studentConfirmPassword = studentConfirmPassword;
    }

    public String geteNumber() {

        return eNumber;
    }

    public void seteNumber(String eNumber) {
        this.eNumber = eNumber;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }
}
