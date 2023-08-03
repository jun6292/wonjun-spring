package org.dongguk.study.Repository;

import org.dongguk.study.domain.Diary;
import org.dongguk.study.domain.DiaryTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryTagRepository extends JpaRepository<DiaryTag, Long> {
    Optional<DiaryTag> findByDiaryTagName(String tagName);

    @Override
    List<DiaryTag> findAll();
}
