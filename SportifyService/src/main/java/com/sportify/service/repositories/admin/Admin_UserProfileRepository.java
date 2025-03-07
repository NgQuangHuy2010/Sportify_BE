package com.sportify.service.repositories.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportify.service.entities.UserProfile;
public interface Admin_UserProfileRepository extends JpaRepository<UserProfile, Long> {
	
	@Query("SELECT u FROM UserProfile u WHERE u.role = 'USER'")
	Page<UserProfile> findAllUsersWithRoleUser(Pageable pageable);
    
    @Query("SELECT u FROM UserProfile u JOIN u.sports s WHERE s.id = :sportId")
    Page<UserProfile> findUsersBySportId(@Param("sportId") Long sportId, Pageable pageable);
    
    @Query("SELECT u FROM UserProfile u WHERE (LOWER(u.firstname) LIKE LOWER(CONCAT('%', :name, '%')) " + 
    	       "OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :name, '%'))) AND u.role = 'USER'")
    	Page<UserProfile> searchUsersByName(@Param("name") String name, Pageable pageable);

    Page<UserProfile> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String firstname, String lastname, Pageable pageable);

    // Danh sach Locked Users
    @Query("SELECT u FROM UserProfile u WHERE u.isLocked = true")
    Page<UserProfile> findLockedUsers(Pageable pageable);
    
 // Đếm tổng số người dùng
    @Query("SELECT COUNT(u) FROM UserProfile u")
    long countTotalUsers();

    // Đếm tổng số người dùng bị khóa
    @Query("SELECT COUNT(u) FROM UserProfile u WHERE u.isLocked = true")
    long countLockedUsers();

    // Đếm tổng số người dùng mới đăng ký trong tháng hiện tại
    @Query("SELECT COUNT(u) FROM UserProfile u WHERE MONTH(u.createdOn) = MONTH(CURRENT_DATE) AND YEAR(u.createdOn) = YEAR(CURRENT_DATE)")
    long countNewUsersThisMonth();

    // Đếm số người dùng theo môn thể thao
    @Query("SELECT s.sportName, COUNT(u) FROM UserProfile u JOIN u.sports s GROUP BY s.sportName")
    List<Object[]> countUsersBySport();
    
    // Thống kê user theo tháng
    @Query("SELECT MONTH(u.createdOn) AS month, COUNT(u) AS userCount " +
    	       "FROM UserProfile u " +
    	       "WHERE YEAR(u.createdOn) = :year " +
    	       "GROUP BY MONTH(u.createdOn) " +
    	       "ORDER BY MONTH(u.createdOn)")
    	List<Object[]> countUsersByMonth(@Param("year") int year);

}

