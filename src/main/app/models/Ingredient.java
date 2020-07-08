package models;

/**
 * This is the MODEL of a Ingredient.
 * It contains the ingredient name as the key.
 * There is also a qty associated with each ingredient.
 *
 * This model is used in both cases:
 *      a) To store ingredients in the ingredient store
 *      b) To associate each beverage with the required qty of ingredient for the recipe
 */
public class Ingredient {
    private String ingredientName;
    private Double ingredientQuantity;

    public Ingredient(final String ingredientName, final Double ingredientQuantity) {
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public Double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(final Double ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientName = '" + ingredientName + '\'' +
                ", ingredientQuantity = " + ingredientQuantity +
                '}';
    }

    @Override
    public Ingredient clone() {
        return new Ingredient(this.ingredientName, this.ingredientQuantity);
    }
}