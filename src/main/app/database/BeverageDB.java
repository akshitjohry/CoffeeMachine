package database;

import java.util.HashMap;
import java.util.Map;

import models.Beverage;

/**
 * This is the database for Beverages. It is a singleton DB which will always have one instance.
 * It can handle race conditions by using synchronize.
 * It contains key-value format of Beverages and is used for DB operations like GET or PUT.
 *      * KEY: BeverageName
 *      * Value: Beverage Object
 */
public class BeverageDB {
    private volatile static BeverageDB instance;
    private volatile static Map<String, Beverage> beverageDetails = new HashMap<>();

    public static BeverageDB getInstance() {
        if(instance == null) {
            synchronized (BeverageDB.class) {

                if(instance == null) {
                    instance = new BeverageDB();
                }

            }
        }
        return instance;
    }

    protected Object readResolve() {
        return instance;
    }

    // private constructor for Singleton
    private BeverageDB() {
        if(instance != null) {
            throw new RuntimeException("Attempt to create new instance of DB!");
        }
    }

    public Beverage get (String key) {
        return beverageDetails.getOrDefault(key, null);
    }

    public void add (Beverage beverage) {
        beverageDetails.put(beverage.getBeverageKey(), beverage);
    }
}
