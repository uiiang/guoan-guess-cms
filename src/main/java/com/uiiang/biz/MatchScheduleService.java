package com.uiiang.biz;

import com.uiiang.entity.MatchSchedule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by fuliqiang on 2017/4/26.
 */
public interface MatchScheduleService extends CrudRepository<MatchSchedule, Long> {
    List<MatchSchedule> findByRoundNum(int round);

    List<MatchSchedule> findByStatusEquals(int status);

    //查找一下场竞猜比赛,
    //查找未结束比赛并时间最接近的比赛
    MatchSchedule findTopByStatusLessThanOrderByMatchDateTimeAsc(int status);

    MatchSchedule findTopByOrderByMatchDateTimeDesc();
}
