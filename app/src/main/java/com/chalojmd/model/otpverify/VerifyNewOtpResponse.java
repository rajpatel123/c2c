package com.chalojmd.model.otpverify;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyNewOtpResponse {

@SerializedName("Status")
@Expose
private Boolean status;
@SerializedName("Message")
@Expose
private String message;
@SerializedName("Otp_Status")
@Expose
private String otpStatus;

public Boolean getStatus() {
return status;
}

public void setStatus(Boolean status) {
this.status = status;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public String getOtpStatus() {
return otpStatus;
}

public void setOtpStatus(String otpStatus) {
this.otpStatus = otpStatus;
}

}