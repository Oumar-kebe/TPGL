package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.AvatarClass;
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
            Player p = new Player("Florian", "Grognak", "ADVENTURER", 100, new ArrayList<>());
            assertThat(p.playerName, is("Florian"));
            assertThat(p.avatarName, is("Grognak"));
            assertThat(p.getAvatarClass(), is(AvatarClass.ADVENTURER));
            assertThat(p.money, is(100));
        }

        @Test
        @DisplayName("Cr�ation r�ussie ARCHER")
        void testCreateArcher() {
            Player p = new Player("Jean", "Legolas", "ARCHER", 200, new ArrayList<>());
            assertThat(p.getAvatarClass(), is(AvatarClass.ARCHER));
        }

        @Test
        @DisplayName("Cr�ation r�ussie DWARF")
        void testCreateDwarf() {
            Player p = new Player("Pierre", "Gimli", "DWARF", 150, new ArrayList<>());
            assertThat(p.getAvatarClass(), is(AvatarClass.DWARF));
        }

        @Test
        @DisplayName("Cr�ation avec classe invalide")
        void testCreateInvalidClass() {
            Player p = new Player("Test", "Invalid", "INVALID", 100, new ArrayList<>());
            assertNull(p.getAvatarClass());
        }
    }

    @Nested
    @DisplayName("Tests de gestion de l'argent")
    class MoneyManagementTests {
        
        @Test
        @DisplayName("Ajout d'argent")
        void testAddMoney() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            p.addMoney(50);
            assertEquals(150, p.money);
        }

        @Test
        @DisplayName("Retrait d'argent")
        void testRemoveMoney() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            p.removeMoney(50);
            assertEquals(50, p.money);
        }

        @Test
        @DisplayName("Impossible argent n�gatif")
        void testNegativeMoney() {
            Player p = new Player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            assertThrows(IllegalArgumentException.class, () -> p.removeMoney(200));
        }
    }

    @Nested
    @DisplayName("Tests du syst�me de niveaux et XP")
    class LevelAndXpTests {
        
        @Test
        @DisplayName("Niveau 1 par d�faut")
        void testDefaultLevel() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            assertEquals(1, p.retrieveLevel());
            assertEquals(0, p.getXp());
        }

        @Test
        @DisplayName("Passage niveau 2 avec 10 XP")
        void testLevelUp2() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 10);
            assertEquals(2, p.retrieveLevel());
            assertEquals(10, p.getXp());
        }

        @Test
        @DisplayName("Passage niveau 3 avec 27 XP")
        void testLevelUp3() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 27);
            assertEquals(3, p.retrieveLevel());
            assertEquals(27, p.getXp());
        }

        @Test
        @DisplayName("Passage niveau 4 avec 57 XP")
        void testLevelUp4() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 57);
            assertEquals(4, p.retrieveLevel());
            assertEquals(57, p.getXp());
        }

        @Test
        @DisplayName("Passage niveau 5 avec 111 XP")
        void testLevelUp5() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 111);
            assertEquals(5, p.retrieveLevel());
            assertEquals(111, p.getXp());
        }

        @Test
        @DisplayName("XP exactement � la limite niveau 2")
        void testExactly10XP() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 10);
            assertEquals(2, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP juste avant limite niveau 2")
        void testJustBeforeLevel2() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 9);
            assertEquals(1, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP exactement � la limite niveau 3")
        void testExactly27XP() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 27);
            assertEquals(3, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP juste avant limite niveau 3")
        void testJustBeforeLevel3() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 26);
            assertEquals(2, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP exactement � la limite niveau 4")
        void testExactly57XP() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 57);
            assertEquals(4, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP juste avant limite niveau 4")
        void testJustBeforeLevel4() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 56);
            assertEquals(3, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP exactement � la limite niveau 5")
        void testExactly111XP() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 111);
            assertEquals(5, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP juste avant limite niveau 5")
        void testJustBeforeLevel5() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 110);
            assertEquals(4, p.retrieveLevel());
        }

        @Test
        @DisplayName("XP bien au-del� niveau 5")
        void testWellBeyondLevel5() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 200);
            assertEquals(5, p.retrieveLevel());
        }

        @Test
        @DisplayName("addXp retourne true lors level-up")
        void testAddXpReturnsTrueOnLevelUp() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            boolean result = UpdatePlayer.addXp(p, 10);
            assertTrue(result);
        }

        @Test
        @DisplayName("addXp retourne false sans level-up")
        void testAddXpReturnsFalseWithoutLevelUp() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            boolean result = UpdatePlayer.addXp(p, 5);
            assertFalse(result);
        }

        @Test
        @DisplayName("Level-up ajoute objet inventaire")
        void testLevelUpAddsItem() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            int initialSize = p.inventory.size();
            UpdatePlayer.addXp(p, 10);
            assertEquals(initialSize + 1, p.inventory.size());
        }

        @Test
        @DisplayName("Level-up ARCHER niveau 2 met � jour abilities")
        void testLevelUpArcherAbilities() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 10);
            assertThat(p.abilities.get("DEF"), is(1));
            assertThat(p.abilities.get("CHA"), is(2));
        }

        @Test
        @DisplayName("Level-up DWARF niveau 2 met � jour abilities")
        void testLevelUpDwarfAbilities() {
            Player p = new Player("Test", "Avatar", "DWARF", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 10);
            assertThat(p.abilities.get("DEF"), is(1));
            assertThat(p.abilities.get("ALC"), is(5));
        }

        @Test
        @DisplayName("Level-up ADVENTURER niveau 2 met � jour abilities")
        void testLevelUpAdventurerAbilities() {
            Player p = new Player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
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
            Player p = new Player("Florian", "Grognak", "ADVENTURER", 100, new ArrayList<>());
            String result = re.forestier.edu.rpg.Affichage.afficherJoueur(p);
            assertThat(result, containsString("Joueur Grognak"));
            assertThat(result, containsString("Florian"));
            assertThat(result, containsString("Niveau : 1"));
        }

        @Test
        @DisplayName("Affichage avec capacit�s")
        void testAffichageWithAbilities() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            String result = re.forestier.edu.rpg.Affichage.afficherJoueur(p);
            assertThat(result, containsString("Capacit"));
        }

        @Test
        @DisplayName("Affichage avec un objet inventaire")
        void testAffichageWithOneItem() {
            Player p = new Player("Test", "Avatar", "DWARF", 100, new ArrayList<>(Arrays.asList("�p�e")));
            String result = re.forestier.edu.rpg.Affichage.afficherJoueur(p);
            assertThat(result, containsString("Inventaire"));
            assertThat(result, containsString("�p�e"));
        }

        @Test
        @DisplayName("Affichage avec plusieurs objets")
        void testAffichageWithMultipleItems() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, 
                new ArrayList<>(Arrays.asList("�p�e", "Bouclier", "Potion")));
            String result = re.forestier.edu.rpg.Affichage.afficherJoueur(p);
            assertThat(result, containsString("�p�e"));
            assertThat(result, containsString("Bouclier"));
            assertThat(result, containsString("Potion"));
        }

        @Test
        @DisplayName("Affichage inventaire vide")
        void testAffichageEmptyInventory() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
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
            Player p = new Player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            p.currentHealthPoints = 0;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(0, p.currentHealthPoints);
        }

        @Test
        @DisplayName("DWARF avec Holy Elixir sous 50%")
        void testDwarfWithHolyElixir() {
            Player p = new Player("Test", "Avatar", "DWARF", 100, new ArrayList<>(Arrays.asList("Holy Elixir")));
            p.currentHealthPoints = 20;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(22, p.currentHealthPoints);
        }

        @Test
        @DisplayName("DWARF sans Holy Elixir sous 50%")
        void testDwarfWithoutHolyElixir() {
            Player p = new Player("Test", "Avatar", "DWARF", 100, new ArrayList<>());
            p.currentHealthPoints = 20;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(21, p.currentHealthPoints);
        }

        @Test
        @DisplayName("ARCHER avec Magic Bow sous 50%")
        void testArcherWithMagicBow() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>(Arrays.asList("Magic Bow")));
            p.currentHealthPoints = 20;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(22, p.currentHealthPoints);
        }

        @Test
        @DisplayName("ARCHER sans Magic Bow sous 50%")
        void testArcherWithoutMagicBow() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            p.currentHealthPoints = 20;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(21, p.currentHealthPoints);
        }

        @Test
        @DisplayName("ADVENTURER niveau 1 sous 50%")
        void testAdventurerLevel1() {
            Player p = new Player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            p.currentHealthPoints = 20;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(21, p.currentHealthPoints);
        }

        @Test
        @DisplayName("ADVENTURER niveau 3+ sous 50%")
        void testAdventurerLevel3Plus() {
            Player p = new Player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 27);
            p.currentHealthPoints = 20;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(22, p.currentHealthPoints);
        }

        @Test
        @DisplayName("Pas de regen au-dessus 50%")
        void testNoRegenAbove50() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            p.currentHealthPoints = 50;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(50, p.currentHealthPoints);
        }

        @Test
        @DisplayName("HP cap� au maximum")
        void testHPCapped() {
            Player p = new Player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
            p.currentHealthPoints = 101;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(100, p.currentHealthPoints);
        }

        @Test
        @DisplayName("HP exactement � 50%")
        void testHPExactly50Percent() {
            Player p = new Player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            p.currentHealthPoints = 50;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(50, p.currentHealthPoints);
        }

        @Test
        @DisplayName("HP juste en dessous de 50%")
        void testHPJustBelow50Percent() {
            Player p = new Player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            p.currentHealthPoints = 49;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(50, p.currentHealthPoints);
        }

        @Test
        @DisplayName("ADVENTURER niveau exactement 3")
        void testAdventurerExactlyLevel3() {
            Player p = new Player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 27);
            p.currentHealthPoints = 20;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(22, p.currentHealthPoints);
        }

        @Test
        @DisplayName("ADVENTURER niveau exactement 2")
        void testAdventurerExactlyLevel2() {
            Player p = new Player("Test", "Avatar", "ADVENTURER", 100, new ArrayList<>());
            UpdatePlayer.addXp(p, 10);
            p.currentHealthPoints = 20;
            p.maxHealthPoints = 100;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(21, p.currentHealthPoints);
        }
    }
}
