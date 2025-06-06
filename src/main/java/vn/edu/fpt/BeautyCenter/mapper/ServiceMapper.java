package vn.edu.fpt.BeautyCenter.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
//import org.springframework.security.core.context.SecurityContextHolder;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceCreationRequest;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceUpdateRequest;
import vn.edu.fpt.BeautyCenter.dto.response.ServiceResponse;
import vn.edu.fpt.BeautyCenter.entity.Service;
import vn.edu.fpt.BeautyCenter.entity.ServiceTag;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {StaffMapper.class, MaterialMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ServiceMapper {

    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    @Mapping(target = "serviceId", ignore = true)
//    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
//    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
//    @Mapping(target = "createdBy", expression = "java(getCurrentUserId())")
    Service toService(ServiceCreationRequest request);


    @Mapping(target = "duration",
            expression = "java(formatDuration(entity.getDuration()))")
    @Mapping(target = "tags", source = "serviceTags", qualifiedByName = "mapTags")
    ServiceResponse toResponse(Service entity);

    @Named("mapTags")
    default List<String> mapTags(Set<ServiceTag> tags) {
        if (tags == null) return Collections.emptyList();
        return tags.stream()
                .map(ServiceTag::getTagName)
                .collect(Collectors.toList());
    }

    @InheritInverseConfiguration(name = "toEntity")
    void updateEntity(@MappingTarget Service entity, ServiceUpdateRequest request);

    // Custom mapping methods cho Instant <-> LocalDateTime
    default LocalDateTime instantToLocalDateTime(Instant instant) {
        return instant == null ? null : LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    default Instant localDateTimeToInstant(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.toInstant(ZoneOffset.UTC);
    }

    default String formatDuration(Integer minutes) {
        if (minutes == null) return "";
        return String.format("%d giờ %02d phút", minutes / 60, minutes % 60);
    }

//    default String getCurrentUserId() {
//        return SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getName();
//    }

}
