
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.*;

public class answer extends HttpServlet {

    private String answer(String question) {
        String[] quest
                = {"FPT tuyển sinh như thế nào ?",
                    "Điều kiện miễn thi sơ tuyển là như thế nào ?",
                    "Thi sơ tuyển ở FPT như thế nào ?",
                    "OK cảm ơn nhé .",};
        String answer = null;

        if (question.equalsIgnoreCase(quest[0])) {
            answer = "Các thí sinh thuộc đối tượng tuyển sinh có nguyện vọng theo học tại Trường đại học FPT cần đáp ứng đủ 2 tiêu chí :  tham dự và trúng tuyển kỳ thi sơ tuyển của Trường đại học FPT hoặc đủ điều kiện miễn thi sơ tuyển của Trường đại học FPT.";
        } else if (question.equalsIgnoreCase(quest[1])) {
            answer = "Để được miễn sơ tuyển thì tổng điểm 3 môn  (mỗi môn tính trung bình hai học kỳ cuối truung học phổ thông) của bạn cần  đạt 18 điểm trở lên xét theo tổ hợp môn tương ứng với ngành đăng ký học tại Trường đại học FPT.";
        } else if (question.equalsIgnoreCase(quest[2])) {
            answer = "Để trúng tuyển vào đại học fpt thì tổng điểm 3 môn trong kỳ thi trung học phổ thông đạt 15 điểm trở lên (đã bao gồm điểm ưu tiên theo quy định của Bộ Giáo dục và Đào tạo) xét theo tổ hợp môn tương ứng với ngành đăng ký học tại Trường đại học  FPT.";
        } else if (question.equalsIgnoreCase(quest[3])) {
            answer = "Vâng mọi thắc mắc bạn có thể liên hệ cho tôi hoặc theo đường dây nóng  0 2 4 7 3 0 0 5 5 8 8";
        } else {
            answer = "Câu hỏi của bạn nằm ngoài kiến thức của tôi mất rồi.";
        }
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
        String answer = null; 
        //get data from client side using buffer 
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("BufferedReader Error: " + e);
        }
        //convert json object to string
        Object obj = JSONValue.parse(sb.toString());
        JSONObject jsonObject = (JSONObject) obj;
        //debug line 1 
        System.out.println(jsonObject + " ----- line 1 ");
        //get property question from client-side
        //the data from client is {"question": "user's question"}
        String quest = (String) jsonObject.get("question");
        //debug line 2
        System.out.println(quest + " ----- line 2 ");
        answer = answer(quest);
        //response to client an answer 
        out.print(answer);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
