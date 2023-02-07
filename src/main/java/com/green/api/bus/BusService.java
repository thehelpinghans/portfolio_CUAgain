package com.green.api.bus;

import org.springframework.web.servlet.ModelAndView;

public interface BusService {
	void getBusPath(String strSrch, ModelAndView mv);

	void getStaionsByRouteList(String busRouteId, ModelAndView mv);
}
