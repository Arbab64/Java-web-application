<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Get Location</title>
		
	</head>
	<body style='text-align:center;margin:40px'>
		<a href="index.jsp">Home</a>|
		<a href="about.jsp">About</a>|
		<a href="GeoLocation.jsp">Get location</a>
		<form action="geolocationServlet" method="post">
			<input placeholder="Enter location" name="location" style='font-size:large; width:500px;'>
			<input type="submit" value="Retrieve Coordinates" style='font-size:15px'>
		</form>
		
		
	</body>
</html>