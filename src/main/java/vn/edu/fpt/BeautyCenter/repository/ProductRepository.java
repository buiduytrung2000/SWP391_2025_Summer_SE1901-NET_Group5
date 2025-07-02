package vn.edu.fpt.BeautyCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

