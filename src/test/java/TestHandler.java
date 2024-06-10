
import com.mycompany.login_auth_server.DatabaseReader;
import com.mycompany.login_auth_server.Login;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.Executors;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
public class TestHandler {
    
    
    private DatabaseReader dr = null;
    public TestHandler(){
        
    }
    @Test
    public void test_01() throws IOException{
        DatabaseReader dr = new DatabaseReader();
        
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/login", new Login(dr));
        
        server.setExecutor(Executors.newFixedThreadPool(5));
        server.start();
        System.out.println("Started");
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost:8000/login?username=alice&password=12345");
        HttpResponse httpresponse = httpclient.execute(httpget);
        Assert.assertEquals(200, httpresponse.getStatusLine().getStatusCode());
        
        Scanner sc = new Scanner(httpresponse.getEntity().getContent());
        while(sc.hasNext()) {
            Assert.assertEquals("{\"status\":\"SUCCESS\"}", sc.nextLine());
        }
        
        server.stop(0);
        System.out.println("Stopped");
    }
    
    @Test
    public void test_02() throws IOException{
        DatabaseReader dr = new DatabaseReader();
        
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/login", new Login(dr));
        
        server.setExecutor(Executors.newFixedThreadPool(5));
        server.start();
        System.out.println("Started");
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost:8000/login?username=sahana&password=43243");
        HttpResponse httpresponse = httpclient.execute(httpget);
        Assert.assertEquals(200, httpresponse.getStatusLine().getStatusCode());
        
        Scanner sc = new Scanner(httpresponse.getEntity().getContent());
        while(sc.hasNext()) {
            Assert.assertEquals("{\"status\":\"FAILURE\"}", sc.nextLine());
        }
        
        server.stop(0);
        System.out.println("Stopped");
    }
}
