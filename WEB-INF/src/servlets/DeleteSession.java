package com.app.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import com.google.gson.Gson;
import com.app.dbutils.DBConnection;
import java.io.*;
import com.app.beans.*;
import java.util.*;

public class DeleteSession extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    Gson gson=new Gson();
    response.getWriter().write(gson.toJson(new ReturnBean(deleteSession((int)request.getAttribute("id")))));
    }


public String deleteSession(int id){
    Connection con  = DBConnection.getConnection();
    try{
        PreparedStatement ps=con.prepareStatement("delete from session_table where _id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        return "SUCCESS";
    }
    catch(Exception e){
        e.printStackTrace();
        try{
            con.close();
        }
        catch(Exception ex){ex.printStackTrace();}
        return "ERROR";
    }
}
}   