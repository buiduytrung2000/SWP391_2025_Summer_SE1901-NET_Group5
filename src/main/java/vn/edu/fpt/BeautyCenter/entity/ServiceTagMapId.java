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

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;

/**
 * Composite primary key class for the ServiceTagMap entity.
 * <p>
 * Represents the combined key of service_id and tag_id for the service_tag_map join table.
 * Implements equals and hashCode for proper entity identity management.
 * </p>
 */
@Getter
@Setter
@Embeddable
public class ServiceTagMapId implements java.io.Serializable {

    /**
     * Serialization UID for consistent deserialization.
     */
    @Serial
    private static final long serialVersionUID = -1603217748815681257L;

    /**
     * Unique identifier for the service. Must not be null and max 36 characters.
     */
    @Size(max = 36)
    @NotNull
    @Column(name = "service_id", nullable = false, length = 36)
    private String serviceId;

    /**
     * Unique identifier for the tag. Must not be null.
     */
    @NotNull
    @Column(name = "tag_id", nullable = false)
    private Integer tagId;

    /**
     * Checks equality based on serviceId and tagId.
     *
     * @param o the other object to compare
     * @return true if both serviceId and tagId match, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ServiceTagMapId entity = (ServiceTagMapId) o;
        return Objects.equals(this.tagId, entity.tagId) &&
                Objects.equals(this.serviceId, entity.serviceId);
    }

    /**
     * Generates a hash code based on serviceId and tagId.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(tagId, serviceId);
    }
}
