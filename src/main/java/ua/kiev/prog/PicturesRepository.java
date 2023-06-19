package ua.kiev.prog;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PicturesRepository extends JpaRepository<Picture, Long> {

    @Query("SELECT p FROM Picture p")
    List<Picture> findByPictures(@Param("picture") Picture picture, Pageable pageable);
}
