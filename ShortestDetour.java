package Lyft;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * 
 * @author Ankit Patel
 *
 *
 * Programming Challenge
 *
 * Calculates the detour distance between two different rides. Given four latitude / longitude pairs,
 * where driver one is traveling from point A to point B and driver two is traveling from point C 
 * to point D, write a function (in your language of choice) to calculate the shorter of the detour 
 * distances the drivers would need to take to pick-up and drop-off the other driver.
 *
 *
 */
public class ShortestDetour {
	
	
	/**
	 * Calculates the distance between two points in miles using the Haversine formula
	 * @param a Point a
	 * @param b Point b
	 * @return The distance (in miles) between point a and point b
	 */
	public double distBetweenTwoPoints(Point locationA, Point locationB){
		
		//System.out.println("Calculating distance between " + locationA + " and " + locationB);
		
		double earthRadius = 3961; 	//Radius of Earth = 3961 miles
		
		double lat1 = Math.toRadians(locationA.getLatitude());
		double long1 = Math.toRadians(locationA.getLongitude());
		double lat2 = Math.toRadians(locationB.getLatitude());
		double long2 = Math.toRadians(locationB.getLongitude());
		
		double changeInLat = lat2 - lat1;
		double changeInLong = long2 - long1;
		
		double a = Math.pow((Math.sin(changeInLat/2)), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(changeInLong/2), 2);
		double c = 2* Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distanceInMiles = earthRadius * c;
		
		return distanceInMiles;
	}
	
		public void calculateShortestDetour(LinkedList<String> routes, Map<String, Point> points){
			double shortestDistance = Double.MAX_VALUE;
			String shortestPath = "";
			for(String route: routes){
				double routeDistance = 0;
				char[] paths = route.toCharArray();
				for(int start = 0; start<paths.length-2;start++){
					Point pointStart = points.get(String.valueOf(paths[start] + ""));
					Point pointEnd = points.get(String.valueOf(paths[start+1]));
					routeDistance += distBetweenTwoPoints(pointStart, pointEnd);
				}
				System.out.println("Route: " + route + " . Total Distance: " + routeDistance + " mi");
				//check to see if it's the shortest distance thus far
				if(routeDistance < shortestDistance){
					shortestPath = route;
					shortestDistance = routeDistance;
				}
			}
			
			System.out.println("\nShortest Route: " + shortestPath + " . Total Distance: " + shortestDistance + " mi");
		}
		
	
	/**
	 * Playground for testing code
	 */
	public static void main(String[] args) {
		
		ShortestDetour detour = new ShortestDetour();
		
		Point a = new Point(30.415606, -97.673254, "A_Lowe's");
		Point b = new Point(30.449418, -97.666291, "B_Texas Roadhouse");
		Point c = new Point(30.437887, -97.698271, "C_Pizza Hut");
		Point d = new Point(30.415758, -97.697413, "D_Curry In A Hurry");

		LinkedList<Point> points = new LinkedList<Point>();
		points.add(a);
		points.add(b);
		points.add(c);
		points.add(d);
		
		System.out.println(points + "\n");
		
		Map<String, Point> map = new LinkedHashMap<String, Point>();
		LinkedList<String> routes = new LinkedList<String>();
		
		
		map.put("A",a);
		map.put("B",b);
		map.put("C",c);
		map.put("D",d);
		
		routes.add("ABCD");	//route: A -> B -> C -> D
		routes.add("BADC");  //route: B -> A -> D -> C
		routes.add("ABDC");  //route: A -> B -> D -> C
		
		detour.calculateShortestDetour(routes, map);
	}

}