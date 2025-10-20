package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.UpdatePlayer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test
    @DisplayName("Test MajFinDeTour: Joueur KO")
    void testMajFinTourJKO(){
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());  
        p.currenthealthpoints = 0;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
   }

   @Test
   @DisplayName("")
   void testMajFinTour(){
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());  
        p.currenthealthpoints = 20;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(21, p.currenthealthpoints);
        
        p = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>(Arrays.asList("Holy Elixir")));  
        p.currenthealthpoints = 20;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(22, p.currenthealthpoints);

        p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>(Arrays.asList("Magic Bow")));  
        p.currenthealthpoints = 20;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(22, p.currenthealthpoints);

        p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>(Arrays.asList("Magic Bow")));  
        p.currenthealthpoints = 50;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints);

        p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>(Arrays.asList("Magic Bow")));  
        p.currenthealthpoints = 101;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(100, p.currenthealthpoints);
        
        
    }

    @Test
    @DisplayName("player")
    void test2() {
        player p = new player("Florian", "Grognak le barbare", "CCCC", 100, new ArrayList<>());  

        p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>(Arrays.asList("Magic Bow"))); 
        p.removeMoney(50);
        assertEquals(50, p.money);

        p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>(Arrays.asList("Magic Bow"))); 
        Integer val = 50;
        p.addMoney(val);
        assertEquals(150, p.money);



        p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>(Arrays.asList("Magic Bow")));  
        assertEquals(1, p.retrieveLevel());
        UpdatePlayer.addXp(p, 10);
        assertEquals(2, p.retrieveLevel());
        UpdatePlayer.addXp(p, 17);
        assertEquals(3, p.retrieveLevel());
        UpdatePlayer.addXp(p, 30);
        assertEquals(4, p.retrieveLevel());
        UpdatePlayer.addXp(p, 54);
        assertEquals(5, p.retrieveLevel());
    }
        

    @Test
    @DisplayName("lambda")
    void test3() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());  
        UpdatePlayer.addXp(p,3);
        assertEquals(3, p.getXp());
        UpdatePlayer.addXp(p,3);
        assertEquals(6, p.getXp());


    }

}
