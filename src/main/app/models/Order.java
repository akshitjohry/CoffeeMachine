package models;

import enums.ORDER_STATUS;

/**
 * This is the MODEL of an order.
 * It has the status of the order as an ENUM.
 * It also has the beverage name that is required to be served.
 */
public class Order {
    private ORDER_STATUS orderStatus;
    private String beverageKey;

    public ORDER_STATUS getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(final ORDER_STATUS orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getBeverageKey() {
        return beverageKey;
    }

    public Order(final String beverageKey) {
        this.beverageKey = beverageKey;
        this.orderStatus = ORDER_STATUS.NEW;
    }
}
