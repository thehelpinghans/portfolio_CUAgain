package com.green.api.bus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.api.OpenApiUtil;
import com.green.api.bus.BusService;
import com.green.api.bus.dto.BusAPItResponse;
import com.green.api.bus.dto.BusRouteItem;
import com.green.api.bus.dto.BusStationItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class BusServiceProcess implements BusService {

	//DB가 아니고 openAPI

	@Value("${data.go.kr.bus.serviceKey}")
	private String serviceKey;

	@Override
	public void getBusPath(String strSrch, ModelAndView mv) {

		StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList"); /*URL*/

		try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("strSrch","UTF-8") + "=" + URLEncoder.encode(strSrch, "UTF-8"));/*검색할 노선번호*/
			urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));/*[xml,json]*/

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String apiURL=urlBuilder.toString();
		//System.out.println(apiURL);
		Map<String, String> requestHeaders=new HashMap<>();
		requestHeaders.put("Content-type", "application/json");

		String responseData= OpenApiUtil.request(apiURL, requestHeaders, "GET");

		//JSONObject jsonObject= XML.toJSONObject(responseData);

		//json->calss Mapping 해줌
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			BusAPItResponse<BusRouteItem> result=objectMapper.readValue(responseData, BusAPItResponse.class);
			//ServiceResult result=objectMapper.readValue(jsonObject.toString(), ServiceResult.class);

			//System.out.println(result);
			//List<BusRouteItem>
			mv.addObject("list", result.getMsgBody().getItemList());
			//mv.addObject("list", result.getServiceResult().getMsgBody().getItemList());


		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// System.out.println(responseData);

	}

	@Override
	public void getStaionsByRouteList(String busRouteId, ModelAndView mv) {
		StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute"); /*URL*/

		try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("busRouteId","UTF-8") + "=" + URLEncoder.encode(busRouteId, "UTF-8"));/*검색할 노선번호*/
			urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));/*[xml,json]*/

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String apiURL=urlBuilder.toString();
		//System.out.println(apiURL);
		Map<String, String> requestHeaders=new HashMap<>();
		requestHeaders.put("Content-type", "application/json");

		String responseData=OpenApiUtil.request(apiURL, requestHeaders, "GET");

		//System.out.println(">>>"+responseData);

		ObjectMapper objectMapper=new ObjectMapper();
		try {
			@SuppressWarnings("unchecked")
			BusAPItResponse<BusStationItem> result=objectMapper.readValue(responseData, BusAPItResponse.class);
			//System.out.println("result:"+result.getMsgBody().getItemList());
			//List<StationItem>
			mv.addObject("list", result.getMsgBody().getItemList());

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
