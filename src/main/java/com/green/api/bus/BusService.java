package com.green.api.bus;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import com.nowon.green.bus.dto.ArriveRequestDTO;
import com.nowon.green.bus.dto.BusArriveItem;
import com.nowon.green.bus.dto.BusStationOfPosItem;
import com.nowon.green.bus.dto.SeoulBusOfArrivalItem;

public interface BusService {
	void getBusPath(String strSrch, ModelAndView mv);

	void getStaionsByRouteList(String busRouteId, ModelAndView mv);

	void getPlacesSearch(String search, ModelAndView mv);

	List<BusArriveItem> arriveInfo(ArriveRequestDTO dto);

	List<BusStationOfPosItem> getBusStation(BusBotStationDTO dto);

	List<SeoulBusOfArrivalItem> busOfArrival(String arsId);
	
}
