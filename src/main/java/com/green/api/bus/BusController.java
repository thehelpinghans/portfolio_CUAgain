package com.green.api.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BusController {

	@Autowired
	BusService service;

	//그냥페이지이동
	//@ResponseBody 생략되어있음 @RestController기에!
	//리턴에 정보(DTO, Map 등등을) 보내면 알아서 JSON 형식으로 파싱해서 보내줌!
	@GetMapping("/bus")
	public ModelAndView bus(){

		return new ModelAndView("bus/bus-index");
	}

	//ajax 요청
	@GetMapping("/bus/search")
	public ModelAndView busSearch(String strSrch, ModelAndView mv){
		mv.setViewName("bus/list");
		service.getBusPath(strSrch,mv);
		return mv;
	}

	//@ajax 요청방식
	//리스트에 버스번호 클릭시 처리되는기능
	@GetMapping("/bus/{busRouteId}")
	public ModelAndView getStaionsByRouteList(@PathVariable String busRouteId, ModelAndView mv) {
		mv.setViewName("bus/station-list");
		service.getStaionsByRouteList(busRouteId, mv);
		return mv;
	}
}
