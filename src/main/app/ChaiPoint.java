
import java.io.IOException;
import java.util.ArrayList;

import Tests.TestType;
import input.InputRequest;
import models.Order;
import services.BeverageService;
import services.IngredientService;
import services.OrderManager;

/**
 * This is the driver class for all methods. It first calls InputRequest to convert the JSONinput file present in
 * resources and bootstraps the machine.
 * Based on number of outlets, it first initializes the order manager.
 * Then the serve function is called for to fulfill the orders.
 *
 * Change the Enum of TestType to view other cases.
 */
public class ChaiPoint {
    public static void main(String[] args) throws IOException {
        InputRequest inputRequest = InputRequest.parseJSON("src/main/resources/InputFile.json");
        addEntriesToDatabase(inputRequest);
        int totalOutlets = inputRequest.getTotalOutlets();

        OrderManager orderManager = new OrderManager(totalOutlets);

        /*
        Uncomment any of the below enums for the relevant test case to run

                    PLEASE RUN ONLY TEST CASE AT AT A TIME
        */

//        orderManager.serve(getOrderList(TestType.REFILL_AND_SERVE));
        orderManager.serve(getOrderList(TestType.UNAVAILABLE_INGREDIENT));
//        orderManager.serve(getOrderList(TestType.HAPPY_TEST));
//        orderManager.serve(getOrderList(TestType.INVALID_INPUT));


    }

    private static void addEntriesToDatabase(InputRequest inputRequest) {
        BeverageService.registerBeverages(inputRequest.getBeverageList());
        IngredientService.addIngredients(inputRequest.getIngredientsList());
    }

    private static ArrayList<Order> getOrderList(TestType testType) {
        ArrayList<Order> ordersList = new ArrayList<>();
        testType.getTestValues().forEach(testValue -> ordersList.add(new Order(testValue)));

        return ordersList;
    }
}


//callable
//checkAndHold
//ExecutorService
