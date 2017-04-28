package com.uiiang.controller;

import com.uiiang.biz.MatchScheduleService;
import com.uiiang.biz.NowMatchInfoService;
import com.uiiang.entity.MatchSchedule;
import com.uiiang.entity.NowMatchInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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
    public Iterable<NowMatchInfo> listAll(Model model) {
        Iterable<NowMatchInfo> all = nowMatchInfoService.findAll();
        return all;
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
        nowMatchInfo.setStadiumName(nextMatch.getStadiumName());
        nowMatchInfo.setMatchLevel(nextMatch.getMatchLevel());
        nowMatchInfo.setStatus(0);
        nowMatchInfo.setRoundNum(nextMatch.getRoundNum());
        nowMatchInfo.setHomeEmblems(nextMatch.getHomeEmblems());
        nowMatchInfo.setAwayEmblems(nextMatch.getAwayEmblems());
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
