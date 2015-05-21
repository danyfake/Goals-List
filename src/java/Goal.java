

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Created by User on 21.05.2015.
 */
public class Goal {
    private Connection con;
    String name;
    String description;
    boolean reached;
    int id;

    public void print() {
        String url = "jdbc:mysql://localhost/goalslist?characterEncoding=utf8";
        String name = "root";
        String password = "0360";
        try {
            con = DriverManager.getConnection(url, name, password);
            System.out.println("Connected.");
            Statement st = con.createStatement();
            String query = "select * from goals";
            ResultSet rs = st.executeQuery(query);
            printResults(rs);
            System.out.println("Disconnected.");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewGoal(){
        String url = "jdbc:mysql://localhost/goalslist?characterEncoding=utf8";
        String namer = "root";
        String password = "0360";
        try {
            con = DriverManager.getConnection(url, namer, password);
            System.out.println("Connected.");

            String query = "INSERT INTO goals values(id, ?, ?, FALSE)";
            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, getName());
            st.setString(2, getDescription());

            st.executeUpdate();
            st.close();
            System.out.println("Disconnected.");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printResults(ResultSet rs) throws SQLException {
        while (rs.next()) {
            name = rs.getString("name");
            description = rs.getString("description");
            reached = rs.getBoolean(4);
            id = rs.getInt("id");
            System.out.println("******************************");
            System.out.println("id: " + id);
            System.out.println("name: " + name);
            System.out.println("description: " + description);
            System.out.println("reached: " + reached);
            System.out.println("******************************");
        }
    }

    public void updateGoal(int id){
        String url = "jdbc:mysql://localhost/goalslist?characterEncoding=utf8";
        String namer = "root";
        String password = "0360";
        try {
            con = DriverManager.getConnection(url, namer, password);
            System.out.println("Connected.");
            String query = "UPDATE goals SET name = ?, description = ? WHERE id=?";
            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, getName());
            st.setString(2, getDescription());
            st.setString(3, String.valueOf(id));

            st.executeUpdate();
            st.close();
            System.out.println("Disconnected.");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getName(){
        System.out.println("Please enter the name of goal (6 to 20 character): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            name = reader.readLine();
            if (checkNameCondition(name) == false) {
                System.out.println("ERROR: Wrong characters!");
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("ERROR in reading name!");
        }
        return name;
    }

    private String getDescription(){
        System.out.println("Please enter the description of goal (10 to 50 character): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            description = reader.readLine();
            if (checkDescriptionCondition(description) == false) {
                System.out.println("ERROR: Wrong characters!");
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("ERROR in reading name!");
        }
        return description;
    }

    public boolean checkNameCondition(String name) {
        if ((name.length() < 6) || (name.length() > 20)) {
            return false;
        }
        String[] b = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "{", "}", "<", ">", "\"", "'", "|", "?", ":", ";"};
        for (int i = 0; i < 20; i++) {
            if (name.contains(b[i])) // + ещё можно строку в которой ищем - перевести в нижний регистр toLowerCase
            {
                return false;
            }
        }
        return true;
    }

    public boolean checkDescriptionCondition(String description) {
        if ((description.length() < 10) || (description.length() > 50)) {
            return false;
        }
        return true;
    }

    public void makeReached(int id){
        String url = "jdbc:mysql://localhost/goalslist?characterEncoding=utf8";
        String namer = "root";
        String password = "0360";
        try {
            con = DriverManager.getConnection(url, namer, password);
            String query = "UPDATE goals SET reached = ? WHERE id=?";
            PreparedStatement st = con.prepareStatement(query);

            st.setBoolean(1, getReached(id));
            st.setString(2, String.valueOf(id));
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean getReached(int id){
        String url = "jdbc:mysql://localhost/goalslist?characterEncoding=utf8";
        String name = "root";
        String password = "0360";
        boolean oldReachedValue = false;
        boolean newReachedValue = false;
        try {
            con = DriverManager.getConnection(url, name, password);

            PreparedStatement st = null;
            st = con.prepareStatement("SELECT * FROM goals where id = ?");

            st.setInt(1, id);
            ResultSet rs =  st.executeQuery();
            rs.next();
            oldReachedValue = rs.getBoolean(4);

            if (oldReachedValue == true) {newReachedValue = false;}
            else {newReachedValue = true;}

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newReachedValue;
    }
}
