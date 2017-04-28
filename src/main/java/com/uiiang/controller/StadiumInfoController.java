package com.uiiang.controller;

import com.uiiang.biz.MatchInfoService;
import com.uiiang.biz.StadiumInfoService;
import com.uiiang.entity.MatchInfo;
import com.uiiang.entity.StadiumInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fuliqiang on 2017/4/26.
 */
@Controller
public class StadiumInfoController {
    private StadiumInfoService service;

    public StadiumInfoController(StadiumInfoService stadiumInfoService) {
        this.service = stadiumInfoService;
    }


    @GetMapping("/stadiumlist.do")
    public String listAll(Model model) {
        Iterable<StadiumInfo> all = service.findAll();
        model.addAttribute("stadiumlist", all);
        return "stadium/stadium";
    }

    @RequestMapping(value = "/createStadium", method = RequestMethod.POST)
    public String saveStadium(@ModelAttribute StadiumInfo stadiumInfo, Model model) {
        service.save(stadiumInfo);
        return "redirect:stadiumlist.do";
    }

    @GetMapping("/editstadium")
    public String toEditStadium(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null && id >= 1) {
            StadiumInfo stadiumInfo = service.findOne(id);
            model.addAttribute("stadiumInfo", stadiumInfo);
        } else {
            model.addAttribute("stadiumInfo", new StadiumInfo());
        }
        return "stadium/editstadium";
    }
}
