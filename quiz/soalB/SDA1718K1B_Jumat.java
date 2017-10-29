import java.util.*;
import java.io.*;

public class SDA1718K1B_Jumat {
	public static void main (String[] args) {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		ArrayList<Candidate> listOfCandidate = new ArrayList<>();
		HashMap<String, Candidate> candidateMap = new HashMap<>();
		HashSet<String> candidateHash = new HashSet<>();
		ArrayList<Agency> listOfAgency = new ArrayList<>();
		HashMap<String, Agency> agencyMap = new HashMap<>();
		String[] input = null;
		try {
			input = scan.readLine().split(" ");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		int numberOfAgency = Integer.valueOf(input[0]);
		int actressNeeded = Integer.valueOf(input[1]);

		String tmp = null;

		try {
			while ((tmp = scan.readLine()) != null) {
				input = tmp.split(" ");
				if(input.length == 0) continue;
				if(!candidateHash.contains(input[0])) {
					Candidate candidate = new Candidate(input[0], input[1], input[2]);
					listOfCandidate.add(candidate);
					candidateHash.add(input[0]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(listOfCandidate);

		for (int i = 0; i < actressNeeded; i++) {
			String candidateAgency = listOfCandidate.get(i).getAgency();
			if (agencyMap.get(candidateAgency) == null){
				Agency agency = new Agency(candidateAgency);
				agencyMap.put(candidateAgency, agency);
				listOfAgency.add(agency);
			}
			agencyMap.get(candidateAgency).incCount();
		}

		// for (Candidate candidate: listOfCandidate) { print.println(candidate); }

		Collections.sort(listOfAgency);
		for (Agency agency: listOfAgency) {
			print.println(agency);
		}
		print.flush();
	}
}

class Agency implements Comparable<Agency>{
	private String name;
	private int count;

	public Agency(String name) {
		this.name = name;
		count = 0;
	}

	public int compareTo(Agency agency){
		if (this.count != agency.getCount()){
			return -(this.count - agency.getCount());
		} else {
			return this.name.compareTo(agency.getName());
		}
	}

	public String toString(){
		return "Jumlah kandidat terpilih dari agensi " + name + " adalah : " + count;
	}

	public void incCount(){ count++; }
	public int getCount(){ return count;}
	public String getName(){ return name;}
}

class Candidate implements Comparable<Candidate>{
	private String name;
	private int quality;
	private String agency;

	public Candidate(String name, String quality, String agency) {
		this.name = name;
		this.quality = Integer.valueOf(quality);
		this.agency = agency;
	}

	public int compareTo(Candidate candidate){
		if (this.quality == candidate.getQuality())
			return this.name.compareTo(candidate.getName());
		else
			return -(this.quality - candidate.getQuality());
	}

	public String toString(){
		return name + " " + quality + " " + agency;
	}

	public String getName() { return name; }
	public int getQuality() { return quality; }
	public String getAgency() { return agency; }

}
