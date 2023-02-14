var weatherIcon = {
			'01' : 'fas fa-sun',
			'02' : 'fas fa-cloud-sun',
			'03' : 'fas fa-cloud',
			'04' : 'fas fa-cloud-meatball',
			'09' : 'fas fa-cloud-sun-rain',
			'10' : 'fas fa-cloud-showers-heavy',
			'11' : 'fas fa-poo-storm',
			'13' : 'far fa-snowflake',
			'50' : 'fas fa-smog'
		};

		// 날씨 api - 서울
		var apiURI = "http://api.openweathermap.org/data/2.5/weather?q="
				+ 'Seoul' + "&lang=" + "kr" + "&appid="
				+ "41f5964141c3f5fbfb905efce58bbd8b";
		$
				.ajax({
					url : apiURI,
					dataType : "json",
					type : "GET",
					async : "false",
					success : function(resp) {

						var $Icon = (resp.weather[0].icon).substr(0, 2);
						var $weather_description = resp.weather[0].main;
						var $Temp = Math.floor(resp.main.temp - 273.15) + 'º';
						var $humidity = '습도&nbsp;&nbsp;&nbsp;&nbsp;'
								+ resp.main.humidity + ' %';
						var $city = '서울';
						var $temp_min = '최저 온도&nbsp;&nbsp;&nbsp;&nbsp;'
								+ Math.floor(resp.main.temp_min - 273.15) + 'º';
						var $temp_max = '최고 온도&nbsp;&nbsp;&nbsp;&nbsp;'
								+ Math.floor(resp.main.temp_max - 273.15) + 'º';

						$('.weather_icon')
								.append(
										'<i class="' + weatherIcon[$Icon] +' fa-5x" style="height : 60px; width : 60px;"></i>');
						$('.weather_description').prepend($weather_description);
						$('.current_temp').prepend($Temp);
						$('.humidity').prepend($humidity);
						$('.city').append($city);
						$('.temp_min').append($temp_min);
						$('.temp_max').append($temp_max);
					}
				})