package com.app.servlets;
import javax.servlet.http.*;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import com.google.gson.Gson;
public class CreateRoom extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    BufferedReader reader = request.getReader();
    Gson gson = new Gson();
    RoomBean rb = gson.fromJson(reader,RoomBean.class);
    return createRoom(rb);
    }

    public String createRoom(RoomBean rbean){
        try{
            Connection con  = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into room(type,location,charge,r_status,p_status) values(?,?,?,?,?);");
            String Message="";
            ps.setString(1,rbean.type);
            ps.setString(2,rbean.location);
            ps.setInt(3,rbean.charge);
            ps.setString(4,rbean.available);
            ps.setString(5,rbean.paymentDone);
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
