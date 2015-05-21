

import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Created by User on 18.05.2015.
 */
public class StartApp {
    public static String goalsFile = "GoalsList.xml";
    public static String userFile = "UserInfo.txt";
    public static int goalsNumber = 0;

    public static void main(String[] args) throws Exception {
        User user = new User();
        read(userFile, user);
        Goal goal = new Goal();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        user.outInfo();
        String choice = "0";
        do {
            choice = "0";
            System.out.println();
            System.out.println("What do you want to do?");
            System.out.println("1 - Change nickname");
            System.out.println("2 - Change phone number");
            System.out.println("3 - See goals list");

            System.out.println();
            System.out.println("Please, enter the choice ('0' - exit the program): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try
            {
                choice = reader.readLine();
            }
            catch (IOException e)
            {
                System.out.println("ERROR in reading the number of choice!");
            }


            if (choice.equals("1")){
                String newNickname = user.changeNickname();
                writeNicknameToFile(userFile, newNickname);
                user.outInfo();
            }
            else if (choice.equals("2")){
                int newPhoneNumber = user.changePhoneNumber();
                writePhoneNumberToFile(userFile, newPhoneNumber);
                user.outInfo();
            }
            else if (choice.equals("3")){
                goal.print();
                menu2();
            }
        }
        while (choice.equals("0") == false);
    }

    public static void read(String fileName, User user) throws IOException {
        FileReader file = new FileReader(fileName);
        BufferedReader br = new BufferedReader(file);

        String str = br.readLine();

        user.setNickName(str);

        str = br.readLine();

        int number = Integer.parseInt(str);
        user.setPhoneNumber(number);
        br.close();
        file.close();
    }

    public static void writeNicknameToFile(String fileName, String newNickname) throws IOException {
        FileReader file = new FileReader(fileName);
        BufferedReader br = new BufferedReader(file);

        String str = br.readLine();
        str = br.readLine();

        FileWriter file2 = new FileWriter(fileName);
        file2.write(newNickname);
        String newLine = System.getProperty("line.separator");
        file2.write(newLine);
        file2.write(str);
        br.close();
        file.close();
        file2.close();
    }

    public static void writePhoneNumberToFile(String fileName, int newPhoneNumber) throws IOException {
        FileReader file = new FileReader(fileName);
        BufferedReader br = new BufferedReader(file);

        String str = br.readLine();

        FileWriter file2 = new FileWriter(fileName);
        file2.write(str);
        String newLine = System.getProperty("line.separator");
        file2.write(newLine);
        str = Integer.toString(newPhoneNumber);
        file2.write(str);
        br.close();
        file.close();
        file2.close();
    }

    public static void menu2() throws Exception {
        String choice = "0";
        Goal goal = new Goal();
        do {
            System.out.println();
            System.out.println("What do you want to do?");
            System.out.println("1 - Add new goal");
            System.out.println("2 - Edit exist goal");
            System.out.println("3 - Make goal reached/unreached");
            System.out.println("4 - See goals list");

            System.out.println();
            System.out.println("Please, enter the choice ('0' - go to previous menu): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                choice = reader.readLine();
            }
            catch (IOException e)
            {
                System.out.println("ERROR in reading the number of choice!");
            }
            System.out.println();


            if (choice.equals("1")) {
                goal.addNewGoal();
                goalsNumber += 1;
                System.out.println(goalsNumber);

            }
            else if (choice.equals("2")) {
                String n = "1";
                System.out.println("Please, enter the id of goal you want to update: ");
                reader = new BufferedReader(new InputStreamReader(System.in));
                try
                {
                    n = reader.readLine();
                }
                catch (IOException e)
                {
                    System.out.println("ERROR in reading the number of choice!");
                }
                System.out.println();
                goal.updateGoal(Integer.parseInt(n));
            }
            else if (choice.equals("3")) {
                String n = "1";
                System.out.println("Please, enter the id of goal you want to update: ");
                reader = new BufferedReader(new InputStreamReader(System.in));
                try
                {
                    n = reader.readLine();
                }
                catch (IOException e)
                {
                    System.out.println("ERROR in reading the number of choice!");
                }
                System.out.println();
                goal.makeReached(Integer.parseInt(n));
            }
            else if (choice.equals("4")) {
                System.out.println();
                goal.print();
            }
        }
        while (choice.equals("0") == false);
    }

}
