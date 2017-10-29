import java.util.*;
import java.io.*;

public class SDA1718TUGAS1 {
	public static void main (String[] args) throws IOException{
		// Prepare input and output
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new BufferedOutputStream(System.out));
		int commandCount = Integer.valueOf(scan.readLine());
		int activeTab = 0;

		// Declaring an ArrayList of Stack
		ArrayList<Stack<Page>> listOfTab = new ArrayList<>();

		// Adding a stack to the list so it's not empty
		listOfTab.add(new Stack<Page>());

		// Using Hashmap as history's data structure for convinience
		HashMap<String, String> history = new HashMap<>();

		// initialize activePage
		Page activePage = null;

		String [] input;
		while(commandCount > 0){
			input = scan.readLine().trim().split(";");
			if(input.length == 0) continue;
			switch(input[0]){

				// Put cat's name and its type to the history
				// and push this page to the current page's stack
				case "VIEW":
					history.put(input[1], input[2]);
					activePage = new Page(input[1], input[2]);
					listOfTab.get(activeTab).push(activePage);
					print.printf("VIEWING([%s])\n", activePage);
					break;

				// Go to previous tab if there are a previous page in current active tab
				case "BACK":
					if(listOfTab.get(activeTab).size() > 1){
						listOfTab.get(activeTab).pop();
						activePage = listOfTab.get(activeTab).peek();
						print.printf("VIEWING([%s])\n",
								activePage);
					} else {
						print.println("EMPTY TAB");
					}
					break;

				// Add a new stack object to the list
				// As known as create new tab
				case "NEWTAB":
					listOfTab.add(new Stack<Page>());
					break;

				// Change tab to specified tab index
				// if the specified index are not existed, it printed "NO TAB"
				case "CHANGETAB":
					int targetTab = Integer.valueOf(input[1]);
					if (targetTab >= 0 && targetTab < listOfTab.size()){
						activeTab = targetTab;
						if(listOfTab.get(activeTab).size() > 0)
							activePage = listOfTab.get(activeTab).peek();
						print.printf("TAB: %d\n", activeTab);
					} else {
						print.println("NO TAB");
					}
					break;

				// Print the list of visited page, usign the history HashSet
				// The list are printed lexicographically
				case "HISTORY":
					if (history.size() > 0){
						ArrayList<String> sortedHistory = new ArrayList<String>(history.keySet());
						Collections.sort(sortedHistory);
						for(String name: sortedHistory){
							print.println(name + ":" + history.get(name));
						}
					} else {
						print.println("NO RECORD");
					}
					break;
			}
			print.flush();
			commandCount--;
		}
	}
}

// New class to simplify data management
// contains the cat's name and its type
class Page {
	private String name;
	private String type;

	public Page(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String toString(){
		return String.format("%s:%s", name, type);
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
}
