import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import com.google.gson.Gson;
import java.util.*;
public class Test extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        response.setContentType("application/json");
        Gson gson = new Gson();
        List li=new ArrayList<RoomBean>();
        for(int i=0;i<4;i++){
            li.add(new RoomBean());
        }
        response.getWriter().write(gson.toJson(li));
        System.out.println("After sent");
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        RoomBean tb = gson.fromJson(reader,RoomBean.class);
        // System.out.println(tb.name+tb.age);
    }
}

