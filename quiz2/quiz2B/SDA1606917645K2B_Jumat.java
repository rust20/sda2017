import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class SDA1606917645K2B_Jumat {
	public static void main (String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		int n = Integer.valueOf(scan.readLine());
		for (int k = 0; k < n; k++) {
			boolean [] setb = new boolean[1000*1000];
			int [] setc = new int[1000*1000];
			for (int i = 0; i < setc.length; i++) {
				setc[i] = -1;
			}

			int [] seti = new int[1000];
			String [] s = scan.readLine().split(" ");
			for (int i = 0; i < s.length; i++) {
				setb[Integer.valueOf(s[i])] = true;
			}
			int index = 0;
			for (int i = 0; i < setb.length; i++) {
				if(setb[i]) seti[index++] = i;
			}


			int target = Integer.valueOf(scan.readLine());
			boolean result = findMatch2(seti, target, 1, 0, s.length, setc);
			print.println((result)?"Ada":"Tidak Ada");
			print.flush();
		}
	}

	public static boolean findMatch2(int[] ar, int target, int current, int pointer, int arlen,
			int[] cek){
		if(cek[current] != -1) return (cek[current] == 0)?false:true;
		boolean temp = false;
		for (int i = pointer; i < arlen; i++) {
			if (current * ar[i] == target) return true;
			boolean lol = findMatch2(ar, target, current * ar[i], i+1, arlen, cek);
			cek[current * ar[i]] = (lol)?1:0;
			temp = temp || lol;
		}
		return temp;
	}

}


