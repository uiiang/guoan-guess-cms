package com.uiiang.biz;

import com.uiiang.entity.MatchInfo;
import com.uiiang.entity.PlayerInfo;
import com.uiiang.entity.PlayerResult;
import com.uiiang.entity.RankingList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by fuliqiang on 2017/5/4.
 */
public interface PlayerResultService extends CrudRepository<PlayerResult, Long> {
    List<PlayerResult> findAllByMatchInfoOrderByTotalScoreDesc(MatchInfo matchInfo);

    PlayerResult findAllLimit1ByMatchInfoAndPlayerInfo(MatchInfo matchInfo, PlayerInfo playerInfo);

    //两个sum和playerinfo的顺序很重要
    @Query("SELECT new com.uiiang.entity.RankingList(sum(joinNum) as joinNum,sum(totalScore) as totalScore,playerInfo) FROM PlayerResult where MATCH_ID in :matchIds group by PLAYER_ID order by totalScore desc,joinNum desc")
    List<RankingList> getRanking(@Param("matchIds") List<MatchInfo> matchIds);
}
