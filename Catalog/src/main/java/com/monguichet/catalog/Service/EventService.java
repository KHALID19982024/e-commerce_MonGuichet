package com.monguichet.catalog.Service;

import com.monguichet.catalog.Entity.DTO.CategoryResponseDto;
import com.monguichet.catalog.Entity.DTO.EventRequestDto;
import com.monguichet.catalog.Entity.DTO.EventResponseDto;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface EventService {




    List<EventResponseDto> getAllEvent();
    List<EventResponseDto> getAllEventsPagination(int page, int size);
    EventResponseDto createEvent(EventRequestDto eventDto);
    EventResponseDto updateEvent(Long id, EventRequestDto eventDto);
    List<EventResponseDto> getEventsBySubCategoryId(Long subCategoryId);

    void deleteEventById(Long id);

    List<EventResponseDto> searchEventsByName(String keyword);

    List<EventResponseDto> searchEventsByNameOrDescription(String keyword);
    // search in Events by word

}
