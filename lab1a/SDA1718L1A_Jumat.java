import java.io.*;

public class SDA1718L1A_Jumat{
	public static void main (String[] args) {
		BufferedReader input = new BufferedReader(
				new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(
				new OutputStreamWriter(System.out)
				);
		try {
			int k = Integer.valueOf(input.readLine());
			int inputChar = 0;
			while((inputChar = input.read()) != -1){
				if (65 <= inputChar && inputChar <= 90)
					output.write(((inputChar - k - 65) % 26 + 26) % 26 + 65);
				else if (97 <= inputChar && inputChar <= 122)
					output.write(((inputChar - k - 97) % 26 + 26) % 26 + 97);
				else
					output.write(inputChar);
				output.flush();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
