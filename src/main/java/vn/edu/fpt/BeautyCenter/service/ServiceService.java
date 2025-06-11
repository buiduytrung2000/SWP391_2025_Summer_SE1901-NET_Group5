package vn.edu.fpt.BeautyCenter.service;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE         Version  AUTHOR   DESCRIPTION
 * 2025-06-08   1.0      TrungBD  First Implement
 */

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceCreationRequest;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceUpdateRequest;
import vn.edu.fpt.BeautyCenter.dto.response.ServiceResponse;
import vn.edu.fpt.BeautyCenter.entity.ServiceTag;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.exception.ErrorCode;
import vn.edu.fpt.BeautyCenter.mapper.ServiceMapper;
import vn.edu.fpt.BeautyCenter.repository.ServiceRepository;
import vn.edu.fpt.BeautyCenter.repository.ServiceTagRepository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Service layer for handling business logic related to beauty services.
 * <p>
 * Provides methods for CRUD operations, tag management, content formatting,
 * and business validation for the Service domain.
 * </p>
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceService {
    ServiceRepository serviceRepository;
    ServiceTagRepository serviceTagRepository;
    ServiceMapper serviceMapper;

    /**
     * Retrieves a paginated list of all services, formatting content for each service.
     * Removes image tags and limits description to 20 words.
     *
     * @param page the page number to retrieve
     * @param size the size of the page
     * @return a page of processed Service entities
     */
    public Page<vn.edu.fpt.BeautyCenter.entity.Service> getAllServices(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<vn.edu.fpt.BeautyCenter.entity.Service> services = serviceRepository.findAll(pageable);

        // Process each service's content: remove <img> tags and limit to 20 words
        List<vn.edu.fpt.BeautyCenter.entity.Service> processedServices = services.getContent()
                .stream()
                .peek(service -> {
                    if (service.getContent() != null) {
                        String cleanContent = removeImgTags(service.getContent()); // Remove image tags
                        String limitedContent = limitWords(cleanContent); // Limit to 20 words
                        service.setContent(limitedContent);
                    }
                })
                .collect(Collectors.toList());

        return new PageImpl<>(processedServices, pageable, services.getTotalElements());
    }

    /**
     * Removes all <img> tags from the given HTML content.
     *
     * @param content the HTML content
     * @return content without image tags
     */
    private String removeImgTags(String content) {
        if (content == null || content.trim().isEmpty()) {
            return content;
        }
        // Remove self-closing <img ... />
        content = content.replaceAll("<img[^>]*/>", "");
        // Remove <img ...>...</img>
        content = content.replaceAll("<img[^>]*>.*?</img>", "");
        // Remove <img ...>
        content = content.replaceAll("<img[^>]*>", "");
        return content.trim();
    }

    /**
     * Limits the given content to the first 20 words.
     *
     * @param content the content to limit
     * @return content truncated to 20 words, with ellipsis if exceeded
     */
    private String limitWords(String content) {
        if (content == null || content.trim().isEmpty()) {
            return content;
        }
        String[] words = content.trim().split("\\s+");
        if (words.length <= 20) {
            return content;
        }
        return String.join(" ", Arrays.copyOf(words, 20)) + "...";
    }

    /**
     * Creates a new service, validating for duplicate names and handling tag associations.
     *
     * @param request the service creation request
     * @param userId  the ID of the user creating the service
     * @throws AppException if a service with the same name already exists
     */
    public void createService(@Valid ServiceCreationRequest request, String userId) {
        if (serviceRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.SERVICE_EXISTED);
        }
        // Build new Service entity from request
        vn.edu.fpt.BeautyCenter.entity.Service service = vn.edu.fpt.BeautyCenter.entity.Service.builder()
                .name(request.getName())
                .content(request.getContent())
                .duration(request.getDuration())
                .price(request.getPrice())
                .createdBy(userId)
                .build();
        // Handle tags if provided
        if (request.getTagNames() != null && !request.getTagNames().isEmpty()) {
            Set<ServiceTag> tags = processServiceTags(request.getTagNames());
            service.setServiceTags(tags);
        }
        serviceRepository.save(service);
    }

    /**
     * Processes a list of tag names, ensuring each exists or is created.
     *
     * @param tagNames list of tag names
     * @return set of ServiceTag entities
     */
    private Set<ServiceTag> processServiceTags(List<String> tagNames) {
        Set<ServiceTag> tags = new HashSet<>();
        for (String tagName : tagNames) {
            if (tagName != null && !tagName.trim().isEmpty()) {
                // Find existing tag or create a new one
                ServiceTag tag = serviceTagRepository.findByTagName(tagName.trim())
                        .orElseGet(() -> {
                            ServiceTag newTag = new ServiceTag();
                            newTag.setTagName(tagName.trim());
                            return serviceTagRepository.save(newTag);
                        });
                tags.add(tag);
            }
        }
        return tags;
    }

    /**
     * Retrieves a service by its ID and maps it to a ServiceResponse DTO.
     *
     * @param serviceId the ID of the service
     * @return Optional containing the ServiceResponse if found
     * @throws NoSuchElementException if the service does not exist
     */
    public Optional<ServiceResponse> getServiceById(String serviceId) {
        vn.edu.fpt.BeautyCenter.entity.Service service = serviceRepository.findById(serviceId).orElseThrow();
        ServiceResponse response = serviceMapper.toResponse(service);
        return Optional.ofNullable(response);
    }

    /**
     * Updates an existing service with new data from the update request.
     *
     * @param serviceId the ID of the service to update
     * @param request   the update request data
     * @throws AppException if the service is not found
     */
    public void updateService(String serviceId, ServiceUpdateRequest request) {
        vn.edu.fpt.BeautyCenter.entity.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new AppException(ErrorCode.SERVICE_NOT_FOUND));
        serviceMapper.updateEntity(service, request);
        serviceMapper.toResponse(serviceRepository.save(service));
    }

    /**
     * Converts a Vietnamese duration string (e.g., "2 hour 30 minute") to total minutes as a string.
     *
     * @param durationString the duration in Vietnamese format
     * @return total minutes as a string, or "0" if input is invalid
     */
    public String formatDurationToTotalMinutes(String durationString) {
        if (durationString == null || durationString.trim().isEmpty()) {
            return "0";
        }
        int hours = 0;
        int minutes = 0;

        // Extract hours from string
        Pattern hourPattern = Pattern.compile("(\\d+)\\s*hour");
        Matcher hourMatcher = hourPattern.matcher(durationString);
        if (hourMatcher.find()) {
            try {
                hours = Integer.parseInt(hourMatcher.group(1));
            } catch (NumberFormatException e) {
                System.err.println("Warning: Could not parse hours from: " + hourMatcher.group(1) + " in string: " + durationString);
            }
        }

        // Extract minutes from string
        Pattern minutePattern = Pattern.compile("(\\d+)\\s*minute");
        Matcher minuteMatcher = minutePattern.matcher(durationString);
        if (minuteMatcher.find()) {
            try {
                minutes = Integer.parseInt(minuteMatcher.group(1));
            } catch (NumberFormatException e) {
                System.err.println("Warning: Could not parse minutes from: " + minuteMatcher.group(1) + " in string: " + durationString);
            }
        }

        long totalMinutes = (long) hours * 60 + minutes;
        return String.valueOf(totalMinutes);
    }

    /**
     * Retrieves all services with formatted tags, mapping entities to response DTOs.
     *
     * @param page the page number
     * @param size the page size
     * @return a page of ServiceResponse objects
     */
    public Page<ServiceResponse> getAllServicesWithFormattedTags(int page, int size) {
        Page<vn.edu.fpt.BeautyCenter.entity.Service> services = getAllServices(page, size);
        return services.map(service -> {
            ServiceResponse response = serviceMapper.toResponse(service);
            // Additional tag formatting can be handled here if needed
            return response;
        });
    }

    /**
     * Searches for services by keyword in their name, paginated and sorted by creation date descending.
     *
     * @param keyword the search keyword
     * @param page    the page number
     * @param size    the page size
     * @return a page of ServiceResponse objects matching the keyword
     */
    public Page<ServiceResponse> searchServices(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<vn.edu.fpt.BeautyCenter.entity.Service> services;

        if (keyword == null || keyword.trim().isEmpty()) {
            // No keyword: return all services
            services = serviceRepository.findAll(pageable);
        } else {
            // Search by name (case-insensitive)
            services = serviceRepository.findByNameContainingIgnoreCase(keyword.trim(), pageable);
            // If searching multiple fields, adjust query here
        }

        // Map entities to DTOs
        return services.map(serviceMapper::toResponse);
    }
}
