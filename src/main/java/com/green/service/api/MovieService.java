package com.green.service.api;

import java.util.List;

import com.green.api.movie.Item;

public interface MovieService {

	List<Item> dailyBoxOfficeList(String targetDt);

}
