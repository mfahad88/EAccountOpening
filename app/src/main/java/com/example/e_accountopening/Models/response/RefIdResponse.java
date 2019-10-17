package com.example.e_accountopening.Models.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefIdResponse {

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
/*@SerializedName("path")
@Expose
private String path;*/
@SerializedName("imageName")
@Expose
private String imageName;
@SerializedName("videoName")
@Expose
private String videoName;
@SerializedName("rpaStatus")
@Expose
private Integer rpaStatus;
@SerializedName("ekyc")
@Expose
private Integer ekyc;
@SerializedName("ekycResponse")
@Expose
private Integer ekycResponse;
@SerializedName("verisysStatus")
@Expose
private Integer verisysStatus;
@SerializedName("orcStatus")
@Expose
private Integer orcStatus;
@SerializedName("dataocrStatus")
@Expose
private Integer dataocrStatus;
@SerializedName("compStatus")
@Expose
private Integer compStatus;

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

/*public String getPath() {
return path;
}

public void setPath(String path) {
this.path = path;
}*/

public String getImageName() {
return imageName;
}

public void setImageName(String imageName) {
this.imageName = imageName;
}

public String getVideoName() {
return videoName;
}

public void setVideoName(String videoName) {
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

public Integer getEkycResponse() {
return ekycResponse;
}

public void setEkycResponse(Integer ekycResponse) {
this.ekycResponse = ekycResponse;
}

public Integer getVerisysStatus() {
return verisysStatus;
}

public void setVerisysStatus(Integer verisysStatus) {
this.verisysStatus = verisysStatus;
}

public Integer getOrcStatus() {
return orcStatus;
}

public void setOrcStatus(Integer orcStatus) {
this.orcStatus = orcStatus;
}

public Integer getDataocrStatus() {
return dataocrStatus;
}

public void setDataocrStatus(Integer dataocrStatus) {
this.dataocrStatus = dataocrStatus;
}

public Integer getCompStatus() {
return compStatus;
}

public void setCompStatus(Integer compStatus) {
this.compStatus = compStatus;
}

}
