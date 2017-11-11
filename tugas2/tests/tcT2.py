#! /usr/bin/env python3

import random
import string

MAX_ROW = 100
MAX_COL = 100
MAX_LENGTH_NAME = 100
MAX_HERO_EACH = 2500
DEFAULT_MANA = 2000000
MAX_HERO_MANA = 2000
MAX_HERO_POWER = 200000
MAX_DUNGEON_POWER = 200

ALL_CHAR = string.ascii_letters+string.digits+' '

def replace(ss, idx, char):
    tmp_l = list(ss)
    tmp_l[idx] = char
    tmp_s = ''.join(x for x in tmp_l)
    return tmp_s

r,c = random.randint(1, MAX_ROW-1), random.randint(1, MAX_COL-1)
mp = []

map_char = '.'

for i in range(r+1):
    mp.append(''.join(random.choice(map_char) for _ in range(c+1)))
    while('.' not in mp[-1]):
        mp[-1] = (''.join(random.choice(map_char) for _ in range(c+1)))

while(True):
    sr, sc = random.randint(0, r), random.randint(0, c)
    if(mp[sr][sc]=='#'): continue
    mp[sr] = replace(mp[sr], sc, 'M')
    break

st = set()
summon = []
hero = []
cnt_s = 0;
cnt_h = 0;

for i in range(r+1):
    for j in range(c+1):
        check = random.choice([True, False])
        if(mp[i][j]=='.' and check):
            lol_h = random.randint(1, MAX_HERO_EACH)
            if(lol_h + cnt_h > 2500): continue

            cnt_h += lol_h
            cnt_s += 1

            all_name = ""
            for k in range(lol_h):
                while(True):
                    name = ''.join(random.choice(ALL_CHAR) for _ in range(random.randint(1, MAX_LENGTH_NAME)))
                    if(name in st): continue
                    st.update(name)
                    break
                power = random.randint(0, MAX_HERO_POWER)
                mana = random.randint(0, MAX_HERO_MANA)
                weapon = random.choice(['pedang', 'panah'])
                hero.append('{};{};{};{}'.format(name, mana, power, weapon))
                all_name += name + ","
            all_name = all_name[:-1]
            summon.append('{};{};{}'.format(i+1, j+1, all_name))

            mp[i] = replace(mp[i], j, 'S')

dungeon = []
cnt_d = 0

for i in range(r+1):
    for j in range(c+1):
        check = random.choice([True, False])
        if(mp[i][j]=='.' and check):
            cnt_d += 1

            power = random.randint(0, MAX_DUNGEON_POWER)
            level = random.randint(0, 2000000)
            weapon = random.choice(['pedang', 'panah'])
            max_hero = random.randint(0, 2000000)

            dungeon.append('{};{};{};{};{};{}'.format(i+1, j+1, power, level, weapon, max_hero))

            mp[i] = replace(mp[i], j, 'D')

assert cnt_h == len(hero)
assert cnt_s == len(summon)
assert cnt_d == len(dungeon)

print(cnt_h, cnt_s, cnt_d, DEFAULT_MANA, r+1, c+1)

for qw in hero:
    print(qw)

for qw in summon:
    print(qw)

for qw in dungeon:
    print(qw)

for i in range(r+1):
    print(mp[i])


