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
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entity representing service tags used for categorizing beauty services.
 * <p>
 * Maintains a bidirectional many-to-many relationship with Service entities through
 * the service_tags join table.
 * </p>
 */
@Getter
@Setter
@Entity
@Table(name = "service_tags")
public class ServiceTag {

    /** Auto-incremented primary key using database identity column. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.INTEGER) // Explicit SQL type mapping for Hibernate
    @Column(name = "tag_id")
    private Integer id;

    /** Tag name. Maximum 50 characters and required. */
    @Column(name = "tag_name", nullable = false, length = 50)
    private String tagName;

    /** Services associated with this tag. Maintains bidirectional relationship. */
    @ManyToMany(mappedBy = "serviceTags")
    private Set<Service> services = new LinkedHashSet<>();
}
