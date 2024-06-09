/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login_auth_server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author bhats
 */
public class Login implements HttpHandler {
    
    private DatabaseReader dr;
    public Login(DatabaseReader dr){
        this.dr = dr;
    }
    
    @Override
    public void handle(HttpExchange t) throws IOException {
        
        System.out.println("Request received");
        String query = t.getRequestURI().toString();
        String request = query.split("[?]",2)[1];
        
        String[] param = request.split("[&]");
        String username = param[0].split("[=]")[1];
        String password = param[1].split("[=]")[1];
               
        String response = dr.queryUserTable(username, password);
        System.out.println(response);
        
        t.sendResponseHeaders(200, response.length());
        OutputStream os =  t.getResponseBody();
        os.write(response.getBytes());
        System.out.println("Response sent");
        os.close();
    }
}
