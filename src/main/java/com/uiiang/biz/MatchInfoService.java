package com.uiiang.biz;

import com.uiiang.entity.MatchInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by fuliqiang on 2017/4/26.
 */
public interface MatchInfoService extends CrudRepository<MatchInfo, Long> {
    List<MatchInfo> findByChineseName(String matchName);

    List<MatchInfo> findByStatus(int status);

    List<MatchInfo> findAllByOrderByStatusAsc();
}
