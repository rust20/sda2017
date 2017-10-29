import java.util.*;
import java.io.*;

public class SDA1606917645K1ARemedial {
	public static void main (String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		ArrayList<Location> listOfLocation = new ArrayList<>();
		HashMap<String, Location> locationMap = new HashMap<>();

		int locations = Integer.valueOf(scan.readLine());
		String [] input = null;
		for (int i = 0; i < locations; i++) {
			input = scan.readLine().split(" ");
			if(locationMap.get(input[0]) == null) {
				Location tmpLocation = new Location(input[0], input[1]);
				listOfLocation.add(tmpLocation);
				locationMap.put(input[0], tmpLocation);
			}
			else
				locationMap.get(input[0]).num = Integer.valueOf(input[1]);
		}

		long total = 0;
		Collections.sort(listOfLocation);
		for (Location loc : listOfLocation) {
			print.println(loc);
			total += loc.num;
		}
		print.println("Total sandera yang diselamatkan: " + total);
		print.flush();
	}
}

class Location implements Comparable<Location>{
	String name;
	int num;

	public Location(String name, String num){
		this.name = name;
		this.num = Integer.valueOf(num);
	}

	public int compareTo(Location location){
		if (this.num == location.num)
			return this.name.compareTo(location.name);
		else
			return -(this.num - location.num);
	}

	public String toString(){
		return name + " " + num;
	}
}
