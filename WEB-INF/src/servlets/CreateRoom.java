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
public class CreateRoom extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    // String type=(String)request.getAttribute("type");
    String type="ADMIN";
    Gson gson = new Gson();
    if(type.equals("ADMIN")){
        BufferedReader reader = request.getReader();
        RoomBean rb = gson.fromJson(reader,RoomBean.class);
        System.out.println(rb.type+rb.location+rb.charge+rb.available+rb.paymentDone);
        if(rb.type!=null && rb.location!=0 && rb.charge!=0 && rb.available !=null && rb.paymentDone!=null){
            response.getWriter().write(gson.toJson(new ReturnBean(createRoom(rb))));
        }
        else{
            response.getWriter().write(gson.toJson(new ReturnBean("DONT LEAVE THE FIELD EMPTY")));
        }
    }
    else{
        response.getWriter().write(gson.toJson(new ReturnBean("YOU ARE NOT SUPPOSED TO CREATE ROOM")));
    }
    
    }

    public String createRoom(RoomBean rbean){
        Connection con  = DBConnection.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("insert into room(type,location,charge,r_status,p_status) values(?,?,?,?,?);");
            String Message="";
            ps.setString(1,rbean.type);
            ps.setInt(2,rbean.location);
            ps.setInt(3,rbean.charge);
            ps.setString(4,rbean.available);
            ps.setString(5,rbean.paymentDone);
            if(ps.executeUpdate()>0){
                Message="SUCCESS";
            }
            else Message="NO_SUCCESS";
            ps.close();
            return Message;
           }
        catch(Exception e){
            e.printStackTrace();
            return "ERROR";
            }   
        finally{
            try{
                con.close();
            }
            catch(Exception ex){ex.printStackTrace();}
        }
    }
    }
