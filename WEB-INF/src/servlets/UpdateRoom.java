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

public class UpdateRoom extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    Gson gson = new Gson();
       // String type=(String)request.getAttribute("type");
    String type="ADMIN";
    System.out.println("Update method");
    if(type.equals("ADMIN")){
        BufferedReader reader = request.getReader();
        UpdateBean ub = gson.fromJson(reader,UpdateBean.class);
        
        if(ub.type!=null &&  ub.charge!=0 &&  ub.paymentDone!=null){
            response.getWriter().write(gson.toJson(new ReturnBean(Update(ub))));
        }
        else{
            response.getWriter().write(gson.toJson(new ReturnBean("DONT LEAVE THE FIELD EMPTY")));
        }
    }
    else{
        response.getWriter().write(gson.toJson(new ReturnBean("YOU ARE NOT SUPPOSED TO CREATE ROOM")));
    }
    }

    public String Update(UpdateBean ubean){
        Connection con  = DBConnection.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("update room set type=?,charge=?,p_status=? where _id=?;");
            String Message="";
            ps.setString(1,ubean.type);
            ps.setInt(2,ubean.charge);
            ps.setString(3,ubean.paymentDone);
            ps.setInt(4,ubean.room_id);
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
