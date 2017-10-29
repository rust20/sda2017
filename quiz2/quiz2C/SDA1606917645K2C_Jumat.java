import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

public class SDA1606917645K2C_Jumat {
	public static void main (String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		String [] input1 = scan.readLine().split(" ");
		String [] input2 = scan.readLine().split(" ");

		int[] pivots = new int[input1.length];
		int[] nums   = new int[input2.length];

		for (int i = 0; i < input2.length; i++) {
			nums[i] = Integer.valueOf(input2[i]);
		}

		Arrays.sort(nums, Collections.reverseOrder());

		for (int i = 0; i < nums.length-1; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println(nums[nums.length-1]);


	}
}
