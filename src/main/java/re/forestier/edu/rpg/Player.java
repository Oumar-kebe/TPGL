package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    public String playerName;
    public String avatarName;
    private AvatarClass avatarClass;

    public Integer money;

    public int level;
    public int maxHealthPoints;
    public int currentHealthPoints;
    protected int xp;

    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
    
    public Player(String playerName, String avatarName, String avatarClassStr, int money, ArrayList<String> inventory) {
        try {
            this.avatarClass = AvatarClass.valueOf(avatarClassStr);
        } catch (IllegalArgumentException e) {
            this.avatarClass = null;
            return;
        }

        this.playerName = playerName;
        this.avatarName = avatarName;
        this.money = Integer.valueOf(money);
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(this.avatarClass).get(1);
    }

    public AvatarClass getAvatarClass() {
        return avatarClass;
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }
        money -= amount;
    }

    public void addMoney(int amount) {
        money += amount;
    }
        
    public int retrieveLevel() {
    if (xp < 10) return 1;
    if (xp < 27) return 2;
    if (xp < 57) return 3;
    if (xp < 111) return 4;
    return 5;
}

    public int getXp() {
        return this.xp;
    }
}