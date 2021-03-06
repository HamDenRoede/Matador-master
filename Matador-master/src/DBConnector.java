import java.sql.*;
import java.util.ArrayList;

public class DBConnector implements IO
{
    // JDBC driver name and database URL
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/matador";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "jonassqlserver1616";


    @Override
    public String[] readFieldData(String path)
    {
        String[] contents = new String[40];
        Connection conn = null;
        Statement stmt = null;
        try
        {
            //STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Field";

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next())
            {
                //Retrieve by column name
                int id = rs.getInt("id");
                String field_type = rs.getString("type");
                String label = rs.getString("label");
                int cost = rs.getInt("cost");
                int income = rs.getInt("income");
                int seriesID = rs.getInt("seriesID");


                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Type: " + field_type);
                System.out.print(", Label: " + label);
                System.out.println(", Cost: " + cost);
                System.out.println(", Income: " + income);
                System.out.println(", seriesID " + seriesID);
                contents[id-1] = id+ "," + field_type + "," + label + "," + income + "," + cost + "," + income + "," + seriesID;

            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally
        {
            //finally block used to close resources
            try
            {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2)
            {
            }// nothing we can do
            try
            {
                if (conn != null)
                    conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }//end finally try
        }//end try
        return contents;
    }



    @Override
    public ArrayList<Player> readGameData()
    {
        ArrayList<Player> players = new ArrayList<Player>();
        //String[] contents = new String[40];
        Connection conn = null;
        Statement stmt = null;
        try
        {
            //STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Player";

            //sql = "SELECT id, name, balance, position, turns left, next";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next())
            {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int balance = rs.getInt("balance");
                int position = rs.getInt("position");
                int turnsleft = rs.getInt("turnsleft");
                boolean next = rs.getBoolean("Next");


                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.print(", Balance: " + balance);
                System.out.println(", Position: " + position);
                System.out.println(", Turns left: " + turnsleft);
                System.out.println(", Next: " + next);
                //contents[id-1] = id+ "," + name + "," + balance + "," + position + "," + turnsleft + "," + next;

                players.add(new Player(id, name, balance, position, turnsleft, next));
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally
        {
            //finally block used to close resources
            try
            {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2)
            {
            }// nothing we can do
            try
            {
                if (conn != null)
                    conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }//end finally try
        }//end try
            return players;
    }

    @Override
    public void setOwnershipData(Player p, String data)
    {

    }

    @Override
    public String[] readCardData(String path)
    {
        return new String[0];
    }

    @Override
    public void saveGameData()
    {

    }

    @Override
    public void deleteSavedGame()
    {

    }
}

