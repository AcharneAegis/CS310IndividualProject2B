
package databasetest;

import java.sql.*;
import org.json.simple.*;

/**
 *
 * @author Payton
 */
public class DatabaseTest {

    //public static void main(String[] args) {
    public static JSONArray getJSONData(){
        // TODO code application logic here
        
      
        Connection conn = null;
        PreparedStatement pstSelect = null, pstUpdate = null;
        ResultSet resultset = null;
        //ResultSetMetaData metadata = null;
        
        String query;
        //String[] keys = new String[8];
        //String newFirstName = "Alfred", newLastName = "Neuman";
        JSONObject jsonobject = new JSONObject();
        JSONArray arrayOfObjects = new JSONArray();
        
        boolean hasresults;
        //int resultCount, columnCount, updateCount = 0;
        
        try {
            
            /* Identify the Server */
            
            String server = ("jdbc:mysql://localhost/p2_test");
            String username = "root";
            String password = "Pman2011";
            System.out.println("Connecting to " + server + "...");
            
            /* Load the MySQL JDBC Driver */
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            /* Open Connection */

            conn = DriverManager.getConnection(server, username, password);

            /* Test Connection */
            
            if (conn.isValid(0)) {
                
                /* Connection Open! */
                
                System.out.println("Connected Successfully!");
                
                // Prepare Update Query
                
                //query = "INSERT INTO people (firstname, lastname) VALUES (?, ?)";
                query = "SELECT * FROM people";
                pstSelect = conn.prepareStatement(query);
                //pstUpdate.setString(1, newFirstName);
                //pstUpdate.setString(2, newLastName
                
                hasresults = pstSelect.execute();
                
                if(hasresults){
                    resultset = pstSelect.getResultSet();
                    
                    
                    while(hasresults){
                        resultset.next();
                        //System.out.println(resultset.toString());
                    
                        //String id = resultset.getString("id");
                        String fName = resultset.getString("firstname");
                        String mInitial = resultset.getString("middleinitial");
                        String lName = resultset.getString("lastname");
                        String address = resultset.getString("address");
                        String city = resultset.getString("city");
                        String state = resultset.getString("state");
                        String zip = resultset.getString("zip");
                        
                        //jsonobject.put("id", id);
                        jsonobject.put("firstname", fName);
                        jsonobject.put("middleinitial", mInitial);
                        jsonobject.put("lastname", lName);
                        jsonobject.put("address", address);
                        jsonobject.put("city", city);
                        jsonobject.put("state", state);
                        jsonobject.put("zip", zip);
                        
                        arrayOfObjects.add(jsonobject);
                        //System.out.println(arrayOfObjects.toString());
                        jsonobject = new JSONObject();
                    }
                }
                
            }
            
            System.out.println(arrayOfObjects.toString());
            
            /* Close Database Connection */
            
            conn.close();
            
            return arrayOfObjects;
            
        }
        
        catch (Exception e) {
            System.err.println(e.toString());
        }
        
        /* Close Other Database Objects */
        
        finally {
            
            if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
            if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
            if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
            
            return arrayOfObjects;
        }
        
    }
    
}
