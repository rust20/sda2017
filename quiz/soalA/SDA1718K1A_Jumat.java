import java.util.*;
import java.io.*;

public class SDA1718K1A_Jumat {
	public static void main (String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));
		int numberOfBuyer = 0;
		StringTokenizer meja = null;
		try {
			numberOfBuyer = Integer.valueOf(scan.readLine());
			meja = new StringTokenizer(scan.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		int countMakanan = 0;
		ArrayDeque<Integer> listOfRak = new ArrayDeque<>();
		HashMap<String, Integer> buyerMap = new HashMap<>();

		listOfRak.
		while(meja.hasMoreTokens()){
			listOfRak.addFirst(Integer.valueOf(meja.nextToken()));
		}

		String [] input = null;
		for (int i = 0; i < numberOfBuyer; i++) {
			try {
				input = scan.readLine().split(" ");
			} catch (IOException e) {e.printStackTrace();}
			buyerMap.put(input[0], Integer.valueOf(input[1]));
		}

		String tmp = null;
		while((tmp = scan.readLine()) != null){
			input = tmp.split(" ");
			if (input.length == 2){
				
			} else if(input.length == 3){

			}
		}



	}
}

