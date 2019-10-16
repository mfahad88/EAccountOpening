package com.example.e_accountopening.Models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateAccountResponse {

@SerializedName("refId")
@Expose
private Integer refId;
@SerializedName("cnic")
@Expose
private String cnic;
@SerializedName("issueDate")
@Expose
private String issueDate;
@SerializedName("userName")
@Expose
private String userName;
@SerializedName("motherName")
@Expose
private String motherName;
@SerializedName("dateOfBirth")
@Expose
private String dateOfBirth;
@SerializedName("placeOfBirth")
@Expose
private String placeOfBirth;
@SerializedName("path")
@Expose
private Object path;
@SerializedName("imageName")
@Expose
private Object imageName;
@SerializedName("videoName")
@Expose
private Object videoName;
@SerializedName("rpaStatus")
@Expose
private Integer rpaStatus;
@SerializedName("ekyc")
@Expose
private Integer ekyc;

public Integer getRefId() {
return refId;
}

public void setRefId(Integer refId) {
this.refId = refId;
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

public Object getPath() {
return path;
}

public void setPath(Object path) {
this.path = path;
}

public Object getImageName() {
return imageName;
}

public void setImageName(Object imageName) {
this.imageName = imageName;
}

public Object getVideoName() {
return videoName;
}

public void setVideoName(Object videoName) {
this.videoName = videoName;
}

public Integer getRpaStatus() {
return rpaStatus;
}

public void setRpaStatus(Integer rpaStatus) {
this.rpaStatus = rpaStatus;
}

public Integer getEkyc() {
return ekyc;
}

public void setEkyc(Integer ekyc) {
this.ekyc = ekyc;
}

}