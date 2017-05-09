package com.uiiang.controller;

import com.uiiang.biz.LeagueTeamService;
import com.uiiang.biz.StadiumInfoService;
import com.uiiang.entity.JsonWrapper;
import com.uiiang.entity.LeagueTeam;
import com.uiiang.entity.StadiumInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fuliqiang on 2017/4/26.
 */
@Controller
public class LeagueTeamController {
    private LeagueTeamService leagueTeamService;
    private StadiumInfoService stadiumInfoService;

    public LeagueTeamController(LeagueTeamService leagueTeamService, StadiumInfoService stadiumInfoService) {
        this.leagueTeamService = leagueTeamService;
        this.stadiumInfoService = stadiumInfoService;
    }

    @GetMapping("/leagueteam.do")
    public String listAll(Model model) {
        Iterable<LeagueTeam> all = leagueTeamService.findAll();

        model.addAttribute("teams", all);
        return "team/leagueteam";
    }

    @GetMapping("/listTeam")
    public Iterable<LeagueTeam> listLeagueTeam(Model model) {
        return leagueTeamService.findAll();
    }


    @GetMapping("/API/listTeam")
    @ResponseBody
    public JsonWrapper listLeagueTeamApi() {
        JsonWrapper jsonWrapper = new JsonWrapper();
        jsonWrapper.setData(leagueTeamService.findAll());
        jsonWrapper.setCode(ErrorCodeManager.ERROR_CODE_SUCCESS);
        jsonWrapper.setMsg(ErrorCodeManager.ERROR_MSG_SUCCESS);
        return jsonWrapper;
    }

    @GetMapping("/{teamname}")
    @ResponseBody
    public Iterable<LeagueTeam> findByChineseName(@PathVariable String teamName) {
        return leagueTeamService.findByChineseName(teamName);
    }

    @RequestMapping(value = "/createTeam", method = RequestMethod.POST)
    public String saveTeam(@ModelAttribute LeagueTeam leagueTeam, Model model) {
        leagueTeamService.save(leagueTeam);
        return "redirect:leagueteam.do";
    }

    @GetMapping("/editteam")
    public String toEditTeam(@RequestParam(value = "id", required = false) Long id, Model model) {
        Iterable<StadiumInfo> allStadium = stadiumInfoService.findAll();
        if (id != null && id >= 1) {
            LeagueTeam leagueTeam = leagueTeamService.findOne(id);
            model.addAttribute("leagueTeam", leagueTeam);
        } else {
            model.addAttribute("leagueTeam", new LeagueTeam());
        }
        model.addAttribute("allStadium",allStadium);
        return "team/editteam";
    }
}
