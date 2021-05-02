package com.app.servlets;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import com.google.gson.Gson;

public class AddStudentRoom extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    BufferedReader br =  new BufferedReader(new InputStreamReader(request.getInputStream()));
    String json = "";
    if(br != null){
        json = br.readLine();
        System.out.println(json);
    }
    }

    public String addStudentRoom(AddStudentRoom rbean){
        
    }


}
