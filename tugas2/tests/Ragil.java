// Ragil Al Badrun Pasaribu
// 1606862734

import java.io.*;
import java.util.*;


class Position {
    // +5 for safety
    public int row;
    public int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    // function to check if objects are the same

    @Override
    public boolean equals(Object other){
        Position ot = (Position) other;
        return (this.row==ot.row && this.col==ot.col);
    }

    @Override
    public int hashCode(){
        return Objects.hash(row, col);
    }
}

class Hero implements Comparable<Hero> {
    public String name;
    public long mana;
    public long level;
    public int weapon; // 0 for sword, 1 for arrow
    public long power;
    public int time; // the time of the hero joins gudako

    public Hero(String name, long mana, long power, int weapon){
        this.name = name;
        this.mana = mana;
        this.power = power;
        this.weapon = weapon;
        this.level = 1;
    }

    public static int convertWeapon(String weapon){
        return weapon.equalsIgnoreCase("pedang")?0:1;
    }

    public long realPower(int dungeonWeapon){
        if(this.weapon > dungeonWeapon) return 2*power;
        else if(this.weapon < dungeonWeapon) return power/2;
        else return power;
    }

    @Override
    public int compareTo(Hero other){
        if(this.power!=other.power) return (other.power<this.power?-1:1); // greater power
        else if(this.mana!=other.mana) return (this.mana<other.mana?-1:1);
        else return this.name.compareTo(other.name);
    }
}

class Dungeon {
    public long power;
    public long level;
    public int weapon; // 0 for sword, 1 for arrow
    public int maxHero;

    public Dungeon(long power, long level, int weapon, int maxHero){
        this.power = power;
        this.level = level;
        this.weapon = weapon;
        this.maxHero = maxHero;
    }
}

class GudakoComparatorDungeon implements Comparator<Hero> {
    public int dungeonWeapon;

    public GudakoComparatorDungeon(int dungeonWeapon){
        this.dungeonWeapon = dungeonWeapon;
    }

    @Override
    public int compare(Hero o1, Hero o2){
        long power1 = o1.realPower(dungeonWeapon);
        long power2 = o2.realPower(dungeonWeapon);

        if(power1!=power2) return (power2<power1?-1:1); // greater power
        else if(o1.time!=o2.time) return o1.time - o2.time;
        else return o1.compareTo(o2);
    }
}

class GudakoComparatorLevel implements Comparator<Hero> {

    @Override
    public int compare(Hero o1, Hero o2){
        if(o1.level!=o2.level) return (o2.level<o1.level?-1:1); // greater level
        else if(o1.time!=o2.time) return o1.time - o2.time;
        else return o1.compareTo(o2);
    }
}

public class Ragil {
    public static void main(String args[]) throws IOException {

        // buffered reader and writer for faster io
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        // variables that store the data
        HashMap<String, Hero> heroName = new HashMap<>();
        HashMap<Position, ArrayList<Hero> > positionHeroList = new HashMap<>();
        HashMap<Position, Dungeon> positionDungeonList = new HashMap<>();
        char gudakoMap[][] = new char[115][115]; // +5 for safety

        // temporary variable to parse input
        String splitted[];

        splitted = input.readLine().split(" ");
        int numberHero = Integer.parseInt(splitted[0]);
        int numberSummon = Integer.parseInt(splitted[1]);
        int numberDungeon = Integer.parseInt(splitted[2]);
        long gudakoMana = Long.parseLong(splitted[3]);
        int row = Integer.parseInt(splitted[4]);
        int col = Integer.parseInt(splitted[5]);

        // read hero
        for(int i=0; i<numberHero; i++){
            splitted = input.readLine().split(";");

            String name = splitted[0];
            long mana = Long.parseLong(splitted[1]);
            long power = Long.parseLong(splitted[2]);
            int weapon = Hero.convertWeapon(splitted[3]);

            heroName.put(name, new Hero(name, mana, power, weapon));
        }

        // read summon
        for(int i=0; i<numberSummon; i++){
            splitted = input.readLine().split(";");

            int tRow = Integer.parseInt(splitted[0]);
            int tCol = Integer.parseInt(splitted[1]);
            String heroListString = splitted[2];

            Position tPos = new Position(tRow, tCol);
            ArrayList<Hero> heroList = new ArrayList<>();

            StringTokenizer token = new StringTokenizer(heroListString, ",");
            while(token.hasMoreTokens()){
                heroList.add(heroName.get(token.nextToken()));
            }

            Collections.sort(heroList);
            positionHeroList.put(tPos, heroList);
        }

        // read dungeon
        for(int i=0; i<numberDungeon; i++){
            splitted = input.readLine().split(";");

            int tRow = Integer.parseInt(splitted[0]);
            int tCol = Integer.parseInt(splitted[1]);
            long power = Long.parseLong(splitted[2]);
            long level = Long.parseLong(splitted[3]);
            int weapon = Hero.convertWeapon(splitted[4]);
            int maxHero = Integer.parseInt(splitted[5]);

            // get the array of dungeon from current position
            Position tPos = new Position(tRow, tCol);
            positionDungeonList.put(tPos, new Dungeon(power, level, weapon, maxHero));
        }

        // Gudako's stats
        ArrayList<Hero> gudakoHero = new ArrayList<>();
        Stack<Position> gudakoPosition = new Stack<>();
        long gudakoLevel = 1;
        int stepRow[] = {0, 1, 0, -1}; // gudako's step
        int stepCol[] = {-1, 0, 1, 0};
        int time = 0; // to know when the hero joins gudako

        for(int i=1; i<=row; i++){
            for(int j=1; j<=col; j++){
                gudakoMap[i][j] = (char) input.read();
                if(gudakoMap[i][j]=='M') gudakoPosition.push(new Position(i, j));
            }
            input.readLine(); // read newline
        }

        while(!gudakoPosition.empty()){
            Position now = gudakoPosition.pop();
            int nRow = now.row;
            int nCol = now.col;

            if(gudakoMap[nRow][nCol]=='X') continue;

            if(gudakoMap[nRow][nCol]=='S'){
                ArrayList<Hero> nowHero = positionHeroList.get(now);
                ArrayList<String> heroListName = new ArrayList<>(); // heroes who follow gudako

                // get the hero
                long tempMana = gudakoMana;
                for(int i=0; i<nowHero.size(); i++){
                    if(tempMana >= nowHero.get(i).mana){
                        nowHero.get(i).time = time; // set the time
                        gudakoHero.add(nowHero.get(i)); // add to gudako hero
                        tempMana -= nowHero.get(i).mana; // subtract mana
                        heroListName.add(nowHero.get(i).name); // store the name
                    } else break;
                }

                // print
                if(!heroListName.isEmpty()){
                    output.write(nRow + "," + nCol + " Pahlawan yang ikut:");
                    for(int i=0; i<heroListName.size(); i++){
                        if(i>0) output.write(",");
                        output.write(heroListName.get(i));
                    }
                } else {
                    output.write(nRow + "," + nCol + " tidak ada pahlawan yang ikut");
                }
                output.newLine();

                time++; // +1 the time
            }

            if(gudakoMap[nRow][nCol]=='D'){
                Dungeon dungeonNow = positionDungeonList.get(now);
                Collections.sort(gudakoHero, new GudakoComparatorDungeon(dungeonNow.weapon));

                long sumPower=0;
                ArrayList<String> heroListName = new ArrayList<>(); // heroes who fight with gudako

                // get the hero
                for(int i=0; i<gudakoHero.size() && i<dungeonNow.maxHero; i++){
                    sumPower += gudakoHero.get(i).realPower(dungeonNow.weapon);
                    heroListName.add(gudakoHero.get(i).name);
                }

                if(sumPower>=dungeonNow.power){
                    output.write(nRow + "," + nCol + " BATTLE, kekuatan: " + sumPower + ", pahlawan: ");
                    for(int i=0; i<heroListName.size(); i++){
                        if(i>0) output.write(",");
                        output.write(heroListName.get(i));
                        gudakoHero.get(i).level += dungeonNow.level; // add the level
                    }
                    gudakoLevel += ((long) heroListName.size())*dungeonNow.level;
                } else {
                    output.write(nRow + "," + nCol + " RUN, kekuatan maksimal sejumlah: " + sumPower);
                }
                output.newLine();
            }

            // flag visited
            gudakoMap[nRow][nCol] = 'X';

            // step to another place
            for(int i=0; i<4; i++){
                if(nRow+stepRow[i]>=1 && nRow+stepRow[i]<=row && nCol+stepCol[i]>=1 && nCol+stepCol[i]<=col &&
                   gudakoMap[nRow+stepRow[i]][nCol+stepCol[i]]!='X' && gudakoMap[nRow+stepRow[i]][nCol+stepCol[i]]!='#'){

                    gudakoPosition.push(new Position(nRow+stepRow[i], nCol+stepCol[i]));
                }
            }
        }

        // print gudako's level and his/her heroes
        output.write("Akhir petualangan Gudako\n");
        output.write("Level Gudako: " +  gudakoLevel + "\n");
        output.write("Level pahlawan:\n");

        Collections.sort(gudakoHero, new GudakoComparatorLevel());
        for(int i=0; i<gudakoHero.size(); i++)
            output.write(gudakoHero.get(i).name + ": " + gudakoHero.get(i).level + "\n");

        output.flush();
    }
}
