import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

public class MainTest {
    private static Player unit1;
    private static Unit unit2;

    @Test
    @DisplayName("Test for battle function")
    void test1() {
        unit1 = new Player("harcos", 20, 100, 1.5,100,0,0,0);
        unit2 = new Unit("saman", 1, 99, 1.5);
        Main.battle(unit1, unit2);
        Assertions.assertTrue(unit1.getHp() > unit2.getHp(), "Harcos should win the battle");
    }

    @Test
    @DisplayName("Test for battle function")
    void test2() {
        unit1 = new Player("harcos", 20, 100, 1.5,100,0,0,0);
        unit2 = new Unit("saman", 20, 100, 1.5);
        Main.battle(unit1, unit2);
        Assertions.assertEquals(100, unit2.getHp(), "Shaman has less or more hp than it should");
    }

    @Test
    @DisplayName("Test for battle function")
    void test3() {
        unit1 = new Player("harcos", 20, 100, 1.5,100,0,0,0);
        unit2 = new Unit("saman", 2, 100, 1.5);
        Main.battle(unit1, unit2);
        Assertions.assertEquals(100, unit1.getHp(), "Harcos has less or more hp than it should");
    }

    @ParameterizedTest
    @ValueSource(ints = { 100, 102, 300, 122, 33, 28, 53, 4000, 195, 222 })
    @DisplayName("Test for battle function")
    void test4(int hp) {
        unit1 = new Player("harcos", 20, 100, 1.5,100,0,0,0);
        unit2 = new Unit("saman", 10, hp, 1.5);
        Main.battle(unit1, unit2);
        Assertions.assertTrue(unit2.isAlive(), "Saman should be alive");
    }

    @ParameterizedTest
    @ValueSource(doubles = { 1.1, 2.3, 3.6, 0.2, 10.1, 3, 1, 0.8, 0.11, 0.1111 })
    @DisplayName("Test for battle function")
    void test5(double as) {
        unit1 = new Player("harcos", 20, 100, 1.5,100,0,0,0);
        unit2 = new Unit("saman", 11, 100, as);
        Main.battle(unit1, unit2);
        Assertions.assertFalse(!unit1.isAlive() && unit2.isAlive(), "Harcos should be alive");
    }

}