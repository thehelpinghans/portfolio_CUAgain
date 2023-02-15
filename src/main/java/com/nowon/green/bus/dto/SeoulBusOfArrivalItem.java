package com.nowon.green.bus.dto;

import lombok.Data;

@Data
public class SeoulBusOfArrivalItem {
	private String stId;                   
	private String stNm;       //정류소이름            
	private String arsId;                  
	private String busRouteId; //        
	private String rtNm;        //버스번호           
	private String busRouteAbrv;//버스번호           
	private String sectNm;                 
	private String gpsX;                   
	private String gpsY;                   
	private String posX;                   
	private String posY;                   
	private String stationTp;              
	private String firstTm;                
	private String lastTm;                 
	private String term;                   
	private String routeType;              
	private String nextBus;                
	private String staOrd;                 
	private String vehId1;                 
	private String plainNo1;               
	private String sectOrd1;               
	private String stationNm1;             
	private String traTime1;               
	private String traSpd1;                
	private String isArrive1;              
	private String repTm1;                 
	private String isLast1;                
	private String busType1;               
	private String vehId2;                 
	private String plainNo2;               
	private String sectOrd2;               
	private String stationNm2;             
	private String traTime2;               
	private String traSpd2;                
	private String isArrive2;              
	private String repTm2;                 
	private String isLast2;                
	private String busType2;               
	private String adirection;             
	private String arrmsg1;    //도작정보            
	private String arrmsg2;                
	private String arrmsgSec1;             
	private String arrmsgSec2;             
	private String nxtStn;                 
	private String rerdieDiv1;             
	private String rerdieDiv2;             
	private String rerideNum1;             
	private String rerideNum2;             
	private String isFullFlag1;            
	private String isFullFlag2;            
	private String deTourAt;               
	private String congestion;             
}
