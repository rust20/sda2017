import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class SDA16069176L4B {
	private static final int SIZE = 1000000;
	public static void main (String[] args) throws IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		Card [] cards = new Card[SIZE];

		int cardIndex = 0;

		String tmpInput = null;
		String [] input = null;
		while((tmpInput = scan.readLine()) != null){
			input = tmpInput.split(" ");
			switch(input[0]){
				case "PICK":
					// put a newly created card object to the array, and then sort it
					cards[cardIndex] = new Card(input[1], input[2]); sortCard(cards, 0, cardIndex);
					print.println(input[1] + " dengan power " + input[2] + " diambil");
					cardIndex++;
					break;
				case "ATTACK":
					// print the highest power card, and then remove it from the array
					// if array contains no card, print a message
					if (cardIndex >= 1){
						print.println(cards[cardIndex-1] + " dikeluarkan");
						cards[cardIndex-1] = null;	// this may seem unnecessary, but
													// with this the garbage collector
													// will be collect the unused object
						cardIndex--;
					} else {
						print.println("Tidak bisa melakukan Attack");
					}
					break;
				case "DEFENSE":
					// print 3 of the lowest power card, and then remove it from the array
					// if array contains less than 3 card, print a message
					if (cardIndex >= 3){
						print.println(cards[0] + " dikeluarkan");
						print.println(cards[1] + " dikeluarkan");
						print.println(cards[2] + " dikeluarkan");
						// shift left the array 3 indexes away
						for (int i = 0; i < cardIndex-3; i++) {
							cards[i] = cards[i+3];
						}
						cardIndex -= 3;
					} else {
						print.println("Tidak bisa melakukan Defense");
					}
					break;
				case "SEE":
					// print all card in ascending order
					// if the array contains no card, print a message
					if (cardIndex > 0) {
						for (int i = cardIndex-1; i >= 0; i--)
							print.println(cards[i]);
					} else
						print.println("Kartu kosong");
					break;
			}
		}
		print.flush();
	}

	// quick sorting algorithm
	public static void sortCard(Card [] cards, int left, int right){
		int leftPointer = left;
		int rightPointer = right;

		Card pivot = cards[(left+right)/2];

		while (leftPointer <= rightPointer){
			while(0 < cards[leftPointer].compareTo(pivot)) leftPointer++;
			while(0 > cards[rightPointer].compareTo(pivot)) rightPointer--;

			if(leftPointer <= rightPointer){
				Card tmp = cards[leftPointer];
				cards[leftPointer] = cards[rightPointer];
				cards[rightPointer] = tmp;
				leftPointer++;
				rightPointer--;
			}
		}
		if (left < rightPointer) sortCard(cards, left, rightPointer);
		if (leftPointer < right) sortCard(cards, leftPointer, right);
	}
}

// Card class to store name and power data, and to make it easier
// when sorting the card with compareTo method
class Card{
	private String name;
	private int power;

	public Card(String name, String power){
		this.name = name;
		this.power = Integer.valueOf(power);
	}

	// custom compareTo method to make the sorting part a lot more easier
	public int compareTo(Card card){
		if (this.power == card.getPower()){
			return this.name.compareTo(card.getName());
		} else {
			return -(this.power - card.getPower());
		}
	}

	public String toString(){
		return name + " " + power;
	}

	public int getPower(){ return power; }
	public String getName(){ return name; }
}
