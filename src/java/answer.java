
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.*;

public class answer extends HttpServlet {

    private String answer(String question) {
        String answer = null;
        return answer;
    }

    //GET METHOD
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        out.println("ready to get");
    }

    //POST METHOD
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        StringBuffer sb = new StringBuffer();
        String line = null;
        //get data from client side using buffer 
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("BufferedReader Error: " + e);
        }
//        String s = "{\"name\":\"sonoo\",\"salary\":600000.0,\"age\":27}";
        Object obj = JSONValue.parse(sb.toString());
        JSONObject jsonObject = (JSONObject) obj;
        String quest = (String) jsonObject.get("question");
        out.print(quest);
        answer(quest);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
