package vn.edu.fpt.BeautyCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.ServiceTag;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceTagRepository extends JpaRepository<ServiceTag, Integer> {
    Optional<ServiceTag> findByTagName(String tagName);
    List<ServiceTag> findByTagNameIn(List<String> tagNames);
}
