package vn.edu.fpt.BeautyCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameAndPassword(String username, String password);
    /**
     * Finds all distinct user IDs who have created at least one service.
     * <p>
     * This query identifies users who are active service creators, which is
     * useful for populating author filter dropdowns and analytics. The query
     * ensures only users with actual service creation activity are included.
     * </p>
     *
     * @return list of unique user IDs who have created services
     */
    @Query("SELECT DISTINCT s.createdBy FROM Service s WHERE s.createdBy IS NOT NULL")
    List<String> findDistinctServiceCreators();

}
