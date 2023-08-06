package org.dongguk.study.Repository;

import org.dongguk.study.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<Diary> findByIdAndIsVisible(Long id, Boolean isVisible);

    @Query(value = "select d from Diary d where d.id =: id and d.isVisible = true", nativeQuery = true)
    Optional<Diary> getByIdAndIsVisible(@Param("id") Long id);

    @Override
    List<Diary> findAll();
}
