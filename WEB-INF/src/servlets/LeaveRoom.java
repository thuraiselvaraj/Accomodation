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

public class LeaveRoom extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    BufferedReader reader = request.getReader();
    Gson gson = new Gson();
    LeaveRoomBean rb = gson.fromJson(reader,LeaveRoomBean.class);
    response.getWriter().write(gson.toJson(new ReturnBean(leave(rb))));
    }

    public String leave(LeaveRoomBean lbean){
        Connection con  = DBConnection.getConnection();
    try{
        PreparedStatement ps = con.prepareStatement("update room set r_status=?,s_id=null where _id=?;");
        String Message="";
        ps.setString(1,"AVAILABLE");
        ps.setInt(2,lbean.room_id);
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
