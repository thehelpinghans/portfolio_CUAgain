package com.green.api.movie;

import lombok.Data;

@Data
public class NaverItem {
	
	private String title;
	private String link;
	private String image;
	private String subtitle;
	private String pubDate;
	private String director;
	private String actor;
	private String userRating;	
}
