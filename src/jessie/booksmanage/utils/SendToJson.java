package jessie.booksmanage.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class SendToJson {
	public static void send(HttpServletResponse response, Object obj) {
		Gson gson = new Gson();
		String json = null;
		if (obj instanceof String) {
			json = (String) obj;
		} else {
			json = gson.toJson(obj);
		}
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("cache-control", "no-cache");
			out = response.getWriter();
			out.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
}
