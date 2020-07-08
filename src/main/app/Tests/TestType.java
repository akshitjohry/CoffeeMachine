package Tests;

import java.util.Arrays;
import java.util.List;

/**
 * This is the different ENUMS for test cases. It contains all the values of the orders.
 */
public enum TestType {
    INVALID_INPUT("hot_coffee", "hot_tea", "cold_drink"),
    HAPPY_TEST("hot_tea", "hot_tea"),
    REFILL_AND_SERVE("hot_coffee", "hot_coffee", "hot_tea", "black_tea"),
    UNAVAILABLE_INGREDIENT("hot_coffee", "hot_tea", "green_tea", "black_tea");


    private List<String> testValues;

    TestType(String... values) {
        this.testValues = Arrays.asList(values);
    }

    public List<String> getTestValues() {
        return testValues;
    }
}
