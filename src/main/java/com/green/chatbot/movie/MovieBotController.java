package com.green.chatbot.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.green.api.movie.MovieBotRequestDTO;

@RestController
public class MovieBotController {
	
	@Autowired
	private MovieBotService service; 
	
	@PostMapping("/movie-bot/{order}")
	public ModelAndView movieScenario(MovieBotRequestDTO dto, ModelAndView mv) {
		service.movieScenario(dto,mv);
		mv.setViewName("chatbot/bot-message-movie");
		return mv;
	}
	
	@GetMapping("/movie-bot/dailyBoxOffice")
	public ModelAndView dailyBoxOffice(String repNationCd, ModelAndView mv) {
		service.botDailyBoxOffice(repNationCd, mv);
		mv.setViewName("chatbot/movie-name-list");
		return mv;
	}
	
	@GetMapping("/movie-bot/search/naverApi")
	public ModelAndView searchNaverApi(String movieName, ModelAndView mv) {
		service.searchNamerApi(movieName, mv);
		mv.setViewName("chatbot/movie-detail");
		return mv;
	}
}
