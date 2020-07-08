package enums;

import java.util.HashMap;

import models.Ingredient;

/**
 * This is an ENUM that is associated with an Order. It represents the status of the order in transit
 *      * NEW: New order
 *      * PROCESSING: Order is being served
 *      * INVALID: The order has an invalid beverage
 *      * INSUFFICIENT_INGREDIENTS: Missing or invalid ingredients for the Order.
 *      * SERVED: Completed orders.
 */
public enum ORDER_STATUS {
    NEW,
    PROCESSING,
    INVALID,
    INSUFFICIENT_INGREDIENTS,
    SERVED;

    private HashMap<INGREDIENT_STATUS, Ingredient> ingredientStatusMap;

    public void setMap(final HashMap<INGREDIENT_STATUS, Ingredient> ingredientStatusMap) {
        this.ingredientStatusMap = ingredientStatusMap;
    }

    public HashMap<INGREDIENT_STATUS, Ingredient> getIngredientStatusMap() {
        return ingredientStatusMap;
    }

}
