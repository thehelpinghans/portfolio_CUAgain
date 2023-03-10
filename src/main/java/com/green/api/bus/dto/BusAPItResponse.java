package com.green.api.bus.dto;

import com.nowon.green.bus.dto.ComMsgHeader;
import com.nowon.green.bus.dto.MsgBody;
import com.nowon.green.bus.dto.MsgHeader;

import lombok.Data;

@Data
public class BusAPItResponse<T> {
	private ComMsgHeader comMsgHeader;
	private MsgHeader msgHeader;
	private MsgBody<T> msgBody;
}