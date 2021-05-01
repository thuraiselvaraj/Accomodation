package com.app.servlets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import javax.servlet.http.*;
import com.google.gson.Gson;
import com.app.*;
public class UserManager extends HttpServlet{
   

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
    
    Gson json=new Gson();
    }


    public String checkIfUserExistsAndDoLogin(HttpServletResponse response,LoginBean lbean){
        try{
        Connection con  = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from login_table where email=?");
        ps.setString(1,lbean.email);
        ResultSet rs=ps.executeQuery();   
        System.out.println("Inside the loginCheked");
        if(rs.next()){
            String Email=rs.getString("email");
            String Password=rs.getString("password");
            int Id=rs.getInt("_id");
            String TYPE=rs.getInt("type");
            rs.close();
            ps.close();
            if(lbean.email.equals(Email)){
                if(lbean.password.equals(Password)){
                    String session_key=createSession(Id);
                    Cookie cookie=new Cookie("SessionKey",session_key);
                    response.addCookie(cookie);
                    return "SUCCESS";
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



    public String CreateUser(SigninBean sbean){
        try{
            Connection con  = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from login_table where email=?");
            ps.setString(1,lbean.email);
            ResultSet rs=ps.executeQuery();   
            System.out.println("Inside the loginCheked");
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

    public String createSession(int Id){
        String SessionKey=Security.get_md5(Security.get_random_string());
        try{
            Connection con  = DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("insert into session_table(_id,session_key) values(?,?) ;");
            ps.setString(2,SessionKey);
            return SessionKey;
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                con.close();
            }
            catch(Exception ex){ex.printStackTrace();}
            return "";
        }
    }

    public void finalize() throws Throwable{
        
    }


    public byte deleteSession(String SessionKey){
        try{
            Connection con  = DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("delete from session_table where session_key=?");
            ps.setString(1,SessionKey);
            ps.executeUpdate();
            ps.close();
            return SUCCESS;
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                con.close();
            }
            catch(Exception ex){ex.printStackTrace();}
            return ERROR;
        }
    }   
}