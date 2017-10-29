import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Stack;

public class SDA1606917645K2A_Jumat {
	public static void main (String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		@SuppressWarnings("unused")
		int num = Integer.valueOf(scan.readLine());

		Stack<Nasabah> stackOfPeople = new Stack<>();
		ArrayDeque<Nasabah> queueOfPeople = new ArrayDeque<>();
		String input;
		String[] inputs;
		PriorityQueue<Nasabah> minimum = new PriorityQueue<>();
		minimum.add(new Nasabah("anon", 100000001));

		while((input = scan.readLine()) != null){
			inputs = input.split(" ");
			switch(inputs[0]){
				case "ANTRI":
					int tmpValue = Integer.valueOf(inputs[2]);
					queueOfPeople.addLast(new Nasabah(inputs[1], tmpValue));
					print.println(inputs[1] + " masuk ke dalam antrian");
					break;
				case "PROSES":
					if (queueOfPeople.size() == 0){
						print.println("Antrian kosong");
						break;
					}
					Nasabah nasabah1 = queueOfPeople.pollFirst();
					if(nasabah1.getAmount() < minimum.peek().getAmount() *1000){
						stackOfPeople.push(nasabah1);
						print.println(nasabah1.getName() + " berhasil menabung sebesar " + nasabah1.getAmount());
						if(nasabah1.getAmount() < Nasabah.minimumValue){
							Nasabah.minimumValue = nasabah1.getAmount();
						}
						minimum.add(nasabah1);
					} else {
						print.println("Saldo " + nasabah1.getName() + " melebihi saldo maksimal: " +
								(minimum.peek().getAmount() * 1000));
					}
					break;
				case "PENARIKAN":
					if(stackOfPeople.size() == 0){
						print.println(inputs[1] + " tidak berhasil melakukan penarikan");

					} else if (inputs[1].equals(stackOfPeople.peek().getName())){
						Nasabah nasabah2 = stackOfPeople.pop();
						print.println(nasabah2.getName() + " berhasil melakukan penarikan sebesar " + nasabah2.getAmount());
						minimum.remove(nasabah2);
					} else {
						print.println(inputs[1] + " tidak berhasil melakukan penarikan");
					}
					break;
			}
		}
		print.flush();
	}
}

class Nasabah implements Comparable<Nasabah>{
	private String name;
	private int amount;

	public static long minimumValue = (long) 10E8;


	public Nasabah(String name, int amount){
		this.name = name;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}

	public int compareTo(Nasabah n){
		return this.amount - n.getAmount();
	}
}
