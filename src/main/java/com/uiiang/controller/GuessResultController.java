package com.uiiang.controller;

import com.uiiang.biz.GuessResultService;
import com.uiiang.biz.MatchScheduleService;
import com.uiiang.biz.PlayerInfoService;
import com.uiiang.entity.GuessResult;
import com.uiiang.entity.MatchSchedule;
import com.uiiang.entity.PlayerInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by fuliqiang on 2017/5/3.
 */
@Controller
public class GuessResultController {
    private GuessResultService guessResultService;
    private MatchScheduleService matchScheduleService;
    private PlayerInfoService playerInfoService;

    public GuessResultController(GuessResultService guessResultService
            , MatchScheduleService matchScheduleService
            , PlayerInfoService playerInfoService) {
        this.guessResultService = guessResultService;
        this.matchScheduleService = matchScheduleService;
        this.playerInfoService = playerInfoService;
    }

    @GetMapping("/guess.do")
    public String listAll(Model model) {
        Iterable<GuessResult> all = guessResultService.findAll();
        model.addAttribute("guesslist", all);
        return "guess/guesslist";
    }

    @RequestMapping(value = "/createGuess", method = RequestMethod.POST)
    public String saveMatch(@ModelAttribute GuessResult guessResult, Model model) {
        guessResult.setSubmitTime(new Date());
        int homeR = guessResult.getHomeResult();
        int awayR = guessResult.getAwayResult();
        if (homeR > awayR){
            guessResult.setResultType("WIN");
        } else if (homeR < awayR) {
            guessResult.setResultType("LOSE");
        } else {
            guessResult.setResultType("DRAW");
        }
        guessResultService.save(guessResult);
        return "redirect:guess.do";
    }

    @GetMapping("/editguess")
    public String toEditGuess(@RequestParam(value = "id", required = false) Long id, Model model) {
        Iterable<PlayerInfo> playerInfos = playerInfoService.findAll();
        Iterable<MatchSchedule> matchSchedules = matchScheduleService.findByStatusEquals(0);
        GuessResult guessResult = new GuessResult();
        if (id != null && id >= 1) {
            guessResult = guessResultService.findOne(id);
        }
        model.addAttribute("playerInfos", playerInfos);
        model.addAttribute("matchSchedules", matchSchedules);
        model.addAttribute("guessResult", guessResult);
        return "guess/editguess";
    }
}
