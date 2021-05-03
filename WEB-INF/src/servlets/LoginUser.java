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
import com.app.security.*;

public class LoginUser extends HttpServlet{
   

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    Gson gson=new Gson();
    BufferedReader reader = request.getReader();
    LoginBean lbean=gson.fromJson(reader,LoginBean.class);
    response.getWriter().write(gson.toJson(new ReturnBean(checkIfUserExistsAndDoLogin(response,lbean))));
    }
    public String checkIfUserExistsAndDoLogin(HttpServletResponse response,LoginBean lbean){
        Connection con  = DBConnection.getConnection();
        try{
        PreparedStatement ps = con.prepareStatement("select * from login_table where email=?");
        ps.setString(1,lbean.email);
        ResultSet rs=ps.executeQuery();   
        
        if(rs.next()){
            String Email=rs.getString("email");
            String Password=rs.getString("password");
            int Id=rs.getInt("_id");
            String TYPE=rs.getString("type");
            rs.close();
            ps.close();
            System.out.println("Inside the loginCheked");
            if(lbean.email.equals(Email)){
                if(Security.get_md5(lbean.password).equals(Password)){
                    return createSession(Id,response);
                }
                else{
                    return "CRED_WRONG";
                }
            } 
        }
        return "NO_LOGIN";
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

    public String createSession(int Id,HttpServletResponse response){
        String SessionKey=Security.get_md5(Security.get_random_string());
        Connection con  = DBConnection.getConnection();
        try{
            PreparedStatement ps=con.prepareStatement("insert into session_table(_id,session_key) values(?,?) ;");
            ps.setString(2,SessionKey);
            ps.setInt(1,Id);
            if(ps.executeUpdate()>0){
                ps.close();
                Cookie cookie=new Cookie("SessionKey",SessionKey);
                response.addCookie(cookie);
                return "SUCCESS";
            }
            else{
                return "UNABLE TO LOGIN";
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