package com.example.e_accountopening.Models.request;

public class CreateAccountBean {
    private String cnic;
    private String issueDate;
    private String userName;
    private String motherName;
    private String dateOfBirth;
    private String placeOfBirth;

    public CreateAccountBean(String cnic, String issueDate, String userName, String motherName, String dateOfBirth, String placeOfBirth) {
        this.cnic = cnic;
        this.issueDate = issueDate;
        this.userName = userName;
        this.motherName = motherName;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    @Override
    public String toString() {
        return "CreateAccountBean{" +
                "cnic='" + cnic + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", userName='" + userName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                '}';
    }
}
