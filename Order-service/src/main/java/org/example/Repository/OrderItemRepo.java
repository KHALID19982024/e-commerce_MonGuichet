package org.example.Repository;

import org.example.DTO.response.OrderItemResponseDto;
import org.example.Entity.OrderItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItems, Long> {


//    List<OrderItemResponseDto> findByOrderId(Long orderId);

}