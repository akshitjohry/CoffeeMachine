package input;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import models.Beverage;
import models.Ingredient;

/**
 * This is the input processor, it convert the input JSON to a valid initial configuration for the machine.
 * The static method is used to create an object.
 */
public class InputRequest {

    private ArrayList<Beverage>  beverageList;
    private ArrayList<Ingredient> ingredientsList;
    private int totalOutlets;

    public ArrayList<Beverage> getBeverageList() {
        return beverageList;
    }

    public ArrayList<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    public int getTotalOutlets() {
        return totalOutlets;
    }

    public InputRequest(final ArrayList<Beverage> beverageList, final ArrayList<Ingredient> ingredientsList,
            final int totalOutlets) {
        this.beverageList = beverageList;
        this.ingredientsList = ingredientsList;
        this.totalOutlets = totalOutlets;
    }

    public static InputRequest parseJSON(String fileName) throws IOException {

        InputStream inputStream = new FileInputStream(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        JsonReader reader = new JsonReader(inputStreamReader);
        JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
        JsonObject machine = jsonObject.getAsJsonObject(InputConstants.MACHINE);
        JsonObject outlets = machine.getAsJsonObject(InputConstants.OUTLETS);


        return new InputRequest( addBeverages(machine.getAsJsonObject(InputConstants.BEVERAGES)),
                getIngredientList(machine.getAsJsonObject(InputConstants.INGREDIENT_STORE)),
                outlets.get(InputConstants.NUMBER_OF_OUTLETS).getAsInt());

    }

    private static ArrayList<Ingredient> getIngredientList(JsonObject itemStore) {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        itemStore.keySet().forEach(ingredientKey -> ingredientList.add(new Ingredient(ingredientKey, itemStore.get(ingredientKey).getAsDouble())));

        return  ingredientList;
    }

    private static ArrayList<Beverage> addBeverages(JsonObject beverages) {
        ArrayList<Beverage> beverageList = new ArrayList<>();
        beverages.keySet().forEach(beverageKey -> {
            beverageList.add(new Beverage(beverageKey, getIngredientList(beverages.getAsJsonObject(beverageKey))));
        });
        return beverageList;
    }

    @Override
    public String toString() {
        return "InputRequest{" +
                "\nallBeverages = " + beverageList.toString() +
                ", \ningredientsList=" + ingredientsList.toString() +
                ", \ntotalOutlets=" + totalOutlets +
                '}';
    }
}
