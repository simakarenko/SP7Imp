package ua.kiev.prog;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT c FROM Contact c WHERE c.group = :group")
    List<Contact> findByGroupTest(@Param("group") Group group, Pageable pageable);

    @Query("SELECT c FROM Group c WHERE c.id = :id")
    List<Group> findByIdGroupTest(@Param("id") long id, Pageable pageable);
}
