import com.crooks.Main;
import com.crooks.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by johncrooks on 6/15/16.
 */
public class MainTest {

    public static Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Main.createTable(conn);
        return conn;
    }

    @Test
    public void testUsers() throws SQLException{
        Connection conn = startConnection();
        User u1 = new User(1,"Alice","1014 river haven Circle", "Alice@TIY.com");
        User u2 = new User(2,"BOB","Barbados way", "Bob@TIY.com");
        Main.insertUser(conn, u1);
        Main.insertUser(conn, u2);

        ArrayList<User> userArrayList = Main.selectUsers(conn);
        conn.close();

        assertTrue(userArrayList.size() ==2);
        assertTrue(userArrayList.get(0).getUsername().equals("Alice"));
        assertTrue(userArrayList.get(1).getUsername().equals("BOB"));
    }
    @Test
    public void testDelete() throws SQLException{
        Connection conn = startConnection();


        User u1 = new User(1,"Alice","1014 river haven Circle", "Alice@TIY.com");
        User u2 = new User(2,"BOB","Barbados way", "Bob@TIY.com");
        Main.insertUser(conn, u1);
        Main.insertUser(conn, u2);


        Main.deleteUser(conn, 1);   //This integer is not an arraylist position but the number the DB entry it references... therefore bob remains intact and Alice should be removed...
        ArrayList<User> userArrayList = Main.selectUsers(conn);

        conn.close();
        assertTrue(userArrayList.size()==1);
        assertTrue(userArrayList.get(0).getUsername().equals("BOB"));

    }

    @Test
    public void testEdit() throws SQLException {
        Connection conn = startConnection();
        User u1 = new User(1,"Alice","1014 river haven Circle", "Alice@TIY.com");
        Main.insertUser(conn, u1);
        User u2 = new User(u1.getId(), "bob", "Barbados way", "Bob@TIY.com");

        Main.updateUser(conn, u2);
        ArrayList<User> userArrayList = Main.selectUsers(conn);
        conn.close();

        assertTrue(userArrayList.get(0).getUsername().equals("bob"));


    }
}