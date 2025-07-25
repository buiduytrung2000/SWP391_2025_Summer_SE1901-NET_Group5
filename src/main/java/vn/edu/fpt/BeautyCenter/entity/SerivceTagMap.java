package vn.edu.fpt.BeautyCenter.entity;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE         Version  AUTHOR   DESCRIPTION
 * 2025-06-08   1.0      TrungBD  First Implement
 */

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Join table entity for managing the many-to-many relationship between services and tags.
 * <p>
 * Uses a composite primary key ({@link ServiceTagMapId}) to map service-tag associations.
 * </p>
 */
@Entity
@Table(name = "service_tag_map")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SerivceTagMap {

    /** Composite primary key containing service_id and tag_id references. */
    @EmbeddedId
    private ServiceTagMapId id;

    /**
     * Service entity associated with this mapping.
     * Maps to the service_id column in the composite key.
     */
    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    /**
     * ServiceTag entity associated with this mapping.
     * Maps to the tag_id column in the composite key.
     */
    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tag_id", nullable = false, columnDefinition = "int")
    private ServiceTag tag;
}
