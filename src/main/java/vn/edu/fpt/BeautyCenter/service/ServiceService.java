package vn.edu.fpt.BeautyCenter.service;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import vn.edu.fpt.BeautyCenter.dto.request.ServiceCreationRequest;
import vn.edu.fpt.BeautyCenter.dto.response.ServiceResponse;
import vn.edu.fpt.BeautyCenter.entity.ServiceTag;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.exception.ErrorCode;
import vn.edu.fpt.BeautyCenter.mapper.ServiceMapper;
import vn.edu.fpt.BeautyCenter.repository.ServiceRepository;
import vn.edu.fpt.BeautyCenter.repository.ServiceTagRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceService {
    ServiceRepository serviceRepository;
    ServiceTagRepository serviceTagRepository;
    ServiceMapper serviceMapper;
    public vn.edu.fpt.BeautyCenter.entity.Service createService(@Valid ServiceCreationRequest request) {
        if(serviceRepository.existsByName(request.getName())) {
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
        System.out.println("Save service success");
        return serviceRepository.save(service);
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
}
