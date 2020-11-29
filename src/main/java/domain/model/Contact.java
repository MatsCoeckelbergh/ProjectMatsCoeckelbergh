package domain.model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    String userID;
    String fName;
    String lName;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    LocalDateTime time;
    String phoneNumber;
    String email;

    public Contact(){}

    public Contact(String userID, String fName, String lName, LocalDateTime time, String phoneNumber, String email)
    {
        this.setUserID(userID);
        this.setfName(fName);
        this.setlName(lName);
        this.setTime(time);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        if (fName.isEmpty()) {
            throw new IllegalArgumentException("No first name given");
        }
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        if (lName.isEmpty()) {
            throw new IllegalArgumentException("No last name given");
        }
        this.lName = lName;
    }

    public String getDate(){
        return this.getTime().format(DateTimeFormatter.ISO_DATE);
    }

    public String getHour(){
        String hourString = Integer.toString(this.getTime().getHour());
        String minuteString = Integer.toString(this.getTime().getMinute());
        if (minuteString.length() == 1){
            minuteString = "0" + minuteString;
        }
        return hourString + ":" + minuteString;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("No phone number given");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.isEmpty()) {
            throw new IllegalArgumentException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException("Email not valid");
        }
        this.email = email;
    }

    public boolean matchesString(String str){
        boolean out = false;
        if (this.getfName().toLowerCase().contains(str.toLowerCase())){
            out = true;
        }
        if (this.getlName().toLowerCase().contains(str.toLowerCase())){
            out = true;
        }
        if (this.getEmail().toLowerCase().contains(str.toLowerCase())){
            out = true;
        }
        if (this.getUserID().toLowerCase().contains(str.toLowerCase())){
            out = true;
        }
        return out;
    }
}
