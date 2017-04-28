package com.uiiang.controller;

import com.uiiang.biz.MatchInfoService;
import com.uiiang.entity.LeagueTeam;
import com.uiiang.entity.MatchInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fuliqiang on 2017/4/26.
 */
@Controller
public class MatchInfoController {
    private MatchInfoService matchInfoService;

    public MatchInfoController(MatchInfoService matchInfoService) {
        this.matchInfoService = matchInfoService;
    }


    @GetMapping("/matchlist.do")
    public String listAll(Model model) {
        Iterable<MatchInfo> all = matchInfoService.findAll();
        model.addAttribute("matchlist", all);
        return "matchlevel/matchlevel";
    }

    @RequestMapping(value = "/createMatch", method = RequestMethod.POST)
    public String saveMatch(@ModelAttribute MatchInfo matchInfo, Model model) {
        matchInfoService.save(matchInfo);
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
