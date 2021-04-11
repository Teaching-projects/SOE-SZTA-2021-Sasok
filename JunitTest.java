import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

public class JunitTest {
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
        Assertions.assertEquals(unit1.getHp() > 0, true, "hp can not be 0");
    }

    @Test
    @DisplayName("Test with as")
    void test3() {
        unit1 = new Unit("harcos", 15, 100, 0);
        Assertions.assertEquals(unit1.getAs() >= 0, true, "as can not be smaller than 0");
    }

    @Test
    @DisplayName("Test with 0 dmg")
    void test4() {
        unit1 = new Unit("harcos", 10, 100, 1);
        unit2 = new Unit("saman", 15, 100, 2);
        Assertions.assertEquals(unit1.getDMG() > 0 && unit2.getDMG() > 0, true,
                "One of the units dmg's need to be bigger than 0");
    }
}