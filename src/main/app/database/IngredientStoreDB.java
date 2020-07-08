package database;

import java.util.HashMap;
import java.util.Map;

import models.Ingredient;

/**
 * This is the database for Ingredients. It is a singleton DB which will always have one instance.
 * It can handle race conditions by using synchronize.
 * It contains key-value format of Ingredients and is used for DB operations like GET or PUT.
 *      * KEY: IngredientName
 *      * Value: Ingredient Object
 */

public class IngredientStoreDB {
    private volatile static IngredientStoreDB instance;
    private volatile static Map<String, Ingredient> availableIngredients = new HashMap<>();

    public static IngredientStoreDB getInstance() {
        if(instance == null) {
            synchronized (IngredientStoreDB.class) {

                if(instance == null) {
                    instance = new IngredientStoreDB();
                }

            }
        }
        return instance;
    }

    protected Object readResolve() {
        return instance;
    }

    // private constructor for Singleton
    private IngredientStoreDB() {
        if(instance != null) {
            throw new RuntimeException("Attempt to create new instance of DB!");
        }
    }

    public Ingredient get(String ingredientName) {
        return availableIngredients.getOrDefault(ingredientName, null);
    }
    public void put(Ingredient ingredient) {
        availableIngredients.put(ingredient.getIngredientName(), ingredient);
    }
    public void update(Ingredient ingredient) {
        String ingredientName = ingredient.getIngredientName();
        if (availableIngredients.containsKey(ingredientName)) {
            Double currentQuantity = availableIngredients.get(ingredientName).getIngredientQuantity();
            Double newQuantity = currentQuantity + ingredient.getIngredientQuantity();
            ingredient.setIngredientQuantity(newQuantity);
        }
        put(ingredient);
    }

}
