import java.util.*;

/**
 * nama : restow frandha
 * npm  : 1606877736
 */
public class restow {
	static int jp,js,jd,ma,R,C,stR,stC;
	static char[][] map;
	static List<Hero> listHero = new ArrayList<Hero>();
	static List<Hero> gidakoFollower = new ArrayList<Hero>();
	static Map<String,Dungeon> mapDg = new HashMap<String,Dungeon>();
	static Map<String,TreeSet<Hero>> mapSum = new HashMap<String,TreeSet<Hero>>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		jp = sc.nextInt();
		js = sc.nextInt();
		jd = sc.nextInt();
		ma = sc.nextInt();
		R = sc.nextInt();
		C = sc.nextInt();
		sc.nextLine();
		int i = 0;
		String rowMap = null;
		map = new char[C+2][R+2];

		while(i < jp){
			String[] hero = sc.nextLine().split(";");
			listHero.add(new Hero(hero[0],Integer.parseInt(hero[1]),Integer.parseInt(hero[2]),hero[3]));
			i++;
		}

		Collections.sort(listHero);
		while(i < jp+js){
			String[] summon = sc.nextLine().split(";");
			String[] lstHero = summon[2].split(",");
			String koor = summon[0] + "," + summon[1];
			TreeSet<Hero> heroPanggil = new TreeSet<Hero>();
			for(int j = 0; j < lstHero.length; j++){
				for(int k = 0; k < listHero.size(); k++){
					if(listHero.get(k).getNama().equals(lstHero[j])){
						heroPanggil.add(listHero.get(k));
					}
				}
			}
			mapSum.put(koor, heroPanggil);
			i++;
		}

		while(i<jp+js+jd){
			String[] dg = sc.nextLine().split(";");
			String koors = dg[0] + "," + dg[1];
			mapDg.put(koors, new Dungeon(Integer.parseInt(dg[2]),Integer.parseInt(dg[3]),dg[4],Integer.parseInt(dg[5])));
			i++;
		}

		for(int a = 0; a < R+2; a++){
			if(a != 0 && a != R+1){
				rowMap = sc.nextLine();
			}
			for(int b = 0; b < C+2; b++){
				if(a == 0 || b == 0 || b == C+1 || a == R+1){
					map[b][a] = '#';
				}
				else if(rowMap.charAt(b-1) == 'M'){
					map[b][a] = 'M';
					stR = a;
					stC = b;
				}else{
					map[b][a] = rowMap.charAt(b-1);

				}
			}
		}
		startGame(stR, stC);
		sc.close();
	}

	private static void startGame(int r, int c) {
		Queue<String> jalan = new LinkedList<String>();
		int lvGudako = 1;
		int sumIdx = 0;
		searchJalan(jalan,r,c);
		int iter = jalan.size();
		jalan.poll();
		for(int i = 0; i < iter; i++){
			String koor = jalan.poll();
			List<Hero> nowH = new ArrayList<Hero>();
			if(mapDg.containsKey(koor)){
				Dungeon dg = mapDg.get(koor);
				int totKet = 0;
				int banding = (dg.getJmlPhlwn() < gidakoFollower.size()?dg.getJmlPhlwn():gidakoFollower.size());
				for(int q = 0; q < gidakoFollower.size(); q++){
					gidakoFollower.get(q).setKekuatan(gidakoFollower.get(q).getKekuatan(dg.getTipe()));
				}
				Collections.sort(gidakoFollower);
				for(int k = 0; k < banding; k++){
					nowH.add(gidakoFollower.get(k));
					totKet += gidakoFollower.get(k).getKekuatan();
				}
				if(totKet >= dg.getKekuatan()){
					System.out.print(koor + " BATTLE, kekuatan: " + totKet + ", pahlawan: ");
					for(int m = 0; m < nowH.size()-1; m++){
						System.out.print(nowH.get(m).getNama() + ",");
						nowH.get(m).setLevel(nowH.get(m).getLevel()+dg.getLevel());
					}
					System.out.println(nowH.get(nowH.size()-1).getNama());
					nowH.get(nowH.size()-1).setLevel(nowH.get(nowH.size()-1).getLevel()+dg.getLevel());
					lvGudako += (banding*dg.getLevel());
				}else{
					System.out.println(koor + " RUN, kekuatan maksimal sejumlah: " + totKet);
				}
				for(int w = 0; w < gidakoFollower.size(); w++){
					gidakoFollower.get(w).setKekuatan(gidakoFollower.get(w).getKekuatanAsli());
				}


			}else if(mapSum.containsKey(koor)){
				TreeSet<Hero> tmp = mapSum.get(koor);
				int nowMana = ma;
				while(!tmp.isEmpty()){
					Hero hero = tmp.pollFirst();
					if(nowMana-hero.getMana() >= 0){
						hero.setPos(sumIdx);
						gidakoFollower.add(hero);
						nowH.add(hero);
						nowMana -= hero.getMana();
						sumIdx++;
					}else{
						break;
					}
				}
				if(nowH.size() == 0){
					System.out.println(koor + " tidak ada pahlawan yang ikut");
				}else{
					System.out.print(koor + " Pahlawan yang ikut:");
					for(int j = 0; j<nowH.size()-1;j++){
						System.out.print(nowH.get(j).getNama() + ",");
					}
					System.out.println(nowH.get(nowH.size()-1).getNama());
				}
				Collections.sort(gidakoFollower);
			}else{
				continue;
			}
		}
		System.out.println("Akhir petualangan Gudako");
		System.out.println("Level Gudako: " + lvGudako);
		System.out.println("Level pahlawan:");
		for(int n = 0; n < gidakoFollower.size(); n++){
			gidakoFollower.get(n).setKekuatan(999);
			gidakoFollower.get(n).setMana(999);
		}
		Collections.sort(gidakoFollower);
		for(int o = 0; o < gidakoFollower.size(); o++){

			System.out.println(gidakoFollower.get(o).getNama() + ": " + gidakoFollower.get(o).getLevel());
		}
	}

	private static void searchJalan(Queue<String> jalan, int r, int c){
		if(getMap(r,c) != '#'){
			jalan.add(r + "," + c);
			setMap(r,c,'#');
			searchJalan(jalan,r-1,c);
			searchJalan(jalan,r,c+1);
			searchJalan(jalan,r+1,c);
			searchJalan(jalan,r,c-1);
		}
	}

	private static char getMap(int r, int c){
		return map[c][r];
	}

	private static void setMap(int r, int c, char ch){
		map[c][r] = ch;
	}
}

class Hero implements Comparable<Hero> {
	private String nama,jenis;
	private int mana,kekuatan,level,pos,kekuatanAsli;
	public Hero(String nama, int mana, int kekuatan, String jenis){
		this.nama = nama;
		this.mana = mana;
		this.kekuatan = kekuatan;
		this.jenis = jenis;
		this.level = 1;
		this.pos = 0;
		this.kekuatanAsli = kekuatan;
	}

	public void setMana(int i) {
		this.mana = i;

	}

	public int getKekuatanAsli() {
		return kekuatanAsli;
	}

	public void setKekuatanAsli(int kekuatanAsli) {
		this.kekuatanAsli = kekuatanAsli;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getNama() {
		return nama;
	}

	public String getJenis() {
		return jenis;
	}

	public int getMana() {
		return mana;
	}

	public int getKekuatan(){
		return kekuatan;
	}

	public int getKekuatan(String tipe) {
		if(tipe.equals(this.jenis)){
			return this.kekuatan;
		}else if(this.jenis.equals("pedang")) {
			return this.kekuatan/2;
		}else{
			return this.kekuatan*2;
		}
	}

	public void setKekuatan(int kek){
		this.kekuatan = kek;
	}

	public int compareTo(Hero o) {
		if(o.kekuatan != this.kekuatan) return o.kekuatan - this.kekuatan;
		if(o.level != this.level) return o.level - this.level;
		if(o.mana != this.mana) return this.mana-o.mana;
		if(o.pos != this.pos) return this.pos-o.pos;
		return this.nama.compareTo(o.nama);
	}
}

class Dungeon {
	private String tipe;
	private int kekuatan, level, jmlPhlwn;
	public Dungeon( int kekuatan, int level, String tipe, int jmlPhlwn) {
		this.tipe = tipe;
		this.kekuatan = kekuatan;
		this.level = level;
		this.jmlPhlwn = jmlPhlwn;
	}

	public String getTipe() {
		return tipe;
	}

	public int getKekuatan() {
		return kekuatan;
	}

	public int getLevel() {
		return level;
	}

	public int getJmlPhlwn() {
		return jmlPhlwn;
	}
}
