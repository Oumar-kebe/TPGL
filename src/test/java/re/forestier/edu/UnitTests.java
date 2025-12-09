package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.UpdatePlayer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class UnitTests {

    @Nested
    @DisplayName("Tests de cr�ation de joueur")
    class PlayerCreationTests {
        
        @Test
        @DisplayName("Cr�ation r�ussie ADVENTURER")
        void testCreateAdventurer() {
            player p = new player("Florian", "Grognak", "ADVENTURER", 100, new ArrayList<>());
            assertThat(p.playerName, is("Florian"));
            assertThat(p.Avatar_name, is("Grognak"));
            assertThat(p.getAvatarClass(), is("ADVENTURER"));
            assertThat(p.money, is(100));
        }

        @Test
        @DisplayName("Cr�ation r�ussie ARCHER")
        void testCreateArcher() {
            player p = new player("Jean", "Legolas", "ARCHER", 200, new ArrayList<>());
            assertThat(p.getAvatarClass(), is("ARCHER"));
        }

        @Test
        @DisplayName("Cr�ation r�ussie DWARF")
        void testCreateDwarf() {
            player p = new player("Pierre", "Gimli", "DWARF", 150, new ArrayList<>());
            assertThat(p.getAvatarClass(), is("DWARF"));
        }

        @Test
        @DisplayName("Cr�ation avec classe invalide")
        void testCreateInvalidClass() {
            player p = new player("Test", "Invalid", "INVALID", 100, new ArrayList<>());
            assertNull(p.getAvatarClass());
        }
    }

    @Nested
    @DisplayName("Tests de gestion de l'argent")
    class MoneyManagementTests {
        
        @Test
        @DisplayName("Ajout d'argent")
        void testAddMoney() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            p.addMoney(50);
            assertEquals(150, p.money);
        }

        @Test
        @DisplayName("Retrait d'argent")
        void testRemoveMoney() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            p.removeMoney(50);
            assertEquals(50, p.money);
        }

        @Test
        @DisplayName("Impossible argent n�gatif")
        void testNegativeMoney() {
            player p = new player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            assertThrows(IllegalArgumentException.class, () -> p.removeMoney(200));
        }
    }

    @Nested
    @DisplayName("Tests du syst�me de niveaux et XP")
    class LevelAndXpTests {
        
        @Test
        @DisplayName("Niveau 1 par d�faut")
        void testDefaultLevel() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            assertEquals(1, p.retrieveLevel());
            assertEquals(0, p.getXp());
        }

        @Test
        @DisplayName("Passage niveau 2 avec 10 XP")
        void testLevelUp2() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 10);
            assertEquals(2, p.retrieveLevel());
            assertEquals(10, p.getXp());
        }

        @Test
        @DisplayName("Passage niveau 3 avec 27 XP")
        void testLevelUp3() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 27);
            assertEquals(3, p.retrieveLevel());
            assertEquals(27, p.getXp());
        }

        @Test
        @DisplayName("Passage niveau 4 avec 57 XP")
        void testLevelUp4() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 57);
            assertEquals(4, p.retrieveLevel());
            assertEquals(57, p.getXp());
        }

        @Test
        @DisplayName("Passage niveau 5 avec 111 XP")
        void testLevelUp5() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 111);
            assertEquals(5, p.retrieveLevel());
            assertEquals(111, p.getXp());
        }

        @Test
        @DisplayName("XP exactement � la limite niveau 2")
        void testExactly10XP() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 10);
            assertEquals(2, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP juste avant limite niveau 2")
        void testJustBeforeLevel2() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 9);
            assertEquals(1, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP exactement � la limite niveau 3")
        void testExactly27XP() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 27);
            assertEquals(3, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP juste avant limite niveau 3")
        void testJustBeforeLevel3() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 26);
            assertEquals(2, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP exactement � la limite niveau 4")
        void testExactly57XP() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 57);
            assertEquals(4, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP juste avant limite niveau 4")
        void testJustBeforeLevel4() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 56);
            assertEquals(3, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP exactement � la limite niveau 5")
        void testExactly111XP() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 111);
            assertEquals(5, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP juste avant limite niveau 5")
        void testJustBeforeLevel5() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 110);
            assertEquals(4, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP bien au-del� niveau 5")
        void testWellBeyondLevel5() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 200);
            assertEquals(5, p.retrieveLevel());
        }

        @Test
        @DisplayName("addXp retourne true lors level-up")
        void testAddXpReturnsTrueOnLevelUp() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            boolean result = UpdatePlayer.addXp(p, 10);
            assertTrue(result);
        }

        @Test
        @DisplayName("addXp retourne false sans level-up")
        void testAddXpReturnsFalseWithoutLevelUp() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            boolean result = UpdatePlayer.addXp(p, 5);
            assertFalse(result);
        }

        @Test
        @DisplayName("Level-up ajoute objet inventaire")
        void testLevelUpAddsItem() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            int initialSize = p.inventory.size();
            UpdatePlayer.addXp(p, 10);
            assertEquals(initialSize + 1, p.inventory.size());
        }

        @Test
        @DisplayName("Level-up ARCHER niveau 2 met � jour abilities")
        void testLevelUpArcherAbilities() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 10);
            assertThat(p.abilities.get("DEF"), is(1));
            assertThat(p.abilities.get("CHA"), is(2));
        }

        @Test
        @DisplayName("Level-up DWARF niveau 2 met � jour abilities")
        void testLevelUpDwarfAbilities() {
            player p = new player("Test", "Avatar", "DWARF", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 10);
            assertThat(p.abilities.get("DEF"), is(1));
            assertThat(p.abilities.get("ALC"), is(5));
        }

        @Test
        @DisplayName("Level-up ADVENTURER niveau 2 met � jour abilities")
        void testLevelUpAdventurerAbilities() {
            player p = new player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 10);
            assertThat(p.abilities.get("INT"), is(2));
            assertThat(p.abilities.get("CHA"), is(3));
        }
    }

    @Nested
    @DisplayName("Tests Affichage")
    class AffichageTests {
        
        @Test
        @DisplayName("Affichage joueur niveau 1")
        void testAffichageLevel1() {
            player p = new player("Florian", "Grognak", "ADVENTURER", 100, new ArrayList<>());
            String result = re.forestier.edu.rpg.Affichage.afficherJoueur(p);
            assertThat(result, containsString("Joueur Grognak"));
            assertThat(result, containsString("Florian"));
            assertThat(result, containsString("Niveau : 1"));
        }

        @Test
        @DisplayName("Affichage avec capacit�s")
        void testAffichageWithAbilities() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            String result = re.forestier.edu.rpg.Affichage.afficherJoueur(p);
            assertThat(result, containsString("Capacit"));
        }

        @Test
        @DisplayName("Affichage avec un objet inventaire")
        void testAffichageWithOneItem() {
            player p = new player("Test", "Avatar", "DWARF", 100, new ArrayList<>(Arrays.asList("�p�e")));
            String result = re.forestier.edu.rpg.Affichage.afficherJoueur(p);
            assertThat(result, containsString("Inventaire"));
            assertThat(result, containsString("�p�e"));
        }

        @Test
        @DisplayName("Affichage avec plusieurs objets")
        void testAffichageWithMultipleItems() {
            player p = new player("Test", "Avatar", "ARCHER", 100, 
                new ArrayList<>(Arrays.asList("�p�e", "Bouclier", "Potion")));
            String result = re.forestier.edu.rpg.Affichage.afficherJoueur(p);
            assertThat(result, containsString("�p�e"));
            assertThat(result, containsString("Bouclier"));
            assertThat(result, containsString("Potion"));
        }

        @Test
        @DisplayName("Affichage inventaire vide")
        void testAffichageEmptyInventory() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            String result = re.forestier.edu.rpg.Affichage.afficherJoueur(p);
            assertThat(result, containsString("Inventaire"));
        }
    }

    @Nested
    @DisplayName("Tests majFinDeTour")
    class MajFinDeTourTests {
        
        @Test
        @DisplayName("Joueur KO reste KO")
        void testPlayerKO() {
            player p = new player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            p.currenthealthpoints = 0;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(0, p.currenthealthpoints);
        }

        @Test
        @DisplayName("DWARF avec Holy Elixir sous 50%")
        void testDwarfWithHolyElixir() {
            player p = new player("Test", "Avatar", "DWARF", 100, new ArrayList<>(Arrays.asList("Holy Elixir")));
            p.currenthealthpoints = 20;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(22, p.currenthealthpoints);
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
        @DisplayName("HP cap� au maximum")
        void testHPCapped() {
            player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            p.currenthealthpoints = 101;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(100, p.currenthealthpoints);
        }

        @Test
        @DisplayName("HP exactement � 50%")
        void testHPExactly50Percent() {
            player p = new player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            p.currenthealthpoints = 50;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(50, p.currenthealthpoints);
        }

        @Test
        @DisplayName("HP juste en dessous de 50%")
        void testHPJustBelow50Percent() {
            player p = new player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            p.currenthealthpoints = 49;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(50, p.currenthealthpoints);
        }

        @Test
        @DisplayName("ADVENTURER niveau exactement 3")
        void testAdventurerExactlyLevel3() {
            player p = new player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 27);
            p.currenthealthpoints = 20;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(22, p.currenthealthpoints);
        }

        @Test
        @DisplayName("ADVENTURER niveau exactement 2")
        void testAdventurerExactlyLevel2() {
            player p = new player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 10);
            p.currenthealthpoints = 20;
            p.healthpoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(21, p.currenthealthpoints);
        }
    }
}
