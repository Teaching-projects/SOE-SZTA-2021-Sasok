import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

public class UnitTest {
    private static Unit unit1;
    private static Unit unit2;

    @Test
    @DisplayName("Test with constructor")
    void test1() {
        unit1 = new Unit("harcos", 15, 100, 1.5);
        Assertions.assertEquals(unit1.getName(), "harcos", "Wrong name");
        Assertions.assertEquals(unit1.getDMG(), 15, "Dmg is wrong");
        Assertions.assertEquals(unit1.getHp(), 100, "Hp is wrong");
        Assertions.assertEquals(unit1.getAs(), 1.5, "Attack speed is wrong");
    }

    @Test
    @DisplayName("Test with hp")
    void test2() {
        unit1 = new Unit("harcos", 15, 100, 1.5);
        Assertions.assertTrue(unit1.getHp() > 0, "hp can not be 0");
    }

    @Test
    @DisplayName("Test with attack function")
    void test3() {
        unit1 = new Unit("harcos", 15, 100, 0);
        unit2 = new Unit("saman", 13, 100, 0);
        unit2.attack(unit1);
        Assertions.assertTrue(unit1.getHp() == 100 - unit2.getDMG(), "Attack function is wrong");
    }

    @Test
    @DisplayName("Test with attack function")
    void test4() {
        unit1 = new Unit("harcos", 15, 100, 0);
        unit2 = new Unit("saman", 13, 100, 0);
        unit2.attack(unit1);
        Assertions.assertTrue(unit1.getHp() == 87, "Attack function is wrong");
    }

    @Test
    @DisplayName("Test with attack function")
    void test5() {
        unit1 = new Unit("harcos", 15, 100, 0);
        unit2 = new Unit("saman", 80, 100, 0);
        unit2.attack(unit1);
        Assertions.assertEquals(unit1.getHp(), 20, "Attack function is wrong");
    }

    @Test
    @DisplayName("Test with attack function")
    void test6() {
        unit1 = new Unit("harcos", 15, 100, 0);
        unit2 = new Unit("saman", 50, 100, 0);
        unit2.attack(unit1);
        unit2.attack(unit1);
        Assertions.assertFalse(unit1.isAlive(), "Unit1 should be dead");
    }

    @Test
    @DisplayName("Test with attack function")
    void test7() {
        unit1 = new Unit("harcos", 15, 100, 0);
        unit2 = new Unit("saman", 49, 100, 0);
        unit2.attack(unit1);
        unit2.attack(unit1);
        Assertions.assertTrue(unit1.isAlive(), "Unit1 should be alive");
    }

    @Test
    @DisplayName("Test with attack function")
    void test8() {
        unit1 = new Unit("harcos", 101, 100, 0);
        unit2 = new Unit("saman", 49, 100, 0);
        unit1.attack(unit2);
        Assertions.assertEquals(unit2.getHp(), -1, "Unit1 should be alive");
    }

    @Test
    @DisplayName("Test with attack function")
    void test9() {
        unit1 = new Unit("harcos", 1, 100, 0);
        unit2 = new Unit("saman", 49, 100, 0);
        for (int i = 1; i < 100; i++) {
            unit1.attack(unit2);
            Assertions.assertEquals(unit2.getHp(), 100 - i, "Unit1.hp value should be something else");
        }
    }

    @Test
    @DisplayName("Test with attack function")
    void test10() {
        unit1 = new Unit("harcos", 2, 100, 0);
        unit2 = new Unit("saman", 49, 100, 0);
        for (int i = 4; i < 100; i += 4) {
            unit1.attack(unit2);
            unit1.attack(unit2);
            Assertions.assertTrue(unit2.getHp() == 100 - i, "Unit1.hp value should be something else");
        }
    }

    @Test
    @DisplayName("Test with attack function")
    void test11() {
        unit1 = new Unit("harcos", 2, 100, 0);
        unit2 = new Unit("saman", 49, 100, 0);
        int i = 0;
        while (unit1.isAlive()) {
            i++;
            unit2.attack(unit1);
        }
        Assertions.assertFalse(unit1.isAlive(), "Unit1 should be dead");
        Assertions.assertTrue(i == 3, "counters value is wrong");
    }

    @Test
    @DisplayName("Test with isAlive function")
    void test12() {
        unit1 = new Unit("harcos", 100, 100, 1);
        unit2 = new Unit("saman", 15, 100, 2);
        unit1.attack(unit2);
        Assertions.assertFalse(unit2.isAlive(), "It should be false, because unit1.dmg > unit2.hp");
    }

    @Test
    @DisplayName("What if unit has no dmg")
    void test13() {
        unit1 = new Unit("harcos", 0, 100, 1);
        unit2 = new Unit("saman", 15, 100, 2);
        unit1.attack(unit2);
        Assertions.assertTrue(unit2.getHp() == 100, "Something wrong with attack function");
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
            27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 })
    @DisplayName("Test for attack function")
    void test14(int attack) {
        unit1 = new Unit("harcos", 0, 100, 1);
        unit2 = new Unit("saman", attack, 100, 2);
        unit2.attack(unit1);
        Assertions.assertEquals(unit1.getHp(), 100 - unit2.getDMG(), "Something wrong with attack function");
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
            27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 })
    @DisplayName("Test for attack function")
    void test15(int attackAndHealth) {
        unit1 = new Unit("harcos", 0, attackAndHealth, 1);
        unit2 = new Unit("saman", attackAndHealth, 100, 2);
        unit2.attack(unit1);
        Assertions.assertEquals(unit1.getHp(), 0, "Something wrong with attack function");
    }
}