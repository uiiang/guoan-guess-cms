package com.uiiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class GuoanApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        QCloud.setupSDK();
        SpringApplication.run(GuoanApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GuoanApplication.class);
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
