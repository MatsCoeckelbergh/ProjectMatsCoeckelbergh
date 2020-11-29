package domain.model;

import net.bytebuddy.asm.Advice;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String userid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String passwordSalt = "ldfk";
    private String role = "user";
    private LocalDateTime testTime;

    public Person() {
    }

    public Person(String userid, String email, String password, String firstName, String lastName, String role) {
        setUserid(userid);
        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setRole(role);
    }

    public String test() {
        return "test";
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        if (userid.isEmpty()) {
            throw new IllegalArgumentException("No userid given");
        }
        //userID mustn't be case sensitive
        this.userid = userid.toLowerCase();
    }

    public void setHashedPassword(String hashedString){
        this.password = hashedString;
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


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCorrectPassword(String password) {
        try {
            if (password.isEmpty()) {
                throw new IllegalArgumentException("No password given");
            }
            String hashedPassword = toSaltedHash(password, this.passwordSalt );
            return hashedPassword.equals(this.getPassword());
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void setPassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        this.password = toSaltedHash(password, this.passwordSalt);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isEmpty()) {
            throw new IllegalArgumentException("No firstname given");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("No last name given");
        }
        this.lastName = lastName;
    }

    private String toSaltedHash(String inputString, String salt){
        try{
           MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
           messageDigest.update((salt+inputString).getBytes());
           byte[] b = messageDigest.digest();
           StringBuffer sb = new StringBuffer();
           for (byte bt: b){
               sb.append(Integer.toHexString((bt & 0xff)).toString());
           }
           return sb.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public LocalDateTime getTestTime() {
        return testTime;
    }

    public void setTestTime(LocalDateTime testTime) {
        this.testTime = testTime;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
    }

}
