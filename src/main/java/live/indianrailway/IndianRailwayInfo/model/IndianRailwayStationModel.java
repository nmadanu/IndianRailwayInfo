package live.indianrailway.IndianRailwayInfo.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "railstations")
public class IndianRailwayStationModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stid;
	
	@Column(name = "stationName")
	private String stationName;
	
	@Column(name = "stationCode")
	private String stationCode;
	
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	
}
