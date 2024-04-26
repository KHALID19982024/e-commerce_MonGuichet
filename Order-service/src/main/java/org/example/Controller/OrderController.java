package org.example.Controller;

import lombok.AllArgsConstructor;
import org.example.DTO.request.OrderRequest;
import org.example.DTO.response.OrderResponseDto;
import org.example.Service.OrderService;
import org.example.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(){
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) throws Exception{
        try {
            OrderResponseDto orderDto = orderService.getOrderById(orderId);
            return ResponseEntity.ok(orderDto);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("order with ID " + orderId + " not found.");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequest orderRequest){
        OrderResponseDto orderResponseDto = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequest orderRequest){
        try {
            OrderResponseDto updateOrder = orderService.updateOrder(orderId, orderRequest);
            return ResponseEntity.ok(updateOrder);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with ID " + orderId + " not found.");
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> cancelOrderById(@PathVariable Long orderId){
        try {
            orderService.cancelOrderById(orderId);
            return ResponseEntity.ok("Order with ID " + orderId + " has been deleted.");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with ID " + orderId + " not found.");
        }
    }
    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getOrderHistoryByUserId(@PathVariable Long userId) {
        List<OrderResponseDto> orderHistory = orderService.getOrderHistoryByUserId(userId);
        return new ResponseEntity<>(orderHistory, HttpStatus.OK);

    }

}
