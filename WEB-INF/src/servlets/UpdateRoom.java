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
import com.app.beans.*;
import java.util.*;

public class UpdateRoom extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    }

    public String Update(UpdateBean ubean){
        try{
            Connection con  = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("update room set type=?,charge=?,r_status=?,p_status=? where _id=?;");
            String Message="";
            ps.setString(1,ubean.type);
            ps.setInt(2,ubean.charge);
            ps.setString(3,ubean.available);
            ps.setString(4,ubean.paymentDone);
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
