package vn.edu.fpt.BeautyCenter.service;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceService {
    ServiceRepository serviceRepository;
    ServiceTagRepository serviceTagRepository;
    ServiceMapper serviceMapper;

    public Page<vn.edu.fpt.BeautyCenter.entity.Service> getAllServices(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<vn.edu.fpt.BeautyCenter.entity.Service> services = serviceRepository.findAll(pageable);

        List<vn.edu.fpt.BeautyCenter.entity.Service> processedServices = services.getContent()
                .stream()
                .peek(service -> {
                    if (service.getContent() != null) {
                        // Loại bỏ img tags và giới hạn 20 từ
                        String cleanContent = removeImgTags(service.getContent());
                        String limitedContent = limitWords(cleanContent);
                        service.setContent(limitedContent);
                    }
                })
                .collect(Collectors.toList());

        return new PageImpl<>(processedServices, pageable, services.getTotalElements());
    }

    // Method để loại bỏ img tags
    private String removeImgTags(String content) {
        if (content == null || content.trim().isEmpty()) {
            return content;
        }

        // Loại bỏ các thẻ img self-closing: <img ... />
        content = content.replaceAll("<img[^>]*/>", "");

        // Loại bỏ các thẻ img với closing tag: <img ...>...</img>
        content = content.replaceAll("<img[^>]*>.*?</img>", "");

        // Loại bỏ các thẻ img không có closing tag: <img ...>
        content = content.replaceAll("<img[^>]*>", "");

        return content.trim();
    }

    // Method để giới hạn số từ
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


    public void createService(@Valid ServiceCreationRequest request) {
        if (serviceRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.SERVICE_EXISTED);
        }
        vn.edu.fpt.BeautyCenter.entity.Service service = vn.edu.fpt.BeautyCenter.entity.Service.builder()
                .name(request.getName())
                .content(request.getContent())
                .duration(request.getDuration())
                .price(request.getPrice())
                .createdBy("f00eec64-3a0b-11f0-9cf0-005056c00001")
                .build();
        if (request.getTagNames() != null && !request.getTagNames().isEmpty()) {
            Set<ServiceTag> tags = processServiceTags(request.getTagNames());
            service.setServiceTags(tags);
        }
        serviceRepository.save(service);
    }

    private Set<ServiceTag> processServiceTags(List<String> tagNames) {
        Set<ServiceTag> tags = new HashSet<>();
        for (String tagName : tagNames) {
            if (tagName != null && !tagName.trim().isEmpty()) {
                ServiceTag tag = serviceTagRepository.findByTagName(tagName.trim())
                        .orElseGet(() -> {
                            // Tạo tag mới nếu chưa tồn tại
                            ServiceTag newTag = new ServiceTag();
                            newTag.setTagName(tagName.trim());
                            return serviceTagRepository.save(newTag);
                        });
                tags.add(tag);
            }
        }
        return tags;
    }

    public Optional<ServiceResponse> getServiceById(String serviceId) {
        vn.edu.fpt.BeautyCenter.entity.Service service = serviceRepository.findById(serviceId).orElseThrow();
        ServiceResponse response = serviceMapper.toResponse(service);
        return Optional.ofNullable(response);
    }

    public void updateService(String serviceId, ServiceUpdateRequest request) {
        vn.edu.fpt.BeautyCenter.entity.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new AppException(ErrorCode.SERVICE_NOT_FOUND));
        serviceMapper.updateEntity(service,request);
        serviceMapper.toResponse(serviceRepository.save(service));
    }
    public String formatVietnameseDurationToTotalMinutes(String durationString) {
        if (durationString == null || durationString.trim().isEmpty()) {
            return "0"; // Default or error value
        }

        int hours = 0;
        int minutes = 0;

        // Regex to find hours: captures digits before "giờ" (case-insensitive for "giờ")
        // (\\d+) captures one or more digits
        // \\s* matches zero or more whitespace characters
        // (?i) makes the rest of the pattern case-insensitive ONLY for "giờ" if needed,
        // or use Pattern.CASE_INSENSITIVE flag for the whole pattern.
        // For simplicity here, we'll assume "giờ" and "phút" are consistently cased.
        // If you need case insensitivity for "giờ" and "phút", you could use:
        // Pattern.compile("(\\d+)\\s*(giờ|GIỜ|Giờ)", Pattern.CASE_INSENSITIVE)
        // or more simply, make the keywords lowercase and convert input to lowercase.

        Pattern hourPattern = Pattern.compile("(\\d+)\\s*giờ");
        Matcher hourMatcher = hourPattern.matcher(durationString);
        if (hourMatcher.find()) {
            try {
                hours = Integer.parseInt(hourMatcher.group(1));
            } catch (NumberFormatException e) {
                // This should ideally not happen if \d+ matches, but good practice for robustness
                System.err.println("Warning: Could not parse hours from: " + hourMatcher.group(1) + " in string: " + durationString);
            }
        }

        // Regex to find minutes: captures digits before "phút"
        Pattern minutePattern = Pattern.compile("(\\d+)\\s*phút");
        Matcher minuteMatcher = minutePattern.matcher(durationString);
        if (minuteMatcher.find()) {
            try {
                minutes = Integer.parseInt(minuteMatcher.group(1));
            } catch (NumberFormatException e) {
                System.err.println("Warning: Could not parse minutes from: " + minuteMatcher.group(1) + " in string: " + durationString);
            }
        }

        // If only a number is provided without units, and we want to assume it's minutes
        // (this part is optional and depends on how strict you want to be)
        // if (hours == 0 && minutes == 0 && durationString.matches("\\d+")) {
        //     try {
        //         minutes = Integer.parseInt(durationString.trim());
        //     } catch (NumberFormatException e) {
        //         // ignore
        //     }
        // }

        long totalMinutes = (long)hours * 60 + minutes;
        return String.valueOf(totalMinutes);
    }

}
