package com.uiiang.controller;

import com.uiiang.biz.MatchScheduleService;
import com.uiiang.biz.NowMatchInfoService;
import com.uiiang.entity.MatchSchedule;
import com.uiiang.entity.NewMatchObj;
import com.uiiang.entity.NowMatchInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * Created by fuliqiang on 2017/4/26.
 */
@Controller
public class NowMatchInfoController {
    private NowMatchInfoService nowMatchInfoService;
    private MatchScheduleService matchScheduleService;

    public NowMatchInfoController(NowMatchInfoService nowMatchInfoService
            , MatchScheduleService matchScheduleService) {
        this.nowMatchInfoService = nowMatchInfoService;
        this.matchScheduleService = matchScheduleService;
    }


    @GetMapping("/nowmatch")
    @ResponseBody
    public NewMatchObj listAll(Model model) {
        Iterable<NowMatchInfo> all = nowMatchInfoService.findAll();
        NewMatchObj newMatchObj = new NewMatchObj();
        newMatchObj.setNewmatch(all);
        return newMatchObj;
    }

    @RequestMapping(value = "/startnewmatch", method = RequestMethod.GET)
    public String startNewMatch(Model model) {
        nowMatchInfoService.deleteAll();
        MatchSchedule nextMatch = matchScheduleService.findTopByStatusLessThanOrderByMatchDateTimeAsc(1);
        NowMatchInfo nowMatchInfo = new NowMatchInfo();
        nowMatchInfo.setAwayResult(0);
        nowMatchInfo.setAwayTeam(nextMatch.getAwayTeam());
        nowMatchInfo.setAwayWinNum(0);
        nowMatchInfo.setMatchDateTime(nextMatch.getMatchDateTime());
        nowMatchInfo.setDrawNum(0);
        nowMatchInfo.setHomeResult(0);
        nowMatchInfo.setHomeTeam(nextMatch.getHomeTeam());
        nowMatchInfo.setHomeWinNum(0);
        nowMatchInfo.setJoinNum(0);
        nowMatchInfo.setMatchDbId(nextMatch.getId());
        nowMatchInfo.setRoundNum(nextMatch.getRoundNum());
        nowMatchInfo.setStadiumName(nextMatch.getStadiumName());
        nowMatchInfo.setMatchLevel(nextMatch.getMatchLevel());
        nowMatchInfo.setStatus(0);
        nowMatchInfoService.save(nowMatchInfo);
        return "redirect:/";
    }


    @GetMapping(value = "/")
    public String index(Model model) {
        Iterable<NowMatchInfo> all = nowMatchInfoService.findAll();
        model.addAttribute("nowmatchlist", all);
        return "index";
    }

}
