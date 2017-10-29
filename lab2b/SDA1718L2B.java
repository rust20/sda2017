import java.util.*;
import java.io.*;

public class SDA1718L2B {
	public static void main (String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(
				new InputStreamReader(System.in));
		PrintWriter printer = new PrintWriter(
				new BufferedOutputStream(System.out));

		LinkedList<String> garage = new LinkedList<>();
		HashSet<String> brokeCar = new HashSet<>();

		String tempInput;
		String [] input = new String[3];

		while((tempInput = scan.readLine()) != null){
			input = tempInput.split(" ");

			String car = input[1];
			switch(input[0]){
				case "MASUK":
					if (input[2].equals("BARAT")){
						garage.addFirst(car);
					} else {
						garage.add(car);
					}
					printer.println(car + " masuk melalui pintu " + input[2]);
					break;
				case "KELUARKAN":
					boolean leftFree = false;
					boolean rightFree = false;
					int pos = garage.indexOf(car);
					int leftpos = pos-1;
					int rightpos = pos+1;
					String leftBlocker = "";
					String rightBlocker = "";
					if (pos == -1) printer.printf("%s tidak ada di garasi\n", car);
					else if (brokeCar.contains(car))printer.printf("Mobil %s sedang mogok\n", car);
					else {
						boolean printthis = true;
						while(!(leftFree && rightFree)){
							if (leftpos < 0){
								garage.remove(car);
								printer.printf("%s keluar melalui pintu BARAT\n", car);
								printthis = false;
								break;
							} else if (rightpos == garage.size()){
								garage.remove(car);
								printer.printf("%s keluar melalui pintu TIMUR\n", car);
								printthis = false;
								break;
							} else {
								if(!brokeCar.contains(garage.get(leftpos)) && !leftFree) leftpos--;
								else {
									leftBlocker = garage.get(leftpos);
									leftFree = true;
								}
								if(!brokeCar.contains(garage.get(rightpos)) && !rightFree) ++rightpos;
								else {
									rightBlocker = garage.get(rightpos);
									rightFree = true;
								}
							}
						}
						if(printthis) printer.printf("%s tidak bisa keluar, mobil %s dan %s sedang mogok\n", car, leftBlocker, rightBlocker);
					}
					break;
				case "MOGOK":
					brokeCar.add(car);
					break;
				case "SERVIS":
					brokeCar.remove(car);
					break;
			}
			printer.flush();
		}
	}
}
