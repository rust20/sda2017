// import java.io.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
public class SDA1606917645L4A {
	private static final int SIZE = 10000;

	public static void main (String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		int[] numbers = new int[SIZE];

		// get input from standard input
		@SuppressWarnings("unused")
		int n = Integer.valueOf(scan.readLine());
		String [] input = scan.readLine().split(" ");

		for (int i = 0; i < input.length; i++) {
			numbers[i] = Integer.valueOf(input[i]);
		}

		// start sorting algorithm
		quicksort(numbers, 0, input.length-1);

		// print the output
		for (int i = 0; i < input.length - 1; i++) {
			print.print(numbers[i] + " ");
		}

		print.print(numbers[input.length-1] + "\n");
		print.flush();
	}

	public static void quicksort(int[] array, int left, int right){
		int leftPointer = left;
		int rightPointer = right;

		int pivot = array[(left+right)/2];

		// makesure that every value that is less than the pivot are
		// on the left side of the pivot and every value that is larger
		// than the pivot are on the right side of the pivot
		while (leftPointer <= rightPointer){

			// find the value that is on the wrong side
			while(array[leftPointer] < pivot) leftPointer++;
			while(array[rightPointer] > pivot) rightPointer--;

			// switch the value if their position are on the wrong side
			if(leftPointer <= rightPointer){
				int tmp = array[leftPointer];
				array[leftPointer] = array[rightPointer];
				array[rightPointer] = tmp;
				leftPointer++;
				rightPointer--;
			}
		}

		// recursive part, sorting the left side, then the right side
		if (left < rightPointer) quicksort(array, left, rightPointer);
		if (leftPointer < right) quicksort(array, leftPointer, right);
	}
}
