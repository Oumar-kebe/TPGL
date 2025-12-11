package re.forestier.edu.rpg;

public class Affichage {

    public static String afficherJoueur(Player player) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Joueur ").append(player.avatarName)
          .append(" joué par ").append(player.playerName);
        
        sb.append("\nNiveau : ").append(player.retrieveLevel())
          .append(" (XP totale : ").append(player.xp).append(")");
        
        sb.append("\n\nCapacités :");
        player.abilities.forEach((name, level) -> 
            sb.append("\n   ").append(name).append(" : ").append(level)
        );
        
        sb.append("\n\nInventaire :");
        player.inventory.forEach(item -> 
            sb.append("\n   ").append(item)
        );
        
        return sb.toString();
    }
}