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

public class GetRooms extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    Gson gson = new Gson();
        BufferedReader reader = request.getReader();
        RoomBean rb = gson.fromJson(reader,RoomBean.class);
        List al=new ArrayList<RoomBean>();
        Getrooms(al);
        response.getWriter().write(gson.toJson(al));
    }

    public String Getrooms(List al){
        Connection con  = DBConnection.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("select * from room;");
            ResultSet rs=ps.executeQuery();
            boolean flag=false;
            while(rs.next()){
                flag=true;
                RoomBean rb=new RoomBean();
                rb.room_id=rs.getInt("_id");
                rb.type=rs.getString("type");
                rb.charge=rs.getInt("charge");
                rb.available=rs.getString("r_status");
                rb.paymentDone=rs.getString("p_status");
                al.add(rb);
            }
            rs.close();
            ps.close();
            if(flag){
              return "SUCCESS";
            }
           else{
               return "NO_DATA_FOUND";
           }
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
