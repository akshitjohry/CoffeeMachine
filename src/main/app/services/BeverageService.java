package services;

import java.util.ArrayList;

import DAO.BeverageDAO;
import models.Beverage;

/**
 * This is Service class for Beverages. It contains all operations related to a beverage like adding a new beverage
 * to the machine.
 */
public class BeverageService {
    private static BeverageDAO beverageDAO = new BeverageDAO();

    public static void registerBeverages(ArrayList<Beverage> beverageList) {
        beverageList.forEach(beverage -> {
            beverageDAO.add(beverage);
        });
    }
}
