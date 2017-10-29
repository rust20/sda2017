import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SDA16069176345T2 {
	static final int PAHLAWAN_MAX = 3000;
	static final int SUMMON_MAX = 2500;
	static final int DUNGEON_MAX = 2500;

	public static void main (String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		HashMap<String, Pahlawan> mapOfHero = new HashMap<>();

		String [] inputs = scan.readLine().split(" ");
		int jumlahPahlawan = Integer.valueOf(inputs[0]);
		int jumlahSummon = Integer.valueOf(inputs[1]);
		int jumlahDungeon = Integer.valueOf(inputs[2]);
		int manaGudako = Integer.valueOf(inputs[3]);
		int row = Integer.valueOf(inputs[4]);
		int column = Integer.valueOf(inputs[5]);

		World world = new World(row, column, manaGudako);

		for (int i = 0; i < jumlahPahlawan; i++) {
			inputs = scan.readLine().split(";");
			Pahlawan tmpHero = new Pahlawan(inputs[0],inputs[1],inputs[2],inputs[3]);
			mapOfHero.put(inputs[0], tmpHero);
		}

		for (int i = 0; i < jumlahSummon; i++) {
			inputs = scan.readLine().split(";");
			String [] heros = inputs[2].split(",");
			ArrayList<Pahlawan> tempHeroList = new ArrayList<>();

			for (String hero : heros) {
				tempHeroList.add(mapOfHero.get(hero));
			}
			world.putSummons(tempHeroList,
					Integer.valueOf(inputs[0]) - 1,
					Integer.valueOf(inputs[1]) - 1);
		}

		for (int i = 0; i < jumlahDungeon; i++) {
			inputs = scan.readLine().split(";");
			world.putDungon(new Dungeon(inputs[2], inputs[3], inputs[4], inputs[5]),
					Integer.valueOf(inputs[0]) - 1,
					Integer.valueOf(inputs[1]) - 1);
		}

		for (int i = 0; i < row; i++) {
			String strRow = scan.readLine();
			world.setRow(strRow, i);
			for (int j = 0; j < strRow.length(); j++) {
				if (strRow. == 'M'){

				}
			}
		}
	}
}
class Pahlawan{
	private String nama;
	private int mana;
	private int kekuatan;
	private String jenisSenjata;

	public Pahlawan(String nama, String mana, String kekuatan, String jenisSenjata) {
		this.nama = nama;
		this.mana = Integer.valueOf(mana);
		this.kekuatan = Integer.valueOf(kekuatan);
		this.jenisSenjata = jenisSenjata;
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
}

class World{
	private HashMap<Integer, Dungeon> mapDungeon;
	private HashMap<Integer, ArrayList<Pahlawan>> mapSummon;
	private char [][] mapChar;
	private int row;
	private int column;
	private int mana;

	public World(int row, int column, int mana) {
		this.row = row;
		this.column = column;
		this.mana = mana;

		mapSummon = new HashMap<>();
		mapDungeon = new HashMap<>();
		mapChar = new char[row][column];
	}

	public void putDungon(Dungeon dungeon, int row, int column){
		 mapDungeon.put(this.row*column + row, dungeon);
	}

	public void putSummons(ArrayList<Pahlawan> listOfPahlawan, int row, int column){
		 mapSummon.put(this.row*column + row, listOfPahlawan);
	}

	public Dungeon getDungeon(int row, int column){
		return mapDungeon.get(this.row*column + row);
	}

	public ArrayList<Pahlawan> getSummon(int row, int column){
		return mapSummon.get(this.row*column + row);
	}

	public void setRow(String row, int nRow){
		mapChar[nRow] = row.toCharArray();
	}
}
