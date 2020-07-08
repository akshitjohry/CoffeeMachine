package DAO;

import java.util.Optional;

import database.BeverageDB;
import models.Beverage;

/**
 * This is the data access layer for Beverage type of objects.
 * It accesses the singleton BeverageDB and can be used
 *      * To add new Beverage
 *      * To getBeverageDetails
 */
public class BeverageDAO implements DAOInterface<Beverage>{
    public void add(Beverage beverage) {
        BeverageDB.getInstance().add(beverage);
    }
    public Optional<Beverage> get(String beverageKey) {
        return Optional.ofNullable(BeverageDB.getInstance().get(beverageKey));
    }
}
