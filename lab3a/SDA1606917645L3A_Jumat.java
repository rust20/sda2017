// import java.util.*;
import java.io.*;

public class SDA1606917645L3A_Jumat {
	public static void main (String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		int count = Integer.valueOf(scan.readLine());

		// Initialize the stack
		CustomStack stack = new CustomStack((int)10E3);

		String input = null;
		for (int i = 0; i < count; i++) {
			// boolean to mark if the string are a bad string
			boolean badString = false;
			int openCount = 0;
			input = scan.readLine();

			// if the length of the string aren't even,
			// then it will be marked as bad string
			if (input.length() % 2 != 0)
				badString = true;

			// checking if the pairing is in the correct
			// order
			for (char ch : input.toCharArray()) {
				switch(ch){
					case 'L': stack.push(1);
										openCount++;
										break;

					case 'O': if (stack.pop() != 1)
											badString = true;
										openCount--;
										break;

					case 'V': stack.push(2);
										openCount++;
										break;

					case 'E': if (stack.pop() != 2)
											badString = true;
										openCount--;
										break;

					case '<': stack.push(3);
										openCount++;
										break;

					case '3': if (stack.pop() != 3)
											badString = true;
										openCount--;
										break;
				}
				if(badString) break;
			}

			// if there are some open characters that are not closed
			// then it will be marked as badstring
			if(openCount!=0) badString = true;
			// print the corresponding output
			print.println(badString?"TIDAK BAGUS":"BAGUS");
		}
		print.flush();
	}
}

// custom stack that can hold integer data type
class CustomStack{
	private int currentPossition = 0;
	private int [] stackArray;

	public CustomStack(int i){
		stackArray = new int[i];
	}

	// simple push function
	public void push(int ch){
		currentPossition++;
		stackArray[currentPossition] = ch;
	}

	// simple pop function that return 0
	// if the stack are "empty"
	public int pop(){
		if (currentPossition == 0)
			return 0;
		currentPossition--;
		return stackArray[currentPossition+1];
	}
}
