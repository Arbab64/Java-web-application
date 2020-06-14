package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class weatherServlet
 */
public class weatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public weatherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Double latitude = Double.parseDouble(request.getParameter("latitude"));
		Double longitude = Double.parseDouble(request.getParameter("longitude"));
		PrintWriter out = response.getWriter();
		
		
		URL urlForGetRequest = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&appid=5712c085b56b9f65fdadeb3b008ba84f");
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");
	    int responseCode = conection.getResponseCode();
	    Double res[] = new Double[2];
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
	        
	        StringBuffer resp = new StringBuffer();
	        while ((readLine = in .readLine()) != null) {
	            resp.append(readLine);
	        } in .close();

	        try {
	        	System.out.println(resp.toString());
	        	String big_string = resp.toString();
	        	int ind_min = big_string.indexOf("\"temp_min\"");
	        	int beg_min = big_string.indexOf(":", ind_min) + 1;
	        	int end_min = big_string.indexOf(",", ind_min);
	        	int ind_max = big_string.indexOf("\"temp_max\"");
	        	int beg_max = big_string.indexOf(":", ind_max) + 1;
	        	int end_max = big_string.indexOf(",", ind_max);
	        	int ind_pressure = big_string.indexOf("\"pressure\"");
	        	int beg_pressure = big_string.indexOf(":", ind_pressure) + 1;
	        	int end_pressure = big_string.indexOf(",", ind_pressure);
	        	int ind_humid = big_string.indexOf("\"humidity\"");
	        	int beg_humid = big_string.indexOf(":", ind_humid) + 1;
	        	int end_humid = big_string.indexOf(",", ind_humid);
	        	
	        	
				Double min,max; Long pressure, humidity;
				min = Double.parseDouble(big_string.substring(beg_min, end_min));
				max = Double.parseDouble(big_string.substring(beg_max, end_max));
				pressure = Long.parseLong(big_string.substring(beg_pressure, end_pressure));
				humidity = Long.parseLong(big_string.substring(beg_humid, end_humid));
				
				out.println("<html>");
				out.println("<head><title>Weather</title></head>");
		        out.println("<body style='text-align:center';>");
		        out.println("<h1>Weather Details </h1>");
		        out.println("<br>");
		        out.println("<br>");
		        out.println("<br>");
		        out.println("<br>");
		        out.println("<br>");
		        out.println("<br>");
		        out.println("<table border='1' style='margin-left:auto;margin-right:auto' >");
		        out.println("<tr>");
		        out.println("<th>Maximum Temperature</th>");
		        out.println("<td>"+max+" K</td>");
		        out.println("</tr>");
		        out.println("<tr>");
		        out.println("<th>Minimum Temperature</th>");
		        out.println("<td>"+min+" K</td>");
		        out.println("</tr>");
		        out.println("<tr>");
		        out.println("<th>Pressure</th>");
		        out.println("<td>"+pressure+" ATM</td>");
		        out.println("</tr>");
		        out.println("<tr>");
		        out.println("<th>Humidity</th>");
		        out.println("<td>"+humidity+" %</td>");
		        out.println("</tr>");
		        out.println("</table>");
				out.println("</body></html>");
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
	        
	        
	    } else {
	        out.println("GET DOES NOT WORK");
	    }
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
