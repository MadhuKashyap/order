package com.example.order.dto;

import com.example.order.client.ClientWrapper;
import com.example.order.dao.OrderDao;
import com.example.order.dao.OrderItemDao;
import com.example.order.model.enums.OrderStatus;
import com.example.order.model.enums.PaymentMethod;
import com.example.order.model.form.OrderForm;
import com.example.order.model.form.OrderItemForm;
import com.example.order.pojo.OrderItemPojo;
import com.example.order.pojo.OrderPojo;
import com.example.order.model.data.ProductData;
import com.example.order.model.data.InventoryData;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDto {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private ClientWrapper clientWrapper;

    @Transactional(rollbackOn = Exception.class)
    public void saveOrder(OrderForm orderForm) throws Exception {
        if(ObjectUtils.isEmpty(orderForm.getItemList()))
            throw new Exception("Order can not be placed with empty order list");
        if(!checkIfOrderCanBeFulfilled(orderForm.getItemList())) {
           throw new Exception("Order can not be fulfilled because some items are out of stock");
        }
        Optional<OrderPojo> pojo = orderDao.findById(orderForm.getOrderId());
        if(pojo.isEmpty()) {
            OrderPojo order = new OrderPojo();
            order.setUserId(orderForm.getUserId());
            order.setCreatedAt(new Date());
            order.setPaymentMethod(PaymentMethod.COD);
            order.setStatus(OrderStatus.CREATED);
            order.setTotalAmount(orderForm.getTotalAmount());
//            order.setCustomerAddress(orderForm.getCustomerAddress());
            orderDao.save(order);

            for(OrderItemForm item : orderForm.getItemList()) {
                OrderItemPojo orderItem = new OrderItemPojo();
                orderItem.setSkuId(item.getSkuId());
                orderItem.setQty(item.getQty());
                orderItem.setSellingPrice(item.getSellingPrice());
                orderItem.setDiscount(item.getDiscount());
                orderItem.setTotalPrice(item.getTotalPrice());
                orderItem.setOrderId(order.getOrderId());
                orderItemDao.save(orderItem);
            }
        }
        //TODO send inventory update event to topic
    }

    private Boolean checkIfOrderCanBeFulfilled(List<OrderItemForm> itemList) throws Exception {
        List<InventoryData> inventoryDataList = clientWrapper.fetchInventoryById(itemList.stream().map(OrderItemForm::getSkuId).collect(Collectors.toUnmodifiableList()));
        for(InventoryData inventoryData : inventoryDataList) {
            if(inventoryData.getQty() == 0)
                return false;
        }
        return true;
    }
    public List<ProductData> getAllProducts() throws Exception {
        return clientWrapper.getAllProducts();
    }
}
