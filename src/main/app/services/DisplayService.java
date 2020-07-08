package services;

import enums.ORDER_STATUS;
import models.Order;
/**
 * This is Service class for displaying anything on the hardware.
 * It indicates about the LOW levels of the ingredients or when the order is served.
 * It also indicates if the choice is invalid.
 */

class DisplayService {
    private static final String IS_PREPARED= " is prepared";
    private static final String IS_INVALID= " is an INVALID choice";
    private static final String UNABLE_TO_PREPARE = " cannot be prepared because ";
    static void display(Order order){
        switch (order.getOrderStatus()) {
            case PROCESSING:
                System.out.println(order.getBeverageKey() + IS_PREPARED);
                order.setOrderStatus(ORDER_STATUS.SERVED);
                break;
            case INVALID:
                System.out.println(order.getBeverageKey() + IS_INVALID);
                break;
            case INSUFFICIENT_INGREDIENTS:
                order.getOrderStatus().getIngredientStatusMap().forEach((ingredient_status, ingredient) -> {
                    System.out.println(order.getBeverageKey() + UNABLE_TO_PREPARE + ingredient.getIngredientName()
                            +ingredient_status.getReason());
                });
                break;
        }
    }
}
