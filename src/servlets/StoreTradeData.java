//package servlets;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.HTTP;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// * Servlet implementation class StoreTradeData
// */
//@WebServlet("/StoreTradeData")
//public class StoreTradeData extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public StoreTradeData() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		StringBuffer jb=new StringBuffer();
//		String line=null;
//		try {
//			BufferedReader reader=request.getReader();
//			while((line = reader.readLine()) != null)
//			{
//				jb.append(line);
//			}
//		}
//		catch(Exception e)
//		{
//			System.out.println("Request is not fullfilled");
//		}
//		try {
//			JSONObject jsonobj=HTTP.toJSONObject(jb.toString());
//		}
//		catch(JSONException e)
//		{
//			throw new IOException("Error while parsing JSON");
//		}
//		doGet(request, response);
//	}
//
//}
