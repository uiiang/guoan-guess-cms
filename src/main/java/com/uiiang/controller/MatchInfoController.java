package com.uiiang.controller;

import com.uiiang.biz.MatchInfoService;
import com.uiiang.entity.MatchInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by fuliqiang on 2017/4/26.
 */
@Controller
public class MatchInfoController {

    //0,未开始, 1,进行中 2,已结束
    public static final int MATCH_INFO_STATUS_UNSTART = 0;
    public static final int MATCH_INFO_STATUS_START = 1;
    public static final int MATCH_INFO_STATUS_FINISH = 2;
    private MatchInfoService matchInfoService;

    public MatchInfoController(MatchInfoService matchInfoService) {
        this.matchInfoService = matchInfoService;
    }


    @GetMapping("/matchlist.do")
    public String listAll(Model model) {
        Iterable<MatchInfo> all = matchInfoService.findAllByOrderByStatusAsc();
        model.addAttribute("matchlist", all);
        return "matchlevel/matchlevel";
    }

    @RequestMapping(value = "/createMatch", method = RequestMethod.POST)
    public String saveMatch(@ModelAttribute MatchInfo matchInfo, Model model) {
        if (matchInfo.getCreateDate() == null || matchInfo.getId() < 1) {
            matchInfo.setCreateDate(new Date());
        }
        matchInfoService.save(matchInfo);
        return "redirect:matchlist.do";
    }


    @GetMapping("/startmatch")
    public String startMatch(@RequestParam(value = "id", required = false) Long id) {
        if (id != null && id >= 1) {
            MatchInfo matchInfo = matchInfoService.findOne(id);
            matchInfo.setStatus(MATCH_INFO_STATUS_START);
            matchInfo.setStartDate(new Date());
            matchInfoService.save(matchInfo);
        }
        return "redirect:matchlist.do";
    }


    @GetMapping("/finishmatch")
    public String finishMatch(@RequestParam(value = "id", required = false) Long id) {
        if (id != null && id >= 1) {
            MatchInfo matchInfo = matchInfoService.findOne(id);
            matchInfo.setStatus(MATCH_INFO_STATUS_FINISH);
            matchInfo.setFinishDate(new Date());
            matchInfoService.save(matchInfo);
        }
        return "redirect:matchlist.do";
    }


    @GetMapping("/editmatch")
    public String toEditMatch(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null && id >= 1) {
            MatchInfo matchInfo = matchInfoService.findOne(id);
            model.addAttribute("matchInfo", matchInfo);
        } else {
            model.addAttribute("matchInfo", new MatchInfo());
        }
        return "matchlevel/editmatch";
    }
}
