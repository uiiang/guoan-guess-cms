package com.uiiang.biz;

import com.uiiang.entity.PlayerInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by fuliqiang on 2017/5/3.
 */
public interface PlayerInfoService extends CrudRepository<PlayerInfo, String> {
    Iterable<PlayerInfo> findByNickName(String nickName);

    List<PlayerInfo> findByOpenId(String openid);

}
