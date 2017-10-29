import java.io.*;
public class SDA1606917645L3B_Jumat {
	public static final int SIZE = (int)10E5;
	static int [] memo = new int[SIZE + 5];
	public static void main (String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		int count = Integer.valueOf(scan.readLine());
		//initialize the array
		for (int i = 0; i < SIZE; i++) {
			memo[i] = SIZE;
		}
		// set the base case
		memo[0] = 0;
		memo[1] = 1;
		memo[2] = 2;

		// get the input query
		for (int counter = 0; counter < count; counter++) {
			int n = Integer.valueOf(scan.readLine());
			print.println(walk(n));
		}

		// print
		print.flush();
}

	public static int walk(int dist){
		// if the query are already calculated,
		// then return the result
		if (memo[dist] != SIZE)
			return memo[dist];

		// check if it is a factor of dist
		// if so, then calculate it again
		// and save it to the memo
		for (int i = (int)Math.sqrt(dist); i > 1; i--) {
			if (dist % i == 0) {
				int distDiv = walk(dist/i) + 1;
				// choose the greater distance or the already set value
				memo[dist] = (distDiv < memo[dist])?distDiv:memo[dist];
			}
		}


		// calculeate if he just jump 1 step at a time
		int distMin = walk(dist-1) + 1;
		// return the minimum value
		return memo[dist] = (memo[dist]<distMin)?memo[dist]:distMin;
	}
}
