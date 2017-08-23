package cloud.mgr.api.verticle;

import cloud.mgr.api.MainApiException;
import cloud.mgr.api.model.Order;

import java.util.List;
import java.util.Map;

public class StoreApiImpl implements StoreApi {

    private final Vertx vertx;

    StoreApiImpl(final Vertx vertx){
        this.vertx = vertx;
    }

    //deleteOrder
    public void deleteOrder(Long orderId){
        
    }
    
    //getInventory
    public Map<String, Integer> getInventory(){
        return null;
        
    }
    
    //getOrderById
    public Order getOrderById(Long orderId){
        return null;
        
    }
    
    //placeOrder
    public Order placeOrder(Order body){
        return null;
        
    }
    
}
