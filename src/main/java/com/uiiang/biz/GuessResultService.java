package com.uiiang.biz;

import com.uiiang.entity.GuessResult;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fuliqiang on 2017/5/3.
 */
public interface GuessResultService extends CrudRepository<GuessResult, Long> {
    Iterable<GuessResult> findByPlayerInfo(String id);
    Iterable<GuessResult> findByMatchSchedule(Long id);
    Iterable<GuessResult> findByPlayerInfoAndMatchSchedule(String playerId, Long matchId);
}