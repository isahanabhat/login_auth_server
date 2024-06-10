
import com.mycompany.login_auth_server.DatabaseReader;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bhats
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDatabaseReader {
    private DatabaseReader dr = null;
    public TestDatabaseReader(){
        
    }
    @Test
    public void test_01() { // success
        dr = new DatabaseReader();
        String username = "bob";
        String password = "26394";
        String response = dr.queryUserTable(username, password);
        Assert.assertEquals("{\"status\":\"SUCCESS\"}", response);
    }
    @Test
    public void test_02() { // failure
        dr = new DatabaseReader();
        String username = "sahana";
        String password = "07897";
        String response = dr.queryUserTable(username, password);
        Assert.assertEquals("{\"status\":\"FAILURE\"}", response);
    }
    
}
