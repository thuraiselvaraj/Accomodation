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
public class SessionFilter implements Filter{
    SessionFilter(){
       
    }
    @Override
    public void doFilter(HttpServletRequest req,HttpServletResponse res,FilterChain chain) throws ServletException,IOException{
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
        try{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select _id from session_table where session_key=?");
        ps.setString(1,session_key);
        System.out.println(ps);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
           System.out.println("Found session");
           req.setAttribute("id",rs.getString("type"));
           rs.close();
           ps.close();
       }
        else{
          System.out.println("Cannot find the session");
          res.sendRedirect("/login.html");//need to be modified
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
}
