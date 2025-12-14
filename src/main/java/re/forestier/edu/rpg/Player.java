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
    public ArrayList<Item> items;        
    public int maxWeight;                
    public int currentWeight;
    
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
    this.items = new ArrayList<>();
    this.maxWeight = 50;  
    this.currentWeight = 0;
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

    public boolean addItem(Item item) {
    if (currentWeight + item.getWeight() > maxWeight) {
        return false; 
    }
    items.add(item);
    currentWeight += item.getWeight();
    return true;
}

public boolean sellItem(Item item) {
    if (items.remove(item)) {
        money += item.getValue();
        currentWeight -= item.getWeight();
        return true;
    }
    return false;
}

public Item getItemByName(String name) {
    for (Item item : items) {
        if (item.getName().equals(name)) {
            return item;
        }
    }
    return null;
}
}