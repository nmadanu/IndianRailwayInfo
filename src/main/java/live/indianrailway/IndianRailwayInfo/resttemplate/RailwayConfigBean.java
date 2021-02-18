package live.indianrailway.IndianRailwayInfo.resttemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RailwayConfigBean {

	@Bean
	public RailwayRestClient getClient() {
		return new RailwayRestClient();
	}
}
