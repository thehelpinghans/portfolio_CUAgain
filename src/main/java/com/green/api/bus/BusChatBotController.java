package com.green.api.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BusChatBotController {
	
	@Autowired
	private BusChatBotService sevice;
	
	@Autowired
	private BusService busSevice;
	
	@PostMapping("/bus-bot/{order}")
	public ModelAndView busBot(BusBotRequestDTO dto, ModelAndView mv) {
		mv.addObject("msg", sevice.scenario(dto));
		mv.setViewName("chatbot/bot-message-bus");
		return mv;
	}
	
	@PostMapping("/bus-bot/getBusStation/{radius}")
	public ModelAndView getBusStation(BusBotStationDTO dto, ModelAndView mv) {
		mv.addObject("list", busSevice.getBusStation(dto));
		mv.setViewName("chatbot/bot-bus-station-list");
		return mv;
	}
	
	@GetMapping("/bus-bot/busOfArrival/{arsId}")
	public ModelAndView busOfArrival(@PathVariable String arsId , ModelAndView mv) {
		mv.addObject("list", busSevice.busOfArrival(arsId));
		mv.setViewName("chatbot/bot-bus-arrival-list");
		return mv;
	}

}
