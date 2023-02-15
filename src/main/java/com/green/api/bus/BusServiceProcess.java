package com.green.api.bus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.api.OpenApiUtil;
import com.green.api.bus.BusService;
import com.green.api.bus.dto.BusAPItResponse;
import com.green.api.bus.dto.BusRouteItem;
import com.green.api.bus.dto.BusStationItem;
import com.nowon.green.bus.dto.ArriveRequestDTO;
import com.nowon.green.bus.dto.BusArriveItem;
import com.nowon.green.bus.dto.BusStationOfPosItem;
import com.nowon.green.bus.dto.SeoulBusOfArrivalItem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        
        String responseData=OpenApiUtil.request(apiURL, requestHeaders, "GET");
        
        //JSONObject jsonObject= XML.toJSONObject(responseData);
        
        //json->calss Mapping 해줌
        ObjectMapper objectMapper=new ObjectMapper();
        try {
        	BusAPItResponse<BusRouteItem> result=
        			objectMapper.readValue(responseData,  new TypeReference<BusAPItResponse<BusRouteItem>>() {});
        	//ServiceResult result=objectMapper.readValue(jsonObject.toString(), ServiceResult.class);
        	
        	//System.out.println(result);
        	//List<BusRouteItem>
        	mv.addObject("list", result.getMsgBody().getItemList().stream()
        			//리턴이 true는 통과, false는 제거  : replaceAll("[^0-9]", "") 숫자(0~9)를제외한 문자 제거
        			.filter(i->i.getBusRouteNm().replaceAll("[^\\d-]", "").length()==strSrch.length())
        			.collect(Collectors.toList()));
        	//mv.addObject("list", result.getServiceResult().getMsgBody().getItemList());
        	mv.addObject("strSrch", strSrch);
        	
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
        	
			BusAPItResponse<BusStationItem> result=objectMapper.readValue(responseData, new TypeReference<BusAPItResponse<BusStationItem>>() {});
        	//System.out.println("result:"+result.getMsgBody().getItemList());
        	//List<StationItem>
        	mv.addObject("list", result.getMsgBody().getItemList());
        	
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getPlacesSearch(String search, ModelAndView mv) {
		// TODO Auto-generated method stub
		
	}

	//한 정류소의 특정노선의 도착예정정보
	@Override
	public List<BusArriveItem> arriveInfo(ArriveRequestDTO dto) {
		String serviceUrl="http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRoute";
		StringBuilder urlBuilder = new StringBuilder(serviceUrl); /*URL*/
        
        try {
        	urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("stId","UTF-8") + "=" + URLEncoder.encode(dto.getStId(), "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("busRouteId","UTF-8") + "=" + URLEncoder.encode(dto.getBusRouteId(), "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("ord","UTF-8") + "=" + URLEncoder.encode(dto.getOrd(), "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));/*[xml,json]*/
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        
        String responseStr=OpenApiUtil.request(urlBuilder.toString(), null, "GET");
        //System.out.println("---->"+responseStr);
        
        ObjectMapper om=new ObjectMapper();
        BusAPItResponse<BusArriveItem> result=null;
        try {
        	result= om.readValue(responseStr, new TypeReference<BusAPItResponse<BusArriveItem>>() {});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        System.out.println("---->"+result);
        
        List<BusArriveItem> list=result.getMsgBody().getItemList();
        
		return list;
	}

	@Override
	public List<BusStationOfPosItem> getBusStation(BusBotStationDTO dto) {
		String serviceUrl="http://ws.bus.go.kr/api/rest/stationinfo/getStationByPos";
		StringBuilder urlBuilder = new StringBuilder(serviceUrl); /*URL*/
        
        try {
        	urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
        	urlBuilder.append("&" + URLEncoder.encode("tmX","UTF-8") + "=" + URLEncoder.encode(dto.getTmX(), "UTF-8")); /*기준위치 X*/
            urlBuilder.append("&" + URLEncoder.encode("tmY","UTF-8") + "=" + URLEncoder.encode(dto.getTmY(), "UTF-8")); /*기준위치 Y*/
            urlBuilder.append("&" + URLEncoder.encode("radius","UTF-8") + "=" + URLEncoder.encode(dto.getRadius(), "UTF-8")); /*단위 m(미터)*/
			urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));/*[xml,json]*/
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        
        Map<String, String> requestHeaders=new HashMap<>();
        requestHeaders.put("Content-type", "application/json");
        
        String responseStr=OpenApiUtil.request(urlBuilder.toString(), requestHeaders, "GET");
        System.out.println("<<<>>>:"+responseStr);
        
        ObjectMapper om=new ObjectMapper();
        BusAPItResponse<BusStationOfPosItem> result=null;
        try {
        	result=om.readValue(responseStr, new TypeReference<BusAPItResponse<BusStationOfPosItem>>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        
		return result.getMsgBody().getItemList();
	}

	@Override
	public List<SeoulBusOfArrivalItem> busOfArrival(String arsId) {
		StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid"); /*URL*/
        
        try {
        	urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
        	urlBuilder.append("&" + URLEncoder.encode("arsId","UTF-8") + "=" + URLEncoder.encode(arsId, "UTF-8")); /*정류소 번호*/
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
        BusAPItResponse<SeoulBusOfArrivalItem> result=null;
        try {
        	
			result=objectMapper.readValue(responseData, new TypeReference<BusAPItResponse<SeoulBusOfArrivalItem>>() {});
        	//System.out.println("result:"+result.getMsgBody().getItemList());
        	//List<StationItem>
        	
        	
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result.getMsgBody().getItemList();
	}

}

