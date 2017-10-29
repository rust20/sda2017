import java.io.*;
import java.util.*;

public final class Main{
	public static void main(String[] args){
		
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
