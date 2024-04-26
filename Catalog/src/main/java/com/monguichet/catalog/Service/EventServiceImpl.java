package com.monguichet.catalog.Service;
import com.monguichet.catalog.Utils.MappingProfile;


import com.monguichet.catalog.Entity.DTO.EventRequestDto;
import com.monguichet.catalog.Entity.DTO.EventResponseDto;
import com.monguichet.catalog.Entity.Event;
import com.monguichet.catalog.Repository.EventRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.monguichet.catalog.Utils.MappingProfile.mapToDto;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepo eventRepo;

// Afficher All Events
    @Override
    public List<EventResponseDto> getAllEvent() {
     //   Pageable pageable = PageRequest(0, 5, Sort.by())
        return eventRepo.findAll().stream()
                .map(MappingProfile::mapToDto)
                .collect(Collectors.toList());
    }
    // Afficher All by pagination
 @Override
 public List<EventResponseDto> getAllEventsPagination(int page, int size) {
     Page<Event> eventsPage = eventRepo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
     return eventsPage.getContent().stream()
             .map(MappingProfile::mapToDto)
             .collect(Collectors.toList());
 }



    @Override
public EventResponseDto createEvent(EventRequestDto eventDto) {
    // Map the EventRequestDto to Event entity
    var event = MappingProfile.mapToEntity(eventDto);
    // Save the event
    Event savedEvent = eventRepo.save(event);
    // Map the saved Event entity to EventResponseDto and return
    return MappingProfile.mapToDto(savedEvent);
}
//Update event
@Override
public EventResponseDto updateEvent(Long id, EventRequestDto eventDto) {
    Event eventToUpdate = eventRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));

    // Update the event attributes
    eventToUpdate.setName(eventDto.getName());
    eventToUpdate.setDescription(eventDto.getDescription());
    eventToUpdate.setDueDate(eventDto.getDueDate()); // due date
    eventToUpdate.setUrl(eventDto.getUrl());
    eventToUpdate.setStock_Ticket(eventDto.getStock_Ticket());
    eventToUpdate.setSubCategoryId(eventDto.getSubCategoryId());

    Event updatedEvent = eventRepo.save(eventToUpdate);
    return MappingProfile.mapToDto(updatedEvent);
}

    @Override
    public List<EventResponseDto> getEventsBySubCategoryId(Long subCategoryId) {
        // Retrieve events by subcategory ID
        List<Event> events = eventRepo.findBySubCategoryId(subCategoryId);

        // Map the events to DTOs and return
        return events.stream()
                .map(MappingProfile::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEventById(Long id) {
        eventRepo.deleteById(id);
    }

      // search in Events by word
      public List<EventResponseDto> searchEventsByName(String keyword) {
          List<Event> events = eventRepo.findByNameContainingIgnoreCase(keyword);
          return events.stream()
                  .map(MappingProfile::mapToDto)
                  .collect(Collectors.toList());
      }
// searche name or description
public List<EventResponseDto> searchEventsByNameOrDescription(String keyword) {
    List<Event> events = eventRepo.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    return events.stream()
            .map(MappingProfile::mapToDto)
            .collect(Collectors.toList());
}



}
