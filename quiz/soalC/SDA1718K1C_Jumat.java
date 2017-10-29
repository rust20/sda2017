import java.util.*;
import java.io.*;

public class SDA1718K1C_Jumat {
	public static void main (String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		String [] input = null;
		try {
			input = scan.readLine().split(" ");
		} catch (IOException e) { e.printStackTrace(); }

		int speciality = Integer.valueOf(input[0]);
		int candidates = Integer.valueOf(input[1]);

		ArrayList<NeoStack> crewList = new ArrayList<>();

		int minimumDelta = 100000000;

		for (int i = 0; i < speciality; i++) {
			try { input = scan.readLine().split(" "); } catch(IOException e) {e.printStackTrace();}
			crewList.add(new NeoStack());
			for (String str : input) {
				crewList.get(i).push(Integer.valueOf(str));
			}
		}
		int min = 0;
		int max = 0;

		for (int i = 0; i < candidates*speciality; i++) {

			System.out.println(i);
			if (Collections.max(crewList).size() > 0 && Collections.min(crewList).size() > 0) {
				max = Collections.max(crewList).peek();
				min = Collections.min(crewList).peek();
				Collections.max(crewList).pop();
				minimumDelta = (minimumDelta > Math.abs(max - min))?Math.abs(max - min):minimumDelta;
			} else {
				break;
			}
		}
		System.out.println("uhu " + minimumDelta);
	}
}

@SuppressWarnings("serial")
class NeoStack extends Stack<Integer> implements Comparable<NeoStack>{
	public int compareTo(NeoStack stack){
		if(this.size() == 0)
			return -1;
		else if(stack.size() == 0)
			return 1;
		else
			return this.peek() - stack.peek();
	}
}
