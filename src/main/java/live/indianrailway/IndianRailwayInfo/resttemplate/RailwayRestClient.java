package live.indianrailway.IndianRailwayInfo.resttemplate;

import java.io.IOException;
import java.util.Iterator;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;

import live.indianrailway.IndianRailwayInfo.model.IndianRailwayStationModel;
import live.indianrailway.IndianRailwayInfo.service.IndianRailwayStationServiceImpl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RailwayRestClient{
	private static final String GET_PNR_ENDPOINT_URL = "https://indianrailways.p.rapidapi.com/index.php?pnr=1234567890";
	private static final String GET_STATION_ENDPOINT_URL = "https://indianrailways.p.rapidapi.com/findstations.php?station=";
	private static final String GET_RAPIDAPI_KEY = "d3f0c7a857mshbe1a7bf0dc268a9p10d5d2jsn040ebf5dd442";
	private static final String GET_RAPIDAPI_HOST = "indianrailways.p.rapidapi.com";
	
	@Autowired
	private IndianRailwayStationServiceImpl indianRailwayStationServiceImpl;
	
	public void getPNRStatus() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		headers.set("x-rapidapi-key", GET_RAPIDAPI_KEY);
		headers.set("x-rapidapi-host", GET_RAPIDAPI_HOST);
		
		HttpEntity request = new HttpEntity(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				GET_PNR_ENDPOINT_URL,
		        HttpMethod.GET,
		        request,
		        String.class,
		        1
		);

		// check response
		if (response.getStatusCode() == HttpStatus.OK) {
		    System.out.println("Request Successful.");
		    System.out.println(response.getBody());
		} else {
		    System.out.println("Request Failed");
		    System.out.println(response.getStatusCode());
		}
		
	}
	
	
	public void getStations(String station) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		String finalEndPoint = GET_STATION_ENDPOINT_URL + station;
		System.out.println(".....station address..........."+ GET_STATION_ENDPOINT_URL +".................");
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		headers.set("x-rapidapi-key", GET_RAPIDAPI_KEY);
		headers.set("x-rapidapi-host", GET_RAPIDAPI_HOST);
		
		HttpEntity request = new HttpEntity(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				finalEndPoint,
		        HttpMethod.GET,
		        request,
		        String.class,
		        1
		);

		// check response
		if (response.getStatusCode() == HttpStatus.OK ) {
		    System.out.println("Request Successful.");
		    System.out.println(response.getBody());
		    String respBody = response.getBody();
		    
			try {
				ObjectMapper mapper = new ObjectMapper();
				
				JsonNode rootNode = mapper.readTree(respBody);
				JsonNode stations = rootNode.path("stations");
				System.out.println("==========="+stations+"===========");
				
				Iterator<JsonNode> iterator = stations.elements();
					
				indianRailwayStationServiceImpl.deleteStations();
		         while (iterator.hasNext()) {
		        	 JsonNode stationValues = iterator.next();
		        	 IndianRailwayStationModel stationNC = new ObjectMapper().readValue(stationValues.toString(), IndianRailwayStationModel.class);
		        	 indianRailwayStationServiceImpl.saveStation(stationNC);
		         }
		         System.out.println("-------------Save Successfully---------");
				
			} catch (IOException e){
				System.out.println("Unable to save stations: " + e.getMessage());
			}
		} else {
		    System.out.println("Request Failed");
		    System.out.println(response.getStatusCode());
		}
	}
	
	
}
