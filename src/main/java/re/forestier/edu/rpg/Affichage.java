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
        
        if (!player.items.isEmpty()) {
            sb.append("\n\nObjets détaillés (").append(player.currentWeight)
              .append("/").append(player.maxWeight).append(" kg) :");
            player.items.forEach(item -> 
                sb.append("\n   ").append(item)
            );
        }
        
        return sb.toString();
    }
    public static String afficherJoueurMarkdown(Player player) {
    StringBuilder sb = new StringBuilder();
    
    sb.append("# ").append(player.avatarName).append("\n\n");
    sb.append("**Joueur :** ").append(player.playerName).append("\n");
    sb.append("**Classe :** ").append(player.getAvatarClass()).append("\n");
    sb.append("**Niveau :** ").append(player.retrieveLevel())
      .append(" (XP: ").append(player.xp).append(")\n\n");
    
    sb.append("## Capacités\n\n");
    player.abilities.forEach((name, level) -> 
        sb.append("* **").append(name).append("** : ").append(level).append("\n")
    );
    
    sb.append("\n## Inventaire\n\n");
    if (player.inventory.isEmpty()) {
        sb.append("*Vide*\n");
    } else {
        player.inventory.forEach(item -> 
            sb.append("* ").append(item).append("\n")
        );
    }
    
    if (!player.items.isEmpty()) {
        sb.append("\n## Objets détaillés\n\n");
        sb.append("*Poids : ").append(player.currentWeight)
          .append("/").append(player.maxWeight).append(" kg*\n\n");
        player.items.forEach(item -> 
            sb.append("* **").append(item.getName()).append("** (")
              .append(item.getWeight()).append("kg, ")
              .append(item.getValue()).append(" po) - ")
              .append(item.getDescription()).append("\n")
        );
    }
    
    return sb.toString();
}
}