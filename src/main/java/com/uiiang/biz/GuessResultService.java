package com.uiiang.biz;

import com.uiiang.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by fuliqiang on 2017/5/3.
 */
public interface GuessResultService extends CrudRepository<GuessResult, Long> {
    List<GuessResult> findByPlayerInfo(PlayerInfo playerInfo);

    List<GuessResult> findByMatchSchedule(MatchSchedule matchSchedule);

    List<GuessResult> findByPlayerInfoAndMatchSchedule(PlayerInfo playerInfo, MatchSchedule matchSchedule);

    List<GuessResult> findByPlayerInfoOrderBySubmitTimeDesc(PlayerInfo playerInfo);

    long countByMatchScheduleAndResultType(MatchSchedule matchSchedule, String resultType);

    long countByPlayerInfoAndMatchInfo(PlayerInfo playerInfo, MatchInfo matchInfo);

    @Query ("SELECT new com.uiiang.entity.GuessResultPreviewObj(homeResult ,awayResult, count(id)) FROM GuessResult where resultType=:resultType and MSCHE_ID=:matchId group by homeResult, awayResult order by  count(id) desc, homeResult desc")
    List<GuessResultPreviewObj> findAllLimit5(@Param("resultType") String resultType, @Param("matchId") Long matchId);
}