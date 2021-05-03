package com.app.filters;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import javax.servlet.Filter;
import javax.servlet.http.*;
import com.app.dbutils.DBConnection;
import javax.servlet.*;
public class SessionFilter implements Filter{
    public SessionFilter(){}
    @Override
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) {
       
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        String path = ((HttpServletRequest) req).getRequestURI();
        System.out.println(((HttpServletRequest) req).getRequestURI());


    if(!path.contains("index") && !path.contains("register") && !path.contains("signin") && !path.contains("css") && !path.contains("js") && !path.contains("login")){
        String session_key="";
        Cookie[] cookies = null;
        cookies = req.getCookies();
        if(cookies==null){cookies=new Cookie[]{};}
        for(Cookie ck:cookies){
            System.out.println(ck.getName()+"  "+ck.getValue());
            if(ck.getName().equals("SessionKey")){
                System.out.println("Found");
                session_key=ck.getValue();
                break;
            }
        }
        Connection con=DBConnection.getConnection();
        try{
        PreparedStatement ps=con.prepareStatement("select login_table.type,login_table._id from session_table join login_table using(_id) where session_key=?");
        ps.setString(1,session_key);
        System.out.println(ps);
        ResultSet rs=ps.executeQuery();
        String type="";
        if(rs.next()){
           System.out.println("Found session");
           type=rs.getString("type");
           req.setAttribute("type",type);
           req.setAttribute("id",rs.getInt("_id"));
           rs.close();
           ps.close();
           System.out.println(path);
           if(path.equals("/")){
               if(type.equals("ADMIN")){
                res.sendRedirect("/views/home.html");
               }
               else{
                res.sendRedirect("/views/studenthome.html");
               }
             }
            chain.doFilter(req, res);
          }
        else{
          System.out.println("Cannot find the session");
              res.sendRedirect("/views/index.html");
          }      
        }
     catch(Exception e){
        e.printStackTrace();
        try{
            con.close();
        }
        catch(Exception ex){ex.printStackTrace();}
    }

   }
   else{
    try{
        System.out.println("Inside else");
        chain.doFilter(req, res);

    }
    catch(Exception e){e.printStackTrace();}
    
   }
    }
}
