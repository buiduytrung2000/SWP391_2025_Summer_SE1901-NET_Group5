package vn.edu.fpt.BeautyCenter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    private String name;

    @Column(columnDefinition = "TINYTEXT")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    private String sku;

    private Integer quantity;

    @Column(name = "created_by", columnDefinition = "CHAR(36)")
    private String created_by;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    // Getters & setters
}

