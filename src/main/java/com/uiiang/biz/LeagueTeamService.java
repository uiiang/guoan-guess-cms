package com.uiiang.biz;

import com.uiiang.entity.LeagueTeam;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fuliqiang on 2017/4/26.
 */
public interface LeagueTeamService extends CrudRepository<LeagueTeam, Long> {
    Iterable<LeagueTeam> findByChineseName(String teamName);
}
