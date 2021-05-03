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

public class CreateUser extends HttpServlet{
   

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    Gson gson=new Gson();
    BufferedReader reader = request.getReader();
    SigninBean sbean=gson.fromJson(reader,SigninBean.class);
    response.getWriter().write(gson.toJson(new ReturnBean(Createuser(sbean))));
    }

    public String Createuser(SigninBean sbean){
        Connection con  = DBConnection.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("select * from login_table where email=?");
            ps.setString(1,sbean.email);
            ResultSet rs=ps.executeQuery();   
            if(rs.next()){
                rs.close();
                ps.close();
                return "EMAIL_EXISTS";
                } 
            else{
                ps=con.prepareStatement("insert into login_table(email,password,type) values(?,?,?);");
                ps.setString(1,sbean.email);
                ps.setString(2,sbean.password);
                ps.setString(3,sbean.type);
                ps.executeUpdate();
                ps.close();
                return "SUCCESS";
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