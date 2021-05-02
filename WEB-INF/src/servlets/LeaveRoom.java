package com.app.servlets;
import javax.servlet.http.*;
import com.google.gson.Gson;

import jdk.nashorn.internal.ir.ReturnNode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import com.google.gson.Gson;
import com.app.beans.*;
import java.util.*;
public class LeaveRoom extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    BufferedReader reader = request.getReader();
    Gson gson = new Gson();
    RoomBean rb = gson.fromJson(reader,RoomBean.class);
    Return
    }

    public String leave(LeaveRoomBean lbean){
    try{
        Connection con  = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("update room set r_status=?,s_id=0 where _id=?;");
        String Message="";
        ps.setString(1,"AVAILABLE");
        ps.setInt(2,lbean.room_id);
        if(ps.executeUpdate()>0){
            Message="SUCCESS";
        }
        else Message="NO_SUCCESS";
        rs.close();
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
