import java.util.*;
import java.io.*;
public class SDA1606917645K1BRemedial {
	public static void main (String[] args) throws IOException{
		// inspiration for this algorithm
		// http://www.geeksforgeeks.org/maximum-contiguous-circular-sum/
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		ArrayList<Long> maguses = new ArrayList<>();

		int num = Integer.valueOf(scan.readLine());
		String [] input = scan.readLine().split(" ");
		for (String str : input) {
			maguses.add(Long.valueOf(str));
		}

		// long maxKadane = kadane(maguses);
		// long currentSum = 0;
		// for (int i = 0; i < maguses.size(); i++) {
			// currentSum += maguses.get(i);
			// maguses.set(i, -maguses.get(i));
		// }

		// currentSum += kadane(maguses);
		print.print((currentSum>maxKadane)?currentSum:maxKadane);
		print.flush();
	}
	public static long kadane(ArrayList<Long> list){
		int n = list.size();
		long maxSoFar = 0;
		long maxEndHere = 0;
		for (int i = 0; i < n; i++) {
			maxEndHere = maxEndHere + list.get(i);
			if (maxEndHere < 0)
				maxEndHere = 0;
			if (maxSoFar < maxEndHere)
				maxSoFar = maxEndHere;
		}
		return maxSoFar;
	}
}
