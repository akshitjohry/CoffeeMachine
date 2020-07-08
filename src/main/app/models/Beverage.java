package models;

import java.util.ArrayList;

/**
 * This is the MODEL of a Beverage.
 * It contains the beverage name as the key.
 * There is also a list of Ingredients that are required to create the Beverage.
 * If in future a process is also associated with each beverage we can add it here.
 */
public class Beverage {
    private String beverageKey;
    private ArrayList<Ingredient> ingredientList;

    public Beverage(final String beverageKey, final ArrayList<Ingredient> ingredientList) {
        this.beverageKey = beverageKey;
        this.ingredientList = ingredientList;
    }

    public String getBeverageKey() {
        return beverageKey;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return new ArrayList<>(ingredientList);
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "\nbeverageKey='" + beverageKey + '\'' +
                ", \ningredientList= " + ingredientList.toString() +
                '}';
    }
}
