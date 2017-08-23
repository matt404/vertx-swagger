package io.swagger.verticle;

import io.swagger.MainApiException;
import io.swagger.model.Order;
import java.util.List;
import java.util.Map;

public interface StoreApi  {
    //deleteOrder
    public void deleteOrder(Long orderId);
    
    //getInventory
    public Map<String, Integer> getInventory();
    
    //getOrderById
    public Order getOrderById(Long orderId);
    
    //placeOrder
    public Order placeOrder(Order body);
    
}
