package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.UpdatePlayer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class UnitTests {

        @Test
        @DisplayName("Création réussie DWARF")
        void testCreateDwarf() {
            player p = new player("Pierre", "Gimli", "DWARF", 150, new ArrayList<>());
            assertThat(p.getAvatarClass(), is("DWARF"));
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
        @DisplayName("DWARF sans Holy Elixir sous 50%")
        void testDwarfWithoutHolyElixir() {
            player p = new player("Test", "Avatar", "DWARF", 100, new ArrayList<>());
            p.currenthealthpoints = 20;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(21, p.currenthealthpoints);
        }

        @Test
        @DisplayName("ARCHER avec Magic Bow sous 50%")
        void testArcherWithMagicBow() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>(Arrays.asList("Magic Bow")));
            p.currenthealthpoints = 20;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(22, p.currenthealthpoints);
        }

        @Test
        @DisplayName("ARCHER sans Magic Bow sous 50%")
        void testArcherWithoutMagicBow() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            p.currenthealthpoints = 20;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(21, p.currenthealthpoints);
        }

        @Test
        @DisplayName("ADVENTURER niveau 1 sous 50%")
        void testAdventurerLevel1() {
            player p = new player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            p.currenthealthpoints = 20;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(21, p.currenthealthpoints);
        }

        @Test
        @DisplayName("ADVENTURER niveau 3+ sous 50%")
        void testAdventurerLevel3Plus() {
            player p = new player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 27);
            p.currenthealthpoints = 20;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(22, p.currenthealthpoints);
        }

        @Test
        @DisplayName("Pas de regen au-dessus 50%")
        void testNoRegenAbove50() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            p.currenthealthpoints = 50;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(50, p.currenthealthpoints);
        }

        @Test
        @DisplayName("HP capé au maximum")
        void testHPCapped() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            p.currenthealthpoints = 101;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(100, p.currenthealthpoints);
        }

        @Test
        @DisplayName("HP égal au max reste inchangé")
        void testHPAtMax() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            p.currenthealthpoints = 100;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(100, p.currenthealthpoints);
        }
    }
}