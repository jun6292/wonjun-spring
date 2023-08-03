package org.dongguk.study.Repository;

import org.dongguk.study.domain.Diary;
import org.dongguk.study.domain.DiaryTagMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryTagMapRepository extends JpaRepository<DiaryTagMap, Long> {
    List<String> findTagsByDiaryId(Long diaryId);
    List<Diary> findDiaryByTagName(String tagName);
}
