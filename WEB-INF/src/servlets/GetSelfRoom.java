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

public class GetSelfRoom extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    Gson gson = new Gson();
        BufferedReader reader = request.getReader();
        RoomBean rb = gson.fromJson(reader,RoomBean.class);
        List al=new ArrayList<RoomBean>();
        int s_id=(int)request.getAttribute("id");
        //  int s_id=3;
         GetSelfroom(s_id,al);
        response.getWriter().write(gson.toJson(al));
    }
  ///s_d can be get from request attr; 
    public String GetSelfroom(int s_id,List al){
        Connection con  = DBConnection.getConnection();
        try{
            
            PreparedStatement ps = con.prepareStatement("select * from room where s_id=?");
            ps.setInt(1,s_id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                RoomBean rb=new RoomBean();
                rb.room_id=rs.getInt("_id");
                rb.type=rs.getString("type");
                rb.charge=rs.getInt("charge");
                rb.available=rs.getString("r_status");
                rb.paymentDone=rs.getString("p_status");
                rb.location=rs.getInt("location");
                al.add(rb);
                rs.close();
                ps.close();

                return "SUCCESS";
            }
           else{
               return Getrooms(al);
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

    public String Getrooms(List al){
        Connection con  = DBConnection.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("select * from room where r_status=?");
            ps.setString(1,"AVAILABLE");
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
