package live.indianrailway.IndianRailwayInfo.service;

import java.util.Collection;
import java.util.List;

import live.indianrailway.IndianRailwayInfo.model.IndianRailwayStationModel;

public interface IndianRailwayStationService {
	List<IndianRailwayStationModel> getAllStations();
	IndianRailwayStationModel saveStation(IndianRailwayStationModel station);
	void deleteStations();
}
