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

public class DeleteRoom extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    
    System.out.println("DeleteROom");
    Gson gson = new Gson();
     String type=(String)request.getAttribute("type");
    //  String type="ADMIN";
    if(type.equals("ADMIN")){
        BufferedReader reader = request.getReader();
        LeaveRoomBean rb = gson.fromJson(reader,LeaveRoomBean.class);
        response.getWriter().write(gson.toJson(new ReturnBean(delete(rb))));
    }
    else{
        response.getWriter().write(gson.toJson(new ReturnBean("YOU ARE NOT SUPPOSED TO DELETE ROOM")));
    }
    }

    public String delete(LeaveRoomBean lbean){
        Connection con  = DBConnection.getConnection();
    try{
        PreparedStatement ps = con.prepareStatement("delete from room where _id=?;");
        String Message="";
        ps.setInt(1,lbean.room_id);
        if(ps.executeUpdate()>0){
            Message="SUCCESS";
        }
        else Message="NO_SUCCESS";
        ps.close();
        return Message;
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
