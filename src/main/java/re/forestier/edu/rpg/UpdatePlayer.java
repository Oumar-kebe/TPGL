package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

public class UpdatePlayer {

    private final static String[] objectList = {"Lookout Ring : Prevents surprise attacks","Scroll of Stupidity : INT-2 when applied to an enemy", "Draupnir : Increases XP gained by 100%", "Magic Charm : Magic +10 for 5 rounds", "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", "Combat Edge : Well, that's an edge", "Holy Elixir : Recover your HP"
    };

    private static HashMap<Integer, HashMap<String, Integer>> getAdventurerAbilities() {
    HashMap<Integer, HashMap<String, Integer>> adventurerMap = new HashMap<>();
    
    HashMap<String, Integer> level1 = new HashMap<>();
    level1.put("INT", 1);
    level1.put("DEF", 1);
    level1.put("ATK", 3);
    level1.put("CHA", 2);
    adventurerMap.put(1, level1);

    HashMap<String, Integer> level2 = new HashMap<>();
    level2.put("INT", 2);
    level2.put("CHA", 3);
    adventurerMap.put(2, level2);

    HashMap<String, Integer> level3 = new HashMap<>();
    level3.put("ATK", 5);
    level3.put("ALC", 1);
    adventurerMap.put(3, level3);

    HashMap<String, Integer> level4 = new HashMap<>();
    level4.put("DEF", 3);
    adventurerMap.put(4, level4);

    HashMap<String, Integer> level5 = new HashMap<>();
    level5.put("VIS", 1);
    level5.put("DEF", 4);
    adventurerMap.put(5, level5);

    return adventurerMap;
}

private static HashMap<Integer, HashMap<String, Integer>> getArcherAbilities() {
    HashMap<Integer, HashMap<String, Integer>> archerMap = new HashMap<>();
    
    HashMap<String, Integer> level1 = new HashMap<>();
    level1.put("INT", 1);
    level1.put("ATK", 3);
    level1.put("CHA", 1);
    level1.put("VIS", 3);
    archerMap.put(1, level1);

    HashMap<String, Integer> level2 = new HashMap<>();
    level2.put("DEF", 1);
    level2.put("CHA", 2);
    archerMap.put(2, level2);

    HashMap<String, Integer> level3 = new HashMap<>();
    level3.put("ATK", 3);
    archerMap.put(3, level3);

    HashMap<String, Integer> level4 = new HashMap<>();
    level4.put("DEF", 2);
    archerMap.put(4, level4);

    HashMap<String, Integer> level5 = new HashMap<>();
    level5.put("ATK", 4);
    archerMap.put(5, level5);

    return archerMap;
}

private static HashMap<Integer, HashMap<String, Integer>> getDwarfAbilities() {
    HashMap<Integer, HashMap<String, Integer>> dwarfMap = new HashMap<>();
    
    HashMap<String, Integer> level1 = new HashMap<>();
    level1.put("ALC", 4);
    level1.put("INT", 1);
    level1.put("ATK", 3);
    dwarfMap.put(1, level1);

    HashMap<String, Integer> level2 = new HashMap<>();
    level2.put("DEF", 1);
    level2.put("ALC", 5);
    dwarfMap.put(2, level2);

    HashMap<String, Integer> level3 = new HashMap<>();
    level3.put("ATK", 4);
    dwarfMap.put(3, level3);

    HashMap<String, Integer> level4 = new HashMap<>();
    level4.put("DEF", 2);
    dwarfMap.put(4, level4);

    HashMap<String, Integer> level5 = new HashMap<>();
    level5.put("CHA", 1);
    dwarfMap.put(5, level5);

    return dwarfMap;
}
private static HashMap<Integer, HashMap<String, Integer>> getGoblinAbilities() {
    HashMap<Integer, HashMap<String, Integer>> goblinMap = new HashMap<>();
    
    HashMap<String, Integer> level1 = new HashMap<>();
    level1.put("INT", 2);
    level1.put("ATK", 2);
    level1.put("ALC", 1);
    goblinMap.put(1, level1);

    HashMap<String, Integer> level2 = new HashMap<>();
    level2.put("ATK", 3);
    level2.put("ALC", 4);
    goblinMap.put(2, level2);

    HashMap<String, Integer> level3 = new HashMap<>();
    level3.put("VIS", 1);
    goblinMap.put(3, level3);

    HashMap<String, Integer> level4 = new HashMap<>();
    level4.put("DEF", 1);
    goblinMap.put(4, level4);

    HashMap<String, Integer> level5 = new HashMap<>();
    level5.put("DEF", 2);
    level5.put("ATK", 4);
    goblinMap.put(5, level5);

    return goblinMap;
}
    public static HashMap<AvatarClass, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel() {
    HashMap<AvatarClass, HashMap<Integer, HashMap<String, Integer>>> abilities = new HashMap<>();
    abilities.put(AvatarClass.ADVENTURER, getAdventurerAbilities());
    abilities.put(AvatarClass.ARCHER, getArcherAbilities());
    abilities.put(AvatarClass.DWARF, getDwarfAbilities());
    abilities.put(AvatarClass.GOBLIN, getGoblinAbilities());
    return abilities;
}
    public static boolean addXp(Player player, int xp) {
        int currentLevel = player.retrieveLevel();
        player.xp += xp;
        int newLevel = player.retrieveLevel();

        if (newLevel != currentLevel) {
            Random random = new Random();
            player.inventory.add(objectList[random.nextInt(objectList.length)]);

            HashMap<String, Integer> abilities = abilitiesPerTypeAndLevel().get(player.getAvatarClass()).get(newLevel);
            abilities.forEach((ability, level) -> {
                player.abilities.put(ability, abilities.get(ability));
            });
            return true;
        }
        return false;
    }

    public static void majFinDeTour(Player player) {
    if (player.currentHealthPoints == 0) {
        System.out.println("Le joueur est KO !");
        return;
    }

    if (player.currentHealthPoints < player.maxHealthPoints / 2) {
        player.currentHealthPoints += calculateRegeneration(player);
    }

    if (player.currentHealthPoints > player.maxHealthPoints) {
        player.currentHealthPoints = player.maxHealthPoints;
    }
}

private static int calculateRegeneration(Player player) {
    if (player.getAvatarClass() == AvatarClass.DWARF) {
        int regen = 1;
        if (player.inventory.contains("Holy Elixir")) {
            regen += 1;
        }
        return regen;
    }
    
    if (player.getAvatarClass() == AvatarClass.ARCHER) {
        int regen = 1;
        if (player.inventory.contains("Magic Bow")) {
            regen += player.currentHealthPoints / 8 - 1;
        }
        return regen;
    }
    
    if (player.getAvatarClass() == AvatarClass.ADVENTURER) {
        int regen = 2;
        if (player.retrieveLevel() < 3) {
            regen -= 1;
        }
        return regen;
    }

     if (player.getAvatarClass() == AvatarClass.GOBLIN) {
        return 1;  
    }
    
    return 0;
}
}