package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import DAO.IngredientsDAO;
import enums.INGREDIENT_STATUS;
import enums.ORDER_STATUS;
import models.Ingredient;


/**
 * This is Service class for Ingredients. It is used for the following operation right now
 *      1. AddIngredients: This is used to add or refill ingredients to the machine.
 *      2. checkAndHold: This is the method responsible for checking if all the requiredIngredients are present in
 *                       the machine. It return the status for the order.
 */
public class IngredientService {

    private static IngredientsDAO ingredientsDAO = new IngredientsDAO();

    public static void addIngredients(ArrayList<Ingredient> ingredientList) {
        ingredientList.forEach(ingredient -> ingredientsDAO.add(ingredient));
    }

    static synchronized ORDER_STATUS checkAndHold(ArrayList<Ingredient> requiredIngredients) {

        HashMap<INGREDIENT_STATUS, Ingredient> absentIngredients = new HashMap<>();
        requiredIngredients.forEach(ingredient -> {
            INGREDIENT_STATUS ingredientStatus = getIngredientStatus(ingredient);
            if(ingredientStatus != INGREDIENT_STATUS.PRESENT) {
                absentIngredients.put(ingredientStatus, ingredient);
            }
        });

        if(absentIngredients.isEmpty()) {
            consume(requiredIngredients);
            return ORDER_STATUS.PROCESSING;
        }


        ORDER_STATUS orderStatus = ORDER_STATUS.INSUFFICIENT_INGREDIENTS;
        orderStatus.setMap(absentIngredients);
        return orderStatus;
    }

    private static void consume(ArrayList<Ingredient> requiredIngredients) {
        requiredIngredients.forEach(ingredient -> {
            //multiplying qty by -1 to consume or remove entry from DB
            ingredient.setIngredientQuantity(ingredient.getIngredientQuantity()*-1);
            ingredientsDAO.add(ingredient);
        });
    }

    private static INGREDIENT_STATUS getIngredientStatus(Ingredient requiredIngredient) {
        String ingredientName = requiredIngredient.getIngredientName();
        Optional<Ingredient> ingredientInStoreOpt = ingredientsDAO.get(ingredientName);
        if(!ingredientInStoreOpt.isPresent()) {
            return INGREDIENT_STATUS.NOT_PRESENT;
        }
        Ingredient ingredientInStore = ingredientInStoreOpt.get();
        if (ingredientInStore.getIngredientQuantity() < requiredIngredient.getIngredientQuantity()) {
            return INGREDIENT_STATUS.RUNNING_LOW;
        }

        return INGREDIENT_STATUS.PRESENT;
    }
}
