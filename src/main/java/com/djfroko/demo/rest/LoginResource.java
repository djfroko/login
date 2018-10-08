package com.djfroko.demo.rest;


import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
public class LoginResource {

    @RequestMapping(method = RequestMethod.POST, path="/login")
    public String login(
            @RequestParam(name = "user") String user,
            @RequestParam(name = "pass")  String pass){
        String respuesta = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8889/login",
                    "root",
                    "root");
            Statement statement = connection.createStatement();
            ResultSet busqueda = statement.executeQuery("select * from usuarios where user ='"+user+"' and pass='"+pass+"'");

            if (busqueda.first()){
                respuesta = "usuario encontrado";
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}
