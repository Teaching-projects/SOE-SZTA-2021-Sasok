import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

public class JunitTest {
    private static Unit unit1;
    private static Unit unit2;

    @Test
    @DisplayName("Test with constructor")
    void testConstructor() {
        unit1 = new Unit("harcos", 15, 100, 1.5);
        Assertions.assertEquals(unit1.getName(), "harcos", "Wrong name");
        Assertions.assertEquals(unit1.getDMG(), 15, "Dmg is wrong");
        Assertions.assertEquals(unit1.getHp(), 100, "Hp is wrong");
        Assertions.assertEquals(unit1.getAs(), 1.5, "Attack speed is wrong");
    }
}