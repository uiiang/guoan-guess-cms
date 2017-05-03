package com.uiiang.biz;

import com.uiiang.entity.GuessResult;
import com.uiiang.entity.MatchSchedule;
import com.uiiang.entity.PlayerInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by fuliqiang on 2017/5/3.
 */
public interface GuessResultService extends CrudRepository<GuessResult, Long> {
    List<GuessResult> findByPlayerInfo(PlayerInfo playerInfo);

    List<GuessResult> findByMatchSchedule(MatchSchedule matchSchedule);

    List<GuessResult> findByPlayerInfoAndMatchSchedule(PlayerInfo playerInfo, MatchSchedule matchSchedule);

    long countByMatchScheduleAndResultType(MatchSchedule matchSchedule, String resultType);
}