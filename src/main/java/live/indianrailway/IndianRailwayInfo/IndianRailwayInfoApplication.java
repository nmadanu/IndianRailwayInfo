package live.indianrailway.IndianRailwayInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import live.indianrailway.IndianRailwayInfo.resttemplate.RailwayRestClient;

@SpringBootApplication
public class IndianRailwayInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndianRailwayInfoApplication.class, args);
	}

}
