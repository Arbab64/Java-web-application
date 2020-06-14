<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Geo Location</title>
		<script src='https://api.mapbox.com/mapbox-gl-js/v1.8.1/mapbox-gl.js'></script>
		<link href='https://api.mapbox.com/mapbox-gl-js/v1.8.1/mapbox-gl.css' rel='stylesheet' />
		
	</head>
	<body style='text-align:center;margin:40px'>
		<div id='map' style='width: 100%; height: 550px; '></div>
		<script>
			mapboxgl.accessToken = 'pk.eyJ1IjoiYWJoaXJhbW4iLCJhIjoiY2thdWt2eGU2MTNzdjJ0cGJxYjhubDgyaCJ9.LLllIJl_Q9koDSw3kXQEfw';
			var map = new mapboxgl.Map({
			container: 'map',
			style: 'mapbox://styles/mapbox/streets-v11',
			center: [-73.9808, 40.7648], // starting position [lng, lat]
			zoom: 6 // starting zoom
			});
		</script>
		<br/>
		<br/>
		<form action="geolocationServlet" method="post">
			<input placeholder="Enter location" name="location" style='font-size:large; width:500px;'>
			<br/>
			<br/>
			<input type="submit" value="Retrieve Coordinates" style='font-size:15px'>
		</form>
		
		
	</body>
</html>