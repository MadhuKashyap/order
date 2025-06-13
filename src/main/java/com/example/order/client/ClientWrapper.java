package com.example.order.client;

import com.example.order.model.data.InventoryData;
import com.example.order.model.data.ProductData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientWrapper {
    @Autowired
    private ProductClient client;

    public List<InventoryData> fetchInventoryById(List<String> productIds) throws Exception {
        try {
            return client.fetchInventoryById(productIds);
        } catch (Exception e) {
            throw new Exception("Facing technical issues, please try placing order after some time : " + e);
        }
    }
    public List<ProductData> getAllProducts() throws Exception {
        try {
            return client.getAllProducts();
        } catch (Exception e) {
            throw new Exception("Facing technical issues, please try after some time : " + e);
        }
    }
}
