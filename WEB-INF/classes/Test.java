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
public class Test extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        response.setContentType("application/json");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(new TestBean()));
        System.out.println("After sent");
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    response.setContentType("application/json");
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        TestBean tb = gson.fromJson(reader,TestBean.class);
        System.out.println(tb.name+tb.age);
    }
}

curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"chella","age":1232}' \
  http://localhost:3000/project/Test
