package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class player {
    public String playerName;
    public String Avatar_name;
    private AvatarClass avatarClass;

    public Integer money;

    public int level;
    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;

    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
    
    public player(String playerName, String avatar_name, String avatarClassStr, int money, ArrayList<String> inventory) {
        try {
            this.avatarClass = AvatarClass.valueOf(avatarClassStr);
        } catch (IllegalArgumentException e) {
            this.avatarClass = null;
            return;
        }

        this.playerName = playerName;
        Avatar_name = avatar_name;
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

        money = Integer.parseInt(money.toString()) - amount;
    }
    
    public void addMoney(int amount) {
        var value = Integer.valueOf(amount);
        money = money + (value != null ? value : 0);
    }
    
    public int retrieveLevel() {
        HashMap<Integer, Integer> levels = new HashMap<>();
        levels.put(2,10);
        levels.put(3,27);
        levels.put(4,57);
        levels.put(5,111);

        if (xp < levels.get(2)) {
            return 1;
        }
        else if (xp < levels.get(3)) {
            return 2;
        }
        if (xp < levels.get(4)) {
            return 3;
        }
        if (xp < levels.get(5)) return 4;
        return 5;
    }

    public int getXp() {
        return this.xp;
    }
}