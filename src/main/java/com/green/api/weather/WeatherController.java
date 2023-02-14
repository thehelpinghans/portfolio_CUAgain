package com.green.api.weather;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WeatherController {
	

	
	@GetMapping("/weather")
	public ModelAndView weather(){
		return new ModelAndView("weather/weather");
	}
	
	
}
