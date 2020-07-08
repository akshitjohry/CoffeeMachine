package services;

import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.sun.xml.internal.ws.util.CompletedFuture;
import models.Order;

/**
 * This is order manager. It allots the number of threads using ExecutorService. Each thread corresponds to an outlet
 * which is used to process an Order.
 * It receives the list of orders and serves them.
 * It send the status to display service to display.
 */
public class OrderManager {
    private ExecutorService executorService;

    public OrderManager (int numberOfOutlets){
        this.executorService = Executors.newFixedThreadPool(numberOfOutlets);
        }

    public void serve(ArrayList<Order> orderList) {
        orderList.forEach(order -> {
            executorService.submit(new OrderTask(order));
        });
    }


}
