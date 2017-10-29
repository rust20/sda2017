import java.io.*;
import java.util.*;

public class SDA1718L2A {
	public static void main (String[] args) throws IOException{
		// preparing for inout and output
		BufferedReader scan = new BufferedReader(
				new InputStreamReader(System.in));
		PrintWriter printer = new PrintWriter(
				new BufferedOutputStream(System.out));

		String tempInput;
		String [] input = new String[3];

		//using priority queue for convinience
		Queue<Dokumen> queue = new PriorityQueue<>(100);
		TreeMap<String, Dokumen> map = new TreeMap<>();
		while((tempInput = scan.readLine()) != null){
			input = tempInput.split(" ");
			if (input.length == 0){

			} else if (input[0].equals("PRINT")){
				int quotaPrint = 10;
				if (queue.size() == 0){
					printer.printf("Antrean kosong\n");
				} else {
					while(queue.peek().halaman <= quotaPrint){
						int page = queue.peek().halaman;
						queue.peek().halaman = 0;
						Dokumen printedDocument = queue.poll();
						quotaPrint -= page;
						printer.printf("Submisi %s telah dicetak sebanyak %d halaman\n",
								printedDocument.npm, page);
					}
				}
			} else if(input[1].equals("SUBMIT")){
				Dokumen newDocument = new Dokumen(input[0],
							Integer.valueOf(input[2]));
				if(newDocument.halaman > 10){
					printer.printf("Jumlah halaman submisi %s terlalu banyak\n", newDocument.npm);
				} else if(map.containsKey(input[0])){
					if(map.get(input[0]).halaman > 0)
						printer.printf("Harap tunggu hingga submisi sebelumnya selesai diproses\n");
					else if(map.get(input[0]).halaman == 0){
						map.get(input[0]).halaman += newDocument.halaman;
						queue.add(map.get(input[0]));
					}
				} else {
					queue.add(newDocument);
					map.put(newDocument.npm, newDocument);
					printer.printf("Submisi %s telah diterima\n", newDocument.npm);
				}
			} else if (input[1].equals("CANCEL")){
				if(map.containsKey(input[0])){
					queue.remove(map.get(input[0]));
					map.get(input[0]).halaman = 0;
					printer.printf("Submisi %s dibatalkan\n", input[0]);
				} else {
					printer.printf("%s tidak ada dalam antrean\n", input[0]);
				}
			} else if (input[0].equals("STATUS")){
				if(!map.containsKey(input[1])){
					printer.printf("%s tidak ada dalam sistem\n", input[1]);
				} else if(queue.contains(map.get(input[1]))){
					printer.printf("Submisi %s masih dalam antrean\n", input[1]);
				} else {
					printer.printf("Submisi %s sudah diproses\n", input[1]);
				}
			}
			printer.flush();
		}
	}
}

// create a class for easy-to-manage data structure
class Dokumen implements Comparable<Dokumen>{
	String npm;
	int halaman;
	int urutan;
	static int count = 0;

	public Dokumen(String npm, int halaman) {
		this.npm = npm;
		this.halaman = halaman;
		this.urutan = count++;
	}

	public Dokumen(String npm){
		this.npm = npm;
	}

	@Override
	public int compareTo(Dokumen o) {
		return this.npm.compareTo(o.npm);
	}

	public boolean equals(Dokumen o){
		return this.npm.equals(o.npm);
	}
}
