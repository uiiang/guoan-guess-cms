package com.uiiang.biz;

import com.uiiang.entity.MatchInfo;
import com.uiiang.entity.StadiumInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fuliqiang on 2017/4/26.
 */
public interface StadiumInfoService extends CrudRepository<StadiumInfo, Long> {
    Iterable<StadiumInfo> findByChineseName(String chineseName);
}
