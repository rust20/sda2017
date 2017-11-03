import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class SDA1606917645T2 {
	static final int PAHLAWAN_MAX = 3000;
	static final int SUMMON_MAX = 2500;
	static final int DUNGEON_MAX = 2500;

	public static void main (String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		HashMap<String, Hero> mapOfHero = new HashMap<>();

		// get input specification
		String [] inputs = scan.readLine().split(" ");
		int numberOfHero = Integer.valueOf(inputs[0]);
		int numberOfSummon = Integer.valueOf(inputs[1]);
		int numberOfDungeon = Integer.valueOf(inputs[2]);
		int manaGudako = Integer.valueOf(inputs[3]);
		int row = Integer.valueOf(inputs[4]);
		int column = Integer.valueOf(inputs[5]);

		Gudako gudako = new Gudako(manaGudako);
		World world = new World(row, column, gudako);

		// get input for Heros
		for (int i = 0; i < numberOfHero; i++) {
			inputs = scan.readLine().split(";");
			Hero tmpHero = new Hero(inputs[0], inputs[1], inputs[2], inputs[3]);
			mapOfHero.put(inputs[0], tmpHero);
		}

		// get input for summons
		for (int i = 0; i < numberOfSummon; i++) {
			inputs = scan.readLine().split(";");
			String [] heros = inputs[2].split(",");
			ArrayList<Hero> tempHeroList = new ArrayList<>();

			for (String hero : heros) {
				tempHeroList.add(mapOfHero.get(hero));
			}
			world.putSummons(tempHeroList,
					Integer.valueOf(inputs[0]) - 1,
					Integer.valueOf(inputs[1]) - 1);
		}

		// get input for dungeons
		for (int i = 0; i < numberOfDungeon; i++) {
			inputs = scan.readLine().split(";");
			world.putDungon(new Dungeon(inputs[2], inputs[3], inputs[4], inputs[5]),
					Integer.valueOf(inputs[0]) - 1,
					Integer.valueOf(inputs[1]) - 1);
		}



		// get input for map
		for (int i = 0; i < row; i++) {
			String strRow = scan.readLine();
			world.setRow(strRow, i);
			for (int j = 0; j < strRow.length(); j++) {
				if (strRow.charAt(j) == 'M'){
					world.setStart(i, j);
				}
			}
		}

		world.startAdventure(print);
		print.flush();
	}
}


class Hero implements Comparable<Hero>{
	private String name;
	private int mana;
	private int power;
	private String weaponType;
	private long level;
	private int numberOfBattle;
	private int summonedIndex;
	private static int numberOfSummoned = 0;
	private static String dungeonWeapon = "";

	public Hero(String name, String mana, String power, String weaponType) {
		this.name = name;
		this.mana = Integer.valueOf(mana);
		this.power = Integer.valueOf(power);
		this.weaponType = weaponType;
		this.level = 1;
		this.numberOfBattle = 0;
	}

	public int getDungeonPower(){
		if(this.weaponType.equals("pedang") && dungeonWeapon.equals("panah")){
			return power/2;
		} else if(this.weaponType.equals("panah") && dungeonWeapon.equals("pedang")){
			return power*2;
		} else {
			return power;
		}
	}

	public int compareTo(Hero pahlawan){
		if (this.power != pahlawan.getPower()){
			return -(this.power - pahlawan.getPower());
		} else if (this.mana != pahlawan.getMana()){
			return (this.mana - pahlawan.getMana());
		} else {
			return this.name.compareTo(pahlawan.getName());
		}
	}

	public void addLevel(long additionalLevel){
		this.level += additionalLevel;
	}

	public void incNumberOfBattle(){
		numberOfBattle++;
	}

	public String getName() {
		return name;
	}

	public int getMana() {
		return mana;
	}

	public int getPower() {
		return power;
	}

	public int getNumberOfBattle() {
		return numberOfBattle;
	}

	public long getLevel() {
		return level;
	}

	public int getSummonedIndex(){
		return summonedIndex;
	}

	public void setSummonedIndex(){
		summonedIndex = numberOfSummoned++;
	}

	public static void setDungeonWeapon(String dungeonWeapon) {
		Hero.dungeonWeapon = dungeonWeapon;
	}
}


class Dungeon{
	private int power;
	private int level;
	private String weaponType;
	private int maxHero;

	public Dungeon(String power, String level, String weaponType, String maxHero) {
		this.power = Integer.valueOf(power);
		this.level = Integer.valueOf(level);
		this.weaponType = weaponType;
		this.maxHero = Integer.valueOf(maxHero);
	}

	public int getPower() {
		return power;
	}

	public int getLevel() {
		return level;
	}

	public String getWeaponType() {
		return weaponType;
	}

	public int getMaxHero() {
		return maxHero;
	}
}

class Gudako{
	private long level;
	private int mana;
	private ArrayList<Hero> summoned;

	public Gudako(int mana){
		this.level = 1;
		this.mana = mana;
		this.summoned = new ArrayList<>();
	}

	public void addLevel(long amount){
		this.level += amount;
	}

	public int getMana(){
		return mana;
	}

	public ArrayList<Hero> getSummoned(){
		return summoned;
	}

	public void addSummoned(Hero hero){
		summoned.add(hero);
	}

	public long getLevel(){
		return level;
	}
}


class World{
	private HashMap<Integer, Dungeon> mapDungeon;
	private HashMap<Integer, ArrayList<Hero>> mapSummon;
	private char [][] mapChar;
	private int worldRow;
	private int worldCol;
	private int rowStart;
	private int colStart;
	private Gudako gudako;

	public World(int worldRow, int worldCol, Gudako gudako) {
		this.worldRow = worldRow;
		this.worldCol = worldCol;
		this.gudako = gudako;

		mapSummon = new HashMap<>();
		mapDungeon = new HashMap<>();
		mapChar = new char[worldRow][worldCol];
	}

	public void putDungon(Dungeon dungeon, int row, int column){
		 mapDungeon.put(this.worldRow*column + row, dungeon);
	}

	public void putSummons(ArrayList<Hero> listOfPahlawan, int row, int column){
		 mapSummon.put(this.worldRow*column + row, listOfPahlawan);
	}

	public Dungeon getDungeon(int row, int column){
		return mapDungeon.get(this.worldRow*column + row);
	}

	public ArrayList<Hero> getSummon(int row, int column){
		return mapSummon.get(this.worldRow*column + row);
	}

	public void setRow(String row, int nRow){
		mapChar[nRow] = row.toCharArray();
	}
	public void setStart(int row, int column){
		this.rowStart = row;
		this.colStart = column;
	}

	public void startAdventure(PrintWriter print){

		Stack<Integer> nextRow = new Stack<>();
		Stack<Integer> nextCol = new Stack<>();
		nextRow.push(rowStart);
		nextCol.push(colStart);

		boolean [][] visited = new boolean[worldRow][worldCol];
		for (int i = 0; i < worldRow; i++){
			for (int j = 0; j < worldCol; j++) {
				visited[i][j] = false;
			}
		}

		int currentRow;
		int currentCol;
		int [] dirRow = {0, 1, 0, -1};
		int [] dirCol = {-1, 0, 1, 0};

		do {
			currentRow = nextRow.pop();
			currentCol = nextCol.pop();

			// if out of boundaries, or is rock tile, or has been visited, then dont do anything
			if (0 > currentRow || currentRow >= worldRow ||
				0 > currentCol || currentCol >= worldCol ||
				visited[currentRow][currentCol] ||
				mapChar[currentRow][currentCol] == '#')	continue;

			// mark as visited
			visited[currentRow][currentCol] = true;

			switch(mapChar[currentRow][currentCol]){
				case 'S': // found a 'S' tile
					int currentMana = gudako.getMana();
					ArrayList<Hero> listOfHeros = getSummon(currentRow, currentCol);
					Collections.sort(listOfHeros);

					String participant = "";

					for (Iterator<Hero> iterHero = listOfHeros.iterator();
							iterHero.hasNext() && currentMana >= 0;){
						Hero i = iterHero.next();
						currentMana -= i.getMana();

						if (currentMana >= 0){
							gudako.addSummoned(i);
							participant += i.getName() + ",";
							i.setSummonedIndex();
						}
					}
					if (participant.length() > 0){
						print.print((currentRow+1) + "," + (currentCol+1) + " Pahlawan yang ikut:");
						print.println(participant.substring(0, participant.length()-1));
					} else {
						print.println((currentRow+1) + "," + (currentCol+1) +
								" tidak ada pahlawan yang ikut"
								);
					}
					break;
				case 'D': // found a 'D' tile
					Dungeon dungeon = getDungeon(currentRow, currentCol);
					ArrayList<Hero> usableHero = new ArrayList<>();
					Hero.setDungeonWeapon(dungeon.getWeaponType());

					// sort gudako's heroes by their power inside the dungeon
					// and how many times has they got into battle
					Collections.sort(gudako.getSummoned(), new Comparator<Hero>(){
						public int compare(Hero p1, Hero p2){
							if (p1.getDungeonPower() != p2.getDungeonPower()){
								return -(p1.getDungeonPower() - p2.getDungeonPower());
							} else {
								return -(p1.getNumberOfBattle() - p2.getNumberOfBattle());
							}
						}
					});


					// get as much hero as allowed
					int powerSum = 0;
					for (int i = 0; i < dungeon.getMaxHero() && i < gudako.getSummoned().size(); i++) {
						usableHero.add(gudako.getSummoned().get(i));
						powerSum += gudako.getSummoned().get(i).getDungeonPower();
					}

					// check wheter its good to go to the dungeon
					if (powerSum >= dungeon.getPower()){
						// gudako get into the dungeon
						String participatingHero = "";
						for (Hero hero : usableHero) {
							hero.addLevel((long)dungeon.getLevel());
							hero.incNumberOfBattle();
							participatingHero += hero.getName() + ",";
						}
						gudako.addLevel((long)usableHero.size()*(long)dungeon.getLevel());
						// gudako.addLevel(usableHero.size()*dungeon.getLevel());
						String herosList = (participatingHero.length() > 0)?participatingHero.substring(0, participatingHero.length()-1):"";
						print.println((currentRow+1) + "," + (currentCol+1) +
								" BATTLE, kekuatan: " + powerSum +
								", pahlawan: " + herosList
								);
					} else {
						// gudako flee from the battle
						print.println((currentRow+1) + "," + (currentCol+1) +
								" RUN, kekuatan maksimal sejumlah: " + powerSum);
					}

					break;
				case '.': // do nothing
				case 'M': // do nothing
					break;
			}

			for (int i = 0; i < 4; i++) {
				nextRow.push(currentRow + dirRow[i]);
				nextCol.push(currentCol + dirCol[i]);
			}

		} while (!nextRow.isEmpty() && !nextCol.isEmpty());

		print.println("Akhir petualangan Gudako\n"+
				"Level Gudako: " + gudako.getLevel() + "\n" +
				"Level pahlawan:");
		Collections.sort(gudako.getSummoned(), new Comparator<Hero>(){
			public int compare(Hero p1, Hero p2){
				if (p1.getLevel() != p2.getLevel()){
					return -Long.compare(p1.getLevel(), p2.getLevel());
					// return -(p1.getLevel() - p2.getLevel());
				} else {
					return (p1.getSummonedIndex() - p2.getSummonedIndex());
				}
			}
		});
		for (int i = 0; i < gudako.getSummoned().size(); i++) {
			Hero tempHero = gudako.getSummoned().get(i);
			print.println(tempHero.getName() + ": " + tempHero.getLevel());
		}
	}
}
