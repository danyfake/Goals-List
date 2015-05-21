
import java.io.*;

/**
 * Created by User on 18.05.2015.
 */
public class User {
    public String firstName = "Darya";
    public String lastName = "Ov";
    public String nickName = "";
    public int phoneNumber = 0;

    public int getPhoneNumber(int phoneNumber) {
        return phoneNumber;
    }

    public String getFirstName(String firstName) {
        return firstName;
    }

    public String getLastName(String lastName) {
        return lastName;
    }

    public String getNickName(String nickName) {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String changeNickname() throws IOException {
        String nickName = "";
        System.out.println("User's old nickname: " + this.nickName);
        System.out.println("Enter new nickname: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            nickName = reader.readLine();
        } catch (IOException e) {
            System.out.println("ERROR in reading nickname!");
        }

        if (nickName.equals("")) {
            System.out.println("ERROR: you didn't enter nickname!");
            return this.nickName;
        }

        this.setNickName(nickName);
        System.out.println("User's new nickname: " + this.nickName);
        System.out.println();

        return this.nickName;
    }

    public int changePhoneNumber() throws IOException {
        String phoneNumber = "";
        int number = 0;
        System.out.println("User's old phone number: " + this.phoneNumber);
        System.out.println("Enter new phone number: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            phoneNumber = reader.readLine();
            number = Integer.parseInt(phoneNumber);
        } catch (IOException e) {
            System.out.println("ERROR in reading phone number!");
        }

        if (phoneNumber.equals("")) {
            System.out.println("ERROR: you didn't enter phone number!");
            number = Integer.parseInt(phoneNumber);
            return number;
        }

        this.setPhoneNumber(number);
        System.out.println("User's new phone number: " + this.phoneNumber);
        System.out.println();

        return number;
    }

    public void outInfo(){
        System.out.println("User's first name: " + this.firstName);
        System.out.println("User's last name: " + this.lastName);
        System.out.println("User's nickname: " + this.nickName);
        System.out.println("User's phone number: " + this.phoneNumber);
    }
}

