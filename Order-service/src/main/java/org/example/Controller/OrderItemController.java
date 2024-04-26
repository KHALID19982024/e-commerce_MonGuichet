package org.example.Controller;

import lombok.AllArgsConstructor;
import org.example.DTO.request.OrderItemRequest;
import org.example.DTO.response.OrderItemResponseDto;
import org.example.Entity.OrderItems;
import org.example.Service.OrderItemService;
import org.example.Service.OrderService;
import org.example.exception.OrderItemAlreadyReservedException;
import org.example.exception.OrderItemNotAvailableException;
import org.example.exception.OrderItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/order/items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping("/{OrderItemsId}")
    public ResponseEntity<?> getOrderItemById(@PathVariable Long OrderItemsId){
        try {
            OrderItemResponseDto responseDto = orderItemService.getOrderItemsById(OrderItemsId);
            return ResponseEntity.ok(responseDto);
        } catch (OrderItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("orderItem with ID " + OrderItemsId + " not found.");
        }
    }
    @PostMapping("/create")

    public ResponseEntity<?> createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {
        try {
            OrderItemResponseDto responseDto = orderItemService.createOrderItems(orderItemRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (OrderItemNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The orderItem is not available");
        }
    }
    @PutMapping("/{OrderItemsId}")
    public ResponseEntity<?> updateOrderItem(@PathVariable  Long OrderItemsId, @RequestBody OrderItemRequest orderItemRequest){
        try {
            OrderItemResponseDto updatedOrderItem = orderItemService.updateOrderItems(OrderItemsId, orderItemRequest);
            return ResponseEntity.ok(updatedOrderItem);
        } catch (OrderItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("orderItem with ID " + OrderItemsId + " not found.");
        }
    }
    @DeleteMapping("/{OrderItemsId}")
    public ResponseEntity<?> deleteOrderItemById(@PathVariable Long OrderItemsId){
        try {
            orderItemService.deleteOrderItemsById(OrderItemsId);
            return ResponseEntity.ok("orderItem with ID " + OrderItemsId + " has been deleted.");
        } catch (OrderItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("orderItem with ID " + OrderItemsId + " not found.");
        }
    }

}