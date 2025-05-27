package vn.edu.fpt.BeautyCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, String> {
//    @Query("SELECT s FROM Service s ORDER BY s.createdAt desc")
//    List<Service> findAllOrderByCreatedAtDesc();
//    @Query("SELECT s FROM Service s WHERE s.name LIKE %:keyword% OR s.content LIKE %:keyword%")
//    List<Service> findByKeyword(String keyword);
    boolean existsByName(String name);
}
