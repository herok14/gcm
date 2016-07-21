package module;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.JSONException;
import lib.JSONObject;

/**
 * Created by HungAnh on 7/21/2016.
 */
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Làm ơn chuyển sang sài POST");
        super.doGet(req, resp);
    }
    final  String TOKEN="SADfivfjvlieyverygbwcgwheywe#kfha313124r##@B3r";
    final  int STATUS_SUCCES=1;
    final  int STATUS_ERROR=2;

    JSONObject getJSON(String mess,int codeStatus,String accessToken)
    {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("statuc",codeStatus);
            jsonObject.put("message",mess);
            jsonObject.put("accesToken",accessToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    Map<String,String>user;
    void intit(){
        user=new HashMap<>();
        user.put("t3h", "t3h");
        user.put("admin", "123456");
        user.put("locnguyen", "123456");
        user.put("duynguyen", "123456");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StringBuffer jb=new StringBuffer();
        String line=null;
       try {
           BufferedReader reader=req.getReader();
           while ((line=reader.readLine())!= null)
           {
               jb.append(line);
           }
       }catch (Exception e)
       { }
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject(jb.toString());
            String UserName=jsonObject.getString("username");
            String Pass=jsonObject.getString("pass");

            intit();

            String val=user.get(UserName);
            if(val !=null  && val.equals(Pass))
            {
                resp.getWriter().print(getJSON(null,STATUS_SUCCES,TOKEN));
            }
            else
            {
                resp.getWriter().print(getJSON("Tai khoan khong hop le",STATUS_ERROR,null));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            resp.getWriter().print(getJSON("Loi he thong"+jsonObject.toString(),STATUS_ERROR,null));
        }

    }
}
