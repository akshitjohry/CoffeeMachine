package enums;

/**
 * This is an ENUM that is associated with an Ingredient. It represents the status of the ingredient in DB.
 *      * PRESENT: Qty to server the order is present.
 *      * RUNNING_LOW: Qty is low to prepare a beverage
 *      * NOT_PRESENT: The ingredient is not present in Store.
 */
public enum INGREDIENT_STATUS {
    RUNNING_LOW(" is not sufficient"),
    NOT_PRESENT(" is not available"),
    PRESENT(" is present");

    String reason;

    INGREDIENT_STATUS(final String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
