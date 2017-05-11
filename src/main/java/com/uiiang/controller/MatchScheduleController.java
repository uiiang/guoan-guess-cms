package com.uiiang.controller;

import com.uiiang.biz.LeagueTeamService;
import com.uiiang.biz.MatchInfoService;
import com.uiiang.biz.MatchScheduleService;
import com.uiiang.entity.JsonWrapper;
import com.uiiang.entity.LeagueTeam;
import com.uiiang.entity.MatchInfo;
import com.uiiang.entity.MatchSchedule;
import com.uiiang.utils.DateUtils;
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

    @GetMapping("/API/schelist")
    @ResponseBody
    public JsonWrapper listAllApi() {
        JsonWrapper jsonWrapper = new JsonWrapper();
        Iterable<MatchSchedule> all = service.findByOrderByRoundNumDesc();
        jsonWrapper.setCode(ErrorCodeManager.ERROR_CODE_SUCCESS);
        jsonWrapper.setMsg(ErrorCodeManager.ERROR_MSG_SUCCESS);
        jsonWrapper.setData(all);
        return jsonWrapper;
    }

    @RequestMapping(value = "/createSche", method = RequestMethod.POST)
    public String saveMatch(@ModelAttribute MatchSchedule matchSchedule, Model model) {
        matchSchedule.setResultType(getResultType(matchSchedule.getHomeResult(), matchSchedule.getAwayResult(), matchSchedule.getStatus()));
        matchSchedule.setMatchDateTimeStr(DateUtils.dateTimeFormat1(matchSchedule.getMatchDateTime()));
        service.save(matchSchedule);
        return "redirect:schelist.do";
    }

    @RequestMapping(value = "/API/createSche", method = RequestMethod.POST)
    @ResponseBody
    public JsonWrapper saveMatchApi(@ModelAttribute MatchSchedule matchSchedule) {
        LogUtils.i("saveMatchApi" + matchSchedule.getHomeTeam() + " " + matchSchedule.getAwayTeam());
        matchSchedule.setResultType(getResultType(matchSchedule.getHomeResult(), matchSchedule.getAwayResult(), matchSchedule.getStatus()));
        matchSchedule.setMatchDateTimeStr(DateUtils.dateTimeFormat1(matchSchedule.getMatchDateTime()));
        service.save(matchSchedule);

        JsonWrapper jsonWrapper = new JsonWrapper();
        jsonWrapper.setCode(ErrorCodeManager.ERROR_CODE_SUCCESS);
        jsonWrapper.setMsg(ErrorCodeManager.ERROR_MSG_SUCCESS);

        return jsonWrapper;
    }

    @GetMapping("/API/editsche")
    @ResponseBody
    public JsonWrapper toEditMatchScheduleApi(@RequestParam(value = "id", required = false) Long id) {
        JsonWrapper jsonWrapper = new JsonWrapper();
        if (id != null && id >= 1) {
            MatchSchedule matchSchedule = service.findOne(id);
            jsonWrapper.setData(matchSchedule);
            jsonWrapper.setCode(ErrorCodeManager.ERROR_CODE_SUCCESS);
            jsonWrapper.setMsg(ErrorCodeManager.ERROR_MSG_SUCCESS);
        } else {
            jsonWrapper.setCode(ErrorCodeManager.ERROR_CODE_MATCH_SCHEDULE_NOT_FOUND);
            jsonWrapper.setMsg(ErrorCodeManager.ERROR_MSG_MATCH_SCHEDULE_NOT_FOUND);
        }
        return jsonWrapper;
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

    @GetMapping("/API/deletesche")
    @ResponseBody
    public JsonWrapper deleteSchedule(@RequestParam(value = "id") Long id) {
        JsonWrapper jsonWrapper = new JsonWrapper();
        if (id != null && id >= 1) {
            MatchSchedule matchSchedule = service.findOne(id);
            if (matchSchedule != null) {
                service.delete(id);
                jsonWrapper.setCode(ErrorCodeManager.ERROR_CODE_SUCCESS);
                jsonWrapper.setMsg(ErrorCodeManager.ERROR_MSG_SUCCESS);
            } else {
                jsonWrapper.setCode(ErrorCodeManager.ERROR_CODE_MATCH_SCHEDULE_NOT_FOUND);
                jsonWrapper.setMsg(ErrorCodeManager.ERROR_MSG_MATCH_SCHEDULE_NOT_FOUND);
            }
        } else {
            jsonWrapper.setCode(ErrorCodeManager.ERROR_CODE_MATCH_SCHEDULE_NOT_FOUND);
            jsonWrapper.setMsg(ErrorCodeManager.ERROR_MSG_MATCH_SCHEDULE_NOT_FOUND);
        }
        return jsonWrapper;
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


    private String getResultType(int homeResult, int awayResult, int status) {
        String resultType = GuessResultController.NONE;
        if (status < 2) {
            return resultType;
        } else {
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

}
