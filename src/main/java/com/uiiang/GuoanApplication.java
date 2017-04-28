package com.uiiang;

import com.uiiang.biz.LeagueTeamService;
import com.uiiang.entity.LeagueTeam;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GuoanApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(GuoanApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner init(LeagueTeamService leagueTeamService) {
//        return args -> {
//            LeagueTeam guoan = new LeagueTeam();
//            guoan.setChineseName("guoan");
//            guoan.setCity("beijing");
//            guoan.setEmblems("1111");
//            LeagueTeam hengda = new LeagueTeam();
//            hengda.setChineseName("hengda");
//            hengda.setCity("guangzhou");
//            hengda.setEmblems("2222");
//            leagueTeamService.save(hengda);
//            leagueTeamService.save(guoan);
//        };
//    }
}
