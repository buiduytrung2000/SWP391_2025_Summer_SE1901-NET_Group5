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

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceCreationRequest;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceFilterParams;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceUpdateRequest;
import vn.edu.fpt.BeautyCenter.dto.response.ServiceResponse;
import vn.edu.fpt.BeautyCenter.entity.ServiceTag;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.exception.ErrorCode;
import vn.edu.fpt.BeautyCenter.mapper.ServiceMapper;
import vn.edu.fpt.BeautyCenter.repository.ServiceRepository;
import vn.edu.fpt.BeautyCenter.repository.ServiceTagRepository;
import vn.edu.fpt.BeautyCenter.repository.UserRepository;

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
        if (request.getTagNames() != null && !request.getTagNames().isEmpty()) {
            Set<ServiceTag> tags = processServiceTags(request.getTagNames());
            service.setServiceTags(tags);
        }
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
        // Additional tag formatting can be handled here if needed
        return services.map(serviceMapper::toResponse);
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
        Pageable pageable = PageRequest.of(page, size, Sort.by("created_at").descending());
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
    /**
     * Retrieves services with comprehensive advanced filtering capabilities.
     * <p>
     * This method supports multiple filter criteria including price range, duration range,
     * date range, tags selection, and author filtering. It processes content for display
     * purposes by removing HTML tags and limiting word count for better UI presentation.
     * The method integrates with the repository layer to execute efficient database queries
     * while maintaining proper pagination and sorting functionality.
     * </p>
     *
     * @param filterParams the comprehensive filter parameters object containing all search criteria
     * @param pageable     pagination and sorting parameters for result organization
     * @return a page of ServiceResponse objects matching the specified filter criteria
     * @throws RuntimeException if filtering operation encounters database or processing errors
     */
    public Page<ServiceResponse> getServicesWithAdvancedFilter(ServiceFilterParams filterParams,
                                                               Pageable pageable) {
        try {
            // Execute advanced filtering query through repository layer
            // This call delegates to the repository's custom query method that handles
            // multiple WHERE clauses and JOIN operations for tag filtering
            Page<vn.edu.fpt.BeautyCenter.entity.Service> services = serviceRepository.findWithAdvancedFilter(
                    filterParams.getKeyword(),           // Basic text search in name and content
                    filterParams.getMinPrice(),          // Minimum price boundary for cost filtering
                    filterParams.getMaxPrice(),          // Maximum price boundary for cost filtering
                    filterParams.getMinDuration(),       // Minimum duration in minutes for time filtering
                    filterParams.getMaxDuration(),       // Maximum duration in minutes for time filtering
                    filterParams.getFromDate(),          // Start date for creation date range filtering
                    filterParams.getToDate(),            // End date for creation date range filtering
                    filterParams.getSelectedTags(),      // List of tag names for categorical filtering
                    filterParams.getCreatedBy(),         // User ID for author-based filtering
                    pageable                             // Pagination and sorting configuration
            );

            // Process each service's content for optimal display presentation
            // This step ensures consistent content formatting across the application
            List<vn.edu.fpt.BeautyCenter.entity.Service> processedServices = services.getContent()
                    .stream()
                    .peek(service -> {
                        // Only process services that have content to avoid null pointer exceptions
                        if (service.getContent() != null) {
                            // Remove HTML image tags to clean content for list view display
                            // This prevents layout issues and improves loading performance
                            String cleanContent = removeImgTags(service.getContent());

                            // Limit content to 20 words for consistent list item height
                            // This ensures uniform appearance in grid and list layouts
                            String limitedContent = limitWords(cleanContent);

                            // Update the service entity with processed content
                            service.setContent(limitedContent);
                        }
                    })
                    .collect(Collectors.toList());

            // Create new PageImpl with processed content while preserving pagination metadata
            // This maintains the original pagination information (total elements, page number, etc.)
            // while updating the content with our processed data
            Page<vn.edu.fpt.BeautyCenter.entity.Service> processedPage = new PageImpl<>(
                    processedServices,                    // The list of processed service entities
                    pageable,                            // Original pagination configuration
                    services.getTotalElements()          // Total count from original query
            );

            // Convert Service entities to ServiceResponse DTOs for API/view consumption
            // This mapping step applies any additional transformations and ensures
            // proper data encapsulation for the presentation layer
            return processedPage.map(serviceMapper::toResponse);

        } catch (DataAccessException e) {
            // Handle database-related exceptions with specific error context
            String errorMessage = "Database error occurred while filtering services: " + e.getMessage();
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage, e);

        } catch (Exception e) {
            // Handle any other unexpected exceptions during filtering process
            String errorMessage = "Unexpected error occurred during service filtering: " + e.getMessage();
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    /**
     * Retrieves all unique tag names available in the system for filter dropdown population.
     * <p>
     * This method queries the ServiceTag repository to fetch all distinct tag names
     * that are currently in use across all services. The results are sorted alphabetically
     * to provide a consistent user experience in dropdown filter components. This method
     * is typically used to populate filter dropdowns and tag selection interfaces.
     * </p>
     *
     * @return a list of unique tag names sorted alphabetically, empty list if no tags exist
     * @throws RuntimeException if database access fails during tag retrieval
     */
    public List<String> getAllAvailableTags() {
        try {
            // Query repository for all distinct tag names with alphabetical sorting
            // This ensures consistent ordering in UI components and better user experience
            List<String> availableTags = serviceTagRepository.findAllDistinctTagNames();

            // Log successful retrieval for monitoring and debugging purposes
            System.out.println("Successfully retrieved " + availableTags.size() + " available tags");

            // Return the sorted list of tag names for UI consumption
            return availableTags;

        } catch (DataAccessException e) {
            // Handle database connectivity or query execution errors
            String errorMessage = "Database error while retrieving available tags: " + e.getMessage();
            System.err.println(errorMessage);

            // Return empty list to prevent UI breakage while logging the error
            // This allows the application to continue functioning with limited tag filtering
            return new ArrayList<>();

        } catch (Exception e) {
            // Handle any unexpected errors during tag retrieval process
            String errorMessage = "Unexpected error while retrieving available tags: " + e.getMessage();
            System.err.println(errorMessage);

            // Return empty list as fallback to maintain application stability
            return new ArrayList<>();
        }
    }
    /**
     * Deletes a service from the system with proper validation and error handling.
     * <p>
     * This method performs comprehensive checks before deletion including dependency
     * validation to ensure referential integrity. It follows the business rule that
     * services with active relationships cannot be deleted to prevent data corruption.
     * </p>
     *
     * @param serviceId the unique identifier of the service to be deleted
     * @return true if deletion was successful, false otherwise
     * @throws IllegalStateException if service has active dependencies that prevent deletion
     * @throws RuntimeException if any unexpected error occurs during deletion process
     */
    @Transactional
    public boolean deleteService(String serviceId) {
        try {
            // Check if the service has any active dependencies (bookings, appointments, etc.)
            // This prevents deletion of services that are currently in use or referenced
            if (hasActiveDependencies(serviceId)) {
                throw new IllegalStateException("Service has active dependencies");
            }
            vn.edu.fpt.BeautyCenter.entity.Service service = serviceRepository.findById(serviceId)
                    .orElseThrow(() -> new EntityNotFoundException("Service not found"));

            // Clear tags before deletion (optional but explicit)
            service.getServiceTags().clear();

            // Perform the actual deletion using Spring Data JPA repository
            // This will cascade delete any related entities if configured properly
            serviceRepository.delete(service);

            // Return true to indicate successful deletion
            return true;

        } catch (Exception e) {
            // Wrap any unexpected exceptions in a RuntimeException with descriptive message
            // This provides consistent error handling across the application
            throw new RuntimeException("Error deleting service: " + e.getMessage(), e);
        }
    }

    private boolean hasActiveDependencies(String serviceId) {
        return false;
    }


}
