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

public class AddStudentRoom extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    Gson gson = new Gson();
    BufferedReader reader = request.getReader();
    AddStudentRoomBean rb = gson.fromJson(reader,AddStudentRoomBean.class);
    // int s_id=(int)request.getAttribute("id");
    int s_id=3;
    if(rb.room_id!=0){
        response.getWriter().write(gson.toJson(new ReturnBean(addStudentRoom(rb,s_id))));
    }
    else{
        response.getWriter().write(gson.toJson(new ReturnBean("DONT LEAVE THE FIELD EMPTY")));
    }
}

    public String addStudentRoom(AddStudentRoomBean rbean,int s_id){
        Connection con  = DBConnection.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("update room set r_status=?,s_id=? where _id=?;");
            String Message="";
            ps.setString(1,"NOAVAILABLE");
            ps.setInt(2,s_id);
            ps.setInt(3,rbean.room_id);
            System.out.println(ps);
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
    
