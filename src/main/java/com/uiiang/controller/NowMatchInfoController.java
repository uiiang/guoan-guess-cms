package com.uiiang.controller;

import com.uiiang.biz.GuessResultService;
import com.uiiang.biz.MatchScheduleService;
import com.uiiang.biz.NowMatchInfoService;
import com.uiiang.entity.JsonWrapper;
import com.uiiang.entity.MatchSchedule;
import com.uiiang.entity.NowMatchInfo;
import com.uiiang.utils.LogUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by fuliqiang on 2017/4/26.
 */
@Controller
public class NowMatchInfoController {
    private NowMatchInfoService nowMatchInfoService;
    private MatchScheduleService matchScheduleService;
    private GuessResultService guessResultService;

    public NowMatchInfoController(NowMatchInfoService nowMatchInfoService
            , MatchScheduleService matchScheduleService
            , GuessResultService guessResultService) {
        this.nowMatchInfoService = nowMatchInfoService;
        this.matchScheduleService = matchScheduleService;
        this.guessResultService = guessResultService;
    }

    @GetMapping("/nowmatch")
    @ResponseBody
    public JsonWrapper listAll(Model model) {
        Iterable<NowMatchInfo> all = nowMatchInfoService.findAll();
        JsonWrapper jsonWrapper = new JsonWrapper();
        if (all == null || nowMatchInfoService.count() == 0) {
            jsonWrapper.setCode(ErrorCodeManager.ERROR_CODE_GUESS_NOTSTART);
            jsonWrapper.setMsg(ErrorCodeManager.ERROR_MSG_GUESS_NOTSTART);
        } else {
            jsonWrapper.setCode(ErrorCodeManager.ERROR_CODE_SUCCESS);
            jsonWrapper.setMsg(ErrorCodeManager.ERROR_MSG_SUCCESS);
        }
        jsonWrapper.setData(all);
        return jsonWrapper;
    }

    @RequestMapping(value = "/startnewmatch", method = RequestMethod.GET)
    public String startNewMatch(Model model) {
        handleStartNewMatch();
        return "redirect:/guoan1992/";
    }

    @RequestMapping(value = "/API/startnewmatch", method = RequestMethod.GET)
    public String startNewMatchApi(Model model) {
        handleStartNewMatch();
        return "redirect:/nowmatch";
    }

    private void handleStartNewMatch() {
        nowMatchInfoService.deleteAll();
        MatchSchedule nextMatch = matchScheduleService.findTopByStatusLessThanOrderByMatchDateTimeAsc(1);
        NowMatchInfo nowMatchInfo = new NowMatchInfo();
        nowMatchInfo.setAwayWinNum(0);
        nowMatchInfo.setDrawNum(0);
        nowMatchInfo.setHomeWinNum(0);
        nowMatchInfo.setJoinNum(0);
        nowMatchInfo.setStatus(0);
        nowMatchInfo.setMatchSchedule(nextMatch);
        nowMatchInfoService.save(nowMatchInfo);
        LogUtils.i("new match " + nowMatchInfo.getMatchSchedule().getHomeTeam() + " : " + nowMatchInfo.getMatchSchedule().getAwayTeam());
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        Iterable<NowMatchInfo> all = nowMatchInfoService.findAll();
        model.addAttribute("nowmatchlist", all);
        return "index";
    }


    @RequestMapping(value = "/countMatchResult", method = RequestMethod.GET)
    public String countMatchResult(@RequestParam(value = "id", required = true) Long id) {
        handleCountGuessResult(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/API/countMatchResult", method = RequestMethod.GET)
    public String countMatchResultApi(@RequestParam(value = "id", required = true) Long id) {
        handleCountGuessResult(id);
        return "redirect:/nowmatch";
    }

    private void handleCountGuessResult(@RequestParam(value = "id", required = false) Long id) {
        NowMatchInfo nowMatchInfo = nowMatchInfoService.findOne(id);

        Long winSum = guessResultService.countByMatchScheduleAndResultType(nowMatchInfo.getMatchSchedule(), GuessResultController.WIN);
        Long loseSum = guessResultService.countByMatchScheduleAndResultType(nowMatchInfo.getMatchSchedule(), GuessResultController.LOSE);
        Long drawSum = guessResultService.countByMatchScheduleAndResultType(nowMatchInfo.getMatchSchedule(), GuessResultController.DRAW);

        nowMatchInfo.setHomeWinNum(winSum.intValue());
        nowMatchInfo.setAwayWinNum(loseSum.intValue());
        nowMatchInfo.setDrawNum(drawSum.intValue());
        nowMatchInfo.setJoinNum(nowMatchInfo.getDrawNum() + nowMatchInfo.getAwayWinNum() + nowMatchInfo.getHomeWinNum());
        nowMatchInfoService.save(nowMatchInfo);
    }

}
