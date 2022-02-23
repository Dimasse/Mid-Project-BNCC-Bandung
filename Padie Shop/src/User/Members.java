package User;

import myDatabase.Database;
import java.util.ArrayList;

public class Members {

    // Declare Atrubute
    private String userName, fullName, email, password;

    // Declare Class
    Database data = new Database();
    ArrayList<String> item= new ArrayList<>();
    ArrayList <Double> price = new ArrayList<>();
    ArrayList <Integer> totalItem = new ArrayList<>();

    // Getter
    public String getUserName() {return userName;}
    public String getFullName() {
       return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {return password;}

    // Satter
    public String  setUserName(String userName) {

        // Check Input Username
        if (userName.length() < 3)
            return "-1";
        else if (userName.length() > 16)
            return "-2";

        return this.userName = userName;

    }
    public String setFullName(String fullName) {

        // Check Input Full Name
        if (fullName.length() < 3)
            return  "-1";
        else if (fullName.length() > 16)
            return "-2";
        else if (fullName.contains("!") || fullName.contains("?") ||  fullName.contains(",") || fullName.contains("+") ||  fullName.contains("-") || fullName.contains("_"))
            return "-3";

        return this.fullName = fullName;
    }
    public String setEmail(String email) {

        // Check Input Email
        if (email.length() < 5)
            return "-1";
        else if (email.length()  > 30)
            return "-2";
        else if (!email.contains("@"))
            return "-3";
        else if (!email.endsWith(".com") || email.endsWith(".net"))
            return  "-4";

        return this.email = email;
    }
    public String setPassword(String password) {

        // Check Input Password
        char[] temp_password = password.toCharArray();
        if (password.length() < 8)
            return "-1";
        else if (password.length()  > 40)
            return "-2";
        else {
            boolean check = false;
            for (char idx : temp_password) {
                if (Character.isDigit(idx)) {
                    check = true;
                    break;
                }else
                    check = false;
            }
            if (!check)
                return "-3";
        }

        return this.password = password;
    }

}
