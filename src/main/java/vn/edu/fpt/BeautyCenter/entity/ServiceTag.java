package vn.edu.fpt.BeautyCenter.entity;
/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-8/6/2025>           <1.0>              TrungBD                      First Implement
 */
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "service_tags")
public class ServiceTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.INTEGER) // Báo hiệu Hibernate sử dụng kiểu INTEGER
    @Column(name = "tag_id")
    private Integer id;

    @Column(name = "tag_name", nullable = false, length = 50)
    private String tagName;

    @ManyToMany(mappedBy = "serviceTags")
    private Set<Service> services = new LinkedHashSet<>();

}