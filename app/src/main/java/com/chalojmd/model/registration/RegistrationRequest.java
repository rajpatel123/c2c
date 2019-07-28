package com.chalojmd.model.registration;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationRequest {

@SerializedName("Id")
@Expose
private String id;
@SerializedName("Name")
@Expose
private String name;
@SerializedName("Email")
@Expose
private String email;
@SerializedName("Gender")
@Expose
private String gender;
@SerializedName("Role")
@Expose
private String role;
@SerializedName("Dob")
@Expose
private String dob;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public String getRole() {
return role;
}

public void setRole(String role) {
this.role = role;
}

public String getDob() {
return dob;
}

public void setDob(String dob) {
this.dob = dob;
}

}