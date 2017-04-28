package com.uiiang.biz;

import com.uiiang.entity.LeagueTeam;
import com.uiiang.entity.MatchInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fuliqiang on 2017/4/26.
 */
public interface MatchInfoService extends CrudRepository<MatchInfo, Long> {
    Iterable<MatchInfo> findByChineseName(String matchName);
}
