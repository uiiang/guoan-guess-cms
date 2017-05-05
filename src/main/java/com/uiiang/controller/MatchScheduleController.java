package com.uiiang.controller;

import com.uiiang.biz.LeagueTeamService;
import com.uiiang.biz.MatchInfoService;
import com.uiiang.biz.MatchScheduleService;
import com.uiiang.entity.LeagueTeam;
import com.uiiang.entity.MatchInfo;
import com.uiiang.entity.MatchSchedule;
import com.uiiang.utils.LogUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fuliqiang on 2017/4/26.
 */
@Controller
public class MatchScheduleController {
    private MatchScheduleService service;
    private LeagueTeamService leagueTeamService;
    private MatchInfoService matchInfoService;

    public MatchScheduleController(MatchScheduleService service
            , LeagueTeamService leagueTeamService
            , MatchInfoService matchInfoService) {
        this.service = service;
        this.leagueTeamService = leagueTeamService;
        this.matchInfoService = matchInfoService;
    }

    @GetMapping("/schelist.do")
    public String listAll(Model model) {
        Iterable<MatchSchedule> all = service.findAll();
        model.addAttribute("schelist", all);
        return "matchsche/schedule";
    }

    @RequestMapping(value = "/createSche", method = RequestMethod.POST)
    public String saveMatch(@ModelAttribute MatchSchedule matchSchedule, Model model) {
        matchSchedule.setResultType(getResultType(matchSchedule.getHomeResult(), matchSchedule.getAwayResult()));
        service.save(matchSchedule);
        return "redirect:schelist.do";
    }

    @GetMapping("/editsche")
    public String toEditMatchSchedule(@RequestParam(value = "id", required = false) Long id, Model model) {
        Iterable<LeagueTeam> allTeam = leagueTeamService.findAll();
        Iterable<MatchInfo> allMatchLevel = matchInfoService.findAll();
        if (id != null && id >= 1) {
            MatchSchedule matchSchedule = service.findOne(id);
            model.addAttribute("scheInfo", matchSchedule);
        } else {
            model.addAttribute("scheInfo", new MatchSchedule());
        }
        model.addAttribute("teamList", allTeam);
        model.addAttribute("matchList", allMatchLevel);
        return "matchsche/editschedule";
    }

    @GetMapping("deletesche")
    public String deleteSchedule(@RequestParam(value = "id") Long id, Model model) {
        if (id != null && id >= 1) {
            MatchSchedule matchSchedule = service.findOne(id);
            if (matchSchedule != null) {
                service.delete(id);
            }
        }
        return "redirect:schelist.do";
    }

    @GetMapping("nextmatch")
    public String getNextGuessMatch(Model model) {
        MatchSchedule nextMatch = service.findTopByStatusLessThanOrderByMatchDateTimeAsc(1);
        LogUtils.i("nextMatch = " + nextMatch.getHomeTeam() + " : " + nextMatch.getAwayTeam());
        model.addAttribute("nextmatch", nextMatch);
        return "";
    }


    private String getResultType(int homeResult, int awayResult) {
        String resultType = GuessResultController.WIN;
        if (homeResult > awayResult) {
            resultType = GuessResultController.WIN;
        } else if (homeResult < awayResult) {
            resultType = GuessResultController.LOSE;
        } else {
            resultType = GuessResultController.DRAW;
        }
        return resultType;
    }

}
