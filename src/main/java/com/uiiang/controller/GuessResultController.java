package com.uiiang.controller;

import com.qcloud.weapp.ConfigurationException;
import com.qcloud.weapp.authorization.LoginService;
import com.qcloud.weapp.authorization.LoginServiceException;
import com.qcloud.weapp.authorization.UserInfo;
import com.uiiang.biz.GuessResultService;
import com.uiiang.biz.MatchInfoService;
import com.uiiang.biz.MatchScheduleService;
import com.uiiang.biz.PlayerInfoService;
import com.uiiang.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by fuliqiang on 2017/5/3.
 */
@Controller
public class GuessResultController {
    public static final String WIN = "WIN";
    public static final String LOSE = "LOSE";
    public static final String DRAW = "DRAW";
    private GuessResultService guessResultService;
    private MatchScheduleService matchScheduleService;
    private PlayerInfoService playerInfoService;
    private MatchInfoService matchInfoService;

    public GuessResultController(GuessResultService guessResultService, MatchScheduleService matchScheduleService, PlayerInfoService playerInfoService, MatchInfoService matchInfoService) {
        this.guessResultService = guessResultService;
        this.matchScheduleService = matchScheduleService;
        this.playerInfoService = playerInfoService;
        this.matchInfoService = matchInfoService;
    }

    @GetMapping("/guess.do")
    public String listAll(Model model) {
        Iterable<GuessResult> all = guessResultService.findAll();
        model.addAttribute("guesslist", all);

        return "guess/guesslist";
    }

    @GetMapping("/guesspre")
    @ResponseBody
    public GuessResultPreview listGuessPreview(@RequestParam(value = "id", required = false) Long id) {
        GuessResultPreview guessResultPreview = new GuessResultPreview();
        List<GuessResultPreviewObj> allLimit5Win = guessResultService.findAllLimit5(WIN, id);
        List<GuessResultPreviewObj> allLimit5Draw = guessResultService.findAllLimit5(DRAW, id);
        List<GuessResultPreviewObj> allLimit5Lose = guessResultService.findAllLimit5(LOSE, id);

        guessResultPreview.setHomeWin(allLimit5Win);
        guessResultPreview.setHomeLose(allLimit5Lose);
        guessResultPreview.setDraw(allLimit5Draw);
        return guessResultPreview;
    }

    @RequestMapping(value = "/submitguess", method = RequestMethod.POST)
    @ResponseBody
    public void submitGuess(@RequestParam(value = "m") String matchid
            , @RequestParam(value = "h") String homegoal
            , @RequestParam(value = "a") String awaygoal
            , HttpServletRequest request, HttpServletResponse response) {
        System.out.println(matchid + "  " + homegoal + "  " + awaygoal);
        LoginService service = new LoginService(request, response);
        UserInfo userInfo = null;
        // 调用检查登录接口，成功后可以获得用户信息，进行正常的业务请求
        try {
            userInfo = service.check();
        } catch (LoginServiceException | ConfigurationException e) {
            e.printStackTrace();
        }

        if (userInfo != null) {
            PlayerInfo playerInfo = new PlayerInfo();
            playerInfo.setOpenId(userInfo.getOpenId());

            MatchSchedule matchSchedule = matchScheduleService.findOne(Long.valueOf(matchid));
            List<MatchInfo> matchInfos = matchInfoService.findByChineseName(matchSchedule.getMatchLevel());
            GuessResult guessResult = new GuessResult();

            List<GuessResult> tmpGuessResultList = guessResultService.findByPlayerInfoAndMatchSchedule(playerInfo
                    , matchSchedule);
            if (tmpGuessResultList != null && tmpGuessResultList.size() > 0) {
                guessResult = tmpGuessResultList.get(0);
            } else {
                guessResult.setMatchSchedule(matchSchedule);
                guessResult.setPlayerInfo(playerInfo);
            }
            guessResult.setMatchInfo(matchInfos.get(0));
            guessResult.setSubmitTime(new Date());
            guessResult.setHomeResult(Integer.valueOf(homegoal));
            guessResult.setAwayResult(Integer.valueOf(awaygoal));
            String resultType = getResultType(guessResult);
            guessResult.setResultType(resultType);
            guessResultService.save(guessResult);
        } else {
            //TODO
        }
    }

    @RequestMapping(value = "/createGuess", method = RequestMethod.POST)
    public String saveMatchGuess(@ModelAttribute GuessResult guessResult, Model model) {
        List<GuessResult> tmpGuessResultList = guessResultService.findByPlayerInfoAndMatchSchedule(guessResult.getPlayerInfo()
                , guessResult.getMatchSchedule());

        if (tmpGuessResultList != null && tmpGuessResultList.size() > 0) {
            guessResult.setId(tmpGuessResultList.get(0).getId());
        }
        List<MatchInfo> matchInfos = matchInfoService.findByChineseName(guessResult.getMatchSchedule().getMatchLevel());
        guessResult.setMatchInfo(matchInfos.get(0));
        guessResult.setSubmitTime(new Date());
        String resultType = getResultType(guessResult);
        guessResult.setResultType(resultType);
        guessResultService.save(guessResult);
        return "redirect:guess.do";
    }

    private String getResultType(@ModelAttribute GuessResult guessResult) {
        int homeR = guessResult.getHomeResult();
        int awayR = guessResult.getAwayResult();
        String resultType = WIN;
        if (homeR > awayR) {
            resultType = WIN;
        } else if (homeR < awayR) {
            resultType = LOSE;
        } else {
            resultType = DRAW;
        }
        return resultType;
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

//    SELECT guess_result.away_result,guess_result.home_result , count(id)
//    FROM guoan1992.guess_result
//    group by guess_result.away_result, guess_result.home_result
//    order by count(id) desc;
}
