package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * Servlet implementation class geolocationServlet
 */
public class geolocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public geolocationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Make a POST request not a GET request");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		String location=request.getParameter("location");
		
		String map_url = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+location+".json?access_token=pk.eyJ1IjoiZGhydXZhcnlhbjAzIiwiYSI6ImNrYmNjbHZmdjAwbmQydHBtc2p1emhhZjMifQ.1jMMc-uRBuXjiPaaC2Slfw";
		map_url = map_url.replace(" ", "%20");
		
		URL urlForGetRequest = new URL(map_url);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    
	    location = location.toUpperCase();
	    
	    conection.setRequestMethod("GET");
	    int responseCode = conection.getResponseCode();
	    Double res[] = new Double[2];
	    
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
	        
	        StringBuffer resp = new StringBuffer();
	        while ((readLine = in .readLine()) != null) {
	            resp.append(readLine);
	        } in .close();
	        System.out.println(resp.toString());
	        
	        try {
	        	String big_string = resp.toString();
	        	
	        	int ind = big_string.indexOf("\"center\"");
	        	int beg = big_string.indexOf(":", ind) + 2;
	        	int end = big_string.indexOf(",", ind);
	        	int beg1 = end + 1;
	        	int end1 = big_string.indexOf("]", beg1);
	        	

				res[0] = Double.parseDouble(big_string.substring(beg, end));
				res[1] = Double.parseDouble(big_string.substring(beg1, end1));
				
				out.println("<html>");
				out.println("<head><title>Geo Location</title></head>");
		        out.println("<body style='text-align:center; font-size:x-large';>");
		        out.println("<h2>Location: "+location+"</h2>");
		        out.println("<h3>Latitude: " +res[0]+ " Longitude: " +res[1]+ "</h3>");
				out.println("<form action=\"weatherServlet\" method=\"post\" >");
				out.println("<input value=\""+res[0]+"\" name=\"latitude\" readonly=\"readonly\">");
				out.println("<br/>");
				out.println("<input value=\""+res[1]+"\" name=\"longitude\" readonly=\"readonly\">");
				out.println("<br/><br/>");
				out.println("<input type=\"Submit\" value=\"Predict Weather\">");
				out.println("</form>");
				out.println("</body></html>");
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
	        
	        
	    } else {
	        out.println("GET DOES NOT WORK");
	    }
//		
	}

}
