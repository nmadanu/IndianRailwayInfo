package live.indianrailway.IndianRailwayInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import live.indianrailway.IndianRailwayInfo.model.IndianRailwayStationModel;
import live.indianrailway.IndianRailwayInfo.repository.IndianRailwayStationRepository;
import live.indianrailway.IndianRailwayInfo.resttemplate.RailwayConfigBean;
import live.indianrailway.IndianRailwayInfo.resttemplate.RailwayRestClient;

@Service
public class IndianRailwayStationServiceImpl implements IndianRailwayStationService {

	@Autowired
	IndianRailwayStationRepository indianRailwayStationRepository;
	@Autowired
	private RailwayConfigBean railwayConfigBean = new RailwayConfigBean();
	@Autowired
	private RailwayRestClient railwayRestClient = railwayConfigBean.getClient();
	
	@Override
	public List<IndianRailwayStationModel> getAllStations() {
		 
		return this.indianRailwayStationRepository.findAll();
	}
	
	@Override
	public IndianRailwayStationModel saveStation(IndianRailwayStationModel station) {
        return this.indianRailwayStationRepository.save(station);
    }

	@Override
	public void deleteStations() {
		this.indianRailwayStationRepository.deleteAll();
	}
	
}
