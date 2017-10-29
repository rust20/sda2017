import java.io.*;
import java.util.*;

public final class SDA1718L1B_Jumat{
	public static void main(String[] args){
		init();
		int n = in.nextInt();
		int k = in.nextInt();
		long ans = 0;

		long [] arr = new long[k+2];
		for (int i = 0; i < n; i++) {
			long temp = in.nextLong();
			int temp2 = (int)(temp%k);
			arr[temp2]++;
		}

		for (int i = 0; i < k; i++) {
			int j = (k - i)%k;
			if (i != j) ans += arr[i] * arr[j];
			else ans += (arr[i] * (arr[i] - 1));
		}

		out.printf("%d\n", ans/2);
		out.close();
}
public static MyScanner in;
public static PrintWriter out;
public static void init(){
	in = new MyScanner();
	out = new PrintWriter(new BufferedOutputStream(System.out));
}
public static class MyScanner{
	BufferedReader br;
	StringTokenizer st;
	MyScanner(){
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	String next(){
		while(st == null || !st.hasMoreElements()){
			try{
				st = new StringTokenizer(br.readLine());
			}catch(IOException e){ e.printStackTrace(); }
		}
		return st.nextToken();
	}
	int nextInt(){return Integer.parseInt(next());}
	long nextLong(){return Long.parseLong(next());}
	double nextDouble(){return Double.parseDouble(next());}
	String nextLine(){
		String str = "";
		try{
			str = br.readLine();
		}catch(IOException e){ e.printStackTrace(); }
		return str;
	}
}
}
