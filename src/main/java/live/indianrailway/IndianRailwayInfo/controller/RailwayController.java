package live.indianrailway.IndianRailwayInfo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import live.indianrailway.IndianRailwayInfo.resttemplate.RailwayRestClient;
import live.indianrailway.IndianRailwayInfo.service.IndianRailwayStationServiceImpl;
import live.indianrailway.IndianRailwayInfo.resttemplate.RailwayConfigBean;
import live.indianrailway.IndianRailwayInfo.model.StationDetailsModel;

@Controller
public class RailwayController {
	
	@Autowired
	private RailwayConfigBean railwayConfigBean = new RailwayConfigBean();
	
	@Autowired
	private RailwayRestClient railwayRestClient = railwayConfigBean.getClient();
	@Autowired
	private IndianRailwayStationServiceImpl indianRailwayStationServiceImpl;
	
	@GetMapping("/")
	public String indexPage(Model model) {	
		StationDetailsModel stationdetails = new StationDetailsModel();
		model.addAttribute("stationdetails", stationdetails);
		return "indexPage";
	}
	
	@PostMapping("/")
	public String saveStations(@ModelAttribute("stationdetails")StationDetailsModel stationdetails, Model model) {
		this.railwayRestClient.getStations(stationdetails.getStation());
		model.addAttribute("liststations", this.indianRailwayStationServiceImpl.getAllStations());
		return "indexPage";
	}

}
