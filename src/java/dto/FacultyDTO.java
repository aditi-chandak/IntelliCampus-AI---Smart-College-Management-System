package dto;
/**
 *
 * @author HP
 */
public class FacultyDTO 
{
private String facultyId;
private String facultyName;
private String facultyPassword;
private String facultyEmail;
private String facultyMobile;
private String branchName;

public  String getFacultyId() {
    return facultyId;
}
public void setFacultyId( String facultyId) {
    this.facultyId = facultyId;
}
public String getFacultyName() {
    return facultyName;
}
public void setFacultyName(String facultyName) {
    this.facultyName = facultyName;
}
public String getFacultyPassword() {
    return facultyPassword;
}
public void setFacultyPassword(String facultyPassword) {
    this.facultyPassword = facultyPassword;
}
public String getFacultyEmail() {
    return facultyEmail;
}
public void setFacultyEmail(String facultyEmail) {
    this.facultyEmail = facultyEmail;
}
public String getFacultyMobile() {
    return facultyMobile;
}
public void setFacultyMobile(String facultyMobile) {
    this.facultyMobile = facultyMobile;
}
public  String getBranchName() {
    return branchName;
}
public void setBranchName( String branchName) {
    this.branchName = branchName;
}

}
