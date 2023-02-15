package com.green.api.bus.dto;

import lombok.Data;

@Data
public class BusStationOfPosItem {
	
	private String stationId;//정류소고유ID
	private String stationNm;//정류소이름
	private String gpsX; //WGS84
	private String gpsY; //WGS84
	private String posX; //GRS80
	private String posY; //GRS80
	private String stationTp; 
	//정류소타입(0:공용, 1:일반형 시내/농어촌버스, 2:좌석형 시내/농어촌버스, 3:직행좌석형 시내/농어촌버스, 4:일반형 시외버스, 5:좌석형 시외버스, 6:고속형 시외버스, 7:마을버스)
	private String arsId; //정류소번호
	private String dist; //거리

}