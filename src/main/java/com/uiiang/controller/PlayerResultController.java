package com.uiiang.controller;

import com.uiiang.biz.*;
import com.uiiang.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by fuliqiang on 2017/5/4.
 */
@Controller
public class PlayerResultController {
    private PlayerResultService playerResultService;
    private MatchScheduleService matchScheduleService;
    private PlayerInfoService playerInfoService;
    private GuessResultService guessResultService;
    private MatchInfoService matchInfoService;

    public PlayerResultController(PlayerResultService playerResultService, MatchScheduleService matchScheduleService, PlayerInfoService playerInfoService, GuessResultService guessResultService, MatchInfoService matchInfoService) {
        this.playerResultService = playerResultService;
        this.matchScheduleService = matchScheduleService;
        this.playerInfoService = playerInfoService;
        this.guessResultService = guessResultService;
        this.matchInfoService = matchInfoService;
    }


    @GetMapping("/rankinglist")
    @ResponseBody
    public List<RankingList> listAllJson(@RequestParam(value = "mainfoid", required = false) Long matchInfoId) {
        List<MatchInfo> matchInfos = matchInfoService.findByStatus(MatchInfoController.MATCH_INFO_STATUS_START);
        List<RankingList> all = playerResultService.getRanking(matchInfos);
        return all;
    }

    @GetMapping("/resultlist.do")
    public String listAll(@RequestParam(value = "mainfoid", required = false) Long matchInfoId, Model model) {
        List<MatchInfo> matchInfos = matchInfoService.findByStatus(MatchInfoController.MATCH_INFO_STATUS_START);
        model.addAttribute("matchlist", matchInfos);

        MatchInfo matchInfo;
        if (matchInfoId != null && matchInfoId > 0) {
            matchInfo = matchInfoService.findOne(matchInfoId);
        } else {
            matchInfo = matchInfos.get(0);
        }
        List<RankingList> all = playerResultService.getRanking(matchInfos);
        model.addAttribute("playerResultList", all);
        return "playerresult/resultList";
    }


    @GetMapping("/countresult")
    public String countPlayerResult(@RequestParam(value = "mainfoid", required = false) Long matchInfoId
            , @RequestParam(value = "mcheId", required = false) Long matchScheduleId) {
        MatchInfo matchInfo = matchInfoService.findOne(matchInfoId);
        if (matchInfo == null) {
            return "redirect:/resultlist.do";
        }
        countGuessResult(matchInfo);
        return "redirect:/resultlist.do";
    }

    private void countGuessResult(MatchInfo matchInfo) {
        //取出所有MatchSchedule.stauts为2, 未计算的比赛
        List<MatchSchedule> matchScheduleList = matchScheduleService.findByStatusEquals(2);
        System.out.println("matchScheduleList  = " + matchScheduleList.size());
        //循环比赛, 根据比赛取出GuessResult
        for (MatchSchedule matchSchedule : matchScheduleList) {
            System.out.println("current matchSchedule  = " + matchSchedule.getHomeTeam() + " : " + matchSchedule.getAwayTeam());
            List<GuessResult> guessResultList = guessResultService.findByMatchSchedule(matchSchedule);
            System.out.println("guessResultList  = " + guessResultList.size());
            //根据GuessResult中的playerinfo和MatchInfo,取出PlayerResult(一个玩家在一个赛事中的记录)
            //PlayerResult为空的判定为新玩家, new新对象
            for (GuessResult guessResult : guessResultList) {
                PlayerInfo playerInfo = guessResult.getPlayerInfo();
                PlayerResult playerResult = playerResultService.findAllLimit1ByMatchInfoAndPlayerInfo(matchInfo, playerInfo);
                System.out.println("playerInfo  = " + playerInfo.getNickName() + " playerResult = " + (playerResult == null));
                if (playerResult == null) {
                    playerResult = new PlayerResult();
                    playerResult.setPlayerInfo(playerInfo);
                    playerResult.setJoinNum(1);
                    playerResult.setMatchInfo(matchInfo);
                    playerResult.setTotalScore(0);
                }
                int score = 0;
                //使用MatchSchedule的主客队进球数, 比对GuessResult的进球数
                //双方进球数相同的, PlayerResult. totalScore += 3
                //胜负关系正确, 只猜对一方进球数的PlayerResult. totalScore += 2
                //胜负关系正确, 猜错双方进球数的, PlayerResult. totalScore += 1
                if (matchSchedule.getHomeResult() == guessResult.getHomeResult()
                        && matchSchedule.getAwayResult() == guessResult.getAwayResult()
                        && matchSchedule.getResultType().equals(guessResult.getResultType())) {
                    score = 3;
                } else if (matchSchedule.getResultType().equals(guessResult.getResultType())
                        && (matchSchedule.getHomeResult() == guessResult.getHomeResult()
                        || matchSchedule.getAwayResult() == guessResult.getAwayResult())) {
                    score = 2;
                } else if (matchSchedule.getResultType().equals(guessResult.getResultType())) {
                    score = 1;
                }
                guessResult.setScore(score);
                guessResultService.save(guessResult);
                playerResult.setTotalScore(playerResult.getTotalScore() + score);

                Long joinNum = guessResultService.countByPlayerInfoAndMatchInfo(playerInfo, matchInfo);
                playerResult.setJoinNum(joinNum.intValue());
                //save PlayerResult
                playerResultService.save(playerResult);
            }
            //MatchSchedule每一场的全部玩家分数计算结束后, MatchSchedule.status = 3 保存
            matchSchedule.setStatus(3);
            matchScheduleService.save(matchSchedule);
        }
    }
}
