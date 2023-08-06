package org.dongguk.study.Repository;

import org.dongguk.study.domain.Diary;
import org.dongguk.study.domain.DiaryTagMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryTagMapRepository extends JpaRepository<DiaryTagMap, Long> {
    @Query(value = "SELECT dt.tag_name FROM diary_tag_map AS dtm join dtm.diaryTag dt WHERE dtm.diary.id =: diaryId", nativeQuery = true)
    List<String> findTagsByDiaryId(@Param("diaryId") Long diaryId);
    List<DiaryTagMap> findAllByDiary(Diary diary);
}
