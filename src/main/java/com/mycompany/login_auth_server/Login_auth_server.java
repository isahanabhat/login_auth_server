/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.login_auth_server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 *
 * @author bhats
 */
public class Login_auth_server {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        
        DatabaseReader dr = new DatabaseReader();
        
        
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/login", new Login(dr));
        
        server.setExecutor(Executors.newFixedThreadPool(5));
        server.start();
        System.out.println("Web server started"); 
        
    }
}
