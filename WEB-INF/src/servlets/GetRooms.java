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

public class GetRooms extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    List<ExtendedRoomBean> al=new ArrayList<ExtendedRoomBean>();
    }

    public String Getrooms(List al){
        try{
            Connection con  = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from login_table where _id=?");
            ps.setInt(1,room.room_id);
            ResultSet rs=ps.executeQuery();
            boolean flag=false;
            while(rs.next()){
                flag=true;
                ExtendedRoomBean rb=new ExtendedRoomBean();
                rb.room_id=room.room_id;
                rb.type=rs.getString("type");
                rb.charge=rs.getInt("charge");
                rb.available=rs.getString("r_status");
                rb.paymentDone=rs.getString("p_status");
                rb.s_id= rs.getInt("s_id");
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
