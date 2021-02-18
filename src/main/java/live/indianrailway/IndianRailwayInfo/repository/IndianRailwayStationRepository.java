package live.indianrailway.IndianRailwayInfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import live.indianrailway.IndianRailwayInfo.model.IndianRailwayStationModel;

@Repository
public interface IndianRailwayStationRepository extends JpaRepository<IndianRailwayStationModel, Integer> {

}
